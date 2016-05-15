package tk.ccbluex.LoginPlus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.ccbluex.LoginPlus.LoginPlus;
import tk.ccbluex.LoginPlus.util.StringCoder;

public class login implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		Player p = (Player)sender;
		if(p.hasPermission("Login+.Login")){
			String name = p.getName();
			boolean register = LoginPlus.INSTANCE.getConfig().getString("Player." + name + ".if").equalsIgnoreCase("true");
			String password = LoginPlus.INSTANCE.getConfig().getString("Player." + name + ".password");
			if(args.length > 0){
				if(register){
					if(StringCoder.decode(password).equalsIgnoreCase(args[0])){
						LoginPlus.INSTANCE.noLogin.remove(name);
						p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("LoginCommand.SL"));
					}else
						p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("LoginCommand.WP"));
				}else
					p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("LoginCommand.RP"));
			}else
				p.sendMessage(LoginPlus.prefix + "/login <pw>");
		}else
			p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("NoPerm"));
		return true;
	}
}