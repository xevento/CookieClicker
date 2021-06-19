package de.zillolp.cookieclicker.profiles;

import de.zillolp.cookieclicker.config.tools.BoosterTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.runnables.BoosterAnimation;
import de.zillolp.cookieclicker.utils.DatenManager;
import de.zillolp.cookieclicker.utils.StringUtil;

public class BoosterProfil {
	private Main plugin = Main.plugin;
	private StringUtil stringutil = plugin.stringutil;
	private DatenManager datenmanager = plugin.datenmanager;
	private final String uuid;
	private boolean inAnimation;
	private BoosterAnimation animation;
	private Long Milk;
	private String Booster;
	private String Booster_1;
	private String Booster_2;

	public BoosterProfil(String uuid) {
		this.uuid = uuid;
		this.inAnimation = false;
		this.Milk = datenmanager.getMilk(uuid);
		this.Booster = datenmanager.getBooster(uuid, 0);
		this.Booster_1 = datenmanager.getBooster(uuid, 1);
		this.Booster_2 = datenmanager.getBooster(uuid, 2);
	}

	public boolean InAnimation() {
		return inAnimation;
	}

	public BoosterAnimation getAnimation() {
		return animation;
	}

	public boolean IsBooster() {
		return Booster.equalsIgnoreCase("NONE");
	}

	public boolean IsBooster_1() {
		return Booster_1.equalsIgnoreCase("NONE");
	}

	public boolean IsBooster_2() {
		return Booster_2.equalsIgnoreCase("NONE");
	}

	public Long getMilk() {
		return Milk;
	}

	public String getBooster() {
		return Booster;
	}

	public String getBooster_1() {
		return Booster_1;
	}

	public String getBooster_2() {
		return Booster_2;
	}

	public String getBoostertime() {
		BoosterTools boostertools = plugin.boostertools;
		return stringutil
				.formatTime(stringutil.parseDate(Booster) + boostertools.getLength() - System.currentTimeMillis());
	}

	public String getBoostertime_1() {
		BoosterTools boostertools = plugin.boostertools;
		return stringutil
				.formatTime(stringutil.parseDate(Booster_1) + boostertools.getLength1() - System.currentTimeMillis());
	}

	public String getBoostertime_2() {
		BoosterTools boostertools = plugin.boostertools;
		return stringutil
				.formatTime(stringutil.parseDate(Booster_2) + boostertools.getLength2() - System.currentTimeMillis());
	}

	public void setInAnimation(boolean inAnimation) {
		this.inAnimation = inAnimation;
	}

	public void setAnimation(BoosterAnimation animation) {
		this.animation = animation;
	}

	public void setMilk(Long milk) {
		Milk = milk;
	}

	public void setBooster(String booster) {
		Booster = booster;
	}

	public void setBooster_1(String booster_1) {
		Booster_1 = booster_1;
	}

	public void setBooster_2(String booster_2) {
		Booster_2 = booster_2;
	}

	public void addMilk(Long milk) {
		Milk = Milk + milk;
	}

	public void removeMilk(Long milk) {
		Milk = Milk - milk;
	}

	public void stopAnimation() {
		if (InAnimation()) {
			getAnimation().stop();
			setInAnimation(false);
		}
	}

	public void UploadMySQL() {
		stopAnimation();
		datenmanager.setMilk(uuid, getMilk());
		datenmanager.setBooster(uuid, 0, getBooster());
		setBooster("NONE");
		datenmanager.setBooster(uuid, 1, getBooster_1());
		setBooster_1("NONE");
		datenmanager.setBooster(uuid, 2, getBooster_2());
		setBooster_2("NONE");
	}
}
