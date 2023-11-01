import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.lang.Math;

public class GameMap{



    private HashMap <Integer,List<List<Integer>>> graph=new HashMap<>();
    private List<Field> allFields=new ArrayList<>();
    private List<Field> startingFieldsDetectives = new ArrayList<>();
    private List<Field> startingFieldsMisterX = new ArrayList<>();

    private int round;
    private GameState gameState;

    private List<Detective> detectives=new ArrayList<>();
    private MisterX misterX;
    private Player currentPlayer;

    private List<Field> lastMisterXFields=new ArrayList<>();
    
    private List<VehicleType> lastMisterXVehicleTypes=new ArrayList<>();
    private Set<Field> misterXCloud=new HashSet<>();
    
    private boolean misterXPlayedByHuman;

    public GameMap(boolean misterXPlayedByHuman)throws FileNotFoundException, IOException{
        this.round=0;
        this.gameState=GameState.ONGOING;
        this.misterXPlayedByHuman=misterXPlayedByHuman;
        this.graph=Graph.loadGraphFromCSV();
        initializeFields();
        initializePlayers();
        lastMisterXFields.add(null);
        
    }

    private GameMap(GameMap original) {
        this.currentPlayer=new Player(original.getCurrentplayer().getId(),original.getCurrentplayer().getCurrentField(),false,original.getCurrentplayer().getTickets());
        this.gameState=original.gameState;
        this.detectives = new ArrayList<>();
        for (Detective detective : original.getDetectives()) {
            this.detectives.add(new Detective(detective.getId(), detective.getCurrentField(), false,detective.getTickets()));
        }
        this.misterX=new MisterX(original.getMisterX().getId(),original.getMisterX().getCurrentField(),false,original.getMisterX().getTickets());
        
        if (original.getLastMisterXFields().getLast()==null){
            this.lastMisterXFields = new ArrayList<>();;
            this.lastMisterXFields.add(null);
            
            
        }else{
            this.lastMisterXFields = new ArrayList<>();
            for (Field lastField : original.getLastMisterXFields()) {
                if (lastField==null){
                    this.lastMisterXFields.add(null);
                }else{
                    this.lastMisterXFields.add(new Field(lastField.getId()));
                }
                
            }
        }
        
        
        this.lastMisterXVehicleTypes = new ArrayList<>();
        for (VehicleType vt : original.getLastMisterXVehicleTypes()) {
            this.lastMisterXVehicleTypes.add(vt);
        }
        this.round=original.getRounds();
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
        
        HashMap<VehicleType,Integer> misterXTickets=new HashMap<>();
        misterXTickets.put(VehicleType.TAXI, 100);
        misterXTickets.put(VehicleType.BUS, 100);
        misterXTickets.put(VehicleType.TRAIN, 100);
        misterXTickets.put(VehicleType.BLACK_TICKET,2);


        for (int i=0;i<=3;i++){
            HashMap<VehicleType,Integer> detectiveTickets=new HashMap<>();
            detectiveTickets.put(VehicleType.TAXI,12);
            detectiveTickets.put(VehicleType.BUS, 8);
            detectiveTickets.put(VehicleType.TRAIN, 5);
            detectives.add(new Detective(i, startingFieldsDetectives.remove( (int)(Math.random() * ((startingFieldsDetectives.size()-1) + 1) )),false,detectiveTickets));
        }
        misterX=new MisterX(4, startingFieldsMisterX.remove( (int)(Math.random() * ((startingFieldsMisterX.size()-1) + 1)) ),misterXPlayedByHuman,misterXTickets);

        currentPlayer=misterX;
    }

