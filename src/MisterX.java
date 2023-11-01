import java.util.HashMap;

public class MisterX extends Player {
    
    public MisterX(int id,Field currentField,boolean playedByHuman,HashMap<VehicleType,Integer> tickets){
        super(id, currentField,playedByHuman,tickets);
    }

    @Override
    public Move getMove(GameMap gameMap){

        return MisterXMoveStrategy.getMove(this, gameMap);

    }

    
}
