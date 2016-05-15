package tk.ccbluex.LoginPlus;

import tk.ccbluex.LoginPlus.commands.changepassword;
import tk.ccbluex.LoginPlus.commands.login;
import tk.ccbluex.LoginPlus.commands.loginplus;
import tk.ccbluex.LoginPlus.commands.register;
import tk.ccbluex.LoginPlus.listeners.Events;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginPlus extends JavaPlugin {
	
	public static LoginPlus INSTANCE;
	public static String prefix = "§l§7[§r§eLogin+§l§7] " + ChatColor.GOLD;
	
	public ArrayList<String> noLogin = new ArrayList<>();
	public ArrayList<String> noRegister = new ArrayList<>();
	
	public YamlConfiguration messages;
	
	@Override
	public void onEnable(){
		INSTANCE = this;
		
		getCommand("loginplus").setExecutor(new loginplus());
		getCommand("login").setExecutor(new login());
		getCommand("register").setExecutor(new register());
		getCommand("changepassword").setExecutor(new changepassword());
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		
		getConfig().options().header("[Login+] By CCBlueX");
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		try{
			File MessageFile = new File("plugins/LoginPlus/messages.yml");
			messages = YamlConfiguration.loadConfiguration(MessageFile);
			messages.addDefault("NoPerm", "You have no permissions.");
			//Login Command Messages!
			messages.addDefault("LoginCommand.RP", "Register for first please! with /register <pw> <pw>");
			messages.addDefault("LoginCommand.WP", "Wrong password");
			messages.addDefault("LoginCommand.SL", "You have successfully logged in!");
			//Register Command Messages!
			messages.addDefault("RegisterCommand.SR", "You have successfully Register!");
			messages.addDefault("RegisterCommand.twopw", "The two passwords do not match");
			messages.addDefault("RegisterCommand.Yarl", "You are already registriert do /login <pw> Join");
			messages.options().copyDefaults(true);
			messages.save(MessageFile);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}