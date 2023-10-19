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

        Move move= new Move(this.getCurrentField(), new Field(32), VehicleType.SHIP);
        gameMap.makeMove(move);
        gameMap.undoMove(move);

        return bestMove;
    }


}
