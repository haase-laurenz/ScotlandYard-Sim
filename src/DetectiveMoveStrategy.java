import java.util.List;

public class DetectiveMoveStrategy {
    public static Move getMove(Detective detective, GameMap gameMap) {

        List<Move> myMoves=gameMap.getLegalMoves(detective,true);
     
        if (myMoves.size()==0) return null;
       
        int bestScore=1000;
        Move bestMove=myMoves.get(0);

        if (gameMap.getMisterXCloud().size()>0){
            
            for (Move move:myMoves){
                gameMap.makeMove(move);          
                gameMap.undoMove(move);
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
        Move move= new Move(detective.getCurrentField(), new Field(32), VehicleType.BUS);
        gameMap.makeMove(move);
        gameMap.undoMove(move);
        return bestMove;

    }
}
