package DAO;
import java.io.*;

/**
 * Represents common utility function used across DAO
 * @version 1.0
 * @since 2022-11-13
 */
public class UtilDAO {
    /**
     * Buffered writer in order to do CRUD operation
     */
    private static BufferedWriter bw;

    /**
     * Creates new folder for database
     * @param filepath The filepath of the directory for new folder
     */
    public static void createFolder(String filepath) {
        File folder = new File(filepath);
        folder.mkdir();
    }

    /**
     * Creates a new file for database
     * @param filepath The filepath of the directory for new file
     */
    public static void createFile(String filepath) {
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Writes a file to specific path with 2 options: appending or overriding
     * @param filepath The filepath of the file that wants to be edited
     * @param text The input string
     * @param append Boolean, true if wants to append, false if wants to override.
     */
    public static void writeFile(String filepath, String text, boolean append) {
        try {
            bw = new BufferedWriter(new FileWriter(filepath, append));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.write(text);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
            bw = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}