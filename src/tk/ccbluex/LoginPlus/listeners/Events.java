package tk.ccbluex.LoginPlus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.ccbluex.LoginPlus.LoginPlus;

public class Events implements Listener{
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		String name = p.getName();
		
		if(!LoginPlus.INSTANCE.getConfig().contains("Player." + name + ".if")){
			LoginPlus.INSTANCE.getConfig().set("Player." + name + ".if", false);
			LoginPlus.INSTANCE.saveConfig();
			LoginPlus.INSTANCE.noRegister.add(p.getName());
		}
		LoginPlus.INSTANCE.noLogin.add(p.getName());
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(LoginPlus.INSTANCE, new Runnable() {
			public void run() {
				if(LoginPlus.INSTANCE.noLogin.contains(p.getName())){
					if(LoginPlus.INSTANCE.noRegister.contains(p.getName()))
						p.sendMessage(LoginPlus.prefix + "/register <pw> <pw>");
					else
						p.sendMessage(LoginPlus.prefix + "/login <pw>");
				}
			}
		}, 20, 20 * 5);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(LoginPlus.INSTANCE.noLogin.contains(p.getName()))
			p.teleport(e.getFrom());
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(LoginPlus.INSTANCE.noLogin.contains(p.getName()))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(LoginPlus.INSTANCE.noLogin.contains(p.getName())){
			e.setCancelled(true);
			p.sendMessage(LoginPlus.prefix + "You must login in to write a message.");
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(LoginPlus.INSTANCE.noLogin.contains(p.getName()))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(LoginPlus.INSTANCE.noLogin.contains(p.getName()))
			e.setCancelled(true);
	}
}