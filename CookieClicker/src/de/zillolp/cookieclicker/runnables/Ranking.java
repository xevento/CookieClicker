package de.zillolp.cookieclicker.runnables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.config.tools.ConfigTools;
import de.zillolp.cookieclicker.config.tools.LocationTools;
import de.zillolp.cookieclicker.config.tools.StatswallTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.utils.DatenManager;

public class Ranking implements Runnable {
	private Main plugin = Main.plugin;
	private DatenManager datenmanager = plugin.datenmanager;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<String, Long> gekauft = plugin.gekauft;
	private HashMap<Integer, String> hour = plugin.hour;
	private HashMap<String, Long> proclicklist;
	private HashMap<Integer, String> rang;
	private int sched;

	public Ranking() {
		StatswallTools statswalltools = plugin.statswalltools;
		proclicklist = new HashMap<>();
		rang = new HashMap<>();
		sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0, statswalltools.getRefresh());
	}

	@Override
	public void run() {
		setRanking();
	}

	public void setRanking() {
		ConfigTools configtools = plugin.configtools;
		if (configtools.getRanking()) {
			rankPlayers();
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

				@Override
				public void run() {
					setWall(loadLocations(false), rang, false);
				}
			}, 5);
		}
		if (configtools.getTimeranking()) {
			rankTimePlayers();
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

				@Override
				public void run() {
					setWall(loadLocations(true), hour, true);
				}
			}, 5);
		}
	}

	public void rankPlayers() {
		StatswallTools statswalltools = plugin.statswalltools;
		proclicklist.clear();
		rang.clear();
		for (Entry<Player, PlayerProfil> profil : profiles.entrySet()) {
			proclicklist.put(profil.getKey().getUniqueId().toString(), profil.getValue().getProclick());
		}
		int in = 0;
		Set<String> keys = proclicklist.keySet();
		for (String key : keys) {
			in++;
			if (in <= 5) {
				Player k = Bukkit.getPlayer(UUID.fromString(key));
				PlayerProfil profil = profiles.get(k);
				if (profil != null) {
					datenmanager.setCookies(key, profil.getCookies());
					datenmanager.setProclick(key, profil.getProclick());
					datenmanager.setClickerclicks(key, profil.getClickerclicks());
				}
			} else {
				break;
			}
		}

		String ranktype = statswalltools.getRanktype();
		ResultSet rs;
		if (ranktype.equalsIgnoreCase("Cookies")) {
			rs = datenmanager.OrderBy("Cookies", 5);
		} else if (ranktype.equalsIgnoreCase("Clickerclicks")) {
			rs = datenmanager.OrderBy("Clickerclicks", 5);
		} else {
			rs = datenmanager.OrderBy("Proclick", 5);
		}
		int place = 0;

		try {
			while (rs.next()) {
				place++;
				rang.put(place, rs.getString("UUID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rankTimePlayers() {
		Set<String> keys = gekauft.keySet();
		TreeMap<Long, Set<String>> treeMap = new TreeMap<Long, Set<String>>();

		for (String key : keys) {
			long value = gekauft.get(key);
			Set<String> values;
			if (treeMap.containsKey(value)) {
				values = treeMap.get(value);
				values.add(key);
			} else {
				values = new HashSet<String>();
				values.add(key);
			}
			treeMap.put(value, values);
		}

		Set<Long> treeValues = treeMap.keySet();
		int place = gekauft.size() + 1;
		for (Long integer : treeValues) {
			Set<String> values = treeMap.get(integer);
			for (String uuid : values) {
				place--;
				if (place <= 3) {
					if (gekauft.get(uuid) > 0) {
						hour.put(place, uuid);
					} else if (hour.containsValue(uuid)) {
						hour.remove(place);
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void setWall(ArrayList<Location> locations, HashMap<Integer, String> rang, boolean time) {
		ConfigTools configtools = plugin.configtools;
		StatswallTools statswalltools = plugin.statswalltools;
		boolean english = configtools.getEnglish();
		for (int i = 0; i < locations.size(); i++) {
			BlockState block = locations.get(i).getBlock().getState();
			BlockState block_1 = locations.get(i).add(0, -1, 0).getBlock().getState();
			int id = i + 1;
			if (block != null && block instanceof Skull) {
				Skull head = (Skull) block;
				if (block_1 != null && block_1 instanceof Sign) {
					Sign sign = (Sign) block_1;
					Long cookies = 0L;
					Long proclick = 0L;
					Long clickerclicks = 0L;
					String name = "?";
					if (rang.get(id) != null) {
						name = datenmanager.getName(rang.get(id));
						head.setOwner(name);
						cookies = datenmanager.getCookies(rang.get(id));
						clickerclicks = datenmanager.getClickerclicks(rang.get(id));
						if (time) {
							proclick = gekauft.get(rang.get(id));
						} else {
							proclick = datenmanager.getProclick(rang.get(id));
						}
					} else {
						head.setOwner("MHF_Question");
					}
					head.update();

					String place = "#?";
					switch (id) {
					case 1:
						if (time) {
							place = statswalltools.getTIME_RANK_PREFIX_1();
						} else {
							place = statswalltools.getALLTIME_RANK_PREFIX_1();
						}
						break;
					case 2:
						if (time) {
							place = statswalltools.getTIME_RANK_PREFIX_2();
						} else {
							place = statswalltools.getALLTIME_RANK_PREFIX_2();
						}
						break;
					case 3:
						if (time) {
							place = statswalltools.getTIME_RANK_PREFIX_3();
						} else {
							place = statswalltools.getALLTIME_RANK_PREFIX_3();
						}
						break;
					case 4:
						place = statswalltools.getALLTIME_RANK_PREFIX_4();
						break;
					case 5:
						place = statswalltools.getALLTIME_RANK_PREFIX_5();
						break;
					}
					if (time) {
						sign.setLine(0, statswalltools.getTIME_SIGN_1(name, place, cookies, proclick, clickerclicks));
						sign.setLine(1, statswalltools.getTIME_SIGN_2(name, place, cookies, proclick, clickerclicks));
						sign.setLine(2, statswalltools.getTIME_SIGN_3(name, place, cookies, proclick, clickerclicks));
						sign.setLine(3, statswalltools.getTIME_SIGN_4(name, place, cookies, proclick, clickerclicks));
					} else {
						sign.setLine(0,
								statswalltools.getALLTIME_SIGN_1(name, place, cookies, proclick, clickerclicks));
						sign.setLine(1,
								statswalltools.getALLTIME_SIGN_2(name, place, cookies, proclick, clickerclicks));
						sign.setLine(2,
								statswalltools.getALLTIME_SIGN_3(name, place, cookies, proclick, clickerclicks));
						sign.setLine(3,
								statswalltools.getALLTIME_SIGN_4(name, place, cookies, proclick, clickerclicks));
					}
					sign.update();
				} else {
					if (english) {
						stop("§cThere is no sign under the head of place-" + id + "!");
					} else {
						stop("§cUnter dem Kopf von Platz-" + id + " befindet sich kein Schild!");
					}
				}
			} else {
				if (english) {
					stop("§cThe head of place-" + id + " is missing!");
				} else {
					stop("§cDer Kopf von Platz-" + id + " fehlt!");
				}
			}
		}
	}

	public ArrayList<Location> loadLocations(boolean time) {
		ArrayList<Location> locations = new ArrayList<Location>();
		if (time) {
			for (int i = 1; i < 4; i++) {
				Location loc = new LocationTools("Time." + i).loadLocation();
				if (loc != null) {
					locations.add(loc);
				}
			}
		} else {
			for (int i = 1; i < 6; i++) {
				Location loc = new LocationTools("Alltime." + i).loadLocation();
				if (loc != null) {
					locations.add(loc);
				}
			}
		}
		return locations;
	}

	public void stop(String message) {
		if (message != null) {
			Bukkit.getConsoleSender().sendMessage(message);
		}
		Bukkit.getScheduler().cancelTask(sched);
	}
}
