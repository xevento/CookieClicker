package de.zillolp.cookieclicker.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import de.zillolp.cookieclicker.config.tools.BoosterTools;
import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.profiles.ShopProfil;
import de.zillolp.cookieclicker.xclasses.XMaterial;

public class InventorySetter {
	private Main plugin = Main.plugin;
	private ItemCreator itemcreator = plugin.itemcreator;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<Player, ShopProfil> shops = plugin.shopprofiles;
	private HashMap<Player, BoosterProfil> booster = plugin.boosterprofiles;
	private XMaterial[] types_h = new XMaterial[] { XMaterial.LIME_TERRACOTTA, XMaterial.PINK_TERRACOTTA,
			XMaterial.COOKIE, XMaterial.BROWN_TERRACOTTA };
	private XMaterial[] types_d = new XMaterial[] { XMaterial.RED_STAINED_GLASS, XMaterial.ORANGE_STAINED_GLASS,
			XMaterial.YELLOW_STAINED_GLASS, XMaterial.PINK_STAINED_GLASS, XMaterial.MAGENTA_STAINED_GLASS,
			XMaterial.PURPLE_STAINED_GLASS, XMaterial.BROWN_STAINED_GLASS, XMaterial.LIME_STAINED_GLASS,
			XMaterial.GREEN_STAINED_GLASS, XMaterial.CYAN_STAINED_GLASS, XMaterial.LIGHT_BLUE_STAINED_GLASS,
			XMaterial.BLUE_STAINED_GLASS, XMaterial.WHITE_STAINED_GLASS, XMaterial.LIGHT_GRAY_STAINED_GLASS,
			XMaterial.GRAY_STAINED_GLASS, XMaterial.BLACK_STAINED_GLASS, XMaterial.BARRIER };
	private XMaterial[] types_dp = new XMaterial[] { XMaterial.RED_STAINED_GLASS_PANE,
			XMaterial.ORANGE_STAINED_GLASS_PANE, XMaterial.YELLOW_STAINED_GLASS_PANE, XMaterial.PINK_STAINED_GLASS_PANE,
			XMaterial.MAGENTA_STAINED_GLASS_PANE, XMaterial.PURPLE_STAINED_GLASS_PANE,
			XMaterial.BROWN_STAINED_GLASS_PANE, XMaterial.LIME_STAINED_GLASS_PANE, XMaterial.GREEN_STAINED_GLASS_PANE,
			XMaterial.CYAN_STAINED_GLASS_PANE, XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE,
			XMaterial.BLUE_STAINED_GLASS_PANE, XMaterial.WHITE_STAINED_GLASS_PANE,
			XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, XMaterial.GRAY_STAINED_GLASS_PANE,
			XMaterial.BLACK_STAINED_GLASS_PANE };
	private XMaterial[] types_s = new XMaterial[] { XMaterial.GOLDEN_HOE, XMaterial.IRON_AXE, XMaterial.DIAMOND_PICKAXE,
			XMaterial.DIAMOND, XMaterial.GOLD_BLOCK, XMaterial.IRON_SHOVEL, XMaterial.ENDER_PEARL,
			XMaterial.BROWN_TERRACOTTA, XMaterial.LIME_TERRACOTTA, XMaterial.GOLD_INGOT, XMaterial.BARRIER };
	private XMaterial[] types_sp = new XMaterial[] { XMaterial.BLAZE_ROD, XMaterial.IRON_INGOT,
			XMaterial.MUSIC_DISC_WARD, XMaterial.FIREWORK_STAR, XMaterial.ENDER_EYE, XMaterial.BLAZE_POWDER,
			XMaterial.NETHER_STAR, XMaterial.BROWN_TERRACOTTA, XMaterial.LIME_TERRACOTTA, XMaterial.BARRIER };
	private XMaterial[] types_b = new XMaterial[] { XMaterial.MILK_BUCKET, XMaterial.BARRIER };
	private String[] urls = new String[] { "1fa5889267249f7fa0d3afcc897b958bf4cea444cf519b2dd15332d6b1d2be4b",
			"1145a6881c620d5f3321a1d16e9185a5dd33fc91cfbc9a0b16440748de70a148",
			"dcedb2f4c97016cae7b89e4c6d6978d22ac3476c815c5a09a6792450dd918b6c",
			"9f8b82427b260d0a61e6483fc3b2c35a585851e08a9a9df372548b4168cc817c",
			"857066879778d95a2a7959c5f02a339fe84e96e9462333cc74152aa2933c5fb" };

