import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class GameManager {

    
    private GameMap gameMap;
    private GameGUI gameGUI;
    private int currentGame=0;
    private int detective_wins=0;
    private int misterX_wins=0;
    private int total_rounds=0;
    private boolean XPlayedByHuman=false;
    List<Integer> heatMap = new ArrayList<>(Collections.nCopies(199, 0));
    

    public GameManager(){
        gameGUI = new GameGUI(this);
    }
    
    public void playGames(int gamesCount,int moveTime) throws FileNotFoundException, IOException,InterruptedException,CloneNotSupportedException {
        currentGame=0;
        detective_wins=0;
        misterX_wins=0;
        total_rounds=0;
        heatMap = new ArrayList<>(Collections.nCopies(199, 0));
        XPlayedByHuman=false;
        long startTime = System.currentTimeMillis();
        
        
        while(currentGame<gamesCount){
            currentGame++;
            this.gameMap=new GameMap(XPlayedByHuman);
            gameGUI.drawPlayers(gameMap.getDetectives(),gameMap.getMisterX(),gameMap.getLastMisterXField(),gameMap.getLastMisterXVehicleTypes(),gameMap.getMisterXCloud(),gameMap.getRounds(),currentGame,detective_wins,misterX_wins,total_rounds);
            if (XPlayedByHuman) gameMap.playerToString();
            
            while(gameMap.getGameState()==GameState.ONGOING){
                
                gameMap.makeMove(moveTime);
                gameGUI.drawPlayers(gameMap.getDetectives(),gameMap.getMisterX(),gameMap.getLastMisterXField(),gameMap.getLastMisterXVehicleTypes(),gameMap.getMisterXCloud(),gameMap.getRounds(),currentGame,detective_wins,misterX_wins,total_rounds);
            }
            if (gameMap.getGameState()==GameState.DETECTIVES_WIN){
                detective_wins++;
                total_rounds+=gameMap.getRounds();
                double schnitt=Math.round((double)misterX_wins/(misterX_wins+detective_wins)*10000)/100;
                System.out.print("\r| Game:"+currentGame+" |--------| Wins M:"+misterX_wins+" | Wins D:"+detective_wins+" |--------| Rate:"+
                                    schnitt+"% |--------| "+total_rounds/(currentGame)+" Rounds per Game      "                  
                ) ;
            }else{
                misterX_wins++;
                total_rounds+=gameMap.getRounds();
                double schnitt=Math.round(((double) misterX_wins / (misterX_wins + detective_wins)) * 10000) / 100.0;
                System.out.print("\r| Game:"+currentGame+" |--------| Wins M:"+misterX_wins+" | Wins D:"+detective_wins+" |--------| Rate:"+
                                    schnitt+"% |--------| "+total_rounds/(currentGame+1)+" Rounds per Game      "                  
                ) ;
                
            }

            List<Field> allF=gameMap.getAllFields();
            for (Field field:allF){
                int index=allF.indexOf(field);
                heatMap.set(index, heatMap.get(index)+field.getHeatMapCount());
            }
            
        }
        System.out.println("");
        long endTime = System.currentTimeMillis();
        gameGUI.drawPlayers(gameMap.getDetectives(),gameMap.getMisterX(),gameMap.getLastMisterXField(),gameMap.getLastMisterXVehicleTypes(),gameMap.getMisterXCloud(),gameMap.getRounds(),currentGame,detective_wins,misterX_wins,total_rounds);
        System.out.println("Runtime: " + (endTime - startTime) + " Millisekunden");

        gameGUI.drawHeatMap();

    }

    public GameMap getGameMap(){
        return gameMap;
    }

    public List<Integer> getHeatMap(){
        return heatMap;
    }

    


}
