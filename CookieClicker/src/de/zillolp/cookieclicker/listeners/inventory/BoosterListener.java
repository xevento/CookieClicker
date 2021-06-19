package de.zillolp.cookieclicker.listeners.inventory;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.zillolp.cookieclicker.config.tools.BoosterTools;
import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.runnables.BoosterAnimation;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.utils.StringUtil;
import de.zillolp.cookieclicker.xclasses.XMaterial;
import de.zillolp.cookieclicker.xclasses.XSound;

public class BoosterListener extends InventorySetter implements Listener {
	private Main plugin = Main.plugin;
	private StringUtil stringutil = plugin.stringutil;
	private ConfigTools configtools = plugin.configtools;
	private HashMap<Player, InventoryProfil> invprofiles = plugin.invprofiles;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<Player, BoosterProfil> booster = plugin.boosterprofiles;
	private Material[] types = new Material[] { XMaterial.BARRIER.parseMaterial() };

	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory clickedinv = e.getClickedInventory();
		ItemStack currentitem = e.getCurrentItem();
		if (clickedinv != null && currentitem != null && currentitem.getType() != Material.AIR) {
			LanguageTools languagetools = plugin.languagetools;
			String[] names = new String[] { languagetools.getBOOSTER_TITLE(), languagetools.getBACK(),
					languagetools.getHOME_TITLE() };
			String viewtitle = e.getView().getTitle();
			boolean sounds = configtools.getSounds();
			if (viewtitle != null && viewtitle.equalsIgnoreCase(names[0])) {
				InventoryProfil invprofil = invprofiles.get(p);
				PlayerProfil profil = profiles.get(p);
				BoosterProfil boosterprofil = booster.get(p);
				if (invprofil != null && profil != null && boosterprofil != null) {
					e.setCancelled(true);
					String itemname = currentitem.getItemMeta().getDisplayName();
					Material itemtype = currentitem.getType();
					if (itemtype == types[0] && itemname.equalsIgnoreCase(names[1])) {
						boosterprofil.stopAnimation();
						Inventory inv = invprofil.getHomeinv();
						if (inv == null) {
							inv = invprofil.setHomeinv(Bukkit.createInventory(null, 5 * 9, names[2]));
						}
						setDesign(p, inv);
						setHomeinv(p, inv);
						p.openInventory(inv);
						if (sounds) {
							p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_SNARE.parseSound(), 10, 10);
						}
					} else if (itemtype == XMaterial.PLAYER_HEAD.parseMaterial()) {
						BoosterTools boostertools = plugin.boostertools;
						if (canBoost(boosterprofil, boostertools)) {
							String name = boostertools.getName();
							String name1 = boostertools.getName1();
							String name2 = boostertools.getName2();
							String booster = languagetools.getBOOSTER_NOT_ACTIVE(name);
							String booster1 = languagetools.getBOOSTER_NOT_ACTIVE(name1);
							String booster2 = languagetools.getBOOSTER_NOT_ACTIVE(name2);
							if (itemname.equalsIgnoreCase(booster)) {
								boosterprofil.removeMilk(boostertools.getPreis());
								startBooster(p, clickedinv, boosterprofil, 1, name);
							} else if (itemname.equalsIgnoreCase(booster1)) {
								boosterprofil.removeMilk(boostertools.getPreis1());
								startBooster(p, clickedinv, boosterprofil, 2, name1);
							} else if (itemname.equalsIgnoreCase(booster2)) {
								boosterprofil.removeMilk(boostertools.getPreis2());
								startBooster(p, clickedinv, boosterprofil, 3, name2);
							}
						} else {
							p.sendMessage(languagetools.getBOOSTER_LIMIT_MESSAGE());
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void on(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		BoosterProfil profil = booster.get(p);
		if (profil != null) {
			LanguageTools languagetools = plugin.languagetools;
			String title = e.getView().getTitle();
			if (title != null && title.equalsIgnoreCase(languagetools.getBOOSTER_TITLE())) {
				profil.stopAnimation();
			}
		}
	}

	private boolean canBoost(BoosterProfil profil, BoosterTools boostertools) {
		int booster = 0;
		if (!profil.IsBooster()) {
			booster++;
		}
		if (!profil.IsBooster_1()) {
			booster++;
		}
		if (!profil.IsBooster_2()) {
			booster++;
		}
		return booster < boostertools.getLimit();
	}

	private void startBooster(Player p, Inventory inv, BoosterProfil profil, int booster, String name) {
		switch (booster) {
		case 3:
			profil.setBooster_2(stringutil.formatDate(System.currentTimeMillis()));
			break;

		case 2:
			profil.setBooster_1(stringutil.formatDate(System.currentTimeMillis()));
			break;

		case 1:
			profil.setBooster(stringutil.formatDate(System.currentTimeMillis()));
			break;
		}
		profil.setInAnimation(true);
		profil.setAnimation(new BoosterAnimation(p, profil, inv, name));
	}
}