	public void setHomeinv(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		PlayerProfil profil = profiles.get(p);
		Long cookies = profil.getCookies();
		Long proclick = profil.getProclick();
		ItemStack perclick = itemcreator.createItem(types_h[0].parseItem(), languagetools.getPER_CLICK(proclick));
		ItemStack booster = itemcreator.getSkullByTextureURL(urls[0], languagetools.getBOOSTER());
		ItemStack shop = itemcreator.createItem(types_h[1].parseItem(), languagetools.getSHOP());
		ItemStack designs = itemcreator.createItem(types_h[2].parseItem(), languagetools.getDESIGNS());
		ItemStack yourcookies = itemcreator.createItem(types_h[3].parseItem(), languagetools.getYOUR_COOKIES(cookies));
		inv.setItem(20, perclick);
		inv.setItem(22, booster);
		inv.setItem(24, shop);
		inv.setItem(31, designs);
		inv.setItem(13, yourcookies);
	}

	public void setBoosterinv(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		BoosterProfil profil = booster.get(p);
		ItemStack milch = itemcreator.createItem(types_b[0].parseItem(), languagetools.getMILK(profil.getMilk()));
		inv.setItem(4, milch);
		setBooster(p, inv);
		inv.setItem(27, itemcreator.createItem(types_d[16].parseItem(), languagetools.getBACK()));
	}

	public void setBooster(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		BoosterTools boostertools = plugin.boostertools;
		BoosterProfil profil = booster.get(p);
		ItemStack booster_1;
		String type = boostertools.getName();
		ItemStack booster_2;
		String type1 = boostertools.getName1();
		ItemStack booster_3;
		String type2 = boostertools.getName2();
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		if (profil.IsBooster()) {
			if (profil.getMilk() >= boostertools.getPreis()) {
				lore.add(languagetools.getBOOSTER_INFO_NOT_ACTIVE_BUYABLE(boostertools.getPreis()));
			} else {
				lore.add(languagetools.getBOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE(boostertools.getPreis()));
			}
			booster_1 = itemcreator.getSkullByTextureURL(urls[1], languagetools.getBOOSTER_NOT_ACTIVE(type), lore);
		} else {
			lore.add(languagetools.getBOOSTER_INFO_ACTIVE(profil.getBoostertime()));
			booster_1 = itemcreator.getSkullByTextureURL(urls[4], languagetools.getBOOSTER_ACTIVE(type), lore);
		}
		if (profil.IsBooster_1()) {
			if (profil.getMilk() >= boostertools.getPreis1()) {
				lore.set(1, languagetools.getBOOSTER_INFO_NOT_ACTIVE_BUYABLE(boostertools.getPreis1()));
			} else {
				lore.set(1, languagetools.getBOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE(boostertools.getPreis1()));
			}
			booster_2 = itemcreator.getSkullByTextureURL(urls[2], languagetools.getBOOSTER_NOT_ACTIVE(type1), lore);
		} else {
			lore.set(1, languagetools.getBOOSTER_INFO_ACTIVE(profil.getBoostertime_1()));
			booster_2 = itemcreator.getSkullByTextureURL(urls[4], languagetools.getBOOSTER_ACTIVE(type1), lore);
		}
		if (profil.IsBooster_2()) {
			if (profil.getMilk() >= boostertools.getPreis2()) {
				lore.set(1, languagetools.getBOOSTER_INFO_NOT_ACTIVE_BUYABLE(boostertools.getPreis2()));
			} else {
				lore.set(1, languagetools.getBOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE(boostertools.getPreis2()));
			}
			booster_3 = itemcreator.getSkullByTextureURL(urls[3], languagetools.getBOOSTER_NOT_ACTIVE(type2), lore);
		} else {
			lore.set(1, languagetools.getBOOSTER_INFO_ACTIVE(profil.getBoostertime_2()));
			booster_3 = itemcreator.getSkullByTextureURL(urls[4], languagetools.getBOOSTER_ACTIVE(type2), lore);
		}
		inv.setItem(20, booster_1);
		inv.setItem(22, booster_2);
		inv.setItem(24, booster_3);
	}

