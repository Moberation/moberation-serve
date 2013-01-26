package Actors;

public class Players {
	String name;
	int playerID;
	int points;
	int roleID;
	int gameID;
	
	public void setName(String name)
	{
		this.name = name;
	}
		
	public void setPlayerID(int playerID)
	{
		this.playerID = playerID;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public void setGameID(int gameID)
	{
		this.gameID = gameID;
	}
	
	public void setRole(int roleID)
	{
		this.roleID = roleID;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPlayerID()
	{
		return playerID;
	}
	
	public int getGameID()
	{
		return this.gameID;
	}
	
	public int getRole()
	{
		return this.roleID;
	}
}
