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
import de.zillolp.cookieclicker.config.tools.PermissionTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.profiles.ShopProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.xclasses.XMaterial;
import de.zillolp.cookieclicker.xclasses.XSound;

public class ShopListener extends InventorySetter implements Listener {
	private Main plugin = Main.plugin;
	private ConfigTools configtools = plugin.configtools;
	private HashMap<Player, InventoryProfil> invprofiles = plugin.invprofiles;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<Player, ShopProfil> shopprofiles = plugin.shopprofiles;
	private HashMap<Player, BoosterProfil> boosterprofiles = plugin.boosterprofiles;
	private Material[] types = new Material[] { XMaterial.BARRIER.parseMaterial(),
			XMaterial.GOLD_INGOT.parseMaterial() };
	private Material[] shoptypes = new Material[] { XMaterial.GOLDEN_HOE.parseMaterial(),
			XMaterial.IRON_AXE.parseMaterial(), XMaterial.DIAMOND_PICKAXE.parseMaterial(),
			XMaterial.DIAMOND.parseMaterial(), XMaterial.GOLD_BLOCK.parseMaterial(),
			XMaterial.IRON_SHOVEL.parseMaterial(), XMaterial.ENDER_PEARL.parseMaterial() };

	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory clickedinv = e.getClickedInventory();
		ItemStack currentitem = e.getCurrentItem();
		if (clickedinv != null && currentitem != null && currentitem.getType() != Material.AIR) {
			LanguageTools languagetools = plugin.languagetools;
			String viewtitle = e.getView().getTitle();
			if (viewtitle != null && viewtitle.equalsIgnoreCase(languagetools.getSHOP_TITLE())) {
				boolean sounds = configtools.getSounds();
				InventoryProfil invprofil = invprofiles.get(p);
				PlayerProfil profil = profiles.get(p);
				ShopProfil shopprofil = shopprofiles.get(p);
				BoosterProfil boosterprofil = boosterprofiles.get(p);
				if (invprofil != null && profil != null && shopprofil != null && boosterprofil != null) {
					e.setCancelled(true);
					String itemname = currentitem.getItemMeta().getDisplayName();
					Material itemtype = currentitem.getType();
					if (itemtype == types[0] && itemname.equalsIgnoreCase(languagetools.getBACK())) {
						Inventory inv = invprofil.getHomeinv();
						if (inv == null) {
							inv = invprofil
									.setHomeinv(Bukkit.createInventory(null, 5 * 9, languagetools.getHOME_TITLE()));
						}
						setDesign(p, inv);
						setHomeinv(p, inv);
						p.openInventory(inv);
						if (sounds) {
							p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_SNARE.parseSound(), 10, 10);
						}
					} else if (itemtype == types[1] && itemname.equalsIgnoreCase(languagetools.getPREMIUM_PAGE())) {
						PermissionTools permissiontools = plugin.permissiontools;
						if (p.hasPermission(permissiontools.getPremiumpermission())) {
							Inventory inv = invprofil.getPremiumshopinv();
							if (inv == null) {
								inv = invprofil.setPremiumshopinv(
										Bukkit.createInventory(null, 4 * 9, languagetools.getPREMIUM_SHOP_TITLE()));
							}
							setDesign(p, inv);
							setPremiumshopinv(p, inv);
							p.openInventory(inv);
							if (sounds) {
								p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 10, 10);
							}
						} else {
							p.sendMessage(languagetools.getPREFIX() + languagetools.getNO_PERMISSION());
							if (sounds) {
								p.playSound(p.getLocation(), XSound.BLOCK_ANVIL_BREAK.parseSound(), 10, 10);
							}
						}
					} else if (!(e.isShiftClick())) {
						Inventory topinv = p.getOpenInventory().getTopInventory();
						if (Arrays.asList(shoptypes).contains(itemtype) && topinv == clickedinv) {
							Long[] preise = new Long[] { shopprofil.getPreis(0), shopprofil.getPreis(1),
									shopprofil.getPreis(2), shopprofil.getPreis(3), shopprofil.getPreis(4),
									shopprofil.getPreis(5), shopprofil.getPreis(6) };
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
									buyPerclick(p, profil, shopprofil, 2L, preise[0]);
									shopprofil.addPreis(60L, 0);
								} else if (itemname.equalsIgnoreCase(not_buyable[0])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[1]) {
								if (itemname.equalsIgnoreCase(buyable[1])) {
									buyPerclick(p, profil, shopprofil, 4L, preise[1]);
									shopprofil.addPreis(360L, 1);
								} else if (itemname.equalsIgnoreCase(not_buyable[1])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[2]) {
								if (itemname.equalsIgnoreCase(buyable[2])) {
									buyPerclick(p, profil, shopprofil, 6L, preise[2]);
									shopprofil.addPreis(690L, 2);
								} else if (itemname.equalsIgnoreCase(not_buyable[2])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[3]) {
								if (itemname.equalsIgnoreCase(buyable[3])) {
									buyPerclick(p, profil, shopprofil, 8L, preise[3]);
									shopprofil.addPreis(920L, 3);
								} else if (itemname.equalsIgnoreCase(not_buyable[3])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[4]) {
								if (itemname.equalsIgnoreCase(buyable[4])) {
									buyPerclick(p, profil, shopprofil, 10L, preise[4]);
									shopprofil.addPreis(1250L, 4);
								} else if (itemname.equalsIgnoreCase(not_buyable[4])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[5]) {
								if (itemname.equalsIgnoreCase(buyable[5])) {
									buyPerclick(p, profil, shopprofil, 12L, preise[5]);
									shopprofil.addPreis(1580L, 5);
								} else if (itemname.equalsIgnoreCase(not_buyable[5])) {
									buyFail(p);
								}
							} else if (itemtype == shoptypes[6]) {
								if (itemname.equalsIgnoreCase(buyable[6])) {
									buyPerclick(p, profil, shopprofil, 14L, preise[6]);
									shopprofil.addPreis(1910L, 6);
								} else if (itemname.equalsIgnoreCase(not_buyable[6])) {
									buyFail(p);
								}
							}
							setShopinv(p, clickedinv);
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
