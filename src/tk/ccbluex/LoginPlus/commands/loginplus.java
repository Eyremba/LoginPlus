package tk.ccbluex.LoginPlus.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import tk.ccbluex.LoginPlus.LoginPlus;

public class loginplus implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(LoginPlus.prefix + "[Commands]");
		sender.sendMessage("§7:§e /register");
		sender.sendMessage("§7:§e /login");
		sender.sendMessage("§7:§e /changepassword");
		return true;
	}
}