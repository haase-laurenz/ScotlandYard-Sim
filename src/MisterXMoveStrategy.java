import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MisterXMoveStrategy {
    public static Move getMove(MisterX misterX, GameMap gameMap) {
        

        List<Move> myMoves=gameMap.getLegalMoves(misterX,false);
            if (myMoves.size()==0) return null;


            double bestScore=-1000;
            List<Move> bestMoves=new ArrayList<>();
            Move bestMove=null;

            for (Move move:myMoves){

                int minDist=Integer.MAX_VALUE;
                for (Detective detective:gameMap.getDetectives()){
                    int dist=gameMap.distanceBetween(move.getTargetField(),detective.getCurrentField(),false);
                    if (dist<minDist){
                        minDist=dist;
                    }
                }
                if (minDist>=bestScore){
                    bestMoves.add(move);
                }

                if (minDist>bestScore){
                    bestMoves.clear();
                    bestMoves.add(move);
                    bestScore=minDist;
                }

            }

            

            int zufall=(int)(Math.random() * ((bestMoves.size()-1) + 1));
            bestMove=bestMoves.get(zufall);

            HashMap<Integer,List<List<Integer>>> graph=gameMap.getGraph();
            int key=misterX.getCurrentField().getId();
            int activity=graph.get(key).get(0).size()+graph.get(key).get(1).size()*2+graph.get(key).get(2).size()*3+graph.get(key).get(3).size()*4;
            int round=gameMap.getRounds();

            if (activity>10 && misterX.getDoubleMoves()>0 && gameMap.getRounds()>2 && round!=7 && round!=12 && round!=17 && round!=23){
                bestMove.setMoveVehicleType_BlackTicket();
            }
            
            return bestMove;
    
    }
}
