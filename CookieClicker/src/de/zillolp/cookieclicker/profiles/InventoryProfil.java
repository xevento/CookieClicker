package de.zillolp.cookieclicker.profiles;

import org.bukkit.inventory.Inventory;

public class InventoryProfil {
	private Inventory Homeinv;
	private Inventory Boosterinv;
	private Inventory Shopinv;
	private Inventory Premiumshopinv;
	private Inventory Designinv;

	public Inventory getHomeinv() {
		return Homeinv;
	}

	public Inventory getBoosterinv() {
		return Boosterinv;
	}

	public Inventory getShopinv() {
		return Shopinv;
	}

	public Inventory getPremiumshopinv() {
		return Premiumshopinv;
	}

	public Inventory getDesigninv() {
		return Designinv;
	}

	public Inventory setHomeinv(Inventory homeinv) {
		Homeinv = homeinv;
		return Homeinv;
	}

	public Inventory setBoosterinv(Inventory boosterinv) {
		Boosterinv = boosterinv;
		return Boosterinv;
	}

	public Inventory setShopinv(Inventory shopinv) {
		Shopinv = shopinv;
		return Shopinv;
	}

	public Inventory setPremiumshopinv(Inventory premiumshopinv) {
		Premiumshopinv = premiumshopinv;
		return Premiumshopinv;
	}

	public Inventory setDesigninv(Inventory designinv) {
		Designinv = designinv;
		return Designinv;
	}

	public void reloadInventorys() {
		setHomeinv(null);
		setBoosterinv(null);
		setShopinv(null);
		setPremiumshopinv(null);
		setDesigninv(null);
	}
}
