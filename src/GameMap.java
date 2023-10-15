import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Math;
import java.lang.Thread;

public class GameMap {



    private HashMap <Integer,List<List<Integer>>> graph=new HashMap<>();
    private List<Field> allFields=new ArrayList<>();
    private List<Field> startingFieldsDetectives = new ArrayList<>();
    private List<Field> startingFieldsMisterX = new ArrayList<>();

    private int round;
    private GameState gameState;

    private List<Detective> detectives=new ArrayList<>();
    private MisterX misterX;
    private Player currentPlayer;
    

    



    public GameMap() throws FileNotFoundException, IOException{
        this.graph=loadGraphFromCSV();
        initializeFields();
        initializePlayers();
        this.round=0;
        this.gameState=GameState.ONGOING;
        
    }

    private void initializeFields(){
        allFields.clear();
        for (int i=1;i<200;i++){
            allFields.add(new Field(i));
            
        }
        
        startingFieldsMisterX.add(allFields.get(34));
        startingFieldsMisterX.add(allFields.get(44));
        startingFieldsMisterX.add(allFields.get(50));
        startingFieldsMisterX.add(allFields.get(70));
        startingFieldsMisterX.add(allFields.get(77));
        startingFieldsMisterX.add(allFields.get(103));
        startingFieldsMisterX.add(allFields.get(105));
        startingFieldsMisterX.add(allFields.get(126));
        startingFieldsMisterX.add(allFields.get(131));
        startingFieldsMisterX.add(allFields.get(145));
        startingFieldsMisterX.add(allFields.get(165));
        startingFieldsMisterX.add(allFields.get(169));
        startingFieldsMisterX.add(allFields.get(171));
  

        startingFieldsDetectives.add(allFields.get(12));
        startingFieldsDetectives.add(allFields.get(25));
        startingFieldsDetectives.add(allFields.get(28));
        startingFieldsDetectives.add(allFields.get(33));
        startingFieldsDetectives.add(allFields.get(49));
        startingFieldsDetectives.add(allFields.get(52));
        startingFieldsDetectives.add(allFields.get(90));
        startingFieldsDetectives.add(allFields.get(93));
        startingFieldsDetectives.add(allFields.get(102));
        startingFieldsDetectives.add(allFields.get(111));
        startingFieldsDetectives.add(allFields.get(116));
        startingFieldsDetectives.add(allFields.get(122));
        startingFieldsDetectives.add(allFields.get(137));
        startingFieldsDetectives.add(allFields.get(140));
        startingFieldsDetectives.add(allFields.get(154));
        startingFieldsDetectives.add(allFields.get(173));
    
    }

    private void initializePlayers(){
        for (int i=0;i<=3;i++){
            detectives.add(new Detective(i, startingFieldsDetectives.remove( (int)(Math.random() * ((startingFieldsDetectives.size()-1) + 1)) )));
        }
        misterX=new MisterX(4, startingFieldsMisterX.remove( (int)(Math.random() * ((startingFieldsMisterX.size()-1) + 1)) ));

        currentPlayer=misterX;
    }

    public void playerToString(){
        for(Player player:detectives){
            System.out.println("Detective "+player.getId()+" steht auf dem Feld "+player.getCurrentField().getId());
        }
        System.out.println("MisterX steht auf dem Feld "+misterX.getCurrentField().getId());
    }

    public boolean isFieldEmpty(Field field){
        return !allFields.get(field.getId()-1).isOccupied();
    }

    public boolean isFieldWithoutDetectives(Field field){
        for(Detective otherDetective:detectives){
            if (otherDetective.getCurrentField().equals(field)){
                return false;
            }
        }
        return true;
    }

    public List<Move> getLegalMoves(Player player,boolean isDetective){
        List<Move> legalMoves=new ArrayList<>();
   
        int key=player.getCurrentField().getId();
        for(List<Integer> transports:graph.get(key)){
            for(Integer endPoint:transports){
                if (!isDetective){
                    if (!allFields.get(endPoint-1).isOccupied()){
                        Move legalMove=new Move(allFields.get(key-1), allFields.get(endPoint-1), VehicleType.values()[graph.get(key).indexOf(transports)]);
                        legalMoves.add(legalMove);
                    }
                }else{
                
                    if(isFieldWithoutDetectives(allFields.get(endPoint-1))){
                        
                        Move legalMove=new Move(allFields.get(key-1), allFields.get(endPoint-1), VehicleType.values()[graph.get(key).indexOf(transports)]);
                        legalMoves.add(legalMove);
                    }
                }
                
            }
        }
     
        return legalMoves;
    }

