package de.zillolp.cookieclicker.listeners.protection;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.PlayerProfil;

public class ClickLimitListener implements Listener {
	private Main plugin = Main.plugin;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;

	public ClickLimitListener() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				ConfigTools configtools = plugin.configtools;
				for (Entry<Player, PlayerProfil> player_profil : profiles.entrySet()) {
					PlayerProfil profil = player_profil.getValue();
					int cps = profil.getCPS();
					if (cps >= configtools.getMaxcps()) {
						profil.setOver_CPS(true);
						profil.removeCPS(10);
					} else {
						profil.setOver_CPS(false);
						profil.removeCPS(15);
					}
				}
			}
		}, 0, 20);
	}

	@EventHandler
	public void on(PlayerInteractEvent e) {
		ConfigTools configtools = plugin.configtools;
		Player p = e.getPlayer();
		PlayerProfil profil = profiles.get(p);
		Block block = e.getClickedBlock();
		if (block != null && plugin.clickerlist.contains(block.getLocation())) {
			if (e.getAction() == Action.LEFT_CLICK_BLOCK && configtools.getMaxcps() > 0) {
				if (profil != null) {
					profil.addCPS(1);
				}
			}
		}
	}
}
