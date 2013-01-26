import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;


public class testClass {
	 enum GameItem
	 {
		 CreateGame, JoinGame, gameJoined, gameWaiting, checkGameRunning, gameOver
	 }
	
	public static void main(String[] args) throws IOException{

	      String current = "gameWaiting";
	      String CurLine = "";
	      GameItem gameMenu = GameItem.valueOf(current);

	      switch (gameMenu) {
	          case CreateGame:
	        	  break;
	          case JoinGame:
	        	  break;
	          case gameJoined:
	              System.out.println("boring");
	              InputStreamReader converter = new InputStreamReader(System.in);
	              BufferedReader in = new BufferedReader(converter);
	              CurLine =  in.readLine();
	              break;
	          case gameWaiting:
	              System.out.println("getting better");
	              break;
	          case gameOver:
	        	  break;  
	      }
	  }
}
