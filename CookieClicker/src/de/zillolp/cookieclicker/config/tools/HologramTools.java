package de.zillolp.cookieclicker.config.tools;

import java.io.File;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.ConfigUtil;
import de.zillolp.cookieclicker.utils.StringUtil;

public class HologramTools {
	private Main plugin = Main.plugin;
	private ConfigUtil configutil;
	private StringUtil stringutil = plugin.stringutil;
	private boolean Clickerholograms;
	private boolean Resettimer;
	private String Clickerhologram1;
	private String Clickerhologram2;
	private String Clickerhologram3;
	private String Resettimerhologram;

	public HologramTools() {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/holograms.yml"));
		Clickerholograms = configutil.getBoolean("Clicker holograms");
		Resettimer = configutil.getBoolean("Resettimer");
		Clickerhologram1 = stringutil.replaceDefaults(configutil.getString("Clicker-hologram.1"));
		Clickerhologram2 = stringutil.replaceDefaults(configutil.getString("Clicker-hologram.2"));
		Clickerhologram3 = stringutil.replaceDefaults(configutil.getString("Clicker-hologram.3"));
		Resettimerhologram = stringutil.replaceDefaults(configutil.getString("Resettimer hologram"));
	}

	public boolean getClickerHolograms() {
		return Clickerholograms;
	}

	public boolean getResettimer() {
		return Resettimer;
	}

	public String getClickerhologram1() {
		return Clickerhologram1;
	}

	public String getClickerhologram2() {
		return Clickerhologram2;
	}

	public String getClickerhologram3() {
		return Clickerhologram3;
	}

	public String getResettimerhologram(String time) {
		return stringutil.replaceTime(Resettimerhologram, time);
	}
}
