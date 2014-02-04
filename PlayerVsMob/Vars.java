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
	static Plugin plugin = PlayerVsMob.getPlugin();
	// public static double x{
	// return plugin.getConfig().getDouble("X");
	// }
	public double x = plugin.getConfig().getDouble("X");
	public double y = plugin.getConfig().getDouble("Y");
	public double z = plugin.getConfig().getDouble("Z");
	public World w = Bukkit.getWorld(plugin.getConfig().getString("WORLD"));
	
	public static Location arena0 = new Location(Bukkit.getWorld(plugin.getConfig().getString("arenas.0.w")), plugin.getConfig().getDouble("arenas.0.x"), plugin.getConfig().getDouble("arenas.0.y"), plugin.getConfig().getDouble("arenas.0.z"));
	public static Location arena1 = new Location(Bukkit.getWorld(plugin.getConfig().getString("arenas.0.w")), plugin.getConfig().getDouble("arenas.0.x"), plugin.getConfig().getDouble("arenas.0.y"), plugin.getConfig().getDouble("arenas.0.z"));
	public static Location arena2 = new Location(Bukkit.getWorld(plugin.getConfig().getString("arenas.0.w")), plugin.getConfig().getDouble("arenas.0.x"), plugin.getConfig().getDouble("arenas.0.y"), plugin.getConfig().getDouble("arenas.0.z"));	
	
	public static Location locations(String arenas) {
		if(arenas == "0"){
			return arena0;
		}else if(arenas == "1"){
			return arena1;
		}else if(arenas == "2"){
			return arena2;
		}else{
			return arena0;
		}
	}
	
	
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
}
