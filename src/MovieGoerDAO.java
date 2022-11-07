import java.util.LinkedList;
import java.util.ArrayList;
import user.MovieGoer;

public class MovieGoerDAO extends BaseDAO {
    String FILEPATH = "src/database/Users/MovieGoers.csv";

    public void save(ArrayList<MovieGoer> instances) {
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            MovieGoer instance = instances.get(i);
            writeStr = String.format(
                "%s,%s,%s,%d",
                instance.getUsername(), instance.getEmail(),
                instance.getPhoneNumber(), instance.getAge()
            );
            writeLine(FILEPATH, writeStr);
        }
    }
    
    public ArrayList<MovieGoer> load() {
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<MovieGoer> returnList = new ArrayList<MovieGoer>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            MovieGoer new_instance = new MovieGoer(
                x[0], x[1], x[2], Integer.parseInt(x[3])
            );
            returnList.add(new_instance);
        }
        return returnList;
    }
}