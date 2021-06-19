package de.zillolp.cookieclicker.profiles;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.DatenManager;

public class ShopProfil {
	private Main plugin = Main.plugin;
	private HashMap<Player, BoosterProfil> boosterprofiles = plugin.boosterprofiles;
	private HashMap<String, Long> gekauft = plugin.gekauft;
	private DatenManager datenmanager = plugin.datenmanager;
	private final String uuid;
	private final Player p;
	private Long Preis;
	private Long Preis1;
	private Long Preis2;
	private Long Preis3;
	private Long Preis4;
	private Long Preis5;
	private Long Preis6;
	private Long Preis7;
	private Long Preis8;
	private Long Preis9;
	private Long Preis10;
	private Long Preis11;
	private Long Preis12;
	private Long Preis13;

	public ShopProfil(String uuid) {
		this.uuid = uuid;
		this.p = Bukkit.getPlayer(UUID.fromString(uuid));
		for (int i = 0; i < 14; i++) {
			setPreis(datenmanager.getPreis(uuid, i), i);
		}
		if (!(gekauft.containsKey(uuid))) {
			gekauft.put(uuid, 0L);
		}
	}

	public Long getPreis(int nummer) {
		BoosterProfil profil = boosterprofiles.get(p);
		switch (nummer) {
		case 0:
			if (!profil.IsBooster_2()) {
				return Preis / 2;
			} else {
				return Preis;
			}
		case 1:
			if (!profil.IsBooster_2()) {
				return Preis1 / 2;
			} else {
				return Preis1;
			}
		case 2:
			if (!profil.IsBooster_2()) {
				return Preis2 / 2;
			} else {
				return Preis2;
			}
		case 3:
			if (!profil.IsBooster_2()) {
				return Preis3 / 2;
			} else {
				return Preis3;
			}
		case 4:
			if (!profil.IsBooster_2()) {
				return Preis4 / 2;
			} else {
				return Preis4;
			}
		case 5:
			if (!profil.IsBooster_2()) {
				return Preis5 / 2;
			} else {
				return Preis5;
			}
		case 6:
			if (!profil.IsBooster_2()) {
				return Preis6 / 2;
			} else {
				return Preis6;
			}
		case 7:
			if (!profil.IsBooster_2()) {
				return Preis7 / 2;
			} else {
				return Preis7;
			}
		case 8:
			if (!profil.IsBooster_2()) {
				return Preis8 / 2;
			} else {
				return Preis8;
			}
		case 9:
			if (!profil.IsBooster_2()) {
				return Preis9 / 2;
			} else {
				return Preis9;
			}
		case 10:
			if (!profil.IsBooster_2()) {
				return Preis10 / 2;
			} else {
				return Preis10;
			}
		case 11:
			if (!profil.IsBooster_2()) {
				return Preis11 / 2;
			} else {
				return Preis11;
			}
		case 12:
			if (!profil.IsBooster_2()) {
				return Preis12 / 2;
			} else {
				return Preis12;
			}
		case 13:
			if (!profil.IsBooster_2()) {
				return Preis13 / 2;
			} else {
				return Preis13;
			}
		}
		return Preis;
	}

	public Long getGekauft() {
		return gekauft.get(uuid);
	}

	public HashMap<String, Long> getGekauft_Map() {
		return gekauft;
	}

	public void setPreis(Long preis, int nummer) {
		switch (nummer) {
		case 0:
			Preis = preis;
			break;
		case 1:
			Preis1 = preis;
			break;
		case 2:
			Preis2 = preis;
			break;
		case 3:
			Preis3 = preis;
			break;
		case 4:
			Preis4 = preis;
			break;
		case 5:
			Preis5 = preis;
			break;
		case 6:
			Preis6 = preis;
			break;
		case 7:
			Preis7 = preis;
			break;
		case 8:
			Preis8 = preis;
			break;
		case 9:
			Preis9 = preis;
			break;
		case 10:
			Preis10 = preis;
			break;
		case 11:
			Preis11 = preis;
			break;
		case 12:
			Preis12 = preis;
			break;
		case 13:
			Preis13 = preis;
			break;
		}
	}

	public void addPreis(Long preis, int nummer) {
		switch (nummer) {
		case 0:
			Preis = Preis + preis;
			break;
		case 1:
			Preis1 = Preis1 + preis;
			break;
		case 2:
			Preis2 = Preis2 + preis;
			break;
		case 3:
			Preis3 = Preis3 + preis;
			break;
		case 4:
			Preis4 = Preis4 + preis;
			break;
		case 5:
			Preis5 = Preis5 + preis;
			break;
		case 6:
			Preis6 = Preis6 + preis;
			break;
		case 7:
			Preis7 = Preis7 + preis;
			break;
		case 8:
			Preis8 = Preis8 + preis;
			break;
		case 9:
			Preis9 = Preis9 + preis;
			break;
		case 10:
			Preis10 = Preis10 + preis;
			break;
		case 11:
			Preis11 = Preis11 + preis;
			break;
		case 12:
			Preis12 = Preis12 + preis;
			break;
		case 13:
			Preis13 = Preis13 + preis;
			break;
		}
	}

	public void addGekauft(Long proclick) {
		gekauft.replace(uuid, getGekauft() + proclick);
	}

	public void removeGekauft() {
		gekauft.replace(uuid, 0L);
	}

	public void UploadMySQL() {
		for (int i = 0; i < 14; i++) {
			datenmanager.setPreis(uuid, i, getPreis(i));
		}
	}
}
