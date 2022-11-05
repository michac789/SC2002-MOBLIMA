package cineplex;
import base.BaseDAO;
import java.util.ArrayList;
import java.util.LinkedList;

public class CineplexesDAO extends BaseDAO {
    String FILEPATH = "database/cineplexes.csv";

    public void save(ArrayList<Cineplex> instances) {
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Cineplex instance = instances.get(i);
            writeStr = String.format("%d,%s",
                instance.getCineplexId(), instance.getLocation()
            );
        }
        writeLine(FILEPATH, writeStr);
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
