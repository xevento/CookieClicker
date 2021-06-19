package de.zillolp.cookieclicker.config.tools;

import java.io.File;

import de.zillolp.cookieclicker.utils.ConfigUtil;

public class ConfigTools {
	private ConfigUtil configutil;
	private boolean MySQL;
	private boolean English;
	private boolean Sounds;
	private boolean Ranking;
	private boolean Timeranking;
	private int Afkcooldown;
	private int Maxcps;

	public ConfigTools() {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/config.yml"));
		MySQL = configutil.getBoolean("MySQL");
		English = configutil.getBoolean("English");
		Sounds = configutil.getBoolean("Sounds");
		Ranking = configutil.getBoolean("Ranking");
		Timeranking = configutil.getBoolean("Time-ranking");
		Afkcooldown = configutil.getInt("Afk-cooldown");
		Maxcps = configutil.getInt("Max-cps");
	}

	public boolean getMySQL() {
		return MySQL;
	}

	public boolean getEnglish() {
		return English;
	}

	public boolean getSounds() {
		return Sounds;
	}

	public boolean getRanking() {
		return Ranking;
	}

	public boolean getTimeranking() {
		return Timeranking;
	}

	public int getAfkcooldown() {
		return Afkcooldown * 1000;
	}

	public int getMaxcps() {
		return Maxcps;
	}
}
