import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main{
    public static void main(String[] args) throws FileNotFoundException, IOException {
        GameManager gm=new GameManager();
        gm.playGames(100);
    }
}