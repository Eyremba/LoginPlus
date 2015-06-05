package gg.web.mcb.LoginPlus.main;

import gg.web.mcb.LoginPlus.Commands.ChangePassword;
import gg.web.mcb.LoginPlus.Commands.LoginC;
import gg.web.mcb.LoginPlus.Commands.LoginPlusCommand;
import gg.web.mcb.LoginPlus.Commands.Register;
import gg.web.mcb.LoginPlus.Listener.Events;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	
	public ArrayList<String> noLoginPlayers = new ArrayList<String>();
	public ArrayList<String> noConfigPlayer = new ArrayList<String>();
	public YamlConfiguration M;
	File Mfile;
	
	@Override
	public void onEnable(){
		getCommand("loginplus").setExecutor(new LoginPlusCommand(this));
		getCommand("login").setExecutor(new LoginC(this));
		getCommand("register").setExecutor(new Register(this));
		getCommand("ChangePassword").setExecutor(new ChangePassword(this));
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		
		getConfig().options().header("[Login+] By Marco606598 Youtube: Marco Mayer https://www.youtube.com/channel/UCeuZKKGXKe8wUw2R5FB5fxA");
		getConfig().addDefault("Login.boolean.Register", "true");
		getConfig().addDefault("Login.boolean.SpawnLocation", "false");
		getConfig().addDefault("Login.Spawn.SpawnLocation.X", "0");
		getConfig().addDefault("Login.Spawn.SpawnLocation.Y", "0");
		getConfig().addDefault("Login.Spawn.SpawnLocation.Z", "0");
		getConfig().addDefault("Login.Spawn.SpawnLocation.World", "world");
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		Mfile = new File("plugins/LoginPlus/messages.yml");
		M = YamlConfiguration.loadConfiguration(Mfile);
		//Login Command Messages!
		M.addDefault("Messages.LoginCommand.RP", "Register for first please! with /register <pw> <pw>");
		M.addDefault("Messages.LoginCommand.WP", "Wrong password");
		M.addDefault("Messages.LoginCommand.SL", "You have successfully logged in!");
		//Register Command Messages!
		M.addDefault("Messages.RegisterCommand.SR", "You have successfully Register!");
		M.addDefault("Messages.RegisterCommand.twopw", "The two passwords do not match");
		M.addDefault("Messages.RegisterCommand.bar", "No one is allowed to registers!");
		M.addDefault("Messages.RegisterCommand.Yarl", "You are already registriert do /login <pw> Join");
		//LoginPlus Command Messages!
		M.addDefault("Messages.LoginPlusCommand.SystemPlayer", "You must be a player!");
		M.addDefault("Messages.LoginPlusCommand.SSS", "Spawn Successfully set!");
		M.options().copyDefaults(true);
		
		try{
			M.save(Mfile);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}