package tk.ccbluex.LoginPlus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.ccbluex.LoginPlus.LoginPlus;
import tk.ccbluex.LoginPlus.util.StringCoder;

public class changepassword implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			String name = p.getName();
			boolean register = LoginPlus.INSTANCE.getConfig().getString("Player." + name + ".if").equalsIgnoreCase("true");
			String password = LoginPlus.INSTANCE.getConfig().getString("Player." + name + ".password");
			if(p.hasPermission("Login+.changepassword")){
				if(args.length > 1){
					if(register){
						if(!LoginPlus.INSTANCE.noLogin.contains(p.getName())){
							if(StringCoder.decode(password).equalsIgnoreCase(args[0])){
								LoginPlus.INSTANCE.getConfig().set("Player." + name + ".password", StringCoder.encode(args[1]));
								LoginPlus.INSTANCE.saveConfig();
								p.sendMessage(LoginPlus.prefix + "Successfully password changed");
							}else
								p.sendMessage(LoginPlus.prefix + "Wrong password");
						}else
							p.sendMessage(LoginPlus.prefix + "Please login with /Login <pw>");
					}else
						p.sendMessage(LoginPlus.prefix + "Register first please with /register <pw> <pw>");
				}else
					p.sendMessage(LoginPlus.prefix + "/changepassword <old Password> <new Password>");
			}else
				p.sendMessage(LoginPlus.prefix + LoginPlus.INSTANCE.messages.getString("NoPerm"));
		}else
			sender.sendMessage("[LoginPlus] You must be a Player!");
		return true;
	}
}