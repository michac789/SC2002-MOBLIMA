package base;
import java.io.*;
import java.util.LinkedList;

public class BaseDAO {
    /*
        You should specify the file path on where to store the database
        in form of csv files.
    */
    // String FILEPATH = "some-path";

    /*
        The 'save' method saves all the changes from objects into database.
        You should define this method and change 'BaseModel' with whatever your
        class name is. Then, replace the string 'todo' with the accessors
        from the object instance using csv format. The commented code below
        provides a template for you to copy paste this method defintion.
    */
    // public void save(ArrayList<BaseModel> instances) {
    //     emptyFile(FILEPATH);
    //     String writeStr = "";
    //     for (int i = 0; i < instances.size(); i++) {
    //         BaseModel instance = instances.get(i);
    //         writeStr = String.format(
    //             "todo", instance
    //         );
    //     }
    //     writeLine(FILEPATH, writeStr);
    // }

    /*
        The 'load' method loads all the data from the database into objects.
        You should define this method and change 'BaseModel' with whatever
        your class name is. Then, replace the string 'todo' with creating
        your new class, converting however many fields you have from string
        to the appropriate data types. The commented code below provides a
        template for you to copy paste this method defintion.
    */
    // public ArrayList<BaseModel> load() {
    //     LinkedList<String> instances = this.getData(FILEPATH);
    //     ArrayList<BaseModel> returnList = new ArrayList<BaseModel>();
    //     for (int i = 0; i < instances.size(); i++) {
    //         System.out.println(instances.get(i));
    //         String[] x = instances.get(i).split(",");
    //         BaseModel new_instance = "todo" + singleField[0];
    //         returnList.add(new_instance);
    //     }
    //     return returnList;
    // }

    public void emptyFile(String path) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(path, false));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String path, String str) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.write(str);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<String> getData(String path) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            String line;
            LinkedList<String> returnList = new LinkedList<String>();
            while ((line = br.readLine()) != null) {
                returnList.add(line);
            }
            br.close();
            return returnList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}