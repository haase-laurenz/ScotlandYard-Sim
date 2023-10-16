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
        Move bestMove=null;

        for (Move move:myMoves){
            int score=gameMap.distanceBetween(move.getTargetField(), gameMap.getLastMisterXField());
            if (score<bestScore){
                bestScore=score;
                bestMove=move;
            }
        }

        return bestMove;
    }

}
