package de.zillolp.cookieclicker.mysql;

import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.utils.ConfigUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;

public class MySQL {
	private Main plugin = Main.plugin;
	private String PREFIX = plugin.languagetools.getPREFIX();
	private boolean english = plugin.configtools.getEnglish();
	private ConfigUtil configutil;
	private String HOST;
	private String PORT;
	private String DATABASE;
	private String USER;
	private String PASSWORD;

	public Connection con;
	public Boolean connected;

	public MySQL() {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/mysql.yml"));
	}

	public void load() {
		configutil.createConfig();

		configutil.addDefaultString(":###", "MySQL data");
		configutil.addDefaultString("Host", "localhost");
		configutil.addDefaultString("Port", "3306");
		configutil.addDefaultString("Database", "CookieClicker");
		configutil.addDefaultString("User", "root");
		configutil.addDefaultString("Password", "123+");

		HOST = configutil.getString("Host");
		PORT = configutil.getString("Port");
		DATABASE = configutil.getString("Database");
		USER = configutil.getString("User");
		PASSWORD = configutil.getString("Password");

		connect();
	}

	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
			update("CREATE TABLE IF NOT EXISTS cookieclicker_players(UUID varchar(64), NAME varchar(64), COOKIES long, PROCLICK long, DESIGN long, CLICKERCLICKS long, MILK long,"
					+ " PREIS long, PREIS1 long, PREIS2 long, PREIS3 long, PREIS4 long, PREIS5 long, PREIS6 long, PREIS7 long, PREIS8 long, PREIS9 long, PREIS10 long, PREIS11 long, PREIS12 long, PREIS13 long"
					+ ", BOOSTER varchar(64), BOOSTER1 varchar(64), BOOSTER2 varchar(64));");
			if (english) {
				Bukkit.getConsoleSender().sendMessage(PREFIX + "§aThe connection with MySQL has been established!");
			} else {
				Bukkit.getConsoleSender().sendMessage(PREFIX + "§aDie Verbindung mit MySQL wurde hergestellt!");
			}
			connected = true;
		} catch (SQLException e) {
			if (english) {
				Bukkit.getConsoleSender()
						.sendMessage(PREFIX + "§cThe connection to MySQL failed! §4Error: " + e.getMessage());
			} else {
				Bukkit.getConsoleSender().sendMessage(
						PREFIX + "§cDie Verbindung mit MySQL ist fehlgeschlagen! §4Fehler: " + e.getMessage());
			}
			connected = false;
			plugin.disabled = false;
		}
	}

	public void close() {
		try {
			if (con != null) {
				con.close();
				if (english) {
					Bukkit.getConsoleSender().sendMessage(PREFIX + "§cThe connection to MySQL was terminated!");
				} else {
					Bukkit.getConsoleSender().sendMessage(PREFIX + "§cDie Verbindung mit MySQL wurde beendet!");
				}
				connected = false;
			}
		} catch (SQLException e) {
			if (english) {
				Bukkit.getConsoleSender().sendMessage(
						PREFIX + "§cThe connection to MySQL could not be terminated! §4Error: " + e.getMessage());
			} else {
				Bukkit.getConsoleSender().sendMessage(
						PREFIX + "§cDie Verbindung mit MySQL konnte nicht beendet werden! §4Fehler: " + e.getMessage());
			}
			connected = false;
			plugin.disabled = false;
		}
	}

	public void update(String qre) {
		if (!(plugin.disabled)) {
			CompletableFuture.runAsync(() -> {
				if (con != null) {
					try {
						PreparedStatement st = con.prepareStatement(qre);
						st.executeUpdate();
						st.close();
					} catch (SQLException e) {
						connect();
						System.err.print(e);
					}
				}
			});
		} else {
			if (con != null) {
				try {
					PreparedStatement st = con.prepareStatement(qre);
					st.executeUpdate();
					st.close();
				} catch (SQLException e) {
					connect();
					System.err.print(e);
				}
			}
		}
	}

	public ResultSet query(String qre) {
		if (con != null) {
			ResultSet rs = null;
			try {
				PreparedStatement st = con.prepareStatement(qre);
				rs = st.executeQuery();
			} catch (SQLException e) {
				connect();
				System.err.print(e);
			}
			return rs;
		}
		return null;
	}
}
