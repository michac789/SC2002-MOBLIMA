package cinema;
import base.BaseDAO;
import java.util.ArrayList;
import java.util.LinkedList;

public class CinemasDAO extends BaseDAO {
    String FILEPATH = "database/cinemas.csv";

    public void save(ArrayList<Cinema> instances) {
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Cinema instance = instances.get(i);
            writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s",
                instance.getCinemaId(), instance.getfkCineplex(), instance.getCinemaCode(),
                instance.getHeight(), instance.getWidth(), instance.getCinemaClass()
            );
        }
        writeLine(FILEPATH, writeStr);
    }

    public ArrayList<Cinema> load() {
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Cinema> returnList = new ArrayList<Cinema>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Cinema new_instance = new Cinema(
                Integer.parseInt(x[1]),
                Integer.parseInt(x[2]),
                Integer.parseInt(x[3]),
                Integer.parseInt(x[4]),
                Cinema.showClassOptions.valueOf(x[5])
            );
            returnList.add(new_instance);
        }
        return returnList;
    }
}