	public void setDesigninv(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		PlayerProfil profil = profiles.get(p);
		int design = Integer.valueOf(Long.toString(profil.getDesign()));
		ItemStack designtype;
		int designslot;
		switch (design) {
		case 16:
			designtype = itemcreator.createItem(types_d[0].parseItem(), languagetools.getRED_GLASS_SELECTED(), true);
			designslot = 10;
			break;
		case 15:
			designtype = itemcreator.createItem(types_d[1].parseItem(), languagetools.getORANGE_GLASS_SELECTED(), true);
			designslot = 11;
			break;
		case 14:
			designtype = itemcreator.createItem(types_d[2].parseItem(), languagetools.getYELLOW_GLASS_SELECTED(), true);
			designslot = 12;
			break;
		case 13:
			designtype = itemcreator.createItem(types_d[3].parseItem(), languagetools.getPINK_GLASS_SELECTED(), true);
			designslot = 13;
			break;
		case 12:
			designtype = itemcreator.createItem(types_d[4].parseItem(), languagetools.getMAGENTA_GLASS_SELECTED(),
					true);
			designslot = 14;
			break;
		case 11:
			designtype = itemcreator.createItem(types_d[5].parseItem(), languagetools.getPURPLE_GLASS_SELECTED(), true);
			designslot = 15;
			break;
		case 10:
			designtype = itemcreator.createItem(types_d[6].parseItem(), languagetools.getBROWN_GLASS_SELECTED(), true);
			designslot = 16;
			break;
		case 9:
			designtype = itemcreator.createItem(types_d[7].parseItem(), languagetools.getLIME_GLASS_SELECTED(), true);
			designslot = 18;
			break;
		case 8:
			designtype = itemcreator.createItem(types_d[8].parseItem(), languagetools.getGREEN_GLASS_SELECTED(), true);
			designslot = 19;
			break;
		case 7:
			designtype = itemcreator.createItem(types_d[9].parseItem(), languagetools.getCYAN_GLASS_SELECTED(), true);
			designslot = 20;
			break;
		case 6:
			designtype = itemcreator.createItem(types_d[10].parseItem(), languagetools.getLIGHT_BLUE_GLASS_SELECTED(),
					true);
			designslot = 21;
			break;
		case 5:
			designtype = itemcreator.createItem(types_d[11].parseItem(), languagetools.getBLUE_GLASS_SELECTED(), true);
			designslot = 22;
			break;
		case 4:
			designtype = itemcreator.createItem(types_d[12].parseItem(), languagetools.getWHITE_GLASS_SELECTED(), true);
			designslot = 23;
			break;
		case 3:
			designtype = itemcreator.createItem(types_d[13].parseItem(), languagetools.getLIGHT_GRAY_GLASS_SELECTED(),
					true);
			designslot = 24;
			break;
		case 2:
			designtype = itemcreator.createItem(types_d[14].parseItem(), languagetools.getGRAY_GLASS_SELECTED(), true);
			designslot = 25;
			break;
		case 1:
			designtype = itemcreator.createItem(types_d[15].parseItem(), languagetools.getBLACK_GLASS_SELECTED(), true);
			designslot = 26;
			break;

		default:
			designtype = itemcreator.createItem(types_d[14].parseItem(), languagetools.getGRAY_GLASS_SELECTED(), true);
			designslot = 25;
			break;
		}
		inv.setItem(10, itemcreator.createItem(types_d[0].parseItem(), languagetools.getRED_GLASS()));
		inv.setItem(11, itemcreator.createItem(types_d[1].parseItem(), languagetools.getORANGE_GLASS()));
		inv.setItem(12, itemcreator.createItem(types_d[2].parseItem(), languagetools.getYELLOW_GLASS()));
		inv.setItem(13, itemcreator.createItem(types_d[3].parseItem(), languagetools.getPINK_GLASS()));
		inv.setItem(14, itemcreator.createItem(types_d[4].parseItem(), languagetools.getMAGENTA_GLASS()));
		inv.setItem(15, itemcreator.createItem(types_d[5].parseItem(), languagetools.getPURPLE_GLASS()));
		inv.setItem(16, itemcreator.createItem(types_d[6].parseItem(), languagetools.getBROWN_GLASS()));
		inv.setItem(18, itemcreator.createItem(types_d[7].parseItem(), languagetools.getLIME_GLASS()));
		inv.setItem(19, itemcreator.createItem(types_d[8].parseItem(), languagetools.getGREEN_GLASS()));
		inv.setItem(20, itemcreator.createItem(types_d[9].parseItem(), languagetools.getCYAN_GLASS()));
		inv.setItem(21, itemcreator.createItem(types_d[10].parseItem(), languagetools.getLIGHT_BLUE_GLASS()));
		inv.setItem(22, itemcreator.createItem(types_d[11].parseItem(), languagetools.getBLUE_GLASS()));
		inv.setItem(23, itemcreator.createItem(types_d[12].parseItem(), languagetools.getWHITE_GLASS()));
		inv.setItem(24, itemcreator.createItem(types_d[13].parseItem(), languagetools.getLIGHT_GRAY_GLASS()));
		inv.setItem(25, itemcreator.createItem(types_d[14].parseItem(), languagetools.getGRAY_GLASS()));
		inv.setItem(26, itemcreator.createItem(types_d[15].parseItem(), languagetools.getBLACK_GLASS()));
		inv.setItem(designslot, designtype);
		inv.setItem(27, itemcreator.createItem(types_d[16].parseItem(), languagetools.getBACK()));
	}

