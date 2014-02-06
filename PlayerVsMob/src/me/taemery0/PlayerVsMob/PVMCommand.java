package me.taemery0.PlayerVsMob;

import org.bukkit.ChatColor;
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
			if (Vars.isInGame.containsKey(player)) {
				if (Vars.isInGame.get(player)) {
					game.exit(player);
					scoreboard.unboard(player);
				} else {
					if (args.length == 1) {
						game.join(player, args[0]);
						scoreboard.board(player);
					}else{
						player.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.RED + "Must specify arena!");
					}
				}
			} else {
				if (args.length == 1) {
					game.join(player, args[0]);
					scoreboard.board(player);
				}else{
					player.sendMessage(ChatColor.BLUE + "[PVM] " + ChatColor.RED + "Must specify arena!");
				}
			}

		} else {
			sender.sendMessage(ChatColor.RED
					+ "You must be a player to run that command");
			sender.sendMessage(ChatColor.BLUE + "Try /pvmadmin");
		}
		return false;
	}
}
