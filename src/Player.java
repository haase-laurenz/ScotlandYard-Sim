import java.util.List;

public class Player {
    
    private int id;
    private Field currentField;
    private List<Player> mitspieler;

    public Player(int id,Field currentField,List<Player> mitspieler){
        this.id=id;
        this.currentField=currentField;
        this.mitspieler=mitspieler;
    }

    public int getId(){
        return id;
    }

    public Field getCurretField(){
        return currentField;
    }

    public List<Player> getMitspieler(){
        return mitspieler;
    }

    public Move getMove(){
        return null;
    }


}
