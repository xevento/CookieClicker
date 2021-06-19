package de.zillolp.cookieclicker.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	private final DecimalFormat twoDigits = new DecimalFormat("0,000");
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd:HH:mm:ss");
	private final SimpleDateFormat format = new SimpleDateFormat("mm:ss");

	public String replaceDefaults(String message) {
		if (message.contains("%Ae%")) {
			message = message.replace("%Ae%", "Ä");
		}
		if (message.contains("%ae%")) {
			message = message.replace("%ae%", "ä");
		}
		if (message.contains("%Oe%")) {
			message = message.replace("%Oe%", "Ö");
		}
		if (message.contains("%oe%")) {
			message = message.replace("%oe%", "ö");
		}
		if (message.contains("%Ue%")) {
			message = message.replace("%Ue%", "Ü");
		}
		if (message.contains("%ue%")) {
			message = message.replace("%ue%", "ü");
		}
		if (message.contains("%sz%")) {
			message = message.replace("%sz%", "ß");
		}
		if (message.contains("%>%")) {
			message = message.replace("%>%", "»");
		}
		if (message.contains("%<%")) {
			message = message.replace("%<%", "«");
		}
		if (message.contains("%*%")) {
			message = message.replace("%*%", "×");
		}
		if (message.contains("%->%")) {
			message = message.replace("%->%", "➜");
		}
		if (message.contains("&")) {
			message = message.replace("&", "§");
		}
		return message;
	}

	public String formatNumber(Long number) {
		if (number < 1000) {
			return String.valueOf(number);
		} else {
			return String.valueOf(twoDigits.format(number));
		}
	}

	public String formatDate(Long date) {
		return formatter.format(date);
	}

	public String formatTime(Long time) {
		return format.format(time);
	}

	public Long parseDate(String date) {
		Long time = new Date().getTime();
		try {
			time = formatter.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public boolean isNumeric(String value, int max) {
		try {
			int number = Integer.parseInt(value);
			if (number <= 0) {
				return false;
			}
			return number <= max;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String replaceCookies(String message, Long cookies) {
		if (message.contains("%cookies%")) {
			if (cookies < 1000) {
				message = message.replace("%cookies%", String.valueOf(cookies));
			} else {
				message = message.replace("%cookies%", String.valueOf(twoDigits.format(cookies)));
			}
		}
		return message;
	}

	public String replaceBoosted_Cookies(String message, Long boosted_cookies) {
		if (message.contains("%boosted_cookies%")) {
			if (boosted_cookies < 1000) {
				message = message.replace("%boosted_cookies%", String.valueOf(boosted_cookies));
			} else {
				message = message.replace("%boosted_cookies%", String.valueOf(twoDigits.format(boosted_cookies)));
			}
		}
		return message;
	}

	public String replacePerClick(String message, Long proclick) {
		if (message.contains("%perclick%")) {
			if (proclick < 1000) {
				message = message.replace("%perclick%", String.valueOf(proclick));
			} else {
				message = message.replace("%perclick%", String.valueOf(twoDigits.format(proclick)));
			}
		}
		return message;
	}

	public String replaceBoosted_PerClick(String message, Long boosted_perclick) {
		if (message.contains("%boosted_perclick%")) {
			if (boosted_perclick < 1000) {
				message = message.replace("%boosted_perclick%", String.valueOf(boosted_perclick));
			} else {
				message = message.replace("%boosted_perclick%", String.valueOf(twoDigits.format(boosted_perclick)));
			}
		}
		return message;
	}

	public String replaceClickerClicks(String message, Long clickerclicks) {
		if (clickerclicks < 1000) {
			message = message.replace("%clickerclicks%", String.valueOf(clickerclicks));
		} else {
			message = message.replace("%clickerclicks%", String.valueOf(twoDigits.format(clickerclicks)));
		}
		return message;
	}

	public String replaceCookiesAndPerclick(String message, Long cookies, Long proclick) {
		return replaceCookies(replacePerClick(message, proclick), cookies);
	}

	public String replaceBoosted(String message, Long boosted_cookies, Long boosted_proclick) {
		return replaceBoosted_Cookies(replaceBoosted_PerClick(message, boosted_proclick), boosted_cookies);
	}

	public String replaceAddClick(String message, int addclick) {
		if (message.contains("%addclick%")) {
			message = message.replace("%addclick%", String.valueOf(addclick));
		}
		return message;
	}

	public String replacePrice(String message, Long price) {
		if (message.contains("%price%")) {
			if (price < 1000) {
				message = message.replace("%price%", String.valueOf(price));
			} else {
				message = message.replace("%price%", String.valueOf(twoDigits.format(price)));
			}
		}
		return message;
	}

	public String replaceBoosted_Price(String message, Long boosted_price) {
		if (message.contains("%boosted_price%")) {
			if (boosted_price < 1000) {
				message = message.replace("%boosted_price%", String.valueOf(boosted_price));
			} else {
				message = message.replace("%boosted_price%", String.valueOf(twoDigits.format(boosted_price)));
			}
		}
		return message;
	}

	public String replaceName(String message, String name) {
		if (message.contains("%name%")) {
			message = message.replace("%name%", name);
		}
		return message;
	}

	public String replacePlace(String message, int place) {
		if (message.contains("%place%")) {
			if (place < 1000) {
				message = message.replace("%place%", String.valueOf(place));
			} else {
				message = message.replace("%place%", String.valueOf(twoDigits.format(place)));
			}
		}
		return message;
	}

	public String replaceTime(String message, String time) {
		if (message.contains("%time%")) {
			message = message.replace("%time%", time);
		}
		return message;
	}

	public String replaceBooster(String message, String booster) {
		if (message.contains("%booster%")) {
			message = message.replace("%booster%", booster);
		}
		return message;
	}

	public String replaceMilk(String message, Long milk) {
		if (milk < 1000) {
			message = message.replace("%milk%", String.valueOf(milk));
		} else {
			message = message.replace("%milk%", String.valueOf(twoDigits.format(milk)));
		}
		return message;
	}
}
