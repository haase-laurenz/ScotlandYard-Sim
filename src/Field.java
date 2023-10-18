public class Field {
    private int id;
    private boolean occupied;
    private int HeatMapCount=0;

    public Field(int id){
        this.id=id;
        this.occupied=false;
    }

    public int getId(){
        return id;
    }
    
    public boolean isOccupied(){
        return occupied;
    }

    public void setOccupiedTrue(){
        this.occupied=true;
    }

    public void setOccupiedFalse(){
        this.occupied=false;
    }

    public int getHeatMapCount(){
        return HeatMapCount;
    }

    public void raiseHeatMapCount(){
        this.HeatMapCount++;
    }

}
