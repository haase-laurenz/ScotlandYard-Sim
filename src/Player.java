import java.util.HashMap;

public class Player {
    
    private int id;
    private Field currentField;
    public boolean playedByHuman;
    HashMap<VehicleType,Integer> tickets = new HashMap<>();

    public Player(int id,Field currentField,boolean playedByHuman,HashMap<VehicleType,Integer> tickets){

        this.id=id;
        this.currentField=currentField;
        this.currentField.setOccupiedTrue();
        this.playedByHuman=playedByHuman;
        this.tickets=tickets;

    }

    public int getId(){
        return id;
    }

    public Field getCurrentField(){
        return currentField;
    }

    public void setCurrentField(Field newField){
        this.currentField.setOccupiedFalse();
        this.currentField=newField;
        this.currentField.setOccupiedTrue();
        newField.raiseHeatMapCount();
    }

    public Move getMove(GameMap gameMap){
        return null;
    }

    public HashMap<VehicleType,Integer> getTickets(){
        return tickets;
    }

    public void reduceTickets(VehicleType vt){
        tickets.put(vt, tickets.get(vt)-1);
    }


}
