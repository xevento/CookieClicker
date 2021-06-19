package de.zillolp.cookieclicker.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.main.Main;

public class SQLStats {
	private MySQL mysql = Main.plugin.datenmanager.getMysql();
	private final String table = "cookieclicker_players";

	public boolean inPlayers(String uuid) {
		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");

			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void checkName(Player p) {
		String uuid = p.getUniqueId().toString();
		String name = p.getName();
		if (!(getName(uuid).equalsIgnoreCase(name))) {
			setName(uuid, name);
		}
	}

	public void createPlayer(Player p) {
		String uuid = p.getUniqueId().toString();
		if (!(inPlayers(uuid))) {
			String status = "NONE";
			mysql.update("INSERT INTO " + table
					+ "(UUID, NAME, COOKIES, PROCLICK, DESIGN, CLICKERCLICKS, MILK, PREIS, PREIS1, PREIS2, PREIS3, PREIS4, PREIS5, PREIS6, PREIS7, PREIS8, PREIS9, PREIS10, PREIS11, PREIS12, PREIS13, BOOSTER, BOOSTER1, BOOSTER2) VALUES ('"
					+ uuid + "', '" + p.getName()
					+ "', '1', '1', '0', '0', '0', '30', '360', '690', '920', '1250', '1580', '1910', '2240', '2570', '2900', '3230', '3560', '3890', '4220', '"
					+ status + "', '" + status + "', '" + status + "');");
		} else {
			checkName(p);
		}
	}

	public String getName(String uuid) {
		String i = "";

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");
			if (rs.next() && rs.getString("NAME") == null) {
				return i;
			}
			i = rs.getString("NAME");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Long getCookies(String uuid) {
		Long i = 0L;

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");
			if (rs.next() && Long.valueOf(rs.getLong("COOKIES")) == null) {
				return i;
			}
			i = rs.getLong("COOKIES");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Long getProclick(String uuid) {
		Long i = 0L;

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");
			if (rs.next() && Long.valueOf(rs.getLong("PROCLICK")) == null) {
				return i;
			}
			i = rs.getLong("PROCLICK");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Long getDesign(String uuid) {
		Long i = 0L;

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");
			if (rs.next() && Long.valueOf(rs.getLong("DESIGN")) == null) {
				return i;
			}
			i = rs.getLong("DESIGN");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Long getClickerclicks(String uuid) {
		Long i = 0L;

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");
			if (rs.next() && Long.valueOf(rs.getLong("CLICKERCLICKS")) == null) {
				return i;
			}
			i = rs.getLong("CLICKERCLICKS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Long getMilk(String uuid) {
		Long i = 0L;

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");
			if (rs.next() && Long.valueOf(rs.getLong("MILK")) == null) {
				return i;
			}
			i = rs.getLong("MILK");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public Long getPreis(String uuid, Integer zahl) {
		Long i = 0L;

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");

			if (zahl <= 0) {
				if (rs.next() && Long.valueOf(rs.getLong("PREIS")) == null) {
					return i;
				}
				i = rs.getLong("PREIS");
			} else {
				if (rs.next() && Long.valueOf(rs.getLong("PREIS" + zahl)) == null) {
					return i;
				}
				i = rs.getLong("PREIS" + zahl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public String getBooster(String uuid, Integer zahl) {
		String i = "";

		try {
			ResultSet rs = mysql.query("SELECT * FROM " + table + " WHERE UUID= '" + uuid + "'");

			if (zahl <= 0) {
				if (rs.next() && rs.getString("BOOSTER") == null) {
					return i;
				}
				i = rs.getString("BOOSTER");
			} else {
				if (rs.next() && rs.getString("BOOSTER" + zahl) == null) {
					return i;
				}
				i = rs.getString("BOOSTER" + zahl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void setName(String uuid, String name) {
		mysql.update("UPDATE " + table + " SET NAME= '" + name + "' WHERE UUID= '" + uuid + "';");
	}

	public void setCookies(String uuid, Long cookies) {
		mysql.update("UPDATE " + table + " SET COOKIES= '" + cookies + "' WHERE UUID= '" + uuid + "';");
	}

	public void setProclick(String uuid, Long proclick) {
		mysql.update("UPDATE " + table + " SET PROCLICK= '" + proclick + "' WHERE UUID= '" + uuid + "';");
	}

	public void setDesign(String uuid, Long design) {
		mysql.update("UPDATE " + table + " SET DESIGN= '" + design + "' WHERE UUID= '" + uuid + "';");
	}

	public void setClickerclicks(String uuid, Long clicks) {
		mysql.update("UPDATE " + table + " SET CLICKERCLICKS= '" + clicks + "' WHERE UUID= '" + uuid + "';");
	}

	public void setMilk(String uuid, Long milk) {
		mysql.update("UPDATE " + table + " SET MILK= '" + milk + "' WHERE UUID= '" + uuid + "';");
	}

	public void setPreis(String uuid, Integer zahl, Long preis) {
		if (zahl <= 0) {
			mysql.update("UPDATE " + table + " SET PREIS= '" + preis + "' WHERE UUID= '" + uuid + "';");
		} else {
			mysql.update("UPDATE " + table + " SET PREIS" + zahl + "= '" + preis + "' WHERE UUID= '" + uuid + "';");
		}
	}

	public void setBooster(String uuid, Integer zahl, String date) {
		if (zahl <= 0) {
			mysql.update("UPDATE " + table + " SET BOOSTER= '" + date + "' WHERE UUID= '" + uuid + "';");
		} else {
			mysql.update("UPDATE " + table + " SET BOOSTER" + zahl + "= '" + date + "' WHERE UUID= '" + uuid + "';");
		}
	}

	public ResultSet OrderBy(String type, int limit) {
		return mysql.query("SELECT UUID FROM " + table + " ORDER BY " + type.toUpperCase() + " DESC LIMIT " + limit);
	}

	public ResultSet OrderBy(String type) {
		return mysql.query("SELECT UUID FROM " + table + " ORDER BY " + type.toUpperCase() + " DESC");
	}
}
