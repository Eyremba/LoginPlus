package gg.web.mck.LoginPlus.Commands;

import gg.web.mck.LoginPlus.main.main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginPlusCommand implements CommandExecutor {
	
	main plugin;
	
	public LoginPlusCommand(main main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		String prefix = ChatColor.GREEN + "[Login+] " + ChatColor.YELLOW;
		String cprefix = "[Login+] ";
		String SystemPlayer = plugin.M.getString("Messages.LoginPlus.Command.SystemPlayer");
		
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(args.length == 0){
				p.sendMessage(prefix + "[Commands]");
				p.sendMessage(ChatColor.YELLOW + "/register");
				p.sendMessage(ChatColor.YELLOW + "/login");
				p.sendMessage(ChatColor.YELLOW + "/LoginPlus setspawn");
			}else if(args[0].equalsIgnoreCase("setspawn")){
				if(p.hasPermission("Login+.LoginPlus.setspawn")){
					Location loc = p.getLocation();
					plugin.getConfig().set("Login.Spawn.SpawnLocation.X", loc.getX());
					plugin.getConfig().set("Login.Spawn.SpawnLocation.Y", loc.getY());
					plugin.getConfig().set("Login.Spawn.SpawnLocation.Z", loc.getZ());
					plugin.getConfig().set("Login.Spawn.SpawnLocation.World", loc.getWorld().getName());
					plugin.saveConfig();
					p.sendMessage(prefix + "Spawn Successfully set");
				}
			}
		}else System.out.println(cprefix + SystemPlayer);
		return true;
	}
}
