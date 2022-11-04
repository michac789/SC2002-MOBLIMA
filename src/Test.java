import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("TESTING PROGRAM");



        String filepath = "database/movies.csv";
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));
        bw.write("abcde");
        bw.newLine();
        bw.close();
    }
}
