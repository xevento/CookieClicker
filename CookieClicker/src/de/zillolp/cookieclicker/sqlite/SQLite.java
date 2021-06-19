package de.zillolp.cookieclicker.sqlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import de.zillolp.cookieclicker.main.Main;

public class SQLite {
	private Main plugin = Main.plugin;
	private String PREFIX = plugin.languagetools.getPREFIX();
	private boolean english = plugin.configtools.getEnglish();
	private String dbname;
	public Connection con;

	public SQLite() {
		dbname = plugin.getConfig().getCurrentPath();
	}

	public void connect() {
		con = getConnection();
		update("CREATE TABLE IF NOT EXISTS cookieclicker_players (UUID varchar(64), NAME varchar(64), COOKIES long, PROCLICK long, DESIGN long, CLICKERCLICKS long, MILK long, "
				+ "PREIS long, PREIS1 long, PREIS2 long, PREIS3 long, PREIS4 long, PREIS5 long, PREIS6 long, "
				+ "PREIS7 long, PREIS8 long, PREIS9 long, PREIS10 long, PREIS11 long, PREIS12 long, PREIS13 long, BOOSTER varchar(64), BOOSTER1 varchar(64), BOOSTER2 varchar(64), PRIMARY KEY (UUID));");
		if (english) {
			Bukkit.getConsoleSender().sendMessage(PREFIX + "§aThe connection with SQLite has been established!");
		} else {
			Bukkit.getConsoleSender().sendMessage(PREFIX + "§aDie Verbindung mit SQLite wurde hergestellt!");
		}
	}

	public void close(PreparedStatement ps, ResultSet rs) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			if (english) {
				Bukkit.getConsoleSender().sendMessage(
						PREFIX + "§cThe connection to SQLite could not be terminated! §4Error: " + e.getMessage());
			} else {
				Bukkit.getConsoleSender().sendMessage(PREFIX
						+ "§cDie Verbindung mit SQLite konnte nicht beendet werden! §4Fehler: " + e.getMessage());
			}
		}
	}

	public void close() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			if (english) {
				Bukkit.getConsoleSender().sendMessage(
						PREFIX + "§cThe connection to SQLite could not be terminated! §4Error: " + e.getMessage());
			} else {
				Bukkit.getConsoleSender().sendMessage(PREFIX
						+ "§cDie Verbindung mit SQLite konnte nicht beendet werden! §4Fehler: " + e.getMessage());
			}
		}
	}

	public void update(String qre) {
		if (!(plugin.disabled)) {
			CompletableFuture.runAsync(() -> {
				try {
					con = getConnection();
					PreparedStatement st = con.prepareStatement(qre);
					st.executeUpdate();
					st.close();
				} catch (SQLException e) {
					System.err.print(e);
				}
			});
		} else {
			try {
				con = getConnection();
				PreparedStatement st = con.prepareStatement(qre);
				st.executeUpdate();
				st.close();
				close();
			} catch (SQLException e) {
				System.err.print(e);
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
				System.err.print(e);
			}
			return rs;
		}
		return null;
	}

	public Connection getConnection() {
		File dataFolder = new File(plugin.getDataFolder(), dbname + "cookieclicker.db");
		if (!dataFolder.exists()) {
			try {
				dataFolder.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().log(Level.SEVERE, "File write error: " + dbname + "cookieclicker.db");
			}
		}
		try {
			if (con != null && !con.isClosed()) {
				return con;
			}
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
			return con;
		} catch (SQLException ex) {
			plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
		} catch (ClassNotFoundException ex) {
			plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
		}
		return null;
	}
}
