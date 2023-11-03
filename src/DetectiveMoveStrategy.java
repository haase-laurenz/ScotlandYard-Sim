import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetectiveMoveStrategy {
    public static Move getMove(Detective detective, GameMap gameMap) {

        List<Move> myMoves=gameMap.getLegalMoves(detective,true);
     
        if (myMoves.size()==0) return null;

        Move bestMove=null;

        if (gameMap.getMisterXCloud().size()>0){

            int bestScore=Integer.MAX_VALUE;
            
            Field mostAwayFromTheOthers=null;
            double minAvgDist=Double.MAX_VALUE;
            
            

            for(Field field:gameMap.getMisterXCloud()){
                double avgDist=0;
                for (Detective otherDetective:gameMap.getDetectives()){

                    if(otherDetective!=detective){
                        int distance=gameMap.distanceBetween(otherDetective.getCurrentField(), field,true);
                        avgDist+=distance/4.0;
                    }
                
                }
                if (avgDist<minAvgDist){
                    mostAwayFromTheOthers=field;
                }
            }

                

            for (Move move:myMoves){

                int distance=gameMap.distanceBetween(move.getTargetField(), mostAwayFromTheOthers,true);

                if (distance<bestScore){
                    bestScore=distance;
                    bestMove=move;
                }
            }
        }else{

            //FINDING A GOOD POSITION AFTER 2 MOVES (we want Max Activity, when MisterX has to show his position)
           
            HashMap<Integer,List<List<Integer>>> graph=gameMap.getGraph();
        
            int maxActivity=Integer.MIN_VALUE;

            for (Move move1:myMoves){
                Field startField1 = move1.getStartField();
                Field targetField1 = move1.getTargetField();

                int key1=targetField1.getId();
                int activity1=graph.get(key1).get(0).size()+graph.get(key1).get(1).size()*2+graph.get(key1).get(2).size()*3;

                if (activity1>maxActivity){
                    maxActivity=activity1;
                    bestMove=move1;
                }


                if (gameMap.getRounds()<1){
                    detective.setCurrentField(targetField1);

                    List<Move> mySecondMoves=gameMap.getLegalMoves(detective, true);
                    for(Move move2:mySecondMoves){

                        Field targetField2 = move2.getTargetField();
                        int key=targetField2.getId();

                        int activity2=graph.get(key).get(0).size()+graph.get(key).get(1).size()*2+graph.get(key).get(2).size()*3;

                        if (activity2>maxActivity){
                            maxActivity=activity2;
                            bestMove=move1;
                        }
                    }

                    detective.setCurrentField(startField1);
                }
                
            }
            
        }

        return (bestMove!=null)?bestMove:myMoves.get(0);

    }
}

