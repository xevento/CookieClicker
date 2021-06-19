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
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.utils.InventorySetter;
import de.zillolp.cookieclicker.xclasses.XMaterial;
import de.zillolp.cookieclicker.xclasses.XSound;

public class DesignListener extends InventorySetter implements Listener {
	private Main plugin = Main.plugin;
	private ConfigTools configtools = plugin.configtools;
	private HashMap<Player, InventoryProfil> invprofiles = plugin.invprofiles;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private Material[] types = new Material[] { XMaterial.BARRIER.parseMaterial(),
			XMaterial.RED_STAINED_GLASS.parseMaterial(), XMaterial.ORANGE_STAINED_GLASS.parseMaterial(),
			XMaterial.YELLOW_STAINED_GLASS.parseMaterial(), XMaterial.PINK_STAINED_GLASS.parseMaterial(),
			XMaterial.MAGENTA_STAINED_GLASS.parseMaterial(), XMaterial.PURPLE_STAINED_GLASS.parseMaterial(),
			XMaterial.BROWN_STAINED_GLASS.parseMaterial(), XMaterial.LIME_STAINED_GLASS.parseMaterial(),
			XMaterial.GREEN_STAINED_GLASS.parseMaterial(), XMaterial.CYAN_STAINED_GLASS.parseMaterial(),
			XMaterial.LIGHT_BLUE_STAINED_GLASS.parseMaterial(), XMaterial.BLUE_STAINED_GLASS.parseMaterial(),
			XMaterial.WHITE_STAINED_GLASS.parseMaterial(), XMaterial.LIGHT_GRAY_STAINED_GLASS.parseMaterial(),
			XMaterial.GRAY_STAINED_GLASS.parseMaterial(), XMaterial.BLACK_STAINED_GLASS.parseMaterial() };

	@EventHandler
	public void on(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory clickedinv = e.getClickedInventory();
		ItemStack currentitem = e.getCurrentItem();
		if (clickedinv != null && currentitem != null && currentitem.getType() != Material.AIR) {
			LanguageTools languagetools = plugin.languagetools;
			String[] names = new String[] { languagetools.getDESIGNS_TITLE(), languagetools.getBACK(),
					languagetools.getHOME_TITLE(), languagetools.getRED_GLASS(), languagetools.getORANGE_GLASS(),
					languagetools.getYELLOW_GLASS(), languagetools.getPINK_GLASS(), languagetools.getMAGENTA_GLASS(),
					languagetools.getPURPLE_GLASS(), languagetools.getBROWN_GLASS(), languagetools.getLIME_GLASS(),
					languagetools.getGREEN_GLASS(), languagetools.getCYAN_GLASS(), languagetools.getLIGHT_BLUE_GLASS(),
					languagetools.getBLUE_GLASS(), languagetools.getWHITE_GLASS(), languagetools.getLIGHT_GRAY_GLASS(),
					languagetools.getGRAY_GLASS(), languagetools.getBLACK_GLASS() };
			String viewtitle = e.getView().getTitle();
			boolean sounds = configtools.getSounds();
			if (viewtitle != null && viewtitle.equalsIgnoreCase(names[0])) {
				InventoryProfil invprofil = invprofiles.get(p);
				PlayerProfil profil = profiles.get(p);
				if (invprofil != null && profil != null) {
					e.setCancelled(true);
					Inventory topinv = p.getOpenInventory().getTopInventory();
					String itemname = currentitem.getItemMeta().getDisplayName();
					Material itemtype = currentitem.getType();
					if (itemtype == types[0] && itemname.equalsIgnoreCase(names[1])) {
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
					} else if (Arrays.asList(types).contains(itemtype) && topinv == clickedinv) {
						Inventory inv = e.getClickedInventory();
						if (itemtype == types[1] && itemname.equalsIgnoreCase(names[3])) {
							setDesign(p, profil, 16L);
						} else if (itemtype == types[2] && itemname.equalsIgnoreCase(names[4])) {
							setDesign(p, profil, 15L);
						} else if (itemtype == types[3] && itemname.equalsIgnoreCase(names[5])) {
							setDesign(p, profil, 14L);
						} else if (itemtype == types[4] && itemname.equalsIgnoreCase(names[6])) {
							setDesign(p, profil, 13L);
						} else if (itemtype == types[5] && itemname.equalsIgnoreCase(names[7])) {
							setDesign(p, profil, 12L);
						} else if (itemtype == types[6] && itemname.equalsIgnoreCase(names[8])) {
							setDesign(p, profil, 11L);
						} else if (itemtype == types[7] && itemname.equalsIgnoreCase(names[9])) {
							setDesign(p, profil, 10L);
						} else if (itemtype == types[8] && itemname.equalsIgnoreCase(names[10])) {
							setDesign(p, profil, 9L);
						} else if (itemtype == types[9] && itemname.equalsIgnoreCase(names[11])) {
							setDesign(p, profil, 8L);
						} else if (itemtype == types[10] && itemname.equalsIgnoreCase(names[12])) {
							setDesign(p, profil, 7L);
						} else if (itemtype == types[11] && itemname.equalsIgnoreCase(names[13])) {
							setDesign(p, profil, 6L);
						} else if (itemtype == types[12] && itemname.equalsIgnoreCase(names[14])) {
							setDesign(p, profil, 5L);
						} else if (itemtype == types[13] && itemname.equalsIgnoreCase(names[15])) {
							setDesign(p, profil, 4L);
						} else if (itemtype == types[14] && itemname.equalsIgnoreCase(names[16])) {
							setDesign(p, profil, 3L);
						} else if (itemtype == types[15] && itemname.equalsIgnoreCase(names[17])) {
							setDesign(p, profil, 2L);
						} else if (itemtype == types[16] && itemname.equalsIgnoreCase(names[18])) {
							setDesign(p, profil, 1L);
						}
						setDesign(p, inv);
						setDesigninv(p, inv);
					}
				}
			}
		}
	}

	private void setDesign(Player p, PlayerProfil profil, Long nummer) {
		boolean sounds = configtools.getSounds();
		profil.setDesign(nummer);
		if (sounds) {
			p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 10, 10);
		}
	}
}
