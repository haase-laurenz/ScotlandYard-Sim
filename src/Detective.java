import java.util.List;
import java.lang.Math;

public class Detective extends Player{

    public Detective(int id,Field currentField){
        super(id, currentField);
    }

    @Override
    public Move getMove(GameMap gameMap){

        List<Move> myMoves=gameMap.getLegalMoves(this,true);

        if (myMoves.size()==0) return null;
        
        int index=(int)(Math.random() * ((myMoves.size()-1) + 1));
        return myMoves.get(index);
    }

}
