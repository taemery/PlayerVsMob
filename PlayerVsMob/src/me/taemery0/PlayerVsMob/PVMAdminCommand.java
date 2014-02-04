package me.taemery0.PlayerVsMob;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PVMAdminCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Vars vars = new Vars();
		// arg0.sendMessage(ChatColor.GOLD + "X: " + PlayerVsMob.x + " | Y:" +
		// PlayerVsMob.y + " | Z:" + PlayerVsMob.z);
		// static vars = new PlayerVsMob().vars();
		if (args.length < 1) {
			return false;
		} else if (args.length == 1) {
			String singlearg = args[0];
			if (singlearg.equalsIgnoreCase("coords")) {
				sender.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.RED + "[Admin] " + ChatColor.GOLD + "X: " + vars.x + " | Y: " + vars.y + " | Z: " + vars.z);
				return true;
			}
			if (singlearg.equalsIgnoreCase("tp")) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					Location loc = new Location(vars.w, vars.x, vars.y, vars.z);
					player.teleport(loc);
					sender.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.RED + "[Admin] " + ChatColor.GOLD + "Teleported to Arena");
					return true;
				} else {
					sender.sendMessage(ChatColor.RED
							+ "You must be a player to run that command");
					return true;
				}
			} else if (singlearg.equalsIgnoreCase("removeall")) {
				sender.sendMessage(Vars.isInGame.toString());
				sender.sendMessage(ChatColor.BLUE + "----Players Removed----");
				for (Player key : Vars.isInGame.keySet()) {
					sender.sendMessage(ChatColor.BLUE + key.getDisplayName());
					game.exit(key);
				}
				return true;
			}else if (singlearg.equalsIgnoreCase("reload")) {
				PlayerVsMob.getPlugin().reloadConfig();
				sender.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.RED + "[Admin] " + ChatColor.GOLD + "Configuration Files Reloaded.");
				return true;
			}
		}
		return false;
	}

}
