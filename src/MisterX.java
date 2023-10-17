import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MisterX extends Player {
    
    public MisterX(int id,Field currentField,boolean playedByHuman){
        super(id, currentField,playedByHuman);
    }

    @Override
    public Move getMove(GameMap gameMap){

        if (playedByHuman){
            List<Move> myMoves=gameMap.getLegalMoves(this,false);
            if (myMoves.size()==0) return null;

            String movesToString="";
            for (Move move:myMoves){
                movesToString+=move.getTargetField().getId()+" ";
            }

            Scanner scanner = new Scanner(System.in);
            Move entered_Move=null;
            while(entered_Move==null){
                System.out.println("Du steht auf Feld "+this.getCurrentField().getId()+". Du kannst auf die Felder "+movesToString);
                System.out.print("Gib ein Feld wo du hinwillst: ");
                String userInput = scanner.nextLine();

                for (Move move:myMoves){
                    if(move.getTargetField().getId()==Integer.valueOf(userInput)){
                        entered_Move=move;
                    }
                }
            }
            
            return new Move(this.getCurrentField(), entered_Move.getTargetField(), null);
            

        }else{

            List<Move> myMoves=gameMap.getLegalMoves(this,false);
            if (myMoves.size()==0) return null;


            double bestScore=-1000;
            List<Move> bestMoves=new ArrayList<>();
            Move bestMove=null;

            for (Move move:myMoves){
                int minDist=Integer.MAX_VALUE;
                for (Detective detective:gameMap.getDetectives()){
                    int dist=gameMap.distanceBetween(move.getTargetField(),detective.getCurrentField());
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
            for (Move move:bestMoves){
                System.out.println(move.toString());
            }
            System.out.println("");
            int zufall=(int)(Math.random() * ((bestMoves.size()-1) + 1));
            return bestMoves.get(zufall);

        }
        
    }

    

    
}
