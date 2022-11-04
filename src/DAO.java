import java.io.*;
import java.util.LinkedList;

public class DAO {
    static BufferedReader br;
    static BufferedWriter bw;
    public DAO() {
        bw = null;
    }

    public static void openFile(String filepath, boolean append) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, append));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeText(String[] text) {
        if (bw != null) {
            try {
                for (int i=0; i < text.length; i++) {
                    bw.write(text[i]);
                }
            } catch (FileNotFoundException e) {
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeText(String text) {
        if (bw != null) {
            try {
                bw.write(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeFile() {
        if (bw != null) {
            try {
                bw.close();
                bw = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static LinkedList<String> readText(String filepath) {
        try {
            br = new BufferedReader(new FileReader(filepath));
            String line;
            LinkedList<String> rtnList = new LinkedList<String>();
            while ((line = br.readLine()) != null) {
                rtnList.add(line);
            }
            br.close();
            return rtnList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