    public HashMap <Integer,List<List<Integer>>> getGraph(){
        return graph;
    }

    public int getRounds(){
        return round;
    }

    public GameState getGameState(){
        return gameState;
    }

    public Player getCurrentplayer(){
        return currentPlayer;
    }

    public Player getMisterX(){
        return misterX;
    }

    public void makeMove() throws InterruptedException {

        if (twoPlayersSameField()){
            throw new IllegalStateException("ZWEI SPIELER STEHEN AUF DEM GLEICHEN FELD");
        }

        Move move=currentPlayer.getMove(this);

        if (move==null){
            //System.out.println("No moves");
            //Thread.sleep(100);
            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                currentPlayer= (index<3)? detectives.get(index+1) : misterX;
            }else{
                gameState=GameState.DETECTIVES_WIN;
            }
        }else{

            //System.out.println("Player"+currentPlayer.getId()+" "+move);
            //Thread.sleep(100);
            currentPlayer.setCurrentField(move.getTargetField());


            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                currentPlayer= (index<3)? detectives.get(index+1) : misterX;
            }else{
                currentPlayer=detectives.get(0);
                round++;
            }

            if (round>30){
                gameState=GameState.MISTERX_WIN;  
            }

            for(Player player:detectives){
                if (player.getCurrentField()==misterX.getCurrentField()){
                    gameState=GameState.DETECTIVES_WIN;
                }
            }

        }

        

    }

    public boolean undoMove(Move move){
        return true;
    }

    public boolean twoPlayersSameField(){
        for(Player player:detectives){
            for(Player player2:detectives){
                if(player!=player2){
                    if (player.getCurrentField()==player2.getCurrentField()){
                        return true;
                    }
                }
            }
            if (player.getCurrentField()==misterX.getCurrentField()){
                return true;
            }
        }
        return false;
    }











    
    private  HashMap <Integer,List<List<Integer>>> loadGraphFromCSV() throws FileNotFoundException, IOException{

        HashMap <Integer,List<List<Integer>>>loaded_map=new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("finalCSV.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values;
                    values = line.split(";");

                    Integer key=Integer.parseInt(values[0]);

                    List<List<Integer>> value=new ArrayList<>();
                    List<Integer> taxi=new ArrayList<>();
                    List<Integer> bus=new ArrayList<>();
                    List<Integer> train=new ArrayList<>();
                    List<Integer> ship=new ArrayList<>();
                    
                    
                    if (!values[1].isEmpty()) {
                       String[] elements1 = values[1].substring(1, values[1].length() - 1).split(",");
                        for (String element : elements1) {
                            if (element!=""){
                                taxi.add(Integer.parseInt(element.trim()));
                            }
                        }
                    }

                    
                    if (!values[2].isEmpty()) {
                        String[] elements2 = values[2].substring(1, values[2].length() - 1).split(",");
                        for (String element : elements2) {
                            if (element!=""){
                                bus.add(Integer.parseInt(element.trim()));
                            }
                        }
                    }

                    
                    if (!values[3].isEmpty()) {
                        String[] elements3 = values[3].substring(1, values[3].length() - 1).split(",");
                        for (String element : elements3) {
                            if (element!=""){
                                train.add(Integer.parseInt(element.trim()));
                            }
                        }
                    }

                    
                    if (!values[4].isEmpty()) {
                        String[] elements4 = values[4].substring(1, values[4].length() - 1).split(",");
                        for (String element : elements4) {
                            if (element!=""){
                                ship.add(Integer.parseInt(element.trim()));
                            }
                            
                        }
                    }
                    value.add(taxi);
                    value.add(bus);
                    value.add(train);
                    value.add(ship);

                    loaded_map.put(key, value);
                }
            }
        return loaded_map;
    }
}
