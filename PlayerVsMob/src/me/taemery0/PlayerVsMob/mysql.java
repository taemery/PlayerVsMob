package me.taemery0.PlayerVsMob;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class mysql {
    private static java.sql.Connection sqlConnection;

    public java.sql.Connection getMyConnection(){
        return sqlConnection;
    }
    private static void connect() throws SQLException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(Vars.dburl, Vars.user, Vars.password);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new SQLException("Cannot connect to the database because: " + ex.getMessage());
        }
    }
    public static int getPoints(Player player) throws SQLException{
    	mysql.connect();
    	int pointsInt = 0;
    	String query;
    	PreparedStatement preparedStatement;
    	String points = "";
    	query = "SELECT * FROM playerPoints WHERE player = ?";
    	preparedStatement = sqlConnection.prepareStatement(query);
    	preparedStatement.setString(1, player.getDisplayName());
    	ResultSet resultset = preparedStatement.executeQuery();
    	while (resultset.next()) {
    		points = resultset.getString("points");
    	}
    	try { 
			pointsInt = Integer.parseInt(points);
	    } catch(NumberFormatException e) { 
	        return pointsInt; 
	    }
		return pointsInt;
    }
    public static void setPoints(Player player, int points) throws SQLException{
    	mysql.connect();
    	String query = "UPDATE playerPoints " + "SET points = ? " + "WHERE player = ?";
    	PreparedStatement preparedStatement = sqlConnection.prepareStatement(query);
    	preparedStatement.setInt(1, points);
        preparedStatement.setString(2, player.getDisplayName());
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected != 1)
        {
            throw new SQLException("Player Not Found");
        }
    }
}