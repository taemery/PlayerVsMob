package me.taemery0.PlayerVsMob;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Vars {
	static Plugin plugin = PlayerVsMob.plugin;	
	
	public double lobbyx = plugin.getConfig().getDouble("lobby.x");
	public double lobbyy = plugin.getConfig().getDouble("lobby.y");
	public double lobbyz = plugin.getConfig().getDouble("lobby.z");
	public World lobbyw = Bukkit.getWorld(plugin.getConfig().getString("lobby.w"));
	public Location lobby = new Location(lobbyw, lobbyx, lobbyy, lobbyz);

	public static boolean setupArenas () {
		return false;
	}
	public int timer = plugin.getConfig().getInt("timer");
	public int tickTimer = timer * 20;
	public static Map<Player, Boolean> isInGame = new HashMap<Player, Boolean>();

	@SuppressWarnings("deprecation")
	ItemStack helmet = new ItemStack(Material.getMaterial(plugin.getConfig()
			.getInt("helmet")));
	@SuppressWarnings("deprecation")
	ItemStack chestplate = new ItemStack(Material.getMaterial(plugin
			.getConfig().getInt("chestplate")));
	@SuppressWarnings("deprecation")
	ItemStack leggings = new ItemStack(Material.getMaterial(plugin.getConfig()
			.getInt("leggings")));
	@SuppressWarnings("deprecation")
	ItemStack boots = new ItemStack(Material.getMaterial(plugin.getConfig()
			.getInt("boots")));
	
	//Arena Locations
	public static Location arenas(int arena){
		double x = plugin.getConfig().getDouble("arenas." + arena + ".x");
		double y = plugin.getConfig().getDouble("arenas." + arena + ".y");
		double z = plugin.getConfig().getDouble("arenas." + arena + ".z");
		World w = Bukkit.getWorld(plugin.getConfig().getString("arenas." + arena + ".w"));
		return new Location(w,x,y,z);
	}
	//End Arena Locations
	
	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
	private static Scoreboard board = manager.getNewScoreboard();
	private static Objective objective = board.registerNewObjective("Kills", "totalKillCount");
	
	public static void board (Player player) {
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard(board);
	}
	public static void unboard (Player player){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		player.setScoreboard(board);
	}
	public static void resetPlayerScore (Player player){	
		board.resetScores(player);
	}
	
	public static boolean isInteger(String string){
		try { 
			@SuppressWarnings("unused")
			int a = Integer.parseInt(string);
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	public static boolean isActiveArena(String string){
		int a;
		try { 
			a = Integer.parseInt(string);
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    if(plugin.getConfig().getBoolean("arenas." + a + ".active", false)){
	    	return true;
	    }
	    return false;
	}
}
