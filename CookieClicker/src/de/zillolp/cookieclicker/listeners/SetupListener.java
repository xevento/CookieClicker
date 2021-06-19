package de.zillolp.cookieclicker.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.zillolp.cookieclicker.config.tools.LocationTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.utils.HologramsUtil;

public class SetupListener implements Listener {
	private Main plugin = Main.plugin;

	@EventHandler
	public void on(PlayerInteractEvent e) {
		Block block = e.getClickedBlock();
		if (block != null && block.getType() != Material.AIR) {
			String PREFIX = plugin.languagetools.getPREFIX();
			boolean english = plugin.configtools.getEnglish();
			Player p = e.getPlayer();
			PlayerProfil profil = plugin.playerprofiles.get(p);
			if (profil != null) {
				int nummer = profil.getClickerNummer();
				int nummer_1 = profil.getAlltimeNummer();
				int nummer_2 = profil.getTimeNummer();
				if (nummer > 0) {
					if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						LocationTools locationtools = new LocationTools("CookieClicker." + nummer, block.getLocation());
						locationtools.saveLocation();
						plugin.hologramsutil = new HologramsUtil();
						if (english) {
							p.sendMessage(PREFIX + "§7You have set the §6" + nummer + " §7cookieclicker.");
						} else {
							p.sendMessage(PREFIX + "§7Du hast den §6" + nummer + " §7CookieClicker gesetzt.");
						}
					} else {
						if (english) {
							p.sendMessage(PREFIX + "§cThe process was canceled!");
						} else {
							p.sendMessage(PREFIX + "§cDer Vorgang wurde abgebrochen!");
						}
					}
					e.setCancelled(true);
					profil.setClickerNummer(0);
				} else if (nummer_1 > 0) {
					if (e.getAction() == Action.RIGHT_CLICK_BLOCK && block.getState() instanceof Skull) {
						LocationTools locationtools = new LocationTools("Alltime." + nummer_1, block.getLocation());
						locationtools.saveLocation();
						if (english) {
							p.sendMessage(PREFIX + "§7You have set the §6" + nummer_1 + " §7alltime statshead.");
						} else {
							p.sendMessage(PREFIX + "§7Du hast den §6" + nummer_1 + " §7Alltime Statskopf gesetzt.");
						}
					} else {
						if (english) {
							p.sendMessage(PREFIX + "§cThe process was canceled!");
						} else {
							p.sendMessage(PREFIX + "§cDer Vorgang wurde abgebrochen!");
						}
					}
					e.setCancelled(true);
					profil.setAlltimeNummer(0);
				} else if (nummer_2 > 0) {
					if (e.getAction() == Action.RIGHT_CLICK_BLOCK && block.getState() instanceof Skull) {
						LocationTools locationtools = new LocationTools("Time." + nummer_2, block.getLocation());
						locationtools.saveLocation();
						if (english) {
							p.sendMessage(PREFIX + "§7You have set the §6" + nummer_2 + " §7time statshead.");
						} else {
							p.sendMessage(PREFIX + "§7Du hast den §6" + nummer_2 + " §7Time Statskopf gesetzt.");
						}
					} else {
						if (english) {
							p.sendMessage(PREFIX + "§cThe process was canceled!");
						} else {
							p.sendMessage(PREFIX + "§cDer Vorgang wurde abgebrochen!");
						}
					}
					e.setCancelled(true);
					profil.setTimeNummer(0);
				}
			}
		}
	}

}
