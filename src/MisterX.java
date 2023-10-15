import java.util.List;
import java.lang.Math;

public class MisterX extends Player {
    
    public MisterX(int id,Field currentField){
        super(id, currentField);
    }

    @Override
    public Move getMove(GameMap gameMap){

        List<Move> myMoves=gameMap.getLegalMoves(this,false);
        if (myMoves.size()==0) return null;


        double bestScore=-1000;
        Move bestMove=null;

        for (Move move:myMoves){
            double score=0;
            for (Detective detective:gameMap.getDetectives()){
                score+=gameMap.distanceBetween(this.getCurrentField(),detective.getCurrentField());
            }
            score/=4;
            if (score>bestScore){
                bestScore=score;
                bestMove=move;
            }
        }

        return bestMove;
    }

    

    
}
