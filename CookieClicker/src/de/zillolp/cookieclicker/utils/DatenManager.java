package de.zillolp.cookieclicker.utils;

import java.sql.ResultSet;

import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.mysql.MySQL;
import de.zillolp.cookieclicker.mysql.SQLStats;
import de.zillolp.cookieclicker.sqlite.SQLite;
import de.zillolp.cookieclicker.sqlite.SQStats;

public class DatenManager {
	private Main plugin = Main.plugin;
	private boolean status;
	private boolean connected;
	private MySQL mysql;
	private SQLite sqlite;
	private SQLStats sqlstats;
	private SQStats sqstats;

	public DatenManager() {
		ConfigTools configtools = plugin.configtools;
		status = configtools.getMySQL();
		if (status) {
			mysql = new MySQL();
			mysql.load();
			connected = mysql.connected;
		} else {
			connected = true;
			sqlite = new SQLite();
			sqlite.connect();
		}
	}

	public void load() {
		if (status) {
			if (connected) {
				sqlstats = new SQLStats();
			}
		} else {
			sqstats = new SQStats();
		}
	}

	public boolean getStatus() {
		return status;
	}

	public boolean getConnected() {
		return connected;
	}

	public MySQL getMysql() {
		return mysql;
	}

	public SQLite getSqlite() {
		return sqlite;
	}

	public void createPlayer(Player p) {
		if (status) {
			sqlstats.createPlayer(p);
		} else {
			sqstats.createPlayer(p);
		}
	}

	public boolean inPlayers(String uuid) {
		boolean in_players = false;
		if (status) {
			in_players = sqlstats.inPlayers(uuid);
		} else {
			in_players = sqstats.inPlayers(uuid);
		}
		return in_players;
	}

	public String getName(String uuid) {
		String i = "";

		if (status) {
			i = sqlstats.getName(uuid);
		} else {
			i = sqstats.getName(uuid);
		}
		return i;
	}

	public Long getCookies(String uuid) {
		Long i = 0L;
		if (status) {
			i = sqlstats.getCookies(uuid);
		} else {
			i = sqstats.getCookies(uuid);
		}
		return i;
	}

	public Long getProclick(String uuid) {
		Long i = 0L;
		if (status) {
			i = sqlstats.getProclick(uuid);
		} else {
			i = sqstats.getProclick(uuid);
		}
		return i;
	}

	public Long getDesign(String uuid) {
		Long i = 0L;
		if (status) {
			i = sqlstats.getDesign(uuid);
		} else {
			i = sqstats.getDesign(uuid);
		}
		return i;
	}

	public Long getClickerclicks(String uuid) {
		Long i = 0L;
		if (status) {
			i = sqlstats.getClickerclicks(uuid);
		} else {
			i = sqstats.getClickerclicks(uuid);
		}
		return i;
	}

	public Long getMilk(String uuid) {
		Long i = 0L;
		if (status) {
			i = sqlstats.getMilk(uuid);
		} else {
			i = sqstats.getMilk(uuid);
		}
		return i;
	}

	public Long getPreis(String uuid, Integer zahl) {
		Long i = 0L;
		if (status) {
			i = sqlstats.getPreis(uuid, zahl);
		} else {
			i = sqstats.getPreis(uuid, zahl);
		}
		return i;
	}

	public String getBooster(String uuid, Integer zahl) {
		String i = "";

		if (status) {
			i = sqlstats.getBooster(uuid, zahl);
		} else {
			i = sqstats.getBooster(uuid, zahl);
		}
		return i;
	}

	public void setCookies(String uuid, Long cookies) {
		if (status) {
			sqlstats.setCookies(uuid, cookies);
		} else {
			sqstats.setCookies(uuid, cookies);
		}
	}

	public void setProclick(String uuid, Long proclick) {
		if (status) {
			sqlstats.setProclick(uuid, proclick);
		} else {
			sqstats.setProclick(uuid, proclick);
		}
	}

	public void setDesign(String uuid, Long design) {
		if (status) {
			sqlstats.setDesign(uuid, design);
		} else {
			sqstats.setDesign(uuid, design);
		}
	}

	public void setClickerclicks(String uuid, Long clicks) {
		if (status) {
			sqlstats.setClickerclicks(uuid, clicks);
		} else {
			sqstats.setClickerclicks(uuid, clicks);
		}
	}

	public void setMilk(String uuid, Long milk) {
		if (status) {
			sqlstats.setMilk(uuid, milk);
		} else {
			sqstats.setMilk(uuid, milk);
		}
	}

	public void setPreis(String uuid, Integer zahl, Long preis) {
		if (status) {
			sqlstats.setPreis(uuid, zahl, preis);
		} else {
			sqstats.setPreis(uuid, zahl, preis);
		}
	}
	
	public void setBooster(String uuid, Integer zahl, String date) {
		if (status) {
			sqlstats.setBooster(uuid, zahl, date);
		} else {
			sqstats.setBooster(uuid, zahl, date);
		}
	}

	public ResultSet OrderBy(String type, int limit) {
		if (status) {
			return sqlstats.OrderBy(type, limit);
		} else {
			return sqstats.OrderBy(type, limit);
		}
	}

	public ResultSet OrderBy(String type) {
		if (status) {
			return sqlstats.OrderBy(type);
		} else {
			return sqstats.OrderBy(type);
		}
	}
}
