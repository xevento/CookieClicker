package de.zillolp.cookieclicker.config.tools;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import de.zillolp.cookieclicker.utils.ConfigUtil;

public class LocationTools {
	private ConfigUtil configutil;
	private String root;
	private Location loc;

	public LocationTools(String root) {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/locations.yml"));
		this.root = root;
	}

	public LocationTools(String root, Location loc) {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/locations.yml"));
		this.root = root;
		this.loc = loc;
	}

	public void saveLocation() {
		configutil.setString(root + ".World", loc.getWorld().getName());
		configutil.setDouble(root + ".X", loc.getX());
		configutil.setDouble(root + ".Y", loc.getY());
		configutil.setDouble(root + ".Z", loc.getZ());
		configutil.setDouble(root + ".Yaw", loc.getYaw());
		configutil.setDouble(root + ".Pitch", loc.getPitch());
	}

	public Location loadLocation() {
		if (configutil.getString(root + ".World") != null
				&& Bukkit.getWorlds().contains(Bukkit.getWorld(configutil.getString(root + ".World")))) {
			World world = Bukkit.getWorld(configutil.getString(root + ".World"));
			double x = configutil.getDouble(root + ".X"), y = configutil.getDouble(root + ".Y"),
					z = configutil.getDouble(root + ".Z");
			float yaw = (float) configutil.getDouble(root + ".Yaw"),
					pitch = (float) configutil.getDouble(root + ".Pitch");
			return new Location(world, x, y, z, yaw, pitch);
		} else {
			return null;
		}
	}

	public void resetLocation() {
		for (String key : configutil.getConfig().getConfigurationSection(root).getKeys(false)) {
			if (configutil.getString(root + "." + key) != null) {
				configutil.setString(root + "." + key, null);
			} else {
				continue;
			}
		}
	}
}
