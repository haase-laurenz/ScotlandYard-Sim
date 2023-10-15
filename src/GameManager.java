import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GameManager {

    
    private GameMap gameMap;
    

    public GameManager(){
    }
    
    public void playGames(int gamesCount) throws FileNotFoundException, IOException {
        int currentGame=0;
        this.gameMap=new GameMap();
        while(currentGame<gamesCount){
            while(gameMap.getGameState()==GameState.ONGOING){
                gameMap.makeMove();
            }
            if (gameMap.getGameState()==GameState.DETECTIVES_WIN){
                System.out.println("DETECTIVES WIN");
            }else{
                System.out.println("MISTER-X WIN");
            }
            currentGame++;
        }

    }

    


}
