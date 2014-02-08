package me.taemery0.PlayerVsMob;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerVsMob extends JavaPlugin{

	public static PlayerVsMob plugin;
	public final Logger logger = Logger.getLogger("Minecraft");

	public void onDisable() {
		for (Player key : Vars.isInGame.keySet()) {
		    game.exit(key);
		}
	}
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.saveDefaultConfig();
		plugin = this;
		getCommand("pvmadmin").setExecutor(new PVMAdminCommand());
		getCommand("pvm").setExecutor(new PVMCommand());
		if(Vars.setupArenas()){
			this.logger.info(pdfFile.getName() + "'s arenas did not load correctly!");
		}
		getServer().getPluginManager().registerEvents(new BukkitListener(), this);
	}
}