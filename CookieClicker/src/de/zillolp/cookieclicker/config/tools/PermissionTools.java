package de.zillolp.cookieclicker.config.tools;

import java.io.File;

import de.zillolp.cookieclicker.utils.ConfigUtil;

public class PermissionTools {
	private ConfigUtil configutil;
	private String Adminpermission;
	private String Premiumpermission;

	public PermissionTools() {
		configutil = new ConfigUtil(new File("plugins/CookieClicker/permissions.yml"));
		Adminpermission = configutil.getString("ADMIN_PERMISSION");
		Premiumpermission = configutil.getString("PREMIUM_PERMISSION");
	}

	public String getAdminpermission() {
		return Adminpermission;
	}

	public String getPremiumpermission() {
		return Premiumpermission;
	}
}
