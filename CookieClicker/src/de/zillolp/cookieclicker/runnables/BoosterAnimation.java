package de.zillolp.cookieclicker.runnables;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.utils.ItemCreator;
import de.zillolp.cookieclicker.xclasses.XMaterial;

public class BoosterAnimation extends InventorySetter implements Runnable {
	private Main plugin = Main.plugin;
	private ItemCreator itemcreator = plugin.itemcreator;
	private Player p;
	private BoosterProfil profil;
	private Inventory inv;
	private String booster;
	private ArrayList<ItemStack> itemstacks;
	private int state;
	private int sched;

	@SuppressWarnings("deprecation")
	public BoosterAnimation(Player p, BoosterProfil profil, Inventory inv, String booster) {
		this.p = p;
		this.profil = profil;
		this.inv = inv;
		this.booster = booster;
		this.itemstacks = new ArrayList<>();
		this.state = 9;
		this.sched = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, this, 0, 10);
	}

	@Override
	public void run() {
		LanguageTools languagetools = plugin.languagetools;
		String[] names = new String[] { languagetools.getANIMATION_CAULDRON(),
				languagetools.getANIMATION_EMPTY_POTION(), languagetools.getANIMATION_EMPTY_POTION_1(),
				languagetools.getANIMATION_EMPTY_POTION_2(), languagetools.getANIMATION_FILLED_POTION(),
				languagetools.getANIMATION_FILLED_POTION_1(), languagetools.getANIMATION_FILLED_POTION_2() };
		ItemStack air = itemcreator.createItem(XMaterial.AIR.parseItem());
		ItemStack kessel = itemcreator.createItem(XMaterial.CAULDRON.parseItem(), names[0]);
		ItemStack bottle;
		ItemStack potion;

		switch (state) {
		case 9:
			for (int i = 19; i <= 25; i++) {
				itemstacks.add(inv.getItem(i));
				inv.setItem(i, air);
			}
			break;
		case 8:
			inv.setItem(22, kessel);
			break;
		case 7:
			bottle = itemcreator.createItem(XMaterial.GLASS_BOTTLE.parseItem(), names[1]);
			inv.setItem(19, bottle);
			inv.setItem(25, bottle);
			break;
		case 6:
			bottle = itemcreator.createItem(XMaterial.GLASS_BOTTLE.parseItem(), names[2]);
			inv.setItem(19, bottle);
			inv.setItem(25, bottle);
			inv.setItem(20, bottle);
			inv.setItem(24, bottle);
			break;
		case 5:
			bottle = itemcreator.createItem(XMaterial.GLASS_BOTTLE.parseItem(), names[3]);
			inv.setItem(19, bottle);
			inv.setItem(25, bottle);
			inv.setItem(20, bottle);
			inv.setItem(24, bottle);
			inv.setItem(21, bottle);
			inv.setItem(23, bottle);
			break;
		case 4:
			potion = itemcreator.createItem(XMaterial.POTION.parseItem(), names[4], ItemFlag.HIDE_POTION_EFFECTS);
			bottle = itemcreator.createItem(XMaterial.GLASS_BOTTLE.parseItem(), names[1]);
			inv.setItem(19, potion);
			inv.setItem(25, potion);
			inv.setItem(20, bottle);
			inv.setItem(24, bottle);
			inv.setItem(21, bottle);
			inv.setItem(23, bottle);
			break;
		case 3:
			potion = itemcreator.createItem(XMaterial.POTION.parseItem(), names[5], ItemFlag.HIDE_POTION_EFFECTS);
			bottle = itemcreator.createItem(XMaterial.GLASS_BOTTLE.parseItem(), names[2]);
			inv.setItem(19, potion);
			inv.setItem(25, potion);
			inv.setItem(20, potion);
			inv.setItem(24, potion);
			inv.setItem(21, bottle);
			inv.setItem(23, bottle);
			break;
		case 2:
			potion = itemcreator.createItem(XMaterial.POTION.parseItem(), names[6], ItemFlag.HIDE_POTION_EFFECTS);
			inv.setItem(19, potion);
			inv.setItem(25, potion);
			inv.setItem(20, potion);
			inv.setItem(24, potion);
			inv.setItem(21, potion);
			inv.setItem(23, potion);
			break;
		case 1:
			setBoosterinv(p, inv);
			inv.setItem(19, itemstacks.get(0));
			inv.setItem(21, itemstacks.get(2));
			inv.setItem(23, itemstacks.get(4));
			inv.setItem(25, itemstacks.get(6));
			profil.stopAnimation();
			break;
		}
		state--;
	}

	public void stop() {
		LanguageTools languagetools = plugin.languagetools;
		p.sendMessage(languagetools.getBOOSTER_STARTED_MESSAGE(booster));
		Bukkit.getScheduler().cancelTask(sched);
	}
}