	public void setDesign(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		PlayerProfil profil = profiles.get(p);
		String designname = languagetools.getDESIGN_GLASS_NAME();
		int design = Integer.valueOf(Long.toString(profil.getDesign()));
		ItemStack designtype;
		switch (design) {
		case 17:
			designtype = itemcreator.createItem(types_dp[15].parseItem(), designname);
			break;
		case 16:
			designtype = itemcreator.createItem(types_dp[0].parseItem(), designname);
			break;
		case 15:
			designtype = itemcreator.createItem(types_dp[1].parseItem(), designname);
			break;
		case 14:
			designtype = itemcreator.createItem(types_dp[2].parseItem(), designname);
			break;
		case 13:
			designtype = itemcreator.createItem(types_dp[3].parseItem(), designname);
			break;
		case 12:
			designtype = itemcreator.createItem(types_dp[4].parseItem(), designname);
			break;
		case 11:
			designtype = itemcreator.createItem(types_dp[5].parseItem(), designname);
			break;
		case 10:
			designtype = itemcreator.createItem(types_dp[6].parseItem(), designname);
			break;
		case 9:
			designtype = itemcreator.createItem(types_dp[7].parseItem(), designname);
			break;
		case 8:
			designtype = itemcreator.createItem(types_dp[8].parseItem(), designname);
			break;
		case 7:
			designtype = itemcreator.createItem(types_dp[9].parseItem(), designname);
			break;
		case 6:
			designtype = itemcreator.createItem(types_dp[10].parseItem(), designname);
			break;
		case 5:
			designtype = itemcreator.createItem(types_dp[11].parseItem(), designname);
			break;
		case 4:
			designtype = itemcreator.createItem(types_dp[12].parseItem(), designname);
			break;
		case 3:
			designtype = itemcreator.createItem(types_dp[13].parseItem(), designname);
			break;
		case 2:
			designtype = itemcreator.createItem(types_dp[14].parseItem(), designname);
			break;
		case 1:
			designtype = itemcreator.createItem(types_dp[15].parseItem(), designname);
			break;

		default:
			designtype = itemcreator.createItem(types_dp[14].parseItem(), designname);
			break;
		}
		IntStream.range(0, inv.getSize()).forEach(i -> inv.setItem(i, designtype));
	}

