package de.zillolp.cookieclicker.listeners.protection;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.PlayerProfil;

public class AFKListener implements Listener {
	private Main plugin = Main.plugin;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;

	@EventHandler
	public void on(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		for (Location clicker_loc : plugin.clickerlist) {
			if (loc.getWorld().getName().equalsIgnoreCase(clicker_loc.getWorld().getName())) {
				if (loc.distance(clicker_loc) <= 6) {
					int x_from = e.getFrom().getBlockX();
					int z_from = e.getFrom().getBlockZ();
					int x_to = e.getTo().getBlockX();
					int z_to = e.getTo().getBlockZ();
					if (x_from + 1 == x_to || x_from - 1 == x_to || z_from + 1 == z_to || z_from - 1 == z_to) {
						profiles.get(p).setLastMove(System.currentTimeMillis());
					}
				}
			}
		}
	}

	@EventHandler
	public void on(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		LanguageTools languagetools = plugin.languagetools;
		String[] titles = new String[] { languagetools.getHOME_TITLE(), languagetools.getSHOP_TITLE(),
				languagetools.getPREMIUM_SHOP_TITLE(), languagetools.getDESIGNS_TITLE() };
		for (String title : titles) {
			if (e.getView().getTitle().equalsIgnoreCase(title)) {
				profiles.get(p).setLastMove(System.currentTimeMillis());
			}
		}
	}

}
