import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;


public class Main{
    public static void main(String[] args) throws FileNotFoundException, IOException,InterruptedException {
        GameManager gm=new GameManager();
        gm.playGames(2);
        
    }
}