	public void setShopinv(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		PlayerProfil profil = profiles.get(p);
		ShopProfil shop = shops.get(p);
		Long[] values = new Long[] { profil.getCookies(), profil.getProclick(), shop.getPreis(0), shop.getPreis(1),
				shop.getPreis(2), shop.getPreis(3), shop.getPreis(4), shop.getPreis(5), shop.getPreis(6) };
		inv.setItem(19, createShopItem(p, values[0], values[2], 2, types_s[0].parseItem()));
		inv.setItem(20, createShopItem(p, values[0], values[3], 4, types_s[1].parseItem()));
		inv.setItem(21, createShopItem(p, values[0], values[4], 6, types_s[2].parseItem()));
		inv.setItem(22, createShopItem(p, values[0], values[5], 8, types_s[3].parseItem()));
		inv.setItem(23, createShopItem(p, values[0], values[6], 10, types_s[4].parseItem()));
		inv.setItem(24, createShopItem(p, values[0], values[7], 12, types_s[5].parseItem()));
		inv.setItem(25, createShopItem(p, values[0], values[8], 14, types_s[6].parseItem()));
		inv.setItem(2, itemcreator.createItem(types_s[7].parseItem(), languagetools.getYOUR_COOKIES(values[0])));
		inv.setItem(6, itemcreator.createItem(types_s[8].parseItem(), languagetools.getPER_CLICK(values[1])));
		inv.setItem(35, itemcreator.createItem(types_s[9].parseItem(), languagetools.getPREMIUM_PAGE()));
		inv.setItem(27, itemcreator.createItem(types_s[10].parseItem(), languagetools.getBACK()));
	}

	public void setPremiumshopinv(Player p, Inventory inv) {
		LanguageTools languagetools = plugin.languagetools;
		PlayerProfil profil = profiles.get(p);
		ShopProfil shop = shops.get(p);
		Long[] values = new Long[] { profil.getCookies(), profil.getProclick(), shop.getPreis(7), shop.getPreis(8),
				shop.getPreis(9), shop.getPreis(10), shop.getPreis(11), shop.getPreis(12), shop.getPreis(13) };
		inv.setItem(19, createShopItem(p, values[0], values[2], 16, types_sp[0].parseItem()));
		inv.setItem(20, createShopItem(p, values[0], values[3], 18, types_sp[1].parseItem()));
		inv.setItem(21, createShopItem(p, values[0], values[4], 20, types_sp[2].parseItem()));
		inv.setItem(22, createShopItem(p, values[0], values[5], 22, types_sp[3].parseItem()));
		inv.setItem(23, createShopItem(p, values[0], values[6], 24, types_sp[4].parseItem()));
		inv.setItem(24, createShopItem(p, values[0], values[7], 26, types_sp[5].parseItem()));
		inv.setItem(25, createShopItem(p, values[0], values[8], 28, types_sp[6].parseItem()));
		inv.setItem(2, itemcreator.createItem(types_sp[7].parseItem(), languagetools.getYOUR_COOKIES(values[0])));
		inv.setItem(6, itemcreator.createItem(types_sp[8].parseItem(), languagetools.getPER_CLICK(values[1])));
		inv.setItem(27, itemcreator.createItem(types_sp[9].parseItem(), languagetools.getBACK()));
	}

	public ItemStack createShopItem(Player p, Long cookies, Long price, int priceinfo, ItemStack type) {
		LanguageTools languagetools = plugin.languagetools;
		BoosterProfil profil = booster.get(p);
		ArrayList<String> kauf = new ArrayList<String>();
		kauf.add("§k");
		ItemFlag flag = ItemFlag.HIDE_ATTRIBUTES;
		if (!profil.IsBooster_2()) {
			if (cookies >= price) {
				kauf.add(languagetools.getPRICE_INFO(priceinfo));
				ItemStack kaufen = itemcreator.createItem(type,
						languagetools.getPRICE_BOOSTED_BUYABLE(price * 2, price), kauf, flag);
				return kaufen;
			} else {
				kauf.add(languagetools.getPRICE_INFO(priceinfo));
				ItemStack kaufen = itemcreator.createItem(type,
						languagetools.getPRICE_BOOSTED_NOT_BUYABLE(price * 2, price), kauf, flag);
				return kaufen;
			}
		} else {
			if (cookies >= price) {
				kauf.add(languagetools.getPRICE_INFO(priceinfo));
				ItemStack kaufen = itemcreator.createItem(type, languagetools.getPRICE_BUYABLE(price), kauf, flag);
				return kaufen;
			} else {
				kauf.add(languagetools.getPRICE_INFO(priceinfo));
				ItemStack kaufen = itemcreator.createItem(type, languagetools.getPRICE_NOT_BUYABLE(price), kauf, flag);
				return kaufen;
			}
		}
	}
}
