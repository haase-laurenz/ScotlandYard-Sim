import java.util.List;

public class Player {
    
    private int id;
    private Field currentField;


    public Player(int id,Field currentField){

        this.id=id;
        this.currentField=currentField;
        this.currentField.setOccupiedTrue();
  

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
    }

    public Move getMove(GameMap gameMap){
        return null;
    }


}
