package gg.web.mck.LoginPlus.Commands;

import gg.web.mck.LoginPlus.main.main;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LoginC implements CommandExecutor {

	main plugin;
	
	public LoginC(main main) {
		plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
	Player p = (Player)sender;
		if(p.hasPermission("Login+.Login")){
			UUID UUID = p.getUniqueId();
			String name = p.getName();
			YamlConfiguration M = plugin.M;
			String RP = M.getString("Messages.LoginCommand.RP");
			String WP = M.getString("Messages.LoginCommand.WP");
			String SL = M.getString("Messages.LoginCommand.SL");
			String IF = plugin.getConfig().getString("Login.Players." + UUID + ".if");
			String pw = plugin.getConfig().getString("Login.Players." + UUID + ".pw");
			String prefix = ChatColor.GREEN + "[Login+] " + ChatColor.YELLOW;
			if(args.length == 1){
				if(IF.equalsIgnoreCase("REGISTERT")){
					if(pw.equalsIgnoreCase(args[0])){
						plugin.noLoginPlayers.remove(name);
						p.sendMessage(prefix + SL);
					}else p.sendMessage(prefix + WP);
				}else p.sendMessage(prefix + RP);
			}else p.sendMessage(prefix + "/login <pw>");
		}
		return true;
	}
}