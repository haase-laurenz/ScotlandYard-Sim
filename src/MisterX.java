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


        int index=(int)(Math.random() * ((myMoves.size()-1) + 1));
        return myMoves.get(index);
    }

    
}
