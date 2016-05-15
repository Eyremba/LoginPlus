package tk.ccbluex.LoginPlus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.ccbluex.LoginPlus.LoginPlus;
import tk.ccbluex.LoginPlus.util.StringCoder;

public class register implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		Player p = (Player)sender;
		if(p.hasPermission("Login+.register")){
			String name = p.getName();
			boolean register = LoginPlus.INSTANCE.getConfig().getString("Player." + name + ".if").equalsIgnoreCase("true");
			
			if(args.length > 1){
				if(!register){
					if(args[0].equalsIgnoreCase(args[1])){
						LoginPlus.INSTANCE.getConfig().set("Player." + name + ".if", true);
						LoginPlus.INSTANCE.getConfig().set("Player." + name + ".password", StringCoder.encode(args[0]));
						LoginPlus.INSTANCE.saveConfig();
						LoginPlus.INSTANCE.noLogin.remove(name);
						LoginPlus.INSTANCE.noRegister.remove(name);
						p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("RegisterCommand.SR"));
					}else
						p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("RegisterCommand.twopw"));
				}else
					p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("RegisterCommand.Yarl"));
			}else
				p.sendMessage(LoginPlus.prefix + "/register <pw> <pw>");
		}else
			p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("NoPerm"));
		return true;
	}
}