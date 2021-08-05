package de.zillolp.cookieclicker.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zillolp.cookieclicker.config.tools.LanguageTools;
import de.zillolp.cookieclicker.config.tools.LocationTools;
import de.zillolp.cookieclicker.config.tools.PermissionTools;
import de.zillolp.cookieclicker.config.tools.StatswallTools;
import de.zillolp.cookieclicker.main.Main;
import de.zillolp.cookieclicker.profiles.BoosterProfil;
import de.zillolp.cookieclicker.profiles.PlayerProfil;
import de.zillolp.cookieclicker.profiles.ShopProfil;
import de.zillolp.cookieclicker.runnables.TimeRanking;
import de.zillolp.cookieclicker.utils.DatenManager;
import de.zillolp.cookieclicker.utils.HologramsUtil;
import de.zillolp.cookieclicker.utils.StringUtil;

public class CookieClickerCommand implements CommandExecutor {
	private Main plugin = Main.plugin;
	private DatenManager datenmanager = plugin.datenmanager;
	private StringUtil stringutil = plugin.stringutil;
	private HashMap<Player, PlayerProfil> profiles = plugin.playerprofiles;
	private HashMap<Player, BoosterProfil> boosterprofiles = plugin.boosterprofiles;

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		LanguageTools languagetools = plugin.languagetools;
		String PREFIX = languagetools.getPREFIX();
		boolean english = plugin.configtools.getEnglish();
		if (sender instanceof Player) {
			Player p = (Player) sender;
			PlayerProfil profil = profiles.get(p);
			PermissionTools permissiontools = plugin.permissiontools;
			String admin_permission = permissiontools.getAdminpermission();
			if (args.length == 0) {
				sendInfo(p);
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("info")) {
					p.sendMessage("§6§lInfos zum Plugin:");
					p.sendMessage("§7Plugin Name: §eCookieClicker");
					p.sendMessage("§7Plugin Version: §e" + plugin.getDescription().getVersion());
					p.sendMessage("§7Author: §eZilloLP");
					p.sendMessage("§7Discord: §ehttps://discord.gg/NBs27JK");
				} else if (p.hasPermission(admin_permission)) {
					if (args[0].equalsIgnoreCase("resettimer")) {
						plugin.timeranking.stop();
						new LocationTools("Resettimer", p.getLocation()).saveLocation();
						plugin.timeranking = new TimeRanking();
						if (english) {
							p.sendMessage(PREFIX + "§7You set the §eResettimer§7.");
						} else {
							p.sendMessage(PREFIX + "§7Du hast den §eResettimer §7gesetzt.");
						}
					} else if (args[0].equalsIgnoreCase("reload")) {
						plugin.reloadConfigs();
						if (english) {
							p.sendMessage(PREFIX + "§7The plugin was reloaded §asuccesful§7!");
						} else
							p.sendMessage(PREFIX + "§7Plugin wurde §aerfolgreich §7neu geladen!");
					} else {
						sendInfo(p);
					}
				} else {
					p.sendMessage(PREFIX + languagetools.getNO_PERMISSION());
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("stats")) {
					OfflinePlayer k = Bukkit.getOfflinePlayer(args[1]);
					if (k != null) {
						StatswallTools statswalltools = plugin.statswalltools;
						String name = k.getName();
						String uuid = k.getUniqueId().toString();
						if (datenmanager.inPlayers(uuid)) {
							String ranktype = statswalltools.getRanktype();
							Long cookies;
							Long proclick;
							Long clickerclicks;
							Long milk;
							if (profiles.containsKey(k) && boosterprofiles.containsKey(k)) {
								PlayerProfil k_profil = profiles.get(k);
								BoosterProfil k_booster = boosterprofiles.get(k);
								cookies = k_profil.getCookies();
								clickerclicks = k_profil.getClickerclicks();
								proclick = k_profil.getProclick();
								milk = k_booster.getMilk();

								if (ranktype.equalsIgnoreCase("Cookies")) {
									datenmanager.setCookies(uuid, cookies);
								} else if (ranktype.equalsIgnoreCase("Clickerclicks")) {
									datenmanager.setClickerclicks(uuid, proclick);
								} else {
									datenmanager.setProclick(uuid, clickerclicks);
								}
							} else {
								cookies = datenmanager.getCookies(uuid);
								proclick = datenmanager.getProclick(uuid);
								clickerclicks = datenmanager.getClickerclicks(uuid);
								milk = datenmanager.getMilk(uuid);
							}

							ResultSet rs;
							if (ranktype.equalsIgnoreCase("Cookies")) {
								rs = datenmanager.OrderBy("Cookies");
							} else if (ranktype.equalsIgnoreCase("Clickerclicks")) {
								rs = datenmanager.OrderBy("Clickerclicks");
							} else {
								rs = datenmanager.OrderBy("Proclick");
							}

							int place = 0;
							try {
								while (rs.next()) {
									place++;
									if (rs.getString("UUID").equalsIgnoreCase(uuid)) {
										break;
									}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}

							p.sendMessage(languagetools.getPLAYER_STATS_INFO(name, place, cookies, proclick,
									clickerclicks, milk));
							p.sendMessage(languagetools.getPLAYER_STATS_INFO_1(name, place, cookies, proclick,
									clickerclicks, milk));
							p.sendMessage(languagetools.getPLAYER_STATS_INFO_2(name, place, cookies, proclick,
									clickerclicks, milk));
							p.sendMessage(languagetools.getPLAYER_STATS_INFO_3(name, place, cookies, proclick,
									clickerclicks, milk));
							p.sendMessage(languagetools.getPLAYER_STATS_INFO_4(name, place, cookies, proclick,
									clickerclicks, milk));
							p.sendMessage(languagetools.getPLAYER_STATS_INFO_5(name, place, cookies, proclick,
									clickerclicks, milk));
							p.sendMessage(languagetools.getPLAYER_STATS_INFO_6(name, place, cookies, proclick,
									clickerclicks, milk));
						} else {
							if (english) {
								p.sendMessage(PREFIX + "§cThis player could not found!");
							} else {
								p.sendMessage(PREFIX + "§cDieser Spieler konnte nicht gefunden werden!");
							}
						}
					} else {
						if (english) {
							p.sendMessage(PREFIX + "§cThis player could not found!");
						} else {
							p.sendMessage(PREFIX + "§cDieser Spieler konnte nicht gefunden werden!");
						}
					}
				} else if (p.hasPermission(admin_permission)) {
					if (args[0].equalsIgnoreCase("reset")) {
						OfflinePlayer k = Bukkit.getOfflinePlayer(args[1]);
						if (k != null) {
							String name = k.getName();
							String uuid = k.getUniqueId().toString();
							Long[] prices = new Long[] { 30L, 360L, 690L, 920L, 1250L, 1580L, 1910L, 2240L, 2570L,
									2900L, 3230L, 3560L, 3890L, 4220L };
							if (datenmanager.inPlayers(uuid)) {
								datenmanager.setCookies(uuid, 1L);
								datenmanager.setProclick(uuid, 1L);
								datenmanager.setClickerclicks(uuid, 0L);
								for (int i = 0; i < 14; i++) {
									datenmanager.setPreis(uuid, i, prices[i]);
								}
								PlayerProfil k_profil = profiles.get(k);
								if (k_profil != null) {
									k.getPlayer().closeInventory();
									k_profil.setCookies(1L);
									k_profil.setProclick(1L);
									k_profil.setClickerclicks(0L);
									ShopProfil shopprofil = plugin.shopprofiles.get(k);
									if (shopprofil != null) {
										shopprofil.removeGekauft();
										for (int i = 0; i < 14; i++) {
											shopprofil.setPreis(prices[i], i);
										}
									}
								} else {
									ShopProfil shopprofil = plugin.shopprofiles.get(p);
									HashMap<String, Long> gekauft = shopprofil.getGekauft_Map();
									if (gekauft.containsKey(uuid)) {
										gekauft.replace(uuid, 0L);
									}
								}
								if (english) {
									p.sendMessage(PREFIX + "§7The player §6" + name + " §7was resetet §asuccessful§7.");
								} else {
									p.sendMessage(
											PREFIX + "§7Der Spieler §6" + name + " §7wurde §aerfolgreich §7resetet.");
								}
							} else {
								if (english) {
									p.sendMessage(PREFIX + "§cThis player could not found!");
								} else {
									p.sendMessage(PREFIX + "§cDieser Spieler konnte nicht gefunden werden!");
								}
							}
						} else {
							if (english) {
								p.sendMessage(PREFIX + "§cThis player could not found!");
							} else {
								p.sendMessage(PREFIX + "§cDieser Spieler konnte nicht gefunden werden!");
							}
						}
					} else if (stringutil.isNumeric(args[1], 100)) {
						int nummer = Integer.valueOf(args[1]);
						if (args[0].equalsIgnoreCase("clicker")) {
							profil.setClickerNummer(nummer);
							if (english) {
								p.sendMessage(PREFIX + "§7Do rightclick on a §eblock§7!");
							} else {
								p.sendMessage(PREFIX + "§7Mache ein Rechtsklick auf einen §eBlock§7!");
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							LocationTools lu = new LocationTools("CookieClicker." + nummer, p.getLocation());
							if (lu.loadLocation() != null) {
								lu.resetLocation();
								plugin.hologramsutil = new HologramsUtil();
								if (english) {
									p.sendMessage(
											PREFIX + "§7You removed the cookieclicker §6" + nummer + " §asuccesful§7.");
								} else {
									p.sendMessage(PREFIX + "§7Du hast den §6" + nummer
											+ " §7CookieClicker §aerfolgreich §7entfernt.");
								}
							} else {
								if (english) {
									p.sendMessage(PREFIX + "§cThe §6cookieclicker §e" + nummer + " §cdoes not exist.");
								} else {
									p.sendMessage(
											PREFIX + "§cEs gibt den §6CookieClicker §e" + nummer + " §cnoch nicht.");
								}
							}
						} else if (args[0].equalsIgnoreCase("alltime")) {
							if (nummer <= 5) {
								profil.setAlltimeNummer(nummer);
								if (english) {
									p.sendMessage(PREFIX + "§7Do rightclick on a §cskull§7!");
								} else {
									p.sendMessage(PREFIX + "§7Mache ein Rechtsklick auf einen §cKopf§7!");
								}
							} else {
								if (english) {
									p.sendMessage(PREFIX + "§cPls enter a real number.");
								} else {
									p.sendMessage(PREFIX + "§cBitte gebe eine Zahl an.");
								}
							}
						} else if (args[0].equalsIgnoreCase("time")) {
							if (nummer <= 3) {
								profil.setTimeNummer(nummer);
								if (english) {
									p.sendMessage(PREFIX + "§7Do rightclick on a §cskull§7!");
								} else {
									p.sendMessage(PREFIX + "§7Mache ein Rechtsklick auf einen §cKopf§7!");
								}
							} else {
								if (english) {
									p.sendMessage(PREFIX + "§cPls enter a real number.");
								} else {
									p.sendMessage(PREFIX + "§cBitte gebe eine Zahl an.");
								}
							}
						} else {
							sendInfo(p);
						}
					} else {
						if (english) {
							p.sendMessage(PREFIX + "§cPls enter a real number.");
						} else {
							p.sendMessage(PREFIX + "§cBitte gebe eine Zahl an.");
						}
					}
				} else {
					p.sendMessage(PREFIX + languagetools.getNO_PERMISSION());
				}
			} else {
				sendInfo(p);
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(PREFIX + languagetools.getONLY_PLAYER());
		}

		return false;
	}

	private void sendInfo(Player p) {
		PermissionTools permissiontools = plugin.permissiontools;
		boolean english = plugin.configtools.getEnglish();
		String admin_permission = permissiontools.getAdminpermission();
		if (english) {
			p.sendMessage("§6§lThe CookieClicker Commands:");
			p.sendMessage("§e/cookieclicker info.");
			p.sendMessage("§7Shows infos about the plugin.");
			p.sendMessage("§e/cookieclicker stats <Player>.");
			p.sendMessage("§7Shows you the stats of a player.");
			if (p.hasPermission(admin_permission)) {
				p.sendMessage("§e/cookieclicker Clicker <number>.");
				p.sendMessage("§7Let you set a clicker.");
				p.sendMessage("§e/cookieclicker remove <number>.");
				p.sendMessage("§7Remove a clicker");
				p.sendMessage("§e/cookieclicker alltime <1-5>.");
				p.sendMessage("§7Set the alltime statswall.");
				p.sendMessage("§e/cookieclicker time <1-3>.");
				p.sendMessage("§7Set the time statswall.");
				p.sendMessage("§e/cookieclicker resettimer.");
				p.sendMessage("§7Set the resettimer.");
				p.sendMessage("§e/cookieclicker reload.");
				p.sendMessage("§7Reloads the plugin.");
				p.sendMessage("§e/cookieclicker reset <Player>.");
				p.sendMessage("§7Reset a player.");
			}
		} else {
			p.sendMessage("§6§lDie CookieClicker Befehle:");
			p.sendMessage("§e/cookieclicker info.");
			p.sendMessage("§7Zeigt dir die Infos zum Plugin.");
			p.sendMessage("§e/cookieclicker stats <Player>.");
			p.sendMessage("§7Zeigt dir die Stats eines Spielers.");
			if (p.hasPermission(admin_permission)) {
				p.sendMessage("§e/cookieclicker Clicker <Nummer>.");
				p.sendMessage("§7Setze den Clicker wo du Cookies sammeln kannst.");
				p.sendMessage("§e/cookieclicker remove <Nummer>.");
				p.sendMessage("§7Entfernt den bestimmten Clicker.");
				p.sendMessage("§e/cookieclicker alltime <1-5>.");
				p.sendMessage("§7Setzt die Alltime Statswall.");
				p.sendMessage("§e/cookieclicker time <1-3>.");
				p.sendMessage("§7Setzt die Time Statswall.");
				p.sendMessage("§e/cookieclicker resettimer.");
				p.sendMessage("§7Setzt den Resettimer.");
				p.sendMessage("§e/cookieclicker reload.");
				p.sendMessage("§7Lädt das Plugin neu.");
				p.sendMessage("§e/cookieclicker reset <Spieler>.");
				p.sendMessage("§7Setzt die CookieClicker Stats von einem Spieler zurück.");
			}
		}
	}
}
