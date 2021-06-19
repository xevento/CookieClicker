package de.zillolp.cookieclicker.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.utils.DatenManager;

public class PlayerConnectionListener implements Listener {
	private Main plugin = Main.plugin;
	private DatenManager datenmanager = plugin.datenmanager;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;

	@EventHandler
	public void on(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		datenmanager.createPlayer(p);
		Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {

			@Override
			public void run() {
				if (!(profiles.containsKey(p))) {
					profiles.put(p, new PlayerProfil(p));
				}
			}
		}, 3);
	}

	@EventHandler
	public void on(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (profiles.containsKey(p)) {
			profiles.get(p).UploadStats();
			profiles.remove(p);
		}
	}
}
