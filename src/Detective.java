import java.util.HashMap;

public class Detective extends Player{

    


    public Detective(int id,Field currentField,boolean playedByHuman,HashMap<VehicleType,Integer> tickets){
        super(id, currentField,playedByHuman,tickets);
       
    }

    @Override
    public Move getMove(GameMap gameMap){

        return DetectiveMoveStrategy.getMove(this, gameMap);

    }


}
