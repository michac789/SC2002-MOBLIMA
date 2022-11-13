package DAO;
import java.io.*;
import java.util.LinkedList;

/**
 * Represents the superclass of all DAO class that serves common functionalities
 * @version 1.0
 * @since 2022-11-13
 */
public abstract class BaseDAO {
    /**
     * Empties the database csv file
     * @param path The path of database file
     */
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

    /**
     * Writes a line in database in the file specified in the path
     * @param path The path of database file
     * @param str The string that is about to be written in database
     */
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

    /**
     * Gets data from the file specified in the path
     * @param path The path of database file
     * @return Linked list containing each line of data read
     */
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