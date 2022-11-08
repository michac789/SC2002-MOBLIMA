package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Cineplex;

public class CineplexDAO extends BaseDAO {
    String FILEPATH = "database/Cineplex/Cineplexes.csv";
    
    public void save(ArrayList<Cineplex> instances) {
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Cineplex instance = instances.get(i);
            writeStr = String.format("%d,%s",
                instance.getCineplexId(), instance.getLocation()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }

    public ArrayList<Cineplex> load() {
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Cineplex> returnList = new ArrayList<Cineplex>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Cineplex new_instance = new Cineplex(x[1]);
            returnList.add(new_instance);
        }
        return returnList;
    }
}