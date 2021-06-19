package de.zillolp.cookieclicker.xclasses;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class Titles {
	private static final boolean SUPPORTED_API = Material.getMaterial("OBSERVER") != null;

	private static final Object TIMES;
	private static final Object TITLE;
	private static final Object SUBTITLE;
	private static final Object CLEAR;

	private static final MethodHandle PACKET;
	private static final MethodHandle CHAT_COMPONENT_TEXT;

	static {
		MethodHandle packetCtor = null;
		MethodHandle chatComp = null;

		Object times = null;
		Object title = null;
		Object subtitle = null;
		Object clear = null;

		if (!SUPPORTED_API) {
			Class<?> chatComponentText = ReflectionUtils.getNMSClass("ChatComponentText");
			Class<?> packet = ReflectionUtils.getNMSClass("PacketPlayOutTitle");
			Class<?> titleTypes = packet.getDeclaredClasses()[0];

			for (Object type : titleTypes.getEnumConstants()) {
				switch (type.toString()) {
				case "TIMES":
					times = type;
					break;
				case "TITLE":
					title = type;
					break;
				case "SUBTITLE":
					subtitle = type;
					break;
				case "CLEAR":
					clear = type;
				}
			}

			MethodHandles.Lookup lookup = MethodHandles.lookup();
			try {
				chatComp = lookup.findConstructor(chatComponentText, MethodType.methodType(void.class, String.class));

				packetCtor = lookup.findConstructor(packet, MethodType.methodType(void.class, titleTypes,
						ReflectionUtils.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class));
			} catch (NoSuchMethodException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		TITLE = title;
		SUBTITLE = subtitle;
		TIMES = times;
		CLEAR = clear;

		PACKET = packetCtor;
		CHAT_COMPONENT_TEXT = chatComp;
	}

	private Titles() {
	}

	public static void sendTitle(@Nonnull Player player, int fadeIn, int stay, int fadeOut, @Nullable String title,
			@Nullable String subtitle) {
		Objects.requireNonNull(player, "Cannot send title to null player");
		if (title == null && subtitle == null)
			return;
		if (SUPPORTED_API) {
			player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
			return;
		}

		try {
			Object timesPacket = PACKET.invoke(TIMES, CHAT_COMPONENT_TEXT.invoke(title), fadeIn, stay, fadeOut);
			ReflectionUtils.sendPacket(player, timesPacket);

			if (title != null) {
				Object titlePacket = PACKET.invoke(TITLE, CHAT_COMPONENT_TEXT.invoke(title), fadeIn, stay, fadeOut);
				ReflectionUtils.sendPacket(player, titlePacket);
			}
			if (subtitle != null) {
				Object subtitlePacket = PACKET.invoke(SUBTITLE, CHAT_COMPONENT_TEXT.invoke(subtitle), fadeIn, stay,
						fadeOut);
				ReflectionUtils.sendPacket(player, subtitlePacket);
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void sendTitle(@Nonnull Player player, @Nonnull String title, @Nonnull String subtitle) {
		sendTitle(player, 10, 20, 10, title, subtitle);
	}

	public static void sendTitle(@Nonnull Player player, @Nonnull ConfigurationSection config) {
		String title = config.getString("title");
		String subtitle = config.getString("subtitle");

		int fadeIn = config.getInt("fade-in");
		int stay = config.getInt("stay");
		int fadeOut = config.getInt("fade-out");

		if (fadeIn < 1)
			fadeIn = 10;
		if (stay < 1)
			stay = 20;
		if (fadeOut < 1)
			fadeOut = 10;

		sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
	}

	public static void clearTitle(@Nonnull Player player) {
		Objects.requireNonNull(player, "Cannot clear title from null player");
		if (SUPPORTED_API) {
			player.resetTitle();
			return;
		}

		Object clearPacket;
		try {
			clearPacket = PACKET.invoke(CLEAR, null, -1, -1, -1);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			return;
		}

		ReflectionUtils.sendPacket(player, clearPacket);
	}

	public static void sendTabList(@Nonnull Player player, @Nullable String header, @Nullable String footer) {
		Objects.requireNonNull(player, "Cannot update tab for null player");
		header = Strings.isNullOrEmpty(header) ? ""
				: StringUtils.replace(ChatColor.translateAlternateColorCodes('&', header), "%player%",
						player.getDisplayName());
		footer = Strings.isNullOrEmpty(footer) ? ""
				: StringUtils.replace(ChatColor.translateAlternateColorCodes('&', footer), "%player%",
						player.getDisplayName());

		try {
			Method chatComponentBuilderMethod = ReflectionUtils.getNMSClass("IChatBaseComponent")
					.getDeclaredClasses()[0].getMethod("a", String.class);
			Object tabHeader = chatComponentBuilderMethod.invoke(null, "{\"text\":\"" + header + "\"}");
			Object tabFooter = chatComponentBuilderMethod.invoke(null, "{\"text\":\"" + footer + "\"}");
			Object packet = ReflectionUtils.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor()
					.newInstance();

			Field aField;
			Field bField;
			try {
				aField = packet.getClass().getDeclaredField("a");
				bField = packet.getClass().getDeclaredField("b");
			} catch (Exception ex) {
				aField = packet.getClass().getDeclaredField("header");
				bField = packet.getClass().getDeclaredField("footer");
			}

			aField.setAccessible(true);
			aField.set(packet, tabHeader);

			bField.setAccessible(true);
			bField.set(packet, tabFooter);

			ReflectionUtils.sendPacket(player, packet);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
