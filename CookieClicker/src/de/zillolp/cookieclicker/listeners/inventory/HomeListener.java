package de.zillolp.cookieclicker.listeners.inventory;

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
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.xclasses.XMaterial;
import de.zillolp.cookieclicker.xclasses.XSound;

public class HomeListener extends InventorySetter implements Listener {
	private Main plugin = Main.plugin;
	private ConfigTools configtools = plugin.configtools;
	private HashMap<Player, InventoryProfil> profiles = plugin.invprofiles;

	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory clickedinv = e.getClickedInventory();
		ItemStack currentitem = e.getCurrentItem();
		if (clickedinv != null && currentitem != null && currentitem.getType() != Material.AIR) {
			LanguageTools languagetools = plugin.languagetools;
			String viewtitle = e.getView().getTitle();
			InventoryProfil profil = profiles.get(p);
			if (viewtitle != null && viewtitle.equalsIgnoreCase(languagetools.getHOME_TITLE()) && profil != null) {
				boolean sounds = configtools.getSounds();
				String itemname = currentitem.getItemMeta().getDisplayName();
				Material itemtype = currentitem.getType();
				e.setCancelled(true);
				if (itemtype == XMaterial.PLAYER_HEAD.parseMaterial()
						&& itemname.equalsIgnoreCase(languagetools.getBOOSTER())) {
					Inventory inv = profil.getBoosterinv();
					if (inv == null) {
						inv = profil
								.setBoosterinv(Bukkit.createInventory(null, 4 * 9, languagetools.getBOOSTER_TITLE()));
					}
					setDesign(p, inv);
					setBoosterinv(p, inv);
					p.openInventory(inv);
					if (sounds) {
						p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 10, 10);
					}
				} else if (itemtype == XMaterial.PINK_TERRACOTTA.parseMaterial()
						&& itemname.equalsIgnoreCase(languagetools.getSHOP())) {
					Inventory inv = profil.getShopinv();
					if (inv == null) {
						inv = profil.setShopinv(Bukkit.createInventory(null, 4 * 9, languagetools.getSHOP_TITLE()));
					}
					setDesign(p, inv);
					setShopinv(p, inv);
					p.openInventory(inv);
					if (sounds) {
						p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 10, 10);
					}
				} else if (itemtype == XMaterial.COOKIE.parseMaterial()
						&& itemname.equalsIgnoreCase(languagetools.getDESIGNS())) {
					Inventory inv = profil.getDesigninv();
					if (inv == null) {
						inv = profil
								.setDesigninv(Bukkit.createInventory(null, 4 * 9, languagetools.getDESIGNS_TITLE()));
					}
					setDesign(p, inv);
					setDesigninv(p, inv);
					p.openInventory(inv);
					if (sounds) {
						p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 10, 10);
					}
				}
			}
		}
	}
}
