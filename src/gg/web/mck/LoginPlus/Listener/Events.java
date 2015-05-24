package gg.web.mck.LoginPlus.Listener;

import gg.web.mck.LoginPlus.main.main;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener{
	
	main plugin;
	public Events(main main) {
		plugin = main;
	}
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e){
		
		final Player p = e.getPlayer();
		UUID UUID = p.getUniqueId();
		String cif = plugin.getConfig().getString("Login.Players." + UUID + ".if");
		final String STeleport = plugin.getConfig().getString("Login.boolean.SpawnLocation");
		final String PlayerName = p.getName();
		final Location loc = p.getLocation();
		
		if(cif == null){
			plugin.getConfig().set("Login.Players." + UUID + ".if", "not reg");
			plugin.saveConfig();
			plugin.noConfigPlayer.add(PlayerName);
		}
		
		plugin.noLoginPlayers.add(PlayerName);
		
		double X = plugin.getConfig().getDouble("Login.Spawn.SpawnLocation.X");
		double Y = plugin.getConfig().getDouble("Login.Spawn.SpawnLocation.Y");
		double Z = plugin.getConfig().getDouble("Login.Spawn.SpawnLocation.Z");
		String World = plugin.getConfig().getString("Login.Spawn.SpawnLocation.World");
		World SWorld = Bukkit.getWorld(World);
		final Location sloc = new Location(SWorld, X, Y, Z);
	
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if(plugin.noLoginPlayers.contains(PlayerName)){
					if(STeleport.equalsIgnoreCase("true")){
						p.teleport(sloc);
					}else p.teleport(loc);
				}
			}
		}, 10, 5);
	
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if(plugin.noLoginPlayers.contains(PlayerName)){
					if(plugin.noConfigPlayer.contains(PlayerName)){
						p.sendMessage(ChatColor.GREEN + "[Login+]" + ChatColor.YELLOW + "/register <pw> <pw>");
					}else p.sendMessage(ChatColor.GREEN + "[Login+]" + ChatColor.YELLOW + "/login <pw>");
				}
			}
		}, 20, 20*5);
	}
	
	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(plugin.noLoginPlayers.contains(p)){
			e.setCancelled(true);
			p.sendMessage(ChatColor.GREEN + "[Login+] Message Block");
		}
	}
}