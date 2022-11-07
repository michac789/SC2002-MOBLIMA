import java.util.LinkedList;
import user.Admin;
import java.util.ArrayList;

public class AdminDAO extends BaseDAO {
    String FILEPATH = "src/database/Users/Admins.csv";

    public void save(ArrayList<Admin> instances) {
        emptyFile(FILEPATH);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Admin instance = instances.get(i);
            writeStr = String.format("%s,%s",
                instance.getUsername(), instance.getPassword()
            );
            writeLine(FILEPATH, writeStr);
        }
    }

    public ArrayList<Admin> load() {
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Admin> returnList = new ArrayList<Admin>();
        for (int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
            String[] x = instances.get(i).split(",");
            Admin new_instance = new Admin(
                x[0], x[1]
            );
            returnList.add(new_instance);
        }
        return returnList;
    }
}