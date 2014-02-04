package me.taemery0.PlayerVsMob;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PVMCommand implements CommandExecutor {
	final Vars vars = new Vars();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if(args[0] == null){
				sender.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.RED + "Please select an arena");
				return false;
			}
			if(Vars.isInGame.containsKey(player)){
		        if(Vars.isInGame.get(player)){
		            game.exit(player);
		            Vars.unboard(player);
			        player.playSound(vars.lobby, Sound.AMBIENCE_THUNDER, 1, 1);
		        } else {
		            game.join(player, args[0]);
		            Vars.board(player);
		        }
		    } else {
		        game.join(player, args[0]);
		        Vars.board(player);
		    }
			/*
			 * this.getServer().getScheduler().scheduleSyncRepeatingTask(this,
			 * new Runnable() { public void run() { if (timer != -1){ if (timer
			 * != 0){ player.sendMessage(ChatColor.BLUE + "You have " + timer +
			 * " Seconds!"); timer--; } else { player.sendMessage(ChatColor.GOLD
			 * + "Kill! Kill! Kill!"); timer--; } } } }, 0L, 20L);
			 */


		} else {
			sender.sendMessage(ChatColor.RED
					+ "You must be a player to run that command");
			sender.sendMessage(ChatColor.BLUE + "Try /pvmadmin");
		}
		// Non-Player (Console) Commands
		return false;		
	}
}
