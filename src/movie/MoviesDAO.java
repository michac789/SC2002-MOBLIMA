package movie;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class MoviesDAO {
    String FILEPATH = "database/movies.csv";

    public void saveMovies(ArrayList<Movie> movies) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(FILEPATH, false));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < movies.size(); i++) {
            try {
                bw = new BufferedWriter(new FileWriter(FILEPATH, true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Movie movie = movies.get(i);
            String writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s",
                movie.getMovieId(), movie.getTitle(), movie.getDurationMinutes(),
                movie.getDirector(), movie.getCast(), movie.getShowStatus(),
                movie.getAgeRating(), movie.getIs3D(), movie.getIsBlockbuster());
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

    public ArrayList<Movie> loadMovies() {
        LinkedList<String> movies = this.getData();
        ArrayList<Movie> retMovies = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
            String singleData = movies.get(i);
            String[] x = singleData.split(",");
            Movie movie = new Movie(
                x[1], Integer.parseInt(x[2]), x[3], x[4],
                Movie.showStatusOptions.valueOf(x[5]),
                Movie.ageRatingOptions.valueOf(x[6]),
                Boolean.valueOf(x[7]), Boolean.valueOf(x[8])
            );
            retMovies.add(movie);
        }
        return retMovies;
    }
}
