package de.zillolp.cookieclicker.profiles;

import java.util.HashMap;

import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.DatenManager;

public class PlayerProfil {
	private Main plugin = Main.plugin;
	private DatenManager datenmanager = plugin.datenmanager;
	private HashMap<Player, InventoryProfil> invprofiles = plugin.invprofiles;
	private HashMap<Player, ShopProfil> shops = plugin.shopprofiles;
	private HashMap<Player, BoosterProfil> booster = plugin.boosterprofiles;
	private final Player p;
	private final String uuid;
	private int ClickerNummer;
	private int AlltimeNummer;
	private int TimeNummer;
	private Long Cookies;
	private Long Proclick;
	private Long Clickerclicks;
	private Long Design;
	private Long LastMove;
	private int CPS;
	private boolean Over_CPS;
	private Long LastStats;

	public PlayerProfil(Player p) {
		this.p = p;
		this.uuid = p.getUniqueId().toString();
		this.ClickerNummer = 0;
		this.AlltimeNummer = 0;
		this.TimeNummer = 0;
		this.Cookies = datenmanager.getCookies(uuid);
		this.Proclick = datenmanager.getProclick(uuid);
		this.Clickerclicks = datenmanager.getClickerclicks(uuid);
		this.Design = datenmanager.getDesign(uuid);
		this.LastMove = System.currentTimeMillis();
		this.CPS = 0;
		this.Over_CPS = false;
		this.LastStats = System.currentTimeMillis();
		if (!(invprofiles.containsKey(p))) {
			invprofiles.put(p, new InventoryProfil());
		}
		if (!(shops.containsKey(p))) {
			shops.put(p, new ShopProfil(uuid));
		}
		if (!(booster.containsKey(p))) {
			booster.put(p, new BoosterProfil(uuid));
		}
	}

	public int getClickerNummer() {
		return ClickerNummer;
	}

	public int getAlltimeNummer() {
		return AlltimeNummer;
	}

	public int getTimeNummer() {
		return TimeNummer;
	}

	public Long getCookies() {
		return Cookies;
	}

	public Long getProclick() {
		return Proclick;
	}

	public Long getClickerclicks() {
		return Clickerclicks;
	}

	public Long getDesign() {
		return Design;
	}

	public Long getLastMove() {
		return LastMove;
	}

	public int getCPS() {
		return CPS;
	}

	public boolean getOver_CPS() {
		return Over_CPS;
	}

	public Long getLastStats() {
		return LastStats;
	}

	public void setClickerNummer(int clickerNummer) {
		ClickerNummer = clickerNummer;
	}

	public void setAlltimeNummer(int alltimeNummer) {
		AlltimeNummer = alltimeNummer;
	}

	public void setTimeNummer(int timeNummer) {
		TimeNummer = timeNummer;
	}

	public void setCookies(Long cookies) {
		Cookies = cookies;
	}

	public void setProclick(Long perClick) {
		Proclick = perClick;
	}

	public void setClickerclicks(Long clickerclicks) {
		Clickerclicks = clickerclicks;
	}

	public void setDesign(Long design) {
		Design = design;
	}

	public void setLastMove(Long lastMove) {
		LastMove = lastMove;
	}

	public void setCPS(int clicks) {
		CPS = clicks;
	}

	public void setOver_CPS(boolean over_CPS) {
		Over_CPS = over_CPS;
	}

	public void setLastStats(Long lastStats) {
		LastStats = lastStats;
	}

	public void addCookies(Long cookies) {
		Cookies = Cookies + cookies;
	}

	public void addProclick(Long proclick) {
		Proclick = Proclick + proclick;
	}

	public void addClickerclicks(Long clicks) {
		Clickerclicks = Clickerclicks + clicks;
	}

	public void addCPS(int clicks) {
		CPS = CPS + clicks;
	}

	public void removeCookies(Long cookies) {
		Cookies = Cookies - cookies;
	}

	public void removePerclick(Long proclick) {
		Proclick = Proclick - proclick;
	}

	public void removeCPS(int clicks) {
		if (CPS - clicks >= 0) {
			CPS = CPS - clicks;
		} else {
			CPS = 0;
		}
	}

	public void reloadProfil() {
		if (invprofiles.containsKey(p)) {
			invprofiles.get(p).reloadInventorys();
		}
	}

	public void UploadStats() {
		if (booster.containsKey(p)) {
			booster.get(p).UploadMySQL();
		}
		datenmanager.setCookies(uuid, getCookies());
		datenmanager.setProclick(uuid, getProclick());
		datenmanager.setClickerclicks(uuid, getClickerclicks());
		datenmanager.setDesign(uuid, getDesign());
		if (invprofiles.containsKey(p)) {
			invprofiles.remove(p);
		}
		if (shops.containsKey(p)) {
			shops.get(p).UploadMySQL();
			shops.remove(p);
		}
		if (booster.containsKey(p)) {
			booster.remove(p);
		}
	}
}
