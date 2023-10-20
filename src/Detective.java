import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Detective extends Player{

    


    public Detective(int id,Field currentField,boolean playedByHuman,HashMap<VehicleType,Integer> tickets){
        super(id, currentField,playedByHuman,tickets);
       
    }

    @Override
    public Move getMove(GameMap gameMap){

        List<Move> myMoves=gameMap.getLegalMoves(this,true);
     
        if (myMoves.size()==0) return null;
       
        int bestScore=1000;
        Move bestMove=myMoves.get(0);
        
        if (gameMap.getMisterXCloud().size()>0){
            
            for (Move move:myMoves){
                
                int minDist=1000;
                for(Field field:gameMap.getMisterXCloud()){
                    int distance=gameMap.distanceBetween(move.getTargetField(), field,true);
                    if (distance<minDist){
                        minDist=distance;
                    }
                }
                if (minDist<bestScore){
                    bestScore=minDist;
                    bestMove=move;
                }
                
            }
        }

        return bestMove;
    }

    public HashMap<VehicleType,Integer> getTickets(){
        return tickets;
    }


}
