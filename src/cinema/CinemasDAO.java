package cinema;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CinemasDAO {
    String FILEPATH = "database/cinemas.csv";

    public void saveCinemas(ArrayList<Cinema> cinemas) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(FILEPATH, false));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < cinemas.size(); i++) {
            try {
                bw = new BufferedWriter(new FileWriter(FILEPATH, true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Cinema cinema = cinemas.get(i);
            String writeStr = String.format("%d,%d,%d,%d,%s",
                cinema.getCinemaId(), cinema.getCinemaCode(),
                cinema.getHeight(), cinema.getWidth(), cinema.getCinemaClass());
            try {
                bw.write(writeStr);
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public LinkedList<String> getData() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(FILEPATH));
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

    public ArrayList<Cinema> loadMovies() {
        LinkedList<String> cinemas = this.getData();
        ArrayList<Cinema> retCinemas = new ArrayList<Cinema>();
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println(cinemas.get(i));
            String singleData = cinemas.get(i);
            String[] x = singleData.split(",");
            Cinema cinema = new Cinema(
                Integer.parseInt(x[1]),
                Integer.parseInt(x[2]),
                Integer.parseInt(x[3]),
                Integer.parseInt(x[4]),
                Cinema.showClassOptions.valueOf(x[5])
            );
            retCinemas.add(cinema);
        }
        return retCinemas;
    }
}
