public class Field {
    private int id;
    private boolean occupied;

    public Field(int id){
        this.id=id;
        this.occupied=false;
    }

    public int getId(){
        return id;
    }
    
    public boolean getOccupied(){
        return occupied;
    }
    
}
