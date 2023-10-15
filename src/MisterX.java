import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MisterX extends Player {
    
    public MisterX(int id,Field currentField){
        super(id, currentField);
    }

    @Override
    public Move getMove(GameMap gameMap){
        List<Move> myMoves=gameMap.getLegalMoves(this);
        
        if (myMoves.size()==0) return null;
        return myMoves.get(0);
    }

    
}
