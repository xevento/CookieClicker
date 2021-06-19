package de.zillolp.cookieclicker.config.tools;

import java.io.File;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.ConfigUtil;
import de.zillolp.cookieclicker.utils.StringUtil;

public class LanguageTools {
	private Main plugin = Main.plugin;
	private ConfigUtil configutil;
	private StringUtil stringutil = plugin.stringutil;
	private String PREFIX;
	private String NO_PERMISSION;
	private String ONLY_PLAYER;
	private String MAX_CPS;
	private String AFK;
	private String CLICK_MESSAGE;
	private String BOOSTED_CLICK_MESSAGE;
	private String PLAYER_STATS_INFO;
	private String PLAYER_STATS_INFO_1;
	private String PLAYER_STATS_INFO_2;
	private String PLAYER_STATS_INFO_3;
	private String PLAYER_STATS_INFO_4;
	private String PLAYER_STATS_INFO_5;
	private String PLAYER_STATS_INFO_6;
	private String HOME_TITLE;
	private String SHOP_TITLE;
	private String PREMIUM_SHOP_TITLE;
	private String BOOSTER_TITLE;
	private String DESIGNS_TITLE;
	private String PER_CLICK;
	private String BOOSTER;
	private String YOUR_COOKIES;
	private String SHOP;
	private String PREMIUM_PAGE;
	private String DESIGNS;
	private String BACK;
	private String PRICE_BUYABLE;
	private String PRICE_NOT_BUYABLE;
	private String PRICE_BOOSTED_BUYABLE;
	private String PRICE_BOOSTED_NOT_BUYABLE;
	private String PRICE_INFO;
	private String DESIGN_GLASS_NAME;
	private String RED_GLASS;
	private String RED_GLASS_SELECTED;
	private String ORANGE_GLASS;
	private String ORANGE_GLASS_SELECTED;
	private String YELLOW_GLASS;
	private String YELLOW_GLASS_SELECTED;
	private String PINK_GLASS;
	private String PINK_GLASS_SELECTED;
	private String MAGENTA_GLASS;
	private String MAGENTA_GLASS_SELECTED;
	private String PURPLE_GLASS;
	private String PURPLE_GLASS_SELECTED;
	private String BROWN_GLASS;
	private String BROWN_GLASS_SELECTED;
	private String LIME_GLASS;
	private String LIME_GLASS_SELECTED;
	private String GREEN_GLASS;
	private String GREEN_GLASS_SELECTED;
	private String CYAN_GLASS;
	private String CYAN_GLASS_SELECTED;
	private String LIGHT_BLUE_GLASS;
	private String LIGHT_BLUE_GLASS_SELECTED;
	private String BLUE_GLASS;
	private String BLUE_GLASS_SELECTED;
	private String WHITE_GLASS;
	private String WHITE_GLASS_SELECTED;
	private String LIGHT_GRAY_GLASS;
	private String LIGHT_GRAY_GLASS_SELECTED;
	private String GRAY_GLASS;
	private String GRAY_GLASS_SELECTED;
	private String BLACK_GLASS;
	private String BLACK_GLASS_SELECTED;
	private String MILK_ALERT_TITLE;
	private String MILK_ALERT_SUBTITLE;
	private String BOOSTER_STARTED_MESSAGE;
	private String BOOSTER_EXPIRED_MESSAGE;
	private String BOOSTER_LIMIT_MESSAGE;
	private String MILK;
	private String BOOSTER_NOT_ACTIVE;
	private String BOOSTER_ACTIVE;
	private String BOOSTER_INFO_NOT_ACTIVE_BUYABLE;
	private String BOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE;
	private String BOOSTER_INFO_ACTIVE;

	private String ANIMATION_CAULDRON;
	private String ANIMATION_EMPTY_POTION;
	private String ANIMATION_EMPTY_POTION_1;
	private String ANIMATION_EMPTY_POTION_2;
	private String ANIMATION_FILLED_POTION;
	private String ANIMATION_FILLED_POTION_1;
	private String ANIMATION_FILLED_POTION_2;

