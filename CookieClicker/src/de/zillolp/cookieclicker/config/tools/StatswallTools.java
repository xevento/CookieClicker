package de.zillolp.cookieclicker.config.tools;

import java.io.File;
import java.text.DecimalFormat;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.ConfigUtil;
import de.zillolp.cookieclicker.utils.StringUtil;

public class StatswallTools {
	private Main plugin = Main.plugin;
	private final DecimalFormat twoDigits = new DecimalFormat("0,000");
	private ConfigUtil configutil;
	private StringUtil stringutil = plugin.stringutil;
	private int Refresh;
	private int Resettime;
	private String ALLTIME_RANK_PREFIX_1;
	private String ALLTIME_RANK_PREFIX_2;
	private String ALLTIME_RANK_PREFIX_3;
	private String ALLTIME_RANK_PREFIX_4;
	private String ALLTIME_RANK_PREFIX_5;
	private String ALLTIME_SIGN_1;
	private String ALLTIME_SIGN_2;
	private String ALLTIME_SIGN_3;
	private String ALLTIME_SIGN_4;
	private String TIME_RANK_PREFIX_1;
	private String TIME_RANK_PREFIX_2;
	private String TIME_RANK_PREFIX_3;
	private String TIME_SIGN_1;
	private String TIME_SIGN_2;
	private String TIME_SIGN_3;
	private String TIME_SIGN_4;
	private String Ranktype;

	public StatswallTools() {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/statswall.yml"));
		Refresh = configutil.getInt("Refresh");
		Resettime = configutil.getInt("Resettime");
		ALLTIME_RANK_PREFIX_1 = stringutil.replaceDefaults(configutil.getString("ALLTIME.RANK_PREFIX.1"));
		ALLTIME_RANK_PREFIX_2 = stringutil.replaceDefaults(configutil.getString("ALLTIME.RANK_PREFIX.2"));
		ALLTIME_RANK_PREFIX_3 = stringutil.replaceDefaults(configutil.getString("ALLTIME.RANK_PREFIX.3"));
		ALLTIME_RANK_PREFIX_4 = stringutil.replaceDefaults(configutil.getString("ALLTIME.RANK_PREFIX.4"));
		ALLTIME_RANK_PREFIX_5 = stringutil.replaceDefaults(configutil.getString("ALLTIME.RANK_PREFIX.5"));
		ALLTIME_SIGN_1 = stringutil.replaceDefaults(configutil.getString("ALLTIME.SIGN.1"));
		ALLTIME_SIGN_2 = stringutil.replaceDefaults(configutil.getString("ALLTIME.SIGN.2"));
		ALLTIME_SIGN_3 = stringutil.replaceDefaults(configutil.getString("ALLTIME.SIGN.3"));
		ALLTIME_SIGN_4 = stringutil.replaceDefaults(configutil.getString("ALLTIME.SIGN.4"));
		TIME_RANK_PREFIX_1 = stringutil.replaceDefaults(configutil.getString("TIME.RANK_PREFIX.1"));
		TIME_RANK_PREFIX_2 = stringutil.replaceDefaults(configutil.getString("TIME.RANK_PREFIX.2"));
		TIME_RANK_PREFIX_3 = stringutil.replaceDefaults(configutil.getString("TIME.RANK_PREFIX.3"));
		TIME_SIGN_1 = stringutil.replaceDefaults(configutil.getString("TIME.SIGN.1"));
		TIME_SIGN_2 = stringutil.replaceDefaults(configutil.getString("TIME.SIGN.2"));
		TIME_SIGN_3 = stringutil.replaceDefaults(configutil.getString("TIME.SIGN.3"));
		TIME_SIGN_4 = stringutil.replaceDefaults(configutil.getString("TIME.SIGN.4"));
		Ranktype = configutil.getString("Alltime-Ranktype");
	}

	public int getRefresh() {
		return Refresh * 20;
	}

	public int getResettime() {
		return Resettime;
	}

	public String getALLTIME_RANK_PREFIX_1() {
		return ALLTIME_RANK_PREFIX_1;
	}

	public String getALLTIME_RANK_PREFIX_2() {
		return ALLTIME_RANK_PREFIX_2;
	}

	public String getALLTIME_RANK_PREFIX_3() {
		return ALLTIME_RANK_PREFIX_3;
	}

	public String getALLTIME_RANK_PREFIX_4() {
		return ALLTIME_RANK_PREFIX_4;
	}

	public String getALLTIME_RANK_PREFIX_5() {
		return ALLTIME_RANK_PREFIX_5;
	}

	public String getALLTIME_SIGN_1(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(ALLTIME_SIGN_1, name, place, cookies, proclick, clickerclicks);
	}

	public String getALLTIME_SIGN_2(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(ALLTIME_SIGN_2, name, place, cookies, proclick, clickerclicks);
	}

	public String getALLTIME_SIGN_3(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(ALLTIME_SIGN_3, name, place, cookies, proclick, clickerclicks);
	}

	public String getALLTIME_SIGN_4(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(ALLTIME_SIGN_4, name, place, cookies, proclick, clickerclicks);
	}

	public String getTIME_RANK_PREFIX_1() {
		return TIME_RANK_PREFIX_1;
	}

	public String getTIME_RANK_PREFIX_2() {
		return TIME_RANK_PREFIX_2;
	}

	public String getTIME_RANK_PREFIX_3() {
		return TIME_RANK_PREFIX_3;
	}

	public String getTIME_SIGN_1(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(TIME_SIGN_1, name, place, cookies, proclick, clickerclicks);
	}

	public String getTIME_SIGN_2(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(TIME_SIGN_2, name, place, cookies, proclick, clickerclicks);
	}

	public String getTIME_SIGN_3(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(TIME_SIGN_3, name, place, cookies, proclick, clickerclicks);
	}

	public String getTIME_SIGN_4(String name, String place, Long cookies, Long proclick, Long clickerclicks) {
		return replaceStatssign(TIME_SIGN_4, name, place, cookies, proclick, clickerclicks);
	}

	public String getRanktype() {
		return Ranktype;
	}

	private String replaceStatssign(String message, String name, String place, Long cookies, Long proclick,
			Long clickerclicks) {
		message = stringutil.replaceName(message, name);
		if (message.contains("%place%")) {
			message = message.replace("%place%", place);
		}
		if (message.contains("%cookies%")) {
			if (cookies <= 0) {
				message = message.replace("%cookies%", "?");
			} else if (cookies < 1000) {
				message = message.replace("%cookies%", String.valueOf(cookies));
			} else {
				message = message.replace("%cookies%", String.valueOf(twoDigits.format(cookies)));
			}
		}
		if (message.contains("%perclick%")) {
			if (proclick <= 0) {
				message = message.replace("%perclick%", "?");
			} else if (proclick < 1000) {
				message = message.replace("%perclick%", String.valueOf(proclick));
			} else {
				message = message.replace("%perclick%", String.valueOf(twoDigits.format(proclick)));
			}
		}
		if (message.contains("%clickerclicks%")) {
			if (clickerclicks <= 0) {
				message = message.replace("%clickerclicks%", "?");
			} else if (clickerclicks < 1000) {
				message = message.replace("%clickerclicks%", String.valueOf(clickerclicks));
			} else {
				message = message.replace("%clickerclicks%", String.valueOf(twoDigits.format(clickerclicks)));
			}
		}
		return message;
	}
}
