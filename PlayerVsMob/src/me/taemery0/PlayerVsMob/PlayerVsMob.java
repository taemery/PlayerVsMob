package me.taemery0.PlayerVsMob;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
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
		this.logger.info(pdfFile.getName()
				+ " Has Been Enabled... The Battle Begins");
		this.saveDefaultConfig();
		plugin = this;
		getCommand("pvmadmin").setExecutor(new PVMAdminCommand());
		getCommand("pvm").setExecutor(new PVMCommand());
		if(Vars.setupArenas()){
			this.logger.info(pdfFile.getName() + "'s arenas did not load correctly!");
		}
		getServer().getPluginManager().registerEvents(new BukkitListener(), this);
	}
	
	
	public static Plugin getPlugin() {
		return plugin;
	}

	/*
	 * public boolean onCommand(CommandSender sender, Command cmd, String
	 * commandLabel, String[] args){ if (sender instanceof Player) { final
	 * Player player = (Player) sender;
	 * if(commandLabel.equalsIgnoreCase("pvm")){ //Countdown
	 * this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new
	 * Runnable() { public void run() { if (timer != -1){ if (timer != 0){
	 * player.sendMessage(ChatColor.BLUE + "You have " + timer + " Seconds!");
	 * timer--; } else { player.sendMessage(ChatColor.GOLD +
	 * "Kill! Kill! Kill!"); timer--; } } } }, 0L, 20L);
	 * 
	 * 
	 * 
	 * //End Countdown
	 * 
	 * player.sendMessage(ChatColor.RED + "Teleporting To PVM Arena...");
	 * player.sendMessage(ChatColor.RED + "Coords: " +
	 * player.getLocation().toString()); // for(PotionEffect effect :
	 * player.getActivePotionEffects()) // { //
	 * player.removePotionEffect(effect.getType()); // }
	 * player.addPotionEffect(new
	 * PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, potionTime, 1789));
	 * player.setHealth(20.0); player.setFoodLevel(20); player.setFireTicks(0);
	 * player.getInventory().setHelmet(Helmet);
	 * player.getInventory().setChestplate(Chest);
	 * player.getInventory().setLeggings(Legs);
	 * player.getInventory().setBoots(Boots);
	 * 
	 * Location loc = new Location(w, x, y, z); player.teleport(loc);
	 * 
	 * } } //Non-Player (Console) Commands return false; }
	 */
}