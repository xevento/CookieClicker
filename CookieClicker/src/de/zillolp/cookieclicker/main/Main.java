package de.zillolp.cookieclicker.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.zillolp.cookieclicker.utils.DatenManager;
import de.zillolp.cookieclicker.utils.HologramsUtil;
import de.zillolp.cookieclicker.utils.ItemCreator;
import de.zillolp.cookieclicker.utils.StringUtil;
import de.zillolp.cookieclicker.bstats.Metrics;
import de.zillolp.cookieclicker.commands.CookieClickerCommand;
import de.zillolp.cookieclicker.config.ConfigCreation;
import de.zillolp.cookieclicker.config.tools.BoosterTools;
import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.config.tools.HologramTools;
import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.config.tools.PermissionTools;
import de.zillolp.cookieclicker.config.tools.StatswallTools;
import de.zillolp.cookieclicker.listeners.PlayerConnectionListener;
import de.zillolp.cookieclicker.listeners.SetupListener;
import de.zillolp.cookieclicker.listeners.interact.ClickerClickListener;
import de.zillolp.cookieclicker.listeners.interact.StatswallListener;
import de.zillolp.cookieclicker.listeners.inventory.BoosterListener;
import de.zillolp.cookieclicker.listeners.inventory.DesignListener;
import de.zillolp.cookieclicker.listeners.inventory.HomeListener;
import de.zillolp.cookieclicker.listeners.inventory.PremiumShopListener;
import de.zillolp.cookieclicker.listeners.inventory.ShopListener;
import de.zillolp.cookieclicker.listeners.protection.AFKListener;
import de.zillolp.cookieclicker.listeners.protection.ClickLimitListener;
import de.zillolp.cookieclicker.placeholder.Expansion;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.InventoryProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.profiles.ShopProfil;
import de.zillolp.cookieclicker.runnables.BoosterTimer;
import de.zillolp.cookieclicker.runnables.Ranking;
import de.zillolp.cookieclicker.runnables.TimeRanking;

public class Main extends JavaPlugin {
	public static Main plugin;
	public DatenManager datenmanager;
	public ConfigTools configtools;
	public LanguageTools languagetools;
	public BoosterTools boostertools;
	public PermissionTools permissiontools;
	public HologramTools hologramstools;
	public StatswallTools statswalltools;
	public StringUtil stringutil;
	public HologramsUtil hologramsutil;
	public ItemCreator itemcreator;
	public Ranking ranking;
	public TimeRanking timeranking;
	public HashMap<Player, InventoryProfil> invprofiles;
	public HashMap<Player, PlayerProfil> playerprofiles;
	public HashMap<Player, ShopProfil> shopprofiles;
	public HashMap<Player, BoosterProfil> boosterprofiles;
	public HashMap<String, Long> gekauft;
	public HashMap<Integer, String> hour;
	public ArrayList<Location> clickerlist;
	public boolean disabled;

	@Override
	public void onEnable() {
		plugin = this;
		if (register()) {
			new Metrics(this, 11733);
			init(Bukkit.getPluginManager());

			Bukkit.getConsoleSender().sendMessage("\r\n"
					+ "§6         ____               _     _         ____  _  _        _               \r\n"
					+ "§6        / ___| ___    ___  | | __(_)  ___  / ___|| |(_)  ___ | | __ ___  _ __ \r\n"
					+ "§6       | |    / _ \\  / _ \\ | |/ /| | / _ \\| |    | || | / __|| |/ // _ \\| '__|\r\n"
					+ "§6       | |___| (_) || (_) ||   < | ||  __/| |___ | || || (__ |   <|  __/| |   \r\n"
					+ "§6        \\____|\\___/  \\___/ |_|\\_\\|_| \\___| \\____||_||_| \\___||_|\\_\\\\___||_|   \r\n"
					+ "");
			Bukkit.getConsoleSender().sendMessage("§7§m---------------------");
			Bukkit.getConsoleSender().sendMessage("§6CookieClicker §e" + getDescription().getVersion());
			Bukkit.getConsoleSender().sendMessage("§7Developed by §bZilloLP");
			Bukkit.getConsoleSender().sendMessage("§7§m---------------------");
		} else {
			Bukkit.getPluginManager().disablePlugin(plugin);
		}
	}

