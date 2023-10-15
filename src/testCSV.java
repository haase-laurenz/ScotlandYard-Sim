import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class testCSV {
    public static void main(String[] args) throws FileNotFoundException, IOException {
            HashMap <Integer,List<List<Integer>>>graph=new HashMap<>();

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

                    graph.put(key, value);
                }
            }
            for (int key:graph.keySet()){
                System.out.println(key+": "+graph.get(key));
            }
            
    }
    
}
