import java.util.List;

public class MisterX extends Player {
    
    public MisterX(int id,Field currentField,List<Player> mitspieler){
        super(id, currentField, mitspieler);
    }

    @Override
    public Move getMove(){
        return null;
    }
}
