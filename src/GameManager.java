import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GameManager {

    
    private GameMap gameMap;
    private List<Detective> detectives;
    private MisterX misterX;
    private Player currentplayer;
    

    public GameManager() throws FileNotFoundException, IOException {
        this.gameMap=new GameMap();
    
    }

    
    public void playGames(int gamesCount){
        int currentGame=0;
        
        while(currentGame<gamesCount){
            while(gameMap.getGameState()==GameState.ONGOING){
                Move nextMove=currentplayer.getMove();
                gameMap.makeMove(nextMove);
            }
            if (gameMap.getGameState()==GameState.DETECTIVES_WIN){
                System.out.println("DETECTIVES WIN");
            }else{
                System.out.println("MISTERX WIN");
            }
            currentGame++;
        }

    }

    


}
