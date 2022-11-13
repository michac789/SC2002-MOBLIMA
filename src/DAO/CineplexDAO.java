package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Cineplex;

public class CineplexDAO extends BaseDAO {
    String FILEPATH = "src/database/Cineplex/Cineplexes.csv";
    
    public void save(ArrayList<Cineplex> instances) {
        emptyFile(FILEPATH);
        String header = "cineplexId,location";
        writeLine(FILEPATH, header);
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
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Cineplex newInstance = new Cineplex(x[1]);
            returnList.add(newInstance);
        }
        return returnList;
    }
    public void createCineplexFolder(int cineplexId, String location) {
        String BASEPATH = "src/database/Cineplex/";
        UtilDAO.createFolder(BASEPATH + cineplexId);
        UtilDAO.createFile(BASEPATH + cineplexId + "/Cinemas.csv");
        UtilDAO.writeFile(
                BASEPATH + "Cineplexes.csv",
                String.format("%d,%s", cineplexId, location),
                true
        );
    }
}