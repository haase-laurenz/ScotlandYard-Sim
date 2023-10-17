import java.io.FileNotFoundException;
import java.io.IOException;


public class GameManager {

    
    private GameMap gameMap;
    private GameGUI gameGUI;
    

    public GameManager(){
    }
    
    public void playGames(int gamesCount) throws FileNotFoundException, IOException,InterruptedException {
        int currentGame=0;
        int detective_wins=0;
        int misterX_wins=0;
        int total_rounds=0;
        long startTime = System.currentTimeMillis();
        boolean XPlayedByHuman=false;
        gameGUI = new GameGUI();
        while(currentGame<gamesCount){
            this.gameMap=new GameMap(XPlayedByHuman);
            //gameGUI.drawPlayers(gameMap.getDetectives(),gameMap.getMisterX());
            if (XPlayedByHuman) gameMap.playerToString();
            
            while(gameMap.getGameState()==GameState.ONGOING){
                gameMap.makeMove();
                //gameGUI.drawPlayers(gameMap.getDetectives(),gameMap.getMisterX());
            }
            if (gameMap.getGameState()==GameState.DETECTIVES_WIN){
                detective_wins++;
                total_rounds+=gameMap.getRounds();
                double schnitt=Math.round((double)misterX_wins/(misterX_wins+detective_wins)*10000)/100;
                System.out.print("\r| Game:"+currentGame+" |--------| Wins M:"+misterX_wins+" | Wins D:"+detective_wins+" |--------| Rate:"+
                                    schnitt+"% |--------| "+total_rounds/(currentGame+1)+" Rounds per Game      "                  
                ) ;
            }else{
                misterX_wins++;
                total_rounds+=gameMap.getRounds();
                double schnitt=Math.round((double)misterX_wins/(misterX_wins+detective_wins)*10000)/100;
                System.out.print("\r| Game:"+currentGame+" |--------| Wins M:"+misterX_wins+" | Wins D:"+detective_wins+" |--------| Rate:"+
                                    schnitt+"% |--------| "+total_rounds/(currentGame+1)+" Rounds per Game      "                  
                ) ;
            }
            currentGame++;
        }
        System.out.println("");
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " Millisekunden");

    }

    public GameMap getGameMap(){
        return gameMap;
    }

    


}
