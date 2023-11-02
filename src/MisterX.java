import java.util.HashMap;

public class MisterX extends Player {
    
    private int doubleMoves;

    public MisterX(int id,Field currentField,boolean playedByHuman,HashMap<VehicleType,Integer> tickets){
        super(id, currentField,playedByHuman,tickets);
        doubleMoves=2;
    }

    @Override
    public Move getMove(GameMap gameMap){

        return MisterXMoveStrategy.getMove(this, gameMap);

    }

    public void reduceDoubleMoves(){
        doubleMoves--;
    }

    public int getDoubleMoves(){
        return doubleMoves;
    }

    
}
