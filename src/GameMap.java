import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMap {



    private HashMap <Integer,List<List<Integer>>> graph=new HashMap<>();
    private List<Field> allFields=new ArrayList<>();
    private final List<Field> startingFieldsDetectives = new ArrayList<Field>() {{
        add(new Field(3));
        add(new Field(3));
        add(new Field(3));
    }};
    private final List<Field> startingFieldsMisterX = new ArrayList<Field>() {{
        add(new Field(3));
        add(new Field(3));
        add(new Field(3));
    }};

    private int round;
    private GameState gameState;

    



    public GameMap() throws FileNotFoundException, IOException{
        this.graph=loadGraphFromCSV();
        initializeFields();
        
    }

    private void initializeFields(){
        allFields.clear();
        for (int i=1;i<200;i++){
            allFields.add(new Field(i));
        }
    }

    public HashMap <Integer,List<List<Integer>>> getGraph(){
        return graph;
    }

    public int getRound(){
        return round;
    }

    public GameState getGameState(){
        return gameState;
    }

    public boolean makeMove(Move move){
        return true;
    }

    public boolean undoMove(Move move){
        return true;
    }











    
    private  HashMap <Integer,List<List<Integer>>> loadGraphFromCSV() throws FileNotFoundException, IOException{

        HashMap <Integer,List<List<Integer>>>loaded_map=new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("finalCSV.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values;
                    values = line.split(";");

                    Integer key=Integer.parseInt(values[0]);

                    List<List<Integer>> value=new ArrayList<>();
                    List<Integer> taxi=new ArrayList<>();
                    List<Integer> bus=new ArrayList<>();
                    List<Integer> train=new ArrayList<>();
                    List<Integer> ship=new ArrayList<>();
                    
                    
                    if (!values[1].isEmpty()) {
                       String[] elements1 = values[1].substring(1, values[1].length() - 1).split(",");
                        for (String element : elements1) {
                            if (element!=""){
                                taxi.add(Integer.parseInt(element.trim()));
                            }
                        }
                    }

                    
                    if (!values[2].isEmpty()) {
                        String[] elements2 = values[2].substring(1, values[2].length() - 1).split(",");
                        for (String element : elements2) {
                            if (element!=""){
                                bus.add(Integer.parseInt(element.trim()));
                            }
                        }
                    }

                    
                    if (!values[3].isEmpty()) {
                        String[] elements3 = values[3].substring(1, values[3].length() - 1).split(",");
                        for (String element : elements3) {
                            if (element!=""){
                                train.add(Integer.parseInt(element.trim()));
                            }
                        }
                    }

                    
                    if (!values[4].isEmpty()) {
                        String[] elements4 = values[4].substring(1, values[4].length() - 1).split(",");
                        for (String element : elements4) {
                            if (element!=""){
                                ship.add(Integer.parseInt(element.trim()));
                            }
                            
                        }
                    }
                    value.add(taxi);
                    value.add(bus);
                    value.add(train);
                    value.add(ship);

                    loaded_map.put(key, value);
                }
            }
        return loaded_map;
    }
}
