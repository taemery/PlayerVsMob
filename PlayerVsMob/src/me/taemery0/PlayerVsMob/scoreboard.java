package me.taemery0.PlayerVsMob;

import org.bukkit.Bukkit;
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
	private static Objective objective = board.registerNewObjective("PVM - Kills", "dummy");
	
	public static void playerStats (Player player) {
		/*
		Scoreboard playerStatsBoard = manager.getNewScoreboard();
		Objective objective = playerStatsBoard.registerNewObjective("PVM", "dummy");
		objective.getScore(Bukkit.getOfflinePlayer("Test")).setScore(1);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		player.setScoreboard(playerStatsBoard);
		*/
	}
	public static void board (Player player) {
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
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
