import java.util.List;

public class Detective extends Player{
    public Detective(int id,Field currentField,List<Player> mitspieler){
        super(id, currentField, mitspieler);
    }

    @Override
    public Move getMove(){
        return null;
    }
    
}
