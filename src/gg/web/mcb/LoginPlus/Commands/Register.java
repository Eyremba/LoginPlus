package gg.web.mcb.LoginPlus.Commands;

import gg.web.mcb.LoginPlus.main.main;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Register implements CommandExecutor {
	
	main plugin;
	
	public Register(main main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		Player p = (Player)sender;
		if(p.hasPermission("Login+.register")){
			String pname = p.getName();
			UUID UUID = p.getUniqueId();
			String prefix = ChatColor.GREEN + "[Login+] " + ChatColor.YELLOW;
			String IF = plugin.getConfig().getString("Login.Players." + UUID + ".if");
			String boo = plugin.getConfig().getString("Login.boolean.Register");
			String SR = plugin.M.getString("Messages.RegisterCommand.SR");
			String twopw = plugin.M.getString("Messages.RegisterCommand.twopw");
			String bar = plugin.M.getString("Messages.RegisterCommand.bar");
			String Yarl = plugin.M.getString("Messages.RegisterCommand.Yarl");
			if(args.length == 2){
				if(IF.equalsIgnoreCase("not reg")){
					if(boo.equalsIgnoreCase("true")){
						if(args[0].equalsIgnoreCase(args[1])){
							plugin.getConfig().set("Login.Players." + UUID + ".if", "REGISTERT");
							plugin.getConfig().set("Login.Players." + UUID + ".pw", args[0]);
							plugin.saveConfig();
							plugin.noLoginPlayers.remove(pname);
							plugin.noConfigPlayer.remove(pname);
							p.sendMessage(prefix + SR);
						}else p.sendMessage(prefix + twopw);
					}else p.sendMessage(prefix + bar);
				}else p.sendMessage(prefix + Yarl);
			}else p.sendMessage(prefix + "/register <pw> <pw>");
		}
	return true;
	}
}