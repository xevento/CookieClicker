package de.zillolp.cookieclicker.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import de.zillolp.cookieclicker.config.tools.BoosterTools;
import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.utils.StringUtil;

public class BoosterTimer extends InventorySetter implements Runnable {
	private Main plugin = Main.plugin;
	private StringUtil stringutil = plugin.stringutil;
	private int sched;

	public BoosterTimer() {
		this.sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, 20);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		LanguageTools languagetools = plugin.languagetools;
		BoosterTools boostertools = plugin.boostertools;
		Long time = System.currentTimeMillis();
		for (Player p : Bukkit.getOnlinePlayers()) {
			InventoryView view = p.getOpenInventory();
			if (view != null) {
				BoosterProfil profil = plugin.boosterprofiles.get(p);
				if (profil != null && (!(profil.InAnimation()))) {
					if (!(profil.IsBooster())) {
						if (stringutil.parseDate(profil.getBooster()) + boostertools.getLength() <= time) {
							profil.setBooster("NONE");
							p.sendMessage(languagetools.getBOOSTER_EXPIRED_MESSAGE(boostertools.getName()));
						}
					}
					if (!(profil.IsBooster_1())) {
						if (stringutil.parseDate(profil.getBooster_1()) + boostertools.getLength1() <= time) {
							profil.setBooster_1("NONE");
							p.sendMessage(languagetools.getBOOSTER_EXPIRED_MESSAGE(boostertools.getName1()));
							for (Player all : Bukkit.getOnlinePlayers()) {
								p.showPlayer(all);
							}
						} else {
							for (Player all : Bukkit.getOnlinePlayers()) {
								p.hidePlayer(all);
							}
						}
					}
					if (!(profil.IsBooster_2())) {
						if (stringutil.parseDate(profil.getBooster_2()) + boostertools.getLength2() <= time) {
							profil.setBooster_2("NONE");
							p.sendMessage(languagetools.getBOOSTER_EXPIRED_MESSAGE(boostertools.getName2()));
						}
					}
					String title = view.getTitle();
					Inventory inv = view.getTopInventory();
					if (inv != null && title != null) {
						if (title.equalsIgnoreCase(languagetools.getBOOSTER_TITLE())) {
							setBooster(p, inv);
						} else if (title.equalsIgnoreCase(languagetools.getSHOP_TITLE())) {
							setShopinv(p, inv);
						} else if (title.equalsIgnoreCase(languagetools.getPREMIUM_SHOP_TITLE())) {
							setPremiumshopinv(p, inv);
						}
					}
				}
			}
		}
	}

	public void stop() {
		if (Bukkit.getScheduler().isCurrentlyRunning(sched)) {
			Bukkit.getScheduler().cancelTask(sched);
		}
	}
}
