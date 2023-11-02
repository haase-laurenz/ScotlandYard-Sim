public class Move {
    private Field startField;
    private Field targetField;
    private VehicleType vehicleType;
    private boolean isDoubleMove;

    public Move(Field startField,Field targetField,VehicleType vehicleType,boolean isDoubleMove){
        this.startField=startField;
        this.targetField=targetField;
        this.vehicleType=vehicleType;
        this.isDoubleMove=isDoubleMove;
    }

    public Field getStartField(){
        return startField;
    }

    public Field getTargetField(){
        return targetField;
    }

    public VehicleType getVehicleTyp(){
        return vehicleType;
    }

    public boolean isDoubleMove(){
        return isDoubleMove;
    }

    public void setDoubleMove(){
        this.isDoubleMove=true;
    }

    public String toString(){
        return "move from "+startField.getId()+" to "+targetField.getId()+" via "+vehicleType;
    }

}
