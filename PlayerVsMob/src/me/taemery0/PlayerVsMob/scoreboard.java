package me.taemery0.PlayerVsMob;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class scoreboard {

	public scoreboard() {
		// TODO Auto-generated constructor stub
	}
	
	// private Plugin plugin = PlayerVsMob.plugin;
	
	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
	
	private static Scoreboard board = manager.getNewScoreboard();
	private static Objective killsobjective = board.registerNewObjective(ChatColor.GOLD + "PVM - Points", "dummy");
	
	public static void statScoreboard (Player player) {
		Scoreboard stat = manager.getNewScoreboard();
		// TODO Config for scoreboard
		Objective main = stat.registerNewObjective(ChatColor.GOLD + "PlayerVsMob", "dummy");
		main.setDisplayName(ChatColor.GOLD + "Welcome to PVM!");
		main.getScore(Bukkit.getOfflinePlayer("Points:")).setScore(0);
		main.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard(stat);
	}
	
	public static void playerStats (Player player) {
		/*
		Scoreboard playerStatsBoard = manager.getNewScoreboard();
		Objective objective = playerStatsBoard.registerNewObjective("PVM", "dummy");
		objective.getScore(Bukkit.getOfflinePlayer("Test")).setScore(1);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard(playerStatsBoard);
		*/
	}
	public static void mobKill (Player player){
		int current = killsobjective.getScore(player).getScore();
		killsobjective.getScore(player).setScore(current + 5);
		
	}
	public static void board (Player player) {
		killsobjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard(board);
	}
	public static void unboard (Player player){
		Scoreboard board = manager.getNewScoreboard();
		player.setScoreboard(board);
	}
	public static void resetPlayerScore (Player player){	
		board.resetScores(player);
	}
}
