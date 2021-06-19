package de.zillolp.cookieclicker.runnables;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.config.tools.HologramTools;
import de.zillolp.cookieclicker.config.tools.LocationTools;
import de.zillolp.cookieclicker.config.tools.StatswallTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.HologramsUtil;

public class TimeRanking implements Runnable {
	private Main plugin = Main.plugin;
	private HologramsUtil hologramsutil = plugin.hologramsutil;
	private HashMap<String, Long> gekauft = plugin.gekauft;
	public ArmorStand timer;
	private Thread thread;
	private boolean running;
	private int resettime;
	private int seconds;
	private int Days;
	private int Hours;
	private int Minutes;
	private int Seconds;

	public TimeRanking() {
		ConfigTools configtools = plugin.configtools;
		StatswallTools statswalltools = plugin.statswalltools;
		this.resettime = statswalltools.getResettime();
		this.running = false;
		this.thread = new Thread(this);
		this.seconds = resettime * 3600;
		this.Days = (seconds / 3600) / 24;
		this.Hours = (this.seconds % 86400) / 3600;
		this.Minutes = ((this.seconds % 86400) % 3600) / 60;
		this.Seconds = ((this.seconds % 86400) % 3600) % 60;

		createResettimer();
		if (configtools.getTimeranking()) {
			start();
		}
	}

	public void start() {
		this.running = true;
		if (running) {
			this.thread.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		this.running = false;
		this.thread.stop();
		removeTimer();
	}

	@Override
	public void run() {
		while (running) {
			if (seconds == 0) {
				plugin.hour.clear();
				gekauft.clear();
				for (Player all : Bukkit.getOnlinePlayers()) {
					gekauft.put(all.getUniqueId().toString(), 0L);
				}
				seconds = resettime * 3600;
			}
			seconds--;
			Days = (seconds / 3600) / 24;
			Hours = (seconds % 86400) / 3600;
			Minutes = ((seconds % 86400) % 3600) / 60;
			Seconds = ((seconds % 86400) % 3600) % 60;

			if (timer != null) {
				timer.setCustomName(getHoloname(getFormat(Days, Hours, Minutes, Seconds)));
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void createResettimer() {
		ConfigTools configtools = plugin.configtools;
		HologramTools hologramstools = plugin.hologramstools;
		LocationTools locationtools = new LocationTools("Resettimer");
		if (locationtools.loadLocation() != null) {
			removeTimer();
			if (configtools.getTimeranking() && hologramstools.getResettimer()) {
				Location loc = locationtools.loadLocation().getBlock().getLocation();
				loc = loc.add(0.5, 0.7, 0.5);
				ArmorStand armor = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
				armor.setVisible(false);
				armor.setCustomNameVisible(true);
				armor.setCustomName(getHoloname(getFormat(Days, Hours, Minutes, Seconds)));
				armor.setGravity(false);
				armor.setBasePlate(false);
				armor.setSmall(true);
				armor.setMarker(true);
				timer = armor;
			}
		} else {
			stop();
		}
	}

	private String getHoloname(String time) {
		HologramTools hologramstools = plugin.hologramstools;
		return hologramstools.getResettimerhologram(time);
	}

	private void removeTimer() {
		LocationTools locationtools = new LocationTools("Resettimer");
		if (locationtools.loadLocation() != null) {
			Location loc = locationtools.loadLocation().getBlock().getLocation();
			loc = loc.add(0.5, 0.7, 0.5);
			hologramsutil.removeHologram(loc);
		}
	}

	private String getFormat(int days, int hours, int minutes, int seconds) {
		if (resettime > 24) {
			return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
		} else {
			return String.format("%02d:%02d:%02d", hours, minutes, seconds);
		}
	}
}
