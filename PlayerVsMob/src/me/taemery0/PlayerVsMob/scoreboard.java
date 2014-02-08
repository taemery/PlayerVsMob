package me.taemery0.PlayerVsMob;

import java.sql.SQLException;

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
	
	public static void statScoreboard (Player player) {
		Scoreboard stat = manager.getNewScoreboard();
		// TODO Config for scoreboard
		Objective main = stat.registerNewObjective("PVM-STATS", "dummy");
		main.setDisplayName(ChatColor.GOLD + "Welcome to PVM!");
		try {
			main.getScore(Bukkit.getOfflinePlayer("Points:")).setScore(mysql.getPoints(player));
		} catch (IllegalStateException | IllegalArgumentException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	private static Scoreboard board = manager.getNewScoreboard();
	private static Objective killsobjective = board.registerNewObjective(ChatColor.GOLD + "PVM-Points", "dummy");
	
	public static void mobKill (Player player){
		int current = killsobjective.getScore(player).getScore();
		killsobjective.getScore(player).setScore(current + 5);
		
	}
	public static void ingameScoreboard (Player player) {
		killsobjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		killsobjective.setDisplayName(ChatColor.GOLD + "PVM Points:");
		player.setScoreboard(board);
	}
	public static void exitgameScoreboard (Player player) throws IllegalStateException, IllegalArgumentException, SQLException{
		mysql.setPoints(player, killsobjective.getScore(player).getScore() + mysql.getPoints(player));
		board.resetScores(player);
		Scoreboard board = manager.getNewScoreboard();
		player.setScoreboard(board);
	}
}
