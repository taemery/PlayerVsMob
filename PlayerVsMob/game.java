package me.taemery0.PlayerVsMob;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class game {
	public static void join(Player player, String arenas){
		Vars vars = new Vars();
		player.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.GRAY
				+ "Teleported To PVM Arena");
		player.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.GOLD
				+ "You have 15 seconds of invulnerability");

		player.addPotionEffect(new PotionEffect(
				PotionEffectType.DAMAGE_RESISTANCE, vars.tickTimer,
				1789));
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.getInventory().setHelmet(vars.helmet);
		player.getInventory().setChestplate(vars.chestplate);
		player.getInventory().setLeggings(vars.leggings);
		player.getInventory().setBoots(vars.boots);

		player.teleport(Vars.locations(arenas));
		
		Vars.isInGame.put(player, true);
	}
	public static void exit(Player player){
		player.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.GREEN + "Exiting Game");
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.getInventory().clear();
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
		Vars vars = new Vars();
		player.teleport(vars.lobby);
		
		// player.setScoreboard(null);
		
		Vars.isInGame.put(player, false);
	}

}