	@Override
	public void onDisable() {
		disabled = true;
		if (datenmanager.getConnected()) {
			timeranking.stop();
			for (Entry<Player, PlayerProfil> profil : playerprofiles.entrySet()) {
				profil.getValue().UploadStats();
			}
			if (!(datenmanager.getStatus())) {
				datenmanager.getSqlite().close();
			} else {
				datenmanager.getMysql().close();
			}
		}
		if (configtools.getEnglish()) {
			Bukkit.getConsoleSender().sendMessage(languagetools.getPREFIX() + "§cThe plugin has stoped");
		} else {
			Bukkit.getConsoleSender().sendMessage(languagetools.getPREFIX() + "§cDas Plugin wurde beendet.");
		}
	}

	private boolean register() {
		new ConfigCreation();
		configtools = new ConfigTools();
		stringutil = new StringUtil();
		languagetools = new LanguageTools();
		datenmanager = new DatenManager();
		datenmanager.load();

		return datenmanager.getConnected();
	}

	private void init(PluginManager pm) {
		disabled = false;
		createLists();
		implementsUtils();
		createPlayers();
		implementsRunnables();
		implementsListeners(pm);
		implementsCommands();
	}

	private void createLists() {
		invprofiles = new HashMap<>();
		playerprofiles = new HashMap<>();
		shopprofiles = new HashMap<>();
		boosterprofiles = new HashMap<>();
		gekauft = new HashMap<>();
		hour = new HashMap<>();
		clickerlist = new ArrayList<>();
	}

	private void implementsRunnables() {
		ranking = new Ranking();
		timeranking = new TimeRanking();
		new BoosterTimer();
	}

	private void implementsUtils() {
		boostertools = new BoosterTools();
		permissiontools = new PermissionTools();
		hologramstools = new HologramTools();
		hologramsutil = new HologramsUtil();
		statswalltools = new StatswallTools();
		itemcreator = new ItemCreator();
	}

	private void createPlayers() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			datenmanager.createPlayer(all);
			Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {

				@Override
				public void run() {
					if (!(playerprofiles.containsKey(all))) {
						playerprofiles.put(all, new PlayerProfil(all));
					}
				}
			}, 3);
		}

		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			new Expansion().register();
		}
	}

	private void implementsListeners(PluginManager pm) {
		pm.registerEvents(new BoosterListener(), this);
		pm.registerEvents(new DesignListener(), this);
		pm.registerEvents(new HomeListener(), this);
		pm.registerEvents(new PremiumShopListener(), this);
		pm.registerEvents(new ShopListener(), this);
		pm.registerEvents(new AFKListener(), this);
		pm.registerEvents(new ClickLimitListener(), this);
		pm.registerEvents(new ClickerClickListener(), this);
		pm.registerEvents(new StatswallListener(), this);
		pm.registerEvents(new PlayerConnectionListener(), this);
		pm.registerEvents(new SetupListener(), this);
	}

	private void implementsCommands() {
		getCommand("cookieclicker").setExecutor(new CookieClickerCommand());
	}

	public void reloadConfigs() {
		configtools = new ConfigTools();
		languagetools = new LanguageTools();
		boostertools = new BoosterTools();
		permissiontools = new PermissionTools();
		if (datenmanager.getConnected()) {
			hologramstools = new HologramTools();
			hologramsutil = new HologramsUtil();
			statswalltools = new StatswallTools();
			ranking.stop(null);
			timeranking.stop();
			ranking = new Ranking();
			timeranking = new TimeRanking();
			for (Entry<Player, PlayerProfil> profil : playerprofiles.entrySet()) {
				profil.getValue().reloadProfil();
			}
		}
	}
}
