import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DetectiveMoveStrategy {
    public static Move getMove(Detective detective, GameMap gameMap) {

        List<Move> myMoves=gameMap.getLegalMoves(detective,true);
     
        if (myMoves.size()==0) return null;
       
        int bestScore=1000;
        Move bestMove=null;

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
        }else{

            HashMap<Integer,List<List<Integer>>> graph=gameMap.getGraph();
        
            int maxActivity=Integer.MIN_VALUE;

            for (Move move:myMoves){
                Field targetField = move.getTargetField();
                int key=targetField.getId();
                int activity=graph.get(key).get(0).size()+graph.get(key).get(1).size()+graph.get(key).get(2).size();

                if (activity>maxActivity){
                    maxActivity=activity;
                    bestMove=move;
                }
            }

            
            

            
        }

        return (bestMove!=null)?bestMove:myMoves.get(0);

    }
}

