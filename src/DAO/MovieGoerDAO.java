package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.MovieGoer;

public class MovieGoerDAO extends BaseDAO {
    String FILEPATH = "src/database/User/MovieGoers.csv";

    public void save(ArrayList<MovieGoer> instances) {
        emptyFile(FILEPATH);
        String header = "id,username,email,phoneNumber,age";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            MovieGoer instance = instances.get(i);
            writeStr = String.format(
                "%d,%s,%s,%s,%s,%d",
                instance.getId(), instance.getUsername(), instance.getPassword(),
                instance.getEmail(), instance.getPhoneNumber(), instance.getAge()
            );
            writeLine(FILEPATH, writeStr);
            instance.getController().save();
        }
    }
    
    public ArrayList<MovieGoer> load() {
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<MovieGoer> returnList = new ArrayList<MovieGoer>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            MovieGoer newInstance = new MovieGoer(
                x[1], x[2], x[3], x[4], Integer.parseInt(x[5])
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}