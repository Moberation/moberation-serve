package Menu;
import Actors.*;
import mainGame.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/JoinGame")
public class JoinGame extends HttpServlet {
	 enum GameItem
	 {
		 CreateGame, JoinGame, gameJoined, gameWaiting, checkGameRunning, gameOver
	 }
	
	private static final long serialVersionUID = 1L;
	
	final int MAX_PLAYERS = 2;
	
    List <Players> playerList = new ArrayList <Players> ();
    List <GameManager> games = new ArrayList <GameManager> ();


    public JoinGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrintWriter out = response.getWriter();
		String menuItem = GameItem.JoinGame.toString(); //request.getParameter("menuIitem");
		String playerName = "Charles"; //request.getParameter("PlayerName");
		String gameName = "game123";//request.getParameter("GameName");
		String playerID = request.getParameter("playerID");
		String gameID = request.getParameter("gameID");
		String roleID = request.getParameter("roleID");
		//If create game button is pushed
		  String current = menuItem;
		  GameItem gameMenu = GameItem.valueOf(current);
		  
		  switch(gameMenu)
		  {
		  	case CreateGame:
		  		createGame(playerName, gameName);		  
				games.get(games.size()-1).setPlayerSize();
				
				int pid = playerList.get(games.size()-1).getPlayerID();
				int gid = games.size()-1;				
				String playerArray = "PlayerID=" + pid + "GameID"+gid;
				out.write(playerArray);
		  		break;
		  		
		  	case JoinGame:
		  		//request.getParameter("gameID");
				//request.getParameter("PlayerID");
				//If no games found
				if (games.isEmpty())
					out.write("No games found");
				else
				{
					//Listing all games available
					String gameArray = listGames();	
					out.write(gameArray);	
				}
		  		break;
		  		
		  	case gameJoined:
				joinGame(gameID ,  playerID, request.getParameter("roleID"));
				Boolean startGame = false;							
				if(games.get(Integer.parseInt(gameID)).gettPlayerSize() >= 2)
				{
					startGame = true;
					games.get(Integer.parseInt(gameID)).startGame(startGame);
				}		
		  		break;
		  		
		  	case gameWaiting:
		  		String gameId2 = request.getParameter("gameID");  		
				String gameStarted =checkGameWaiting(gameId2);			
				out.write(gameStarted);
		  		break;
		  		
		  	case checkGameRunning:
		  		String gameId3 = request.getParameter("gameID");
				String gameRunning = isGameRunning(gameId3);
				out.write("running=" + gameRunning);				
		  		break;
		  		
		  	case gameOver:
		  		String gameId4 = request.getParameter("gameID");
				games.get(Integer.parseInt(gameId4)).setGameOver(true);
		  		break;
		  }	
		}
	
	public void createPlayer(String playerName, int GameID)
	{
		Players player = new Players();
		player.setName(playerName);
		player.setPlayerID(playerList.size()-1);
		player.setGameID(GameID);
		
		playerList.add(player);
	}
		
	public void createGame(String playerName, String gameName)
	{
		GameManager game = new GameManager();
    	game.setGameName(gameName);
		games.add(game);
		
		Players player = new Players();
		player.setName(playerName);
		player.setPlayerID(playerList.size()-1);
		player.setGameID(games.size()-1);
		
		playerList.add(player);
				
	}
	
	public String listGames()
	{
		String gameArray = "";
		for (int i = 0; i < games.size(); i++)
		{
			if(games.get(i).gettPlayerSize() >= MAX_PLAYERS)
				gameArray += "id="+i; 
		}			
		return gameArray;
	}
	
	public void joinGame(String playerInD, String gameID,  String roleID)
	{		
		int gid = Integer.parseInt(gameID);
		int pid = Integer.parseInt(playerInD);
		int rid  = Integer.parseInt(roleID);
		playerList.get(pid).setGameID(gid);
		playerList.get(rid).setRole(rid);
		games.get(gid).setPlayerSize();
	}
		
	public String checkGameWaiting(String gameID)
	{
		boolean startGame = false;	
		String gameStarted;
		if(games.get(Integer.parseInt(gameID)).gettPlayerSize() >= 2)
		{
			startGame = true;
			games.get(Integer.parseInt(gameID)).startGame(startGame);
			return gameStarted = "gameStarted=true";
			
		}
		else
		{
			return gameStarted = "gameStarted=false";
			
		}
	}
	
	public String isGameRunning(String gameID)
	{
		String gameRunning = "";
		if(games.get(Integer.parseInt(gameID)).getGameOver() == true)
		{
			return gameRunning = "false";
		}
		else
		{
			return gameRunning = "true";
		}
	}

}









/*
if(menuItem == GameItem.CreateGame.toString())
{
	createGame(playerName, gameName);		  
	games.get(games.size()-1).setPlayerSize();
	int pid = playerList.get(games.size()-1).getPlayerID();
	int gid = games.size()-1;
	
	String playerArray = "PlayerID=" + pid + "GameID"+gid;
	out.write(playerArray);
}

//If join game button is pushed
else if(menuItem == GameItem.JoinGame.toString())
{
	request.getParameter("gameID");
	request.getParameter("PlayerID");
	
	//If no games found
	if (games.isEmpty())
		out.write("No games found");

	else
	{
		games.get(0).setPlayerSize();
		games.get(0).setPlayerSize();
		
		String gameArray = "";
		for (int i = 0; i < games.size(); i++)
		{
			if(games.get(i).gettPlayerSize() >= MAX_PLAYERS)
				gameArray += "id="+i; 
		}
		
		out.write(gameArray);	
	}	
}
	else if(menuItem == "gameJoined")
	{	
		String gameId = request.getParameter("gameID");
		joinGame(gameId ,  request.getParameter("playerID"), request.getParameter("roleID"));
		Boolean startGame = false;			
		
		if(games.get(Integer.parseInt(gameId)).gettPlayerSize() >= 2)
		{
			startGame = true;
			games.get(Integer.parseInt(gameId)).startGame(startGame);
		}			
	}

	else if(menuItem == "gameWaiting")
	{
		String gameId = request.getParameter("gameID");
		boolean startGame = false;	
		String gameStarted = "";
		
		if(games.get(Integer.parseInt(gameId)).gettPlayerSize() >= 2)
		{
			startGame = true;
			games.get(Integer.parseInt(gameId)).startGame(startGame);
			gameStarted = "gameStarted=true";
			out.write(gameStarted);
		}
		else
		{
			gameStarted = "gameStarted=false";
			out.write(gameStarted);
		}
	}

	else if (menuItem == "checkGameRunning")
	{
		String gameId = request.getParameter("gameID");
		String gameRunning;
		
		if(games.get(Integer.parseInt(gameId)).getGameOver() == true)
		{
			gameRunning = "false";
			out.write("running=" + gameRunning);
		}
		else
		{
			gameRunning = "true";
			out.write("running=" + gameRunning);
		}
	
	}
	
	else if (menuItem == "gameOver")
	{
		String gameId = request.getParameter("gameID");
		games.get(Integer.parseInt(gameId)).setGameOver(true);
	}
	*/