	public LanguageTools() {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/language.yml"));
		PREFIX = stringutil.replaceDefaults(configutil.getString("PREFIX"));
		NO_PERMISSION = stringutil.replaceDefaults(configutil.getString("NO_PERMISSION"));
		ONLY_PLAYER = stringutil.replaceDefaults(configutil.getString("ONLY_PLAYER"));
		MAX_CPS = stringutil.replaceDefaults(configutil.getString("MAX_CPS"));
		AFK = stringutil.replaceDefaults(configutil.getString("AFK"));
		CLICK_MESSAGE = stringutil.replaceDefaults(configutil.getString("CLICK_MESSAGE"));
		BOOSTED_CLICK_MESSAGE = stringutil.replaceDefaults(configutil.getString("BOOSTED_CLICK_MESSAGE"));
		PLAYER_STATS_INFO = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.1"));
		PLAYER_STATS_INFO_1 = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.2"));
		PLAYER_STATS_INFO_2 = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.3"));
		PLAYER_STATS_INFO_3 = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.4"));
		PLAYER_STATS_INFO_4 = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.5"));
		PLAYER_STATS_INFO_5 = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.6"));
		PLAYER_STATS_INFO_6 = stringutil.replaceDefaults(configutil.getString("PLAYER_STATS_INFO.7"));
		HOME_TITLE = stringutil.replaceDefaults(configutil.getString("HOME_TITLE"));
		SHOP_TITLE = stringutil.replaceDefaults(configutil.getString("SHOP_TITLE"));
		PREMIUM_SHOP_TITLE = stringutil.replaceDefaults(configutil.getString("PREMIUM_SHOP_TITLE"));
		BOOSTER_TITLE = stringutil.replaceDefaults(configutil.getString("BOOSTER_TITLE"));
		DESIGNS_TITLE = stringutil.replaceDefaults(configutil.getString("DESIGNS_TITLE"));
		PER_CLICK = stringutil.replaceDefaults(configutil.getString("PER_CLICK"));
		BOOSTER = stringutil.replaceDefaults(configutil.getString("BOOSTER"));
		YOUR_COOKIES = stringutil.replaceDefaults(configutil.getString("YOUR_COOKIES"));
		SHOP = stringutil.replaceDefaults(configutil.getString("SHOP"));
		PREMIUM_PAGE = stringutil.replaceDefaults(configutil.getString("PREMIUM_PAGE"));
		DESIGNS = stringutil.replaceDefaults(configutil.getString("DESIGNS"));
		BACK = stringutil.replaceDefaults(configutil.getString("BACK"));
		PRICE_BUYABLE = stringutil.replaceDefaults(configutil.getString("PRICE.BUYABLE"));
		PRICE_NOT_BUYABLE = stringutil.replaceDefaults(configutil.getString("PRICE.NOT_BUYABLE"));
		PRICE_BOOSTED_BUYABLE = stringutil.replaceDefaults(configutil.getString("PRICE.BOOSTED_BUYABLE"));
		PRICE_BOOSTED_NOT_BUYABLE = stringutil.replaceDefaults(configutil.getString("PRICE.BOOSTED_NOT_BUYABLE"));
		PRICE_INFO = stringutil.replaceDefaults(configutil.getString("PRICE_INFO"));
		DESIGN_GLASS_NAME = stringutil.replaceDefaults(configutil.getString("DESIGN_GLASS_NAME"));
		RED_GLASS = stringutil.replaceDefaults(configutil.getString("RED_GLASS"));
		RED_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("RED_GLASS_SELECTED"));
		ORANGE_GLASS = stringutil.replaceDefaults(configutil.getString("ORANGE_GLASS"));
		ORANGE_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("ORANGE_GLASS_SELECTED"));
		YELLOW_GLASS = stringutil.replaceDefaults(configutil.getString("YELLOW_GLASS"));
		YELLOW_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("YELLOW_GLASS_SELECTED"));
		PINK_GLASS = stringutil.replaceDefaults(configutil.getString("PINK_GLASS"));
		PINK_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("PINK_GLASS_SELECTED"));
		MAGENTA_GLASS = stringutil.replaceDefaults(configutil.getString("MAGENTA_GLASS"));
		MAGENTA_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("MAGENTA_GLASS_SELECTED"));
		PURPLE_GLASS = stringutil.replaceDefaults(configutil.getString("PURPLE_GLASS"));
		PURPLE_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("PURPLE_GLASS_SELECTED"));
		BROWN_GLASS = stringutil.replaceDefaults(configutil.getString("BROWN_GLASS"));
		BROWN_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("BROWN_GLASS_SELECTED"));
		LIME_GLASS = stringutil.replaceDefaults(configutil.getString("LIME_GLASS"));
		LIME_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("LIME_GLASS_SELECTED"));
		GREEN_GLASS = stringutil.replaceDefaults(configutil.getString("GREEN_GLASS"));
		GREEN_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("GREEN_GLASS_SELECTED"));
		CYAN_GLASS = stringutil.replaceDefaults(configutil.getString("CYAN_GLASS"));
		CYAN_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("CYAN_GLASS_SELECTED"));
		LIGHT_BLUE_GLASS = stringutil.replaceDefaults(configutil.getString("LIGHT_BLUE_GLASS"));
		LIGHT_BLUE_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("LIGHT_BLUE_GLASS_SELECTED"));
		BLUE_GLASS = stringutil.replaceDefaults(configutil.getString("BLUE_GLASS"));
		BLUE_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("BLUE_GLASS_SELECTED"));
		WHITE_GLASS = stringutil.replaceDefaults(configutil.getString("WHITE_GLASS"));
		WHITE_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("WHITE_GLASS_SELECTED"));
		LIGHT_GRAY_GLASS = stringutil.replaceDefaults(configutil.getString("LIGHT_GRAY_GLASS"));
		LIGHT_GRAY_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("LIGHT_GRAY_GLASS_SELECTED"));
		GRAY_GLASS = stringutil.replaceDefaults(configutil.getString("GRAY_GLASS"));
		GRAY_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("GRAY_GLASS_SELECTED"));
		BLACK_GLASS = stringutil.replaceDefaults(configutil.getString("BLACK_GLASS"));
		BLACK_GLASS_SELECTED = stringutil.replaceDefaults(configutil.getString("BLACK_GLASS_SELECTED"));

		MILK_ALERT_TITLE = stringutil.replaceDefaults(configutil.getString("MILK_ALERT.TITLE"));
		MILK_ALERT_SUBTITLE = stringutil.replaceDefaults(configutil.getString("MILK_ALERT.SUBTITLE"));
		BOOSTER_STARTED_MESSAGE = stringutil.replaceDefaults(configutil.getString("BOOSTER_STARTED_MESSAGE"));
		BOOSTER_EXPIRED_MESSAGE = stringutil.replaceDefaults(configutil.getString("BOOSTER_EXPIRED_MESSAGE"));
		BOOSTER_LIMIT_MESSAGE = stringutil.replaceDefaults(configutil.getString("BOOSTER_LIMIT_MESSAGE"));
		MILK = stringutil.replaceDefaults(configutil.getString("MILK"));
		BOOSTER_NOT_ACTIVE = stringutil.replaceDefaults(configutil.getString("BOOSTER_NOT_ACTIVE"));
		BOOSTER_ACTIVE = stringutil.replaceDefaults(configutil.getString("BOOSTER_ACTIVE"));
		BOOSTER_INFO_NOT_ACTIVE_BUYABLE = stringutil
				.replaceDefaults(configutil.getString("BOOSTER_INFO.NOT_ACTIVE.BUYABLE"));
		BOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE = stringutil
				.replaceDefaults(configutil.getString("BOOSTER_INFO.NOT_ACTIVE.NOT_BUYABLE"));
		BOOSTER_INFO_ACTIVE = stringutil.replaceDefaults(configutil.getString("BOOSTER_INFO.ACTIVE"));
		ANIMATION_CAULDRON = stringutil.replaceDefaults(configutil.getString("ANIMATION.CAULDRON"));
		ANIMATION_EMPTY_POTION = stringutil.replaceDefaults(configutil.getString("ANIMATION.EMPTY_POTION.1"));
		ANIMATION_EMPTY_POTION_1 = stringutil.replaceDefaults(configutil.getString("ANIMATION.EMPTY_POTION.2"));
		ANIMATION_EMPTY_POTION_2 = stringutil.replaceDefaults(configutil.getString("ANIMATION.EMPTY_POTION.3"));
		ANIMATION_FILLED_POTION = stringutil.replaceDefaults(configutil.getString("ANIMATION.FILLED_POTION.1"));
		ANIMATION_FILLED_POTION_1 = stringutil.replaceDefaults(configutil.getString("ANIMATION.FILLED_POTION.2"));
		ANIMATION_FILLED_POTION_2 = stringutil.replaceDefaults(configutil.getString("ANIMATION.FILLED_POTION.3"));
	}

	public String getPREFIX() {
		return PREFIX;
	}

	public String getNO_PERMISSION() {
		return NO_PERMISSION;
	}

	public String getONLY_PLAYER() {
		return ONLY_PLAYER;
	}

	public String getMAX_CPS() {
		return MAX_CPS;
	}

	public String getAFK() {
		return AFK;
	}

	public String getCLICK_MESSAGE(Long cookies, Long proclick) {
		return stringutil.replaceCookiesAndPerclick(CLICK_MESSAGE, cookies, proclick);
	}

	public String getBOOSTED_CLICK_MESSAGE(Long cookies, Long boosted_cookies, Long proclick, Long boosted_proclick) {
		return stringutil.replaceBoosted(stringutil.replaceCookiesAndPerclick(BOOSTED_CLICK_MESSAGE, cookies, proclick),
				boosted_cookies, boosted_proclick);
	}

	public String getPLAYER_STATS_INFO(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getPLAYER_STATS_INFO_1(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO_1, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getPLAYER_STATS_INFO_2(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO_2, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getPLAYER_STATS_INFO_3(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO_3, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getPLAYER_STATS_INFO_4(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO_4, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getPLAYER_STATS_INFO_5(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO_5, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getPLAYER_STATS_INFO_6(String name, int place, Long cookies, Long proclick, Long clickerclicks,
			Long milk) {
		return replaceStatsinfo(PLAYER_STATS_INFO_6, name, place, cookies, proclick, clickerclicks, milk);
	}

	public String getHOME_TITLE() {
		return HOME_TITLE;
	}

	public String getSHOP_TITLE() {
		return SHOP_TITLE;
	}

	public String getPREMIUM_SHOP_TITLE() {
		return PREMIUM_SHOP_TITLE;
	}

	public String getBOOSTER_TITLE() {
		return BOOSTER_TITLE;
	}

	public String getDESIGNS_TITLE() {
		return DESIGNS_TITLE;
	}

	public String getPER_CLICK(Long proclick) {
		return stringutil.replacePerClick(PER_CLICK, proclick);
	}

	public String getBOOSTER() {
		return BOOSTER;
	}

	public String getYOUR_COOKIES(Long cookies) {
		return stringutil.replaceCookies(YOUR_COOKIES, cookies);
	}

	public String getSHOP() {
		return SHOP;
	}

	public String getPREMIUM_PAGE() {
		return PREMIUM_PAGE;
	}

	public String getDESIGNS() {
		return DESIGNS;
	}

	public String getBACK() {
		return BACK;
	}

	public String getPRICE_BUYABLE(Long price) {
		return stringutil.replacePrice(PRICE_BUYABLE, price);
	}

	public String getPRICE_NOT_BUYABLE(Long price) {
		return stringutil.replacePrice(PRICE_NOT_BUYABLE, price);
	}

	public String getPRICE_BOOSTED_BUYABLE(Long price, Long boosted_price) {
		return stringutil.replaceBoosted_Price(stringutil.replacePrice(PRICE_BOOSTED_BUYABLE, price), boosted_price);
	}

	public String getPRICE_BOOSTED_NOT_BUYABLE(Long price, Long boosted_price) {
		return stringutil.replaceBoosted_Price(stringutil.replacePrice(PRICE_BOOSTED_NOT_BUYABLE, price),
				boosted_price);
	}

	public String getPRICE_INFO(int addclick) {
		return stringutil.replaceAddClick(PRICE_INFO, addclick);
	}

	public String getDESIGN_GLASS_NAME() {
		return DESIGN_GLASS_NAME;
	}

	public String getRED_GLASS() {
		return RED_GLASS;
	}

	public String getRED_GLASS_SELECTED() {
		return RED_GLASS_SELECTED;
	}

	public String getORANGE_GLASS() {
		return ORANGE_GLASS;
	}

	public String getORANGE_GLASS_SELECTED() {
		return ORANGE_GLASS_SELECTED;
	}

	public String getYELLOW_GLASS() {
		return YELLOW_GLASS;
	}

	public String getYELLOW_GLASS_SELECTED() {
		return YELLOW_GLASS_SELECTED;
	}

	public String getPINK_GLASS() {
		return PINK_GLASS;
	}

	public String getPINK_GLASS_SELECTED() {
		return PINK_GLASS_SELECTED;
	}

	public String getMAGENTA_GLASS() {
		return MAGENTA_GLASS;
	}

	public String getMAGENTA_GLASS_SELECTED() {
		return MAGENTA_GLASS_SELECTED;
	}

	public String getPURPLE_GLASS() {
		return PURPLE_GLASS;
	}

	public String getPURPLE_GLASS_SELECTED() {
		return PURPLE_GLASS_SELECTED;
	}

	public String getBROWN_GLASS() {
		return BROWN_GLASS;
	}

	public String getBROWN_GLASS_SELECTED() {
		return BROWN_GLASS_SELECTED;
	}

	public String getLIME_GLASS() {
		return LIME_GLASS;
	}

	public String getLIME_GLASS_SELECTED() {
		return LIME_GLASS_SELECTED;
	}

	public String getGREEN_GLASS() {
		return GREEN_GLASS;
	}

	public String getGREEN_GLASS_SELECTED() {
		return GREEN_GLASS_SELECTED;
	}

	public String getCYAN_GLASS() {
		return CYAN_GLASS;
	}

	public String getCYAN_GLASS_SELECTED() {
		return CYAN_GLASS_SELECTED;
	}

	public String getLIGHT_BLUE_GLASS() {
		return LIGHT_BLUE_GLASS;
	}

	public String getLIGHT_BLUE_GLASS_SELECTED() {
		return LIGHT_BLUE_GLASS_SELECTED;
	}

	public String getBLUE_GLASS() {
		return BLUE_GLASS;
	}

	public String getBLUE_GLASS_SELECTED() {
		return BLUE_GLASS_SELECTED;
	}

	public String getWHITE_GLASS() {
		return WHITE_GLASS;
	}

	public String getWHITE_GLASS_SELECTED() {
		return WHITE_GLASS_SELECTED;
	}

	public String getLIGHT_GRAY_GLASS() {
		return LIGHT_GRAY_GLASS;
	}

	public String getLIGHT_GRAY_GLASS_SELECTED() {
		return LIGHT_GRAY_GLASS_SELECTED;
	}

	public String getGRAY_GLASS() {
		return GRAY_GLASS;
	}

	public String getGRAY_GLASS_SELECTED() {
		return GRAY_GLASS_SELECTED;
	}

	public String getBLACK_GLASS() {
		return BLACK_GLASS;
	}

	public String getBLACK_GLASS_SELECTED() {
		return BLACK_GLASS_SELECTED;
	}

	public String getMILK_ALERT_TITLE() {
		return MILK_ALERT_TITLE;
	}

	public String getMILK_ALERT_SUBTITLE() {
		return MILK_ALERT_SUBTITLE;
	}

	public String getBOOSTER_STARTED_MESSAGE(String booster) {
		return stringutil.replaceBooster(BOOSTER_STARTED_MESSAGE, booster);
	}

	public String getBOOSTER_EXPIRED_MESSAGE(String booster) {
		return stringutil.replaceBooster(BOOSTER_EXPIRED_MESSAGE, booster);
	}
	
	public String getBOOSTER_LIMIT_MESSAGE() {
		return BOOSTER_LIMIT_MESSAGE;
	}

	public String getMILK(Long milk) {
		return stringutil.replaceMilk(MILK, milk);
	}

	public String getBOOSTER_NOT_ACTIVE(String booster) {
		return stringutil.replaceBooster(BOOSTER_NOT_ACTIVE, booster);
	}

	public String getBOOSTER_ACTIVE(String booster) {
		return stringutil.replaceBooster(BOOSTER_ACTIVE, booster);
	}

	public String getBOOSTER_INFO_NOT_ACTIVE_BUYABLE(Long price) {
		return stringutil.replacePrice(BOOSTER_INFO_NOT_ACTIVE_BUYABLE, price);
	}

	public String getBOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE(Long price) {
		return stringutil.replacePrice(BOOSTER_INFO_NOT_ACTIVE_NOT_BUYABLE, price);
	}

	public String getBOOSTER_INFO_ACTIVE(String time) {
		return stringutil.replaceTime(BOOSTER_INFO_ACTIVE, time);
	}

	public String getANIMATION_CAULDRON() {
		return ANIMATION_CAULDRON;
	}

	public String getANIMATION_EMPTY_POTION() {
		return ANIMATION_EMPTY_POTION;
	}

	public String getANIMATION_EMPTY_POTION_1() {
		return ANIMATION_EMPTY_POTION_1;
	}

	public String getANIMATION_EMPTY_POTION_2() {
		return ANIMATION_EMPTY_POTION_2;
	}

	public String getANIMATION_FILLED_POTION() {
		return ANIMATION_FILLED_POTION;
	}

	public String getANIMATION_FILLED_POTION_1() {
		return ANIMATION_FILLED_POTION_1;
	}

	public String getANIMATION_FILLED_POTION_2() {
		return ANIMATION_FILLED_POTION_2;
	}

	private String replaceStatsinfo(String message, String name, int place, Long cookies, Long proclick,
			Long clickerclicks, Long milk) {
		message = stringutil.replaceName(message, name);
		message = stringutil.replacePlace(message, place);
		message = stringutil.replaceCookiesAndPerclick(message, cookies, proclick);
		message = stringutil.replaceClickerClicks(message, clickerclicks);
		message = stringutil.replaceMilk(message, milk);
		return message;
	}
}
