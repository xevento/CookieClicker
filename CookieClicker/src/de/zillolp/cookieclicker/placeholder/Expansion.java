package de.zillolp.cookieclicker.placeholder;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.OfflinePlayer;

import de.zillolp.cookieclicker.config.tools.StatswallTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.utils.DatenManager;
import de.zillolp.cookieclicker.utils.StringUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Expansion extends PlaceholderExpansion {
	private Main plugin = Main.plugin;
	private DatenManager datenmanager = plugin.datenmanager;
	private StringUtil stringutil = plugin.stringutil;

	@Override
	public boolean canRegister() {
		return true;
	}

	@Override
	public String getAuthor() {
		return "ZilloLP";
	}

	@Override
	public String getIdentifier() {
		return "cookieclicker";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String onRequest(OfflinePlayer p, String identifier) {
		PlayerProfil profil = plugin.playerprofiles.get(p);
		BoosterProfil booster = plugin.boosterprofiles.get(p);

		if (profil != null) {
			Long cookies = profil.getCookies();
			Long clickerclicks = profil.getClickerclicks();
			Long proclick = profil.getProclick();
			Long milk = booster.getMilk();

			// %cookieclicker_cookies%
			if (identifier.equals("cookies")) {
				return stringutil.formatNumber(cookies);
			}

			// %cookieclicker_perclick%
			if (identifier.equals("perclick")) {
				return stringutil.formatNumber(proclick);
			}

			// %cookieclicker_clickerclicks%
			if (identifier.equals("clickerclicks")) {
				return stringutil.formatNumber(clickerclicks);
			}

			// %cookieclicker_milk%
			if (identifier.equals("milk")) {
				return stringutil.formatNumber(milk);
			}

			// %cookieclicker_place%
			if (identifier.equals("place")) {
				StatswallTools statswalltools = plugin.statswalltools;
				String uuid = p.getUniqueId().toString();
				String ranktype = statswalltools.getRanktype();
				if (ranktype.equalsIgnoreCase("Cookies")) {
					datenmanager.setCookies(uuid, cookies);
				} else if (ranktype.equalsIgnoreCase("Clickerclicks")) {
					datenmanager.setClickerclicks(uuid, proclick);
				} else {
					datenmanager.setProclick(uuid, clickerclicks);
				}

				ResultSet rs;
				if (ranktype.equalsIgnoreCase("Cookies")) {
					rs = datenmanager.OrderBy("Cookies");
				} else if (ranktype.equalsIgnoreCase("Clickerclicks")) {
					rs = datenmanager.OrderBy("Clickerclicks");
				} else {
					rs = datenmanager.OrderBy("Proclick");
				}

				Long place = 0L;
				try {
					while (rs.next()) {
						place++;
						if (rs.getString("UUID").equalsIgnoreCase(uuid)) {
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return stringutil.formatNumber(place);
			}
		} else {
			System.err.println("The player profil is null. Contact the CookieClicker developer.");
		}

		return null;
	}
}
