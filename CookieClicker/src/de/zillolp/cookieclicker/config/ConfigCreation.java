package de.zillolp.cookieclicker.config;

import java.io.File;
import java.util.LinkedHashMap;

import de.zillolp.cookieclicker.utils.ConfigUtil;

public class ConfigCreation {
	private LinkedHashMap<String, String> string_defaults = new LinkedHashMap<>();
	private LinkedHashMap<String, Boolean> boolean_defaults = new LinkedHashMap<>();
	private LinkedHashMap<String, Integer> integer_defaults = new LinkedHashMap<>();

	public ConfigCreation() {
		createConfigfile();
		createLanguagefile();
		createPermissionsfile();
		createHologramsfile();
		createStatswallfile();
		createBoosterfile();
		createLocationsfile();
	}

	private void createConfigfile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/config.yml"));
		configutil.createConfig();
		configutil.addDefaultString(":###", "Settings");
		boolean_defaults.put("MySQL", false);
		boolean_defaults.put("English", false);
		boolean_defaults.put("Sounds", true);
		boolean_defaults.put("Ranking", true);
		boolean_defaults.put("Time-ranking", true);
		configutil.addDefaultBooleans(boolean_defaults);
		boolean_defaults.clear();
		configutil.addDefaultString(":#", "The cooldown is in seconds!");
		integer_defaults.put("Afk-cooldown", 15);
		integer_defaults.put("Max-cps", 30);
		configutil.addDefaultIntegers(integer_defaults);
		integer_defaults.clear();
	}

	private void createLanguagefile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/language.yml"));
		configutil.createConfig();
		string_defaults.put(":#", "Normal Tags %Ue%, %ue%, %ae%, %ae%, %Oe%, %oe%, %sz%");
		string_defaults.put(":##", "Special Tags %>%, %<%, %*%, %->%, &");
		string_defaults.put(":###", "Basic messages");
		string_defaults.put("PREFIX", "&7[&6CookieClicker&7] ");
		string_defaults.put("NO_PERMISSION", "&cDazu hast du keine Rechte!");
		string_defaults.put("ONLY_PLAYER", "&cDu musst ein Spieler sein!");
		string_defaults.put(":####", "Interact messages");
		string_defaults.put("MAX_CPS", "&cReduziere bitte deine Klickrate!");
		string_defaults.put("AFK", "&cBitte bewege dich!");
		string_defaults.put("CLICK_MESSAGE", "&3Deine Cookies &7%>% &e%cookies%");
		string_defaults.put("BOOSTED_CLICK_MESSAGE", "&bDeine Cookies &7%>% &e&m%cookies%&6 %boosted_cookies%");
		string_defaults.put("MILK_ALERT.TITLE", "&6&lMILCH");
		string_defaults.put("MILK_ALERT.SUBTITLE", "&7Du hast eine &eMilch &7bekommen!");
		string_defaults.put("BOOSTER_STARTED_MESSAGE", "&7Der Booster &b%booster% &7wurde &aaktiviert&7!");
		string_defaults.put("BOOSTER_EXPIRED_MESSAGE", "&7Der Booster &b%booster% &7ist &cabgelaufen&7!");
		string_defaults.put("BOOSTER_LIMIT_MESSAGE", "&cDu kannst nicht mehr Booster aktivieren!");
		string_defaults.put("PLAYER_STATS_INFO.1", "&7-= &eStatistiken von &6%name% &7=-");
		string_defaults.put("PLAYER_STATS_INFO.2", "&7Platz&8: &e%place%");
		string_defaults.put("PLAYER_STATS_INFO.3", "&7Cookies&8: &e%cookies%");
		string_defaults.put("PLAYER_STATS_INFO.4", "&7Pro Klick&8: &e%perclick%");
		string_defaults.put("PLAYER_STATS_INFO.5", "&7Clicker geklickt&8: &e%clickerclicks%");
		string_defaults.put("PLAYER_STATS_INFO.6", "&7Milch&8: &e%milk%");
		string_defaults.put("PLAYER_STATS_INFO.7", "&7--------------------------------");

		string_defaults.put(":#####", "Inventory titles");
		string_defaults.put("HOME_TITLE", "&eCookieClicker");
		string_defaults.put("SHOP_TITLE", "&eCookieClicker | &3Shop");
		string_defaults.put("PREMIUM_SHOP_TITLE", "&eCookieClicker | &3Premium");
		string_defaults.put("BOOSTER_TITLE", "&eCookieClicker | &3Booster");
		string_defaults.put("DESIGNS_TITLE", "&eCookieClicker | &3Designs");

		string_defaults.put(":######", "Item names");
		string_defaults.put("PER_CLICK", "&3Pro Klick: &e%perclick%");
		string_defaults.put("BOOSTER", "&3Booster");
		string_defaults.put("YOUR_COOKIES", "&3Deine Cookies: &e%cookies%");
		string_defaults.put("SHOP", "&3Shop");
		string_defaults.put("PREMIUM_PAGE", "&3Premium Seite");
		string_defaults.put("DESIGNS", "&3Design");
		string_defaults.put("BACK", "&cZur%ue%ck");

		string_defaults.put(":#-", "Shop");
		string_defaults.put("PRICE.BUYABLE", "&3Preis: &a%price%");
		string_defaults.put("PRICE.NOT_BUYABLE", "&3Preis: &c%price%");
		string_defaults.put("PRICE.BOOSTED_BUYABLE", "&3Preis: &a&m%price%&2 %boosted_price%");
		string_defaults.put("PRICE.BOOSTED_NOT_BUYABLE", "&3Preis: &c&m%price%&4 %boosted_price%");
		string_defaults.put("PRICE_INFO", "&3Pro Klick: &e+ %addclick%");

		string_defaults.put(":##-", "Design");
		string_defaults.put("DESIGN_GLASS_NAME", "&7*");
		string_defaults.put("RED_GLASS", "&fRotes Glas");
		string_defaults.put("RED_GLASS_SELECTED", "&bRotes Glas");
		string_defaults.put("ORANGE_GLASS", "&fOranges Glas");
		string_defaults.put("ORANGE_GLASS_SELECTED", "&bOranges Glas");
		string_defaults.put("YELLOW_GLASS", "&fGelbes Glas");
		string_defaults.put("YELLOW_GLASS_SELECTED", "&bGelbes Glas");
		string_defaults.put("PINK_GLASS", "&fRosa Glas");
		string_defaults.put("PINK_GLASS_SELECTED", "&bRosa Glas");
		string_defaults.put("MAGENTA_GLASS", "&fMagenta Glas");
		string_defaults.put("MAGENTA_GLASS_SELECTED", "&bMagenta Glas");
		string_defaults.put("PURPLE_GLASS", "&fViolettes Glas");
		string_defaults.put("PURPLE_GLASS_SELECTED", "&bViolettes Glas");
		string_defaults.put("BROWN_GLASS", "&fBraunes Glas");
		string_defaults.put("BROWN_GLASS_SELECTED", "&bBraunes Glas");
		string_defaults.put("LIME_GLASS", "&fHellgr%ue%nes Glas");
		string_defaults.put("LIME_GLASS_SELECTED", "&bHellgr%ue%nes Glas");
		string_defaults.put("GREEN_GLASS", "&fGr%ue%nes Glas");
		string_defaults.put("GREEN_GLASS_SELECTED", "&bGr%ue%nes Glas");
		string_defaults.put("CYAN_GLASS", "&fT%ue%rkises Glas");
		string_defaults.put("CYAN_GLASS_SELECTED", "&bT%ue%rkises Glas");
		string_defaults.put("LIGHT_BLUE_GLASS", "&fHellblaues Glas");
		string_defaults.put("LIGHT_BLUE_GLASS_SELECTED", "&bHellblaues Glas");
		string_defaults.put("BLUE_GLASS", "&fBlaues Glas");
		string_defaults.put("BLUE_GLASS_SELECTED", "&bBlaues Glas");
		string_defaults.put("WHITE_GLASS", "&fWei%sz%es Glas");
		string_defaults.put("WHITE_GLASS_SELECTED", "&bWei%sz%es Glas");
		string_defaults.put("LIGHT_GRAY_GLASS", "&fHellgraues Glas");
		string_defaults.put("LIGHT_GRAY_GLASS_SELECTED", "&bHellgraues Glas");
		string_defaults.put("GRAY_GLASS", "&fGraues Glas");
		string_defaults.put("GRAY_GLASS_SELECTED", "&bGraues Glas");
		string_defaults.put("BLACK_GLASS", "&fSchwarzes Glas");
		string_defaults.put("BLACK_GLASS_SELECTED", "&bSchwarzes Glas");

		string_defaults.put(":###-", "Booster");
		string_defaults.put("MILK", "&3Milch: &e%milk%");
		string_defaults.put("BOOSTER_NOT_ACTIVE", "&7%booster%");
		string_defaults.put("BOOSTER_ACTIVE", "&b%booster%");
		string_defaults.put("BOOSTER_INFO.NOT_ACTIVE.BUYABLE", "&7Preis: &a%price%");
		string_defaults.put("BOOSTER_INFO.NOT_ACTIVE.NOT_BUYABLE", "&7Preis: &c%price%");
		string_defaults.put("BOOSTER_INFO.ACTIVE", "&7Läuft ab in &c%time%");
		string_defaults.put("ANIMATION.CAULDRON", "&eKessel");
		string_defaults.put("ANIMATION.EMPTY_POTION.1", "&eBraut.");
		string_defaults.put("ANIMATION.EMPTY_POTION.2", "&eBraut..");
		string_defaults.put("ANIMATION.EMPTY_POTION.3", "&eBraut...");
		string_defaults.put("ANIMATION.FILLED_POTION.1", "&eBraut.");
		string_defaults.put("ANIMATION.FILLED_POTION.2", "&eBraut..");
		string_defaults.put("ANIMATION.FILLED_POTION.3", "&eBraut...");

		configutil.addDefaultStrings(string_defaults);
		string_defaults.clear();
	}

	private void createPermissionsfile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/permissions.yml"));
		configutil.createConfig();
		string_defaults.put(":###", "Permissions");
		string_defaults.put("ADMIN_PERMISSION", "cookieclicker.admin");
		string_defaults.put("PREMIUM_PERMISSION", "cookieclicker.premium");
		configutil.addDefaultStrings(string_defaults);
		string_defaults.clear();
	}

	private void createHologramsfile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/holograms.yml"));
		configutil.createConfig();
		configutil.addDefaultString(":##", "Settings");
		boolean_defaults.put("Clicker holograms", true);
		boolean_defaults.put("Resettimer", true);
		configutil.addDefaultBooleans(boolean_defaults);
		boolean_defaults.clear();
		string_defaults.put(":###", "Lines");
		string_defaults.put("Clicker-hologram.1", "&3%>% &eCookieClicker &3%<%");
		string_defaults.put("Clicker-hologram.2", "&3Rechtsklick &7%>% &eInventar %oe%ffnen");
		string_defaults.put("Clicker-hologram.3", "&3Linksklick &7%>% &eCookieClicker");
		string_defaults.put("Resettimer hologram", "&7%*% &a%time% &7%*%");
		configutil.addDefaultStrings(string_defaults);
		string_defaults.clear();
	}

	private void createStatswallfile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/statswall.yml"));
		configutil.createConfig();
		configutil.addDefaultString(":##-", "Options");
		configutil.addDefaultString(":#", "The Refresh is in seconds!");
		configutil.addDefaultInteger("Refresh", 10);
		configutil.addDefaultString(":##", "The Resettime is in hours!");
		configutil.addDefaultInteger("Resettime", 1);
		string_defaults.put(":###", "Types Perclick, Cookies, Clickerclicks");
		string_defaults.put("Alltime-Ranktype", "Perclick");
		string_defaults.put(":###-", "Design");
		string_defaults.put("ALLTIME.RANK_PREFIX.1", "&e&l#1");
		string_defaults.put("ALLTIME.RANK_PREFIX.2", "&b&l#2");
		string_defaults.put("ALLTIME.RANK_PREFIX.3", "&a&l#3");
		string_defaults.put("ALLTIME.RANK_PREFIX.4", "&f&l#4");
		string_defaults.put("ALLTIME.RANK_PREFIX.5", "&f&l#5");
		string_defaults.put("ALLTIME.SIGN.1", "%*% CookieClicker %*%");
		string_defaults.put("ALLTIME.SIGN.2", "%place%");
		string_defaults.put("ALLTIME.SIGN.3", "%name%");
		string_defaults.put("ALLTIME.SIGN.4", "%perclick% Pro Click");
		string_defaults.put("TIME.RANK_PREFIX.1", "&e&l#1");
		string_defaults.put("TIME.RANK_PREFIX.2", "&b&l#2");
		string_defaults.put("TIME.RANK_PREFIX.3", "&a&l#3");
		string_defaults.put("TIME.SIGN.1", "%*% CookieClicker %*%");
		string_defaults.put("TIME.SIGN.2", "%place%");
		string_defaults.put("TIME.SIGN.3", "%name%");
		string_defaults.put("TIME.SIGN.4", "%perclick% Pro Click");
		configutil.addDefaultStrings(string_defaults);
		string_defaults.clear();
	}

	private void createBoosterfile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/booster.yml"));
		configutil.createConfig();
		string_defaults.put(":###", "Settings");
		string_defaults.put(":##", "The two indicate the probability of milk.");
		string_defaults.put(":#", "The chance is for example 5000 out of 1000000");
		configutil.addDefaultStrings(string_defaults);
		string_defaults.clear();
		integer_defaults.put("Entire area", 1000000);
		integer_defaults.put("Area for profit", 5000);
		integer_defaults.put("Active booster limitation", 1);
		configutil.addDefaultIntegers(integer_defaults);
		integer_defaults.clear();
		string_defaults.put(":#-", "The length is in minutes");
		string_defaults.put("Booster.Booster-1.Name", "Doppler");
		string_defaults.put("Booster.Booster-2.Name", "Ausblenden");
		string_defaults.put("Booster.Booster-3.Name", "Discounter");
		configutil.addDefaultStrings(string_defaults);
		string_defaults.clear();
		integer_defaults.put("Booster.Booster-1.Price", 10);
		integer_defaults.put("Booster.Booster-1.Length", 60);
		integer_defaults.put("Booster.Booster-2.Price", 20);
		integer_defaults.put("Booster.Booster-2.Length", 60);
		integer_defaults.put("Booster.Booster-3.Price", 30);
		integer_defaults.put("Booster.Booster-3.Length", 60);
		configutil.addDefaultIntegers(integer_defaults);
		integer_defaults.clear();
	}

	private void createLocationsfile() {
		ConfigUtil configutil = new ConfigUtil(new File("plugins/CookieClicker/locations.yml"));
		configutil.createConfig();
		configutil.addDefaultString(":###", "Locations");
	}
}
