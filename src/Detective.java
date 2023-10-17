import java.util.ArrayList;
import java.util.List;

public class Detective extends Player{

    public Detective(int id,Field currentField,boolean playedByHuman){
        super(id, currentField,playedByHuman);
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
                    int distance=gameMap.distanceBetween(move.getTargetField(), field);
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


}
