package gg.web.mcb.LoginPlus.Commands;

import gg.web.mcb.LoginPlus.main.main;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangePassword implements CommandExecutor{
	
	String prefix = ChatColor.GREEN + "[Login+] " + ChatColor.YELLOW;
	
	main plugin;
	
	public ChangePassword(main main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			UUID UUID = p.getUniqueId();
			String IF = plugin.getConfig().getString("Login.Players." + UUID + ".if");
			String pw = plugin.getConfig().getString("Login.Players." + UUID + ".pw");
			if(p.hasPermission("Login+.changepassword")){
				if(args.length == 2){
					if(IF.equalsIgnoreCase("REGISTERT")){
						if(!plugin.noLoginPlayers.contains(p.getName())){
							if(pw.equalsIgnoreCase(args[0])){
								plugin.getConfig().set("Login.Players." + UUID + ".pw", args[1]);
								plugin.saveConfig();
								p.sendMessage(prefix + "successfully password changed");
							}else p.sendMessage(prefix + "Wrong password");
						}else p.sendMessage(prefix + "Please login with /Login <pw>");
					}else p.sendMessage(prefix + "Register for first please! with /register <pw> <pw>");
				}else p.sendMessage(prefix + "/changepassword <old Password> <new Password>");
			}
		}else sender.sendMessage("[LoginPlus] You must be a Player!");
		return true;
	}
}