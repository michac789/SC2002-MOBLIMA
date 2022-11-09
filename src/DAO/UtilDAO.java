package DAO;
import java.io.*;

public class UtilDAO {
    private static BufferedWriter bw;

    public static void createFolder(String filepath) {
        File folder = new File(filepath);
        folder.mkdir();
    }

    public static void createFile(String filepath) {
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

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