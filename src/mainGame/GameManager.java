package mainGame;

import java.util.*;

public class GameManager {
	List  <Integer> playerID = new ArrayList<Integer>();
	List <Integer>roles = new ArrayList<Integer>();
	int roleID;
	int roleSize;
	int playerSize;
	String s;
	String gameName;
	boolean gameOver = true;
	
	
	public void setPlayerID(int playerID)
	{
		if(this.playerID.size() < 3)
			this.playerID.add(playerID);
		else
			s = "No more players on this game";
	}
	
	public void setRole(int roleID)
	{
		for(int i = 0; i < 3; i++)
		{
			if(!roles.contains(roleID) && roles.size() < 3)
				{
					roles.add(roleID);
					roleSize++;	
				}
				
		}
	}
	
	public void setPlayerSize()
	{
		this.playerSize++;
	}
	
	public void startGame(boolean gameOver)
	{
		this.gameOver = false;
	}
	
	public void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}
	
	public void setGameName(String gameName)
	{
		this.gameName = gameName;
	}
		
	public boolean getGameOver()
	{
		return this.gameOver;
	}
	
	public int getRole(int roleID)
	{
		return this.roleID = roleID;
	}
	
	public int getRoleSize()
	{
		return roleSize;
	}
	
	public String gettGameName()
	{
		return this.gameName;
	}
	
	public int gettPlayerSize()
	{
		return this.playerSize;
	}
}
