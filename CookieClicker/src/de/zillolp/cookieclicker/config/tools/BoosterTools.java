package de.zillolp.cookieclicker.config.tools;

import java.io.File;

import de.zillolp.cookieclicker.utils.ConfigUtil;

public class BoosterTools {
	private ConfigUtil configutil;
	private int Area;
	private int Profit;
	private int Limit;
	private String Name;
	private String Name1;
	private String Name2;
	private Long Preis;
	private Long Preis1;
	private Long Preis2;
	private Long Length;
	private Long Length1;
	private Long Length2;

	public BoosterTools() {
		this.configutil = new ConfigUtil(new File("plugins/CookieClicker/booster.yml"));
		this.Area = configutil.getInt("Entire area");
		this.Profit = configutil.getInt("Area for profit");
		this.Limit = configutil.getInt("Active booster limitation");
		this.Name = configutil.getString("Booster.Booster-1.Name");
		this.Name1 = configutil.getString("Booster.Booster-2.Name");
		this.Name2 = configutil.getString("Booster.Booster-3.Name");
		this.Preis = configutil.getLong("Booster.Booster-1.Price");
		this.Preis1 = configutil.getLong("Booster.Booster-2.Price");
		this.Preis2 = configutil.getLong("Booster.Booster-3.Price");
		this.Length = configutil.getLong("Booster.Booster-1.Length");
		this.Length1 = configutil.getLong("Booster.Booster-2.Length");
		this.Length2 = configutil.getLong("Booster.Booster-3.Length");
	}

	public int getArea() {
		return Area;
	}

	public int getProfit() {
		return Profit;
	}

	public int getLimit() {
		return Limit;
	}
	
	public String getName() {
		return Name;
	}

	public String getName1() {
		return Name1;
	}

	public String getName2() {
		return Name2;
	}

	public Long getPreis() {
		return Preis;
	}

	public Long getPreis1() {
		return Preis1;
	}

	public Long getPreis2() {
		return Preis2;
	}

	public Long getLength() {
		return Length * 60000;
	}

	public Long getLength1() {
		return Length1 * 60000;
	}

	public Long getLength2() {
		return Length2 * 60000;
	}
}
