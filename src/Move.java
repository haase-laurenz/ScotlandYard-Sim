public class Move {
    private Field startField;
    private Field targetField;
    private VehicleType vehicleType;

    public Move(Field startField,Field targetField,VehicleType vehicleType){
        this.startField=startField;
        this.targetField=targetField;
        this.vehicleType=vehicleType;
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

    public String toString(){
        return "moved from "+startField.getId()+" to "+targetField.getId()+" via "+vehicleType;
    }

}
