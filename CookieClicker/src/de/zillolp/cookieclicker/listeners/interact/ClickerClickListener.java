package de.zillolp.cookieclicker.listeners.interact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.zillolp.cookieclicker.config.tools.BoosterTools;
import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.xclasses.ActionBar;
import de.zillolp.cookieclicker.xclasses.Titles;
import de.zillolp.cookieclicker.xclasses.XSound;

public class ClickerClickListener extends InventorySetter implements Listener {
	private Main plugin = Main.plugin;
	private ArrayList<Location> clickerlist = plugin.clickerlist;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<Player, BoosterProfil> boosters = plugin.boosterprofiles;

	@EventHandler
	public void onAnimation(PlayerAnimationEvent e) {
		Block block = e.getPlayer().getTargetBlock((Set<Material>) null, 5);
		if(e.getAnimationType() == PlayerAnimationType.ARM_SWING && block.getType() != Material.AIR && e.getPlayer().getGameMode() == GameMode.ADVENTURE) {
			if (block.getType() != Material.AIR && clickerlist.contains(block.getLocation())) {
				LanguageTools languagetools = plugin.languagetools;
				boolean sounds = plugin.configtools.getSounds();
				Player p = e.getPlayer();
				PlayerProfil profil = profiles.get(p);
				BoosterProfil booster = boosters.get(p);
				if (booster.IsBooster()) {
					sendClickMessage(p, false, profil.getProclick());
				} else {
					sendClickMessage(p, true, profil.getProclick() * 2);
				}
			}
		}
	}
	@EventHandler
	public void on(PlayerInteractEvent e) {
		Block block = e.getClickedBlock();
		if (block != null && block.getType() != Material.AIR && clickerlist.contains(block.getLocation())) {
			LanguageTools languagetools = plugin.languagetools;
			boolean sounds = plugin.configtools.getSounds();
			e.setCancelled(true);
			Player p = e.getPlayer();
			Action action = e.getAction();
			if (action == Action.LEFT_CLICK_BLOCK) {
				PlayerProfil profil = profiles.get(p);
				BoosterProfil booster = boosters.get(p);
				if (booster.IsBooster()) {
					sendClickMessage(p, false, profil.getProclick());
				} else {
					sendClickMessage(p, true, profil.getProclick() * 2);
				}
			} else if (action == Action.RIGHT_CLICK_BLOCK) {
				InventoryProfil invprofil = plugin.invprofiles.get(p);
				Inventory inv = invprofil.getHomeinv();
				if (inv == null) {
					inv = invprofil.setHomeinv(Bukkit.createInventory(null, 5 * 9, languagetools.getHOME_TITLE()));
				}
				setDesign(p, inv);
				setHomeinv(p, inv);
				p.openInventory(inv);
				if (sounds) {
					p.playSound(p.getLocation(), XSound.BLOCK_CHEST_OPEN.parseSound(), 10, 10);
				}
			}
		}
	}

	@EventHandler
	public void on(BlockBreakEvent e) {
		Block block = e.getBlock();
		if (block != null && block.getType() != Material.AIR && clickerlist.contains(block.getLocation())) {
			e.setCancelled(true);
		}
	}

	private void sendClickMessage(Player p, boolean boosted, Long proklick) {
		ConfigTools configtools = plugin.configtools;
		LanguageTools languagetools = plugin.languagetools;
		BoosterTools boostertools = plugin.boostertools;
		boolean sounds = plugin.configtools.getSounds();
		PlayerProfil profil = profiles.get(p);
		BoosterProfil booster = boosters.get(p);
		Long cookies = profil.getCookies();
		String click_message = languagetools.getCLICK_MESSAGE(cookies, proklick);
		XSound click_sound = XSound.ENTITY_ITEM_PICKUP;

		if (profil.getOver_CPS()) {
			click_message = languagetools.getMAX_CPS();
			click_sound = XSound.BLOCK_ANVIL_BREAK;
		} else if (profil.getLastMove() > 0 && configtools.getAfkcooldown() > 0) {
			if (profil.getLastMove() + configtools.getAfkcooldown() <= System.currentTimeMillis()) {
				click_message = languagetools.getAFK();
				click_sound = XSound.BLOCK_ANVIL_BREAK;
			} else {
				profil.addCookies(proklick);
				profil.addClickerclicks(1L);
				cookies = profil.getCookies();
				if (boosted) {
					Long boosted_proklick = proklick / 2;
					Long boosted_cookies = cookies - boosted_proklick;
					click_message = languagetools.getBOOSTED_CLICK_MESSAGE(boosted_cookies, cookies, boosted_proklick,
							proklick);
				} else {
					click_message = languagetools.getCLICK_MESSAGE(cookies, proklick);
				}

				int random = new Random().nextInt(boostertools.getArea());
				if (random <= boostertools.getProfit()) {
					booster.addMilk(1L);
					Titles.sendTitle(p, languagetools.getMILK_ALERT_TITLE(), languagetools.getMILK_ALERT_SUBTITLE());
				}
			}
		}
		ActionBar.sendActionBar(p, click_message);
		if (sounds) {
			p.playSound(p.getLocation(), click_sound.parseSound(), 10, 10);
		}
	}
}