    public void playerToString(){
        for(Player player:detectives){
            System.out.println("Detective "+player.getId()+" steht auf dem Feld "+player.getCurrentField().getId());
        }
        System.out.println("MisterX steht auf dem Feld "+misterX.getCurrentField().getId());
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
                        if (VehicleType.values()[graph.get(key).indexOf(transports)]==VehicleType.SHIP){
                            Move legalMove=new Move(allFields.get(key-1), allFields.get(endPoint-1), VehicleType.BLACK_TICKET);
                            legalMoves.add(legalMove);
                        }else{
                            Move legalMove=new Move(allFields.get(key-1), allFields.get(endPoint-1), VehicleType.values()[graph.get(key).indexOf(transports)]);
                            legalMoves.add(legalMove);
                        }
                        
                        
                    }
                }else{   
                    if(isFieldWithoutDetectives(allFields.get(endPoint-1)) && VehicleType.values()[graph.get(key).indexOf(transports)]!=VehicleType.SHIP && player.getTickets().get(VehicleType.values()[graph.get(key).indexOf(transports)])>0){
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

    public List<Detective> getDetectives(){
        return detectives;
    }

    public MisterX getMisterX(){
        return misterX;
    }
    public List<Field> getAllFields(){
        return allFields;
    }
    public int getRounds(){
        return round;
    }

    public Set<Field> getMisterXCloud(){
        return misterXCloud;
    }

    public GameState getGameState(){
        return gameState;
    }

    public Player getCurrentplayer(){
        return currentPlayer;
    }

    public List<Field> getLastMisterXFields(){
        return lastMisterXFields;
    }

    public List<VehicleType> getLastMisterXVehicleTypes(){
        return lastMisterXVehicleTypes;
    }

    public void makeMove(int moveTime) throws InterruptedException{
        if (twoPlayersSameField()){
            System.out.println("ZWEI SPIELER STEHEN AUF DEM GLEICHEN FELD");
            throw new IllegalStateException("ZWEI SPIELER STEHEN AUF DEM GLEICHEN FELD");
        }
       
        GameMap clone=new GameMap(this);
       
        Move move=currentPlayer.getMove(this);
    
        if (!equalGameMap(clone)) {
            throw new IllegalStateException("!!!-ILLEGAL: CHANGED STATE OF THE BOARD-!!! in GameMap MakeMove()");
        }
       
        
        
        if (currentPlayer==misterX && lastMisterXFields.getLast()!=null){
            List<Move> misterXMoves=this.getLegalMoves(currentPlayer,false);
            for(Move mv:misterXMoves){
                if (move.getVehicleTyp()==mv.getVehicleTyp() || move.getVehicleTyp()==VehicleType.BLACK_TICKET){
                    misterXCloud.add(mv.getTargetField());
                }
            }
        }
        
        

        if (move==null){
           
            Thread.sleep(moveTime);
            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                if (index<3){
                    currentPlayer=detectives.get(index+1);
                }else{
                    currentPlayer=misterX;
                    round++;
                }
            }else{
                gameState=GameState.DETECTIVES_WIN;
            }
        }else{
          
            if (move.getVehicleTyp()==null){
                System.out.println("MAKE MOVE ERROR: VEHICLE-TYPE CANNOT BE NULL");
                throw new IllegalArgumentException("VEHICLE-TYPE CANNOT BE NULL");
            }

         
            Thread.sleep(moveTime);
            currentPlayer.setCurrentField(move.getTargetField());
            currentPlayer.reduceTickets(move.getVehicleTyp());
           
            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                currentPlayer= (index<3)? detectives.get(index+1) : misterX;
                if (index<3){
                    currentPlayer=detectives.get(index+1);
                }else{
                    currentPlayer=misterX;
                    round++;
                }

            }else{
                if (round==2 || round==7 || round==12 || round==17 || round==23){
                    //System.out.println("MisterX wird aufgedeckt an: "+currentPlayer.getCurrentField().getId());
                    lastMisterXFields.add(currentPlayer.getCurrentField());
                    misterXCloud.clear();
                    misterXCloud.add(lastMisterXFields.getLast());
                }

                if(move.getVehicleTyp()!=VehicleType.BLACK_TICKET){
                    List<Field> fieldsToRemove = new ArrayList<>();
                    for(Field field:misterXCloud){
                        List<Integer> list=graph.get(field.getId()).get(move.getVehicleTyp().ordinal());
                        if (list.isEmpty()){
                        fieldsToRemove.add(field);
                        }
                    }
                    misterXCloud.removeAll(fieldsToRemove);
                }
                

                lastMisterXVehicleTypes.add(move.getVehicleTyp());
                currentPlayer=detectives.get(0);
            }

            if (round==24){
                gameState=GameState.MISTERX_WIN;  
            }

            for(Player player:detectives){
                if (player.getCurrentField()==misterX.getCurrentField()){
                    gameState=GameState.DETECTIVES_WIN;
                }
            }
    
        }
    }

    //making a move on the board
    public void makeMove(Move move){

        if (move==null){
            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                currentPlayer= (index<3)? detectives.get(index+1) : misterX;
            }else{
                gameState=GameState.DETECTIVES_WIN;
            }
        }else{

            currentPlayer.setCurrentField(move.getTargetField());


            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                currentPlayer= (index<3)? detectives.get(index+1) : misterX;
                if (index<3){
                    currentPlayer=detectives.get(index+1);
                }else{
                    currentPlayer=misterX;
                    round++;
                }

            }else{
                if (round==2 || round==7 || round==12 || round==17 || round==23){
                    lastMisterXFields.add(currentPlayer.getCurrentField());
                }
                lastMisterXVehicleTypes.add(move.getVehicleTyp());
                currentPlayer=detectives.get(0);
            }

            if (round==24){
                gameState=GameState.MISTERX_WIN;  
            }

            for(Player player:detectives){
                if (player.getCurrentField()==misterX.getCurrentField()){
                    gameState=GameState.DETECTIVES_WIN;
                }
            }

        }

        

    }

    //undoing a move on the board
    public void undoMove(Move move){
        
       
        if (move==null){
            if (detectives.contains(currentPlayer)){
                int index=detectives.indexOf(currentPlayer);
                currentPlayer= (index>0)? detectives.get(index-1) : misterX;
            }else{
                gameState=GameState.ONGOING;
            }
        }else{

            if (move.getVehicleTyp()==null){
                System.out.println("MAKE MOVE ERROR: VEHICLE-TYPE CANNOT BE NULL");
                throw new IllegalArgumentException("VEHICLE-TYPE CANNOT BE NULL");
            }


            if (detectives.contains(currentPlayer)){

                int index=detectives.indexOf(currentPlayer);
                if (index>0){
                    currentPlayer=detectives.get(index-1);
                }else{
                  
                    lastMisterXVehicleTypes.removeLast();
                  
                    currentPlayer=misterX;
                    if (round==2 || round==7 || round==12 || round==17 || round==23){
                        lastMisterXFields.removeLast();
                    }
                  
                    
                }
                
            }else{

                

                round--;
                
                currentPlayer=detectives.get(3);
                
            }

            if (round<=24){
                gameState=GameState.ONGOING;  
            }

            for(Player player:detectives){
                if (player.getCurrentField()==misterX.getCurrentField()){
                    gameState=GameState.ONGOING;
                }
            }

            currentPlayer.setCurrentField(move.getStartField());

        }
    }

    private boolean twoPlayersSameField(){
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

    public int distanceBetween(Field f1,Field f2,boolean isDetective){
        

        // Verwende eine Queue, um die Felder zu durchlaufen
        Queue<Field> queue = new LinkedList<>();
        // Verwende eine Set, um bereits besuchte Felder zu verfolgen
        Set<Field> visited = new HashSet<>();

        queue.add(f1);
        visited.add(f1);

        int distance=0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Durchlaufe alle Felder auf dem aktuellen Level
            for (int i = 0; i < levelSize; i++) {
                Field currentField = queue.poll();

                // Überprüfe, ob das aktuelle Feld das Ziel ist
                if (currentField==f2) {
                    return distance;
                }

                // Durchlaufe alle Nachbarfelder des aktuellen Feldes
                int key=currentField.getId();
                for(List<Integer> transports:graph.get(key)){
                    if (VehicleType.values()[graph.get(key).indexOf(transports)]!=VehicleType.SHIP || !isDetective )
                    for(Integer endPoint:transports){
                        if (!visited.contains(allFields.get(endPoint-1))) {
                            queue.add(allFields.get(endPoint-1));
                            visited.add(allFields.get(endPoint-1));
                        } 
                    }
                }
            }
            distance++;
        }

        return distance;
    }

    protected boolean equalGameMap(GameMap clone){

        boolean failed=false;

        if (this.getCurrentplayer().getCurrentField().getId()!=clone.getCurrentplayer().getCurrentField().getId() || this.getCurrentplayer().getId()!=clone.getCurrentplayer().getId()){
            System.out.println("EqualGameMap Test 1 failed: getMove changed CurrentPlayer");
            failed=true;
        }

        if (!this.gameState.equals(clone.getGameState())){
            System.out.println("EqualGameMap Test 2 failed: getMove changed GameState - should be:"+clone.getGameState()+" is:"+this.gameState);
            failed=true;
        }

        
        for (int i=0;i<4;i++){
            if (clone.getDetectives().get(i).getCurrentField()!=this.getDetectives().get(i).getCurrentField() || clone.getDetectives().get(i).getId()!=this.getDetectives().get(i).getId()){
                System.out.println("EqualGameMap Test 3 failed: getMove changed Detective "+i);
                failed=true;
            }
        }
        if (this.misterX.getCurrentField().getId()!=clone.getMisterX().getCurrentField().getId() || this.misterX.getId()!=clone.getMisterX().getId()){
            System.out.println("EqualGameMap Test 4 failed: getMove changed MisterX");
            failed=true;
        }
    
        if ((this.getLastMisterXFields().getLast() == null && clone.getLastMisterXFields().getLast() != null) 
            || (this.getLastMisterXFields().getLast() != null 
                && this.getLastMisterXFields().getLast().getId()!=(clone.getLastMisterXFields().getLast().getId()))) {
            System.out.println("EqualGameMap Test 5 failed: getMove changed lastMisterXField - should be:"+clone.getLastMisterXFields().getLast().getId()+" is:"+this.getLastMisterXFields().getLast().getId());
            failed = true;
        }
 
        if (clone.getLastMisterXVehicleTypes().size()!=this.getLastMisterXVehicleTypes().size()){
            System.out.println("EqualGameMap Test 6 failed: getMove changed lastMisterXVehicleTypes");
            failed=true;
        }

        if (this.round!=clone.getRounds()){
            System.out.println("EqualGameMap Test 7 failed: getMove changed Round counter");
            failed=true;
        }
        
        if (failed)return false;
        return true;
    }

}
