package DAO;
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
            bw = new BufferedWriter(new FileWriter(filepath, append));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearFile(String filepath) {
        try {
            bw = new BufferedWriter(new FileWriter(filepath, false));
            bw.write("");
            bw.close();
            bw = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeText(String[] text) {
        if (bw != null) {
            try {
                for (int i=0; i < text.length; i++) {
                    bw.write(text[i]);
                    bw.newLine();
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
                bw.newLine();
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

    public static void deleteFile(String filepath) {
        File deleteFile = new File(filepath);
        if (deleteFile.delete()) {
            // Deleted Successfully
        } else {
            // Failed to delete
        }
    }
    public static LinkedList<String> readText(String filepath) {
        LinkedList<String> rtnList = new LinkedList<String>();
        try {
            br = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = br.readLine()) != null) {
                rtnList.add(line);
            }
            br.close();
            return rtnList;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rtnList;
    }
}
