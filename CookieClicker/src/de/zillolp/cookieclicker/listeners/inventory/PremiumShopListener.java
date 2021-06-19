package de.zillolp.cookieclicker.listeners.inventory;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.profiles.ShopProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.xclasses.XMaterial;
import de.zillolp.cookieclicker.xclasses.XSound;

public class PremiumShopListener extends InventorySetter implements Listener {
	private Main plugin = Main.plugin;
	private ConfigTools configtools = plugin.configtools;
	private HashMap<Player, InventoryProfil> invprofiles = plugin.invprofiles;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<Player, ShopProfil> shopprofiles = plugin.shopprofiles;
	private HashMap<Player, BoosterProfil> boosterprofiles = plugin.boosterprofiles;
	private Material[] types = new Material[] { XMaterial.BARRIER.parseMaterial() };
	private Material[] shoptypes = new Material[] { XMaterial.BLAZE_ROD.parseMaterial(),
			XMaterial.IRON_INGOT.parseMaterial(), XMaterial.MUSIC_DISC_WARD.parseMaterial(),
			XMaterial.FIREWORK_STAR.parseMaterial(), XMaterial.ENDER_EYE.parseMaterial(),
			XMaterial.BLAZE_POWDER.parseMaterial(), XMaterial.NETHER_STAR.parseMaterial() };

	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory clickedinv = e.getClickedInventory();
		ItemStack currentitem = e.getCurrentItem();
		if (clickedinv != null && currentitem != null && currentitem.getType() != Material.AIR) {
			LanguageTools languagetools = plugin.languagetools;
			String viewtitle = e.getView().getTitle();
			if (viewtitle != null && viewtitle.equalsIgnoreCase(languagetools.getPREMIUM_SHOP_TITLE())) {
				boolean sounds = configtools.getSounds();
				InventoryProfil invprofil = invprofiles.get(p);
				PlayerProfil profil = profiles.get(p);
				ShopProfil shopprofil = shopprofiles.get(p);
				BoosterProfil boosterprofil = boosterprofiles.get(p);
				if (invprofil != null && profil != null && shopprofil != null) {
					e.setCancelled(true);
					String itemname = currentitem.getItemMeta().getDisplayName();
					Material itemtype = currentitem.getType();
					if (itemtype == types[0] && itemname.equalsIgnoreCase(languagetools.getBACK())) {
						Inventory inv = invprofil.getShopinv();
						if (inv == null) {
							inv = invprofil
									.setShopinv(Bukkit.createInventory(null, 4 * 9, languagetools.getSHOP_TITLE()));
						}
						setDesign(p, inv);
						setShopinv(p, inv);
						p.openInventory(inv);
						if (sounds) {
							p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_SNARE.parseSound(), 10, 10);
						}
					} else if (!(e.isShiftClick())) {
						Inventory topinv = p.getOpenInventory().getTopInventory();
						if (Arrays.asList(shoptypes).contains(itemtype) && topinv == clickedinv) {
							Long[] preise = new Long[] { shopprofil.getPreis(7), shopprofil.getPreis(8),
									shopprofil.getPreis(9), shopprofil.getPreis(10), shopprofil.getPreis(11),
									shopprofil.getPreis(12), shopprofil.getPreis(13) };
							String[] buyable = new String[] { languagetools.getPRICE_BUYABLE(preise[0]),
									languagetools.getPRICE_BUYABLE(preise[1]),
									languagetools.getPRICE_BUYABLE(preise[2]),
									languagetools.getPRICE_BUYABLE(preise[3]),
									languagetools.getPRICE_BUYABLE(preise[4]),
									languagetools.getPRICE_BUYABLE(preise[5]),
									languagetools.getPRICE_BUYABLE(preise[6]) };
							String[] not_buyable = new String[] { languagetools.getPRICE_NOT_BUYABLE(preise[0]),
									languagetools.getPRICE_NOT_BUYABLE(preise[1]),
									languagetools.getPRICE_NOT_BUYABLE(preise[2]),
									languagetools.getPRICE_NOT_BUYABLE(preise[3]),
									languagetools.getPRICE_NOT_BUYABLE(preise[4]),
									languagetools.getPRICE_NOT_BUYABLE(preise[5]),
									languagetools.getPRICE_NOT_BUYABLE(preise[6]) };
							if (!boosterprofil.IsBooster_2()) {
								buyable = new String[] {
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[0] * 2, preise[0]),
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[1] * 2, preise[1]),
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[2] * 2, preise[2]),
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[3] * 2, preise[3]),
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[4] * 2, preise[4]),
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[5] * 2, preise[5]),
										languagetools.getPRICE_BOOSTED_BUYABLE(preise[6] * 2, preise[6]) };
								not_buyable = new String[] {
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[0] * 2, preise[0]),
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[1] * 2, preise[1]),
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[2] * 2, preise[2]),
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[3] * 2, preise[3]),
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[4] * 2, preise[4]),
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[5] * 2, preise[5]),
										languagetools.getPRICE_BOOSTED_NOT_BUYABLE(preise[6] * 2, preise[6]) };
							}
							if (itemtype == shoptypes[0]) {
								if (itemname.equalsIgnoreCase(buyable[0])) {
									buyPerclick(p, profil, shopprofil, 16L, preise[0]);
									shopprofil.addPreis(2240L, 7);
								} else if (itemname.equalsIgnoreCase(not_buyable[0])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[1]) {
								if (itemname.equalsIgnoreCase(buyable[1])) {
									buyPerclick(p, profil, shopprofil, 18L, preise[1]);
									shopprofil.addPreis(2570L, 8);
								} else if (itemname.equalsIgnoreCase(not_buyable[1])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[2]) {
								if (itemname.equalsIgnoreCase(buyable[2])) {
									buyPerclick(p, profil, shopprofil, 20L, preise[2]);
									shopprofil.addPreis(2900L, 9);
								} else if (itemname.equalsIgnoreCase(not_buyable[2])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[3]) {
								if (itemname.equalsIgnoreCase(buyable[3])) {
									buyPerclick(p, profil, shopprofil, 22L, preise[3]);
									shopprofil.addPreis(3230L, 10);
								} else if (itemname.equalsIgnoreCase(not_buyable[3])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[4]) {
								if (itemname.equalsIgnoreCase(buyable[4])) {
									buyPerclick(p, profil, shopprofil, 24L, preise[4]);
									shopprofil.addPreis(3560L, 11);
								} else if (itemname.equalsIgnoreCase(not_buyable[4])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[5]) {
								if (itemname.equalsIgnoreCase(buyable[5])) {
									buyPerclick(p, profil, shopprofil, 26L, preise[5]);
									shopprofil.addPreis(3890L, 12);
								} else if (itemname.equalsIgnoreCase(not_buyable[5])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[6]) {
								if (itemname.equalsIgnoreCase(buyable[6])) {
									buyPerclick(p, profil, shopprofil, 28L, preise[6]);
									shopprofil.addPreis(4220L, 13);
								} else if (itemname.equalsIgnoreCase(not_buyable[6])) {
									buyFail(p);
								}
							}
							setPremiumshopinv(p, clickedinv);
						}
					}
				}
			}
		}
	}

	private void buyPerclick(Player p, PlayerProfil profil, ShopProfil shopprofil, Long perclick, Long price) {
		boolean sounds = plugin.configtools.getSounds();
		profil.addProclick(perclick);
		shopprofil.addGekauft(perclick);
		profil.removeCookies(price);
		if (sounds) {
			p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 10, 10);
		}
	}

	private void buyFail(Player p) {
		boolean sounds = plugin.configtools.getSounds();
		if (sounds) {
			p.playSound(p.getLocation(), XSound.BLOCK_ANVIL_BREAK.parseSound(), 10, 10);
		}
	}
}
