package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Cinema;

public class CinemaDAO extends BaseDAO {
    String BASEPATH = "src/database/Cineplex/";
    String FILEPATH;

    public void save(ArrayList<Cinema> instances, int cineplexId) {
        FILEPATH = BASEPATH + cineplexId + "/Cinemas.csv";
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Cinema instance = instances.get(i);
            writeStr = String.format("%d,%d,%d,%s,%s,%s",
                instance.getCineplexId(), instance.getCinemaCode(),
                instance.getHeight(), instance.getWidth(),
                instance.getCinemaClass(), instance.getSeatConfiguration()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }
    
    public ArrayList<Cinema> load(int cineplexId) {
        FILEPATH = BASEPATH + cineplexId + "/Cinemas.csv";
        LinkedList<String> instances = getData(FILEPATH);
        ArrayList<Cinema> returnList = new ArrayList<Cinema>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Cinema newInstance = new Cinema(
                Integer.parseInt(x[0]),
                Integer.parseInt(x[1]),
                Integer.parseInt(x[2]),
                Integer.parseInt(x[3]),
                Cinema.showClassOptions.valueOf(x[4]),
                x[5]
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}