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

public class Vars {
	static Plugin plugin = PlayerVsMob.plugin;	
	
	public static Location lobby = new Location(Bukkit.getWorld(plugin.getConfig().getString("lobby.w")),
			plugin.getConfig().getDouble("lobby.x"),  plugin.getConfig().getDouble("lobby.y"), plugin.getConfig().getDouble("lobby.z"));
	public static boolean lobbyTeleport = plugin.getConfig().getBoolean("lobby.teleport-on-join", false);
	
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
