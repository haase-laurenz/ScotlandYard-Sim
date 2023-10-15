import java.util.List;

public class Detective extends Player{
    public Detective(int id,Field currentField){
        super(id, currentField);
    }

    @Override
    public Move getMove(GameMap gameMap){
        List<Move> myMoves=gameMap.getLegalMoves(this);
        

        return myMoves.get(0);
    }

}
