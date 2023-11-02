import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MisterXMoveStrategy {
    public static Move getMove(MisterX misterX, GameMap gameMap) {
        
        HashMap<Integer,List<List<Integer>>> graph=gameMap.getGraph();

        List<Move> myMoves=gameMap.getLegalMoves(misterX,false);
            if (myMoves.size()==0) return null;


            double bestScore=Double.MIN_VALUE;
            List<Move> bestMoves=new ArrayList<>();
            Move bestMove=null;
            boolean safetyDouble=true;

            for (Move move:myMoves){

                int minDist=Integer.MAX_VALUE;
                double avgDist=0;

                for (Detective detective:gameMap.getDetectives()){
                    int dist=gameMap.distanceBetween(move.getTargetField(),detective.getCurrentField(),false);
                    avgDist+=dist/4.0;
                    if (dist<minDist){
                        minDist=dist;
                    }
                }
                
                if (minDist>1) safetyDouble=false;

                int key=move.getTargetField().getId();
                int activity=graph.get(key).get(0).size()+graph.get(key).get(1).size()*2+graph.get(key).get(2).size()*3+graph.get(key).get(3).size()*4;

                double moveScore=minDist+avgDist/5;
                //System.out.println(move.toString()+" moveScore: "+moveScore);

                if (moveScore==bestScore){
                    bestMoves.add(move);
                }

                if (moveScore>bestScore){
                    bestMoves.clear();
                    bestMoves.add(move);
                    bestScore=minDist;
                }

            }

            
          
            int zufall=(int)(Math.random() * ((bestMoves.size()-1) + 1));
            bestMove=bestMoves.get(zufall);

            
            int key=misterX.getCurrentField().getId();
            int activity=graph.get(key).get(0).size()+graph.get(key).get(1).size()*2+graph.get(key).get(2).size()*3+graph.get(key).get(3).size()*4;
            int round=gameMap.getRounds();

            if (activity>10 && misterX.getTickets().get(VehicleType.BLACK_TICKET)>0 && gameMap.getRounds()>2 && round!=7 && round!=12 && round!=17 && round!=23){
                bestMove.setMoveVehicleType_BlackTicket();
            }

            if (safetyDouble && misterX.getDoubleMoves()>0 && gameMap.isDoubleMoveAllowed() && gameMap.getRounds()<23){
                bestMove.setDoubleMove();
            }

                        
            return bestMove;
    
    }
}
