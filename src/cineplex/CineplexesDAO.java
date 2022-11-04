package cineplex;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CineplexesDAO {
    String FILEPATH = "database/cineplexes.csv";

    public void saveCineplexes(ArrayList<Cineplex> cineplexes) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(FILEPATH, false));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < cineplexes.size(); i++) {
            try {
                bw = new BufferedWriter(new FileWriter(FILEPATH, true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Cineplex cineplex = cineplexes.get(i);
            String writeStr = String.format("%d,%s",
                cineplex.getCineplexId(), cineplex.getLocation());
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

    public ArrayList<Cineplex> loadMovies() {
        LinkedList<String> cineplexes = this.getData();
        ArrayList<Cineplex> retCineplexes = new ArrayList<Cineplex>();
        for (int i = 0; i < cineplexes.size(); i++) {
            System.out.println(cineplexes.get(i));
            String singleData = cineplexes.get(i);
            String[] x = singleData.split(",");
            Cineplex movie = new Cineplex(x[1]);
            retCineplexes.add(movie);
        }
        return retCineplexes;
    }
}
