package de.zillolp.cookieclicker.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import de.zillolp.cookieclicker.config.tools.HologramTools;
import de.zillolp.cookieclicker.config.tools.LocationTools;
import de.zillolp.cookieclicker.main.Main;

public class HologramsUtil {
	private Main plugin = Main.plugin;
	private static ArrayList<ArmorStand> holos = new ArrayList<ArmorStand>();
	private ArrayList<Location> clickerlist = plugin.clickerlist;

	public HologramsUtil() {
		Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {

			@Override
			public void run() {
				clickerlist.clear();
				removeHolograms();
				createClickerHolograms();
			}
		}, 10);
	}

	public void createClickerHolograms() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/locations.yml"));
		HologramTools hologramstools = plugin.hologramstools;
		ConfigurationSection section = configutil.getConfig().getConfigurationSection("CookieClicker");
		if (section != null && section.getKeys(false).size() > 0) {
			for (String current : section.getKeys(false)) {
				LocationTools locationtools = new LocationTools("CookieClicker." + current);
				Location loc = locationtools.loadLocation();
				if (loc != null) {
					clickerlist.add(loc.getBlock().getLocation());
					removeHologram(loc);
					if (hologramstools.getClickerHolograms()) {
						World w = loc.getWorld();
						EntityType type = EntityType.ARMOR_STAND;
						loc = loc.add(0.5, 1, 0.5);
						setHoloSettings((ArmorStand) w.spawnEntity(loc, type), hologramstools.getClickerhologram3());
						loc = loc.add(0, 0.25, 0);
						setHoloSettings((ArmorStand) w.spawnEntity(loc, type), hologramstools.getClickerhologram2());
						loc = loc.add(0, 0.7, 0);
						setHoloSettings((ArmorStand) w.spawnEntity(loc, type), hologramstools.getClickerhologram1());
					}
				}
			}
		}
	}

	public void removeHologram(Location loc) {
		List<Entity> nearbyEntites = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 1, 2, 1);

		for (Entity ent : nearbyEntites) {
			if (ent instanceof ArmorStand) {
				ArmorStand as = (ArmorStand) ent;
				as.remove();
			}
		}
	}

	public void removeHolograms() {
		for (ArmorStand armor : holos) {
			armor.remove();
		}
	}

	private void setHoloSettings(ArmorStand armor, String customname) {
		armor.setVisible(false);
		armor.setCustomNameVisible(true);
		armor.setCustomName(customname);
		armor.setGravity(false);
		armor.setBasePlate(false);
		armor.setSmall(true);
		armor.setMarker(true);
		holos.add(armor);
	}
}
