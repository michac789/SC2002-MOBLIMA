import java.util.LinkedList;

public class MovieDAO {
    String FILEPATH = "database/Movies.csv";

    public void saveMovie(Movie m) {
        // movieId, title, duration, director, cast, status, age rating, is3D, isBlockbuster
        String writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%d,%d",
            m.getMovieId(), m.getTitle(), m.getDurationMinutes(),m.getDirector(), m.getCast(),
            m.getShowStatus(), m.getAgeRating(), (m.is3D() ? 1: 0), (m.isBlockbuster() ? 1: 0)
        );
        DAO.openFile(FILEPATH, true);
        DAO.writeText(writeStr);
        DAO.closeFile();
    }

    public void saveMovie(LinkedList<Movie> movies) {
        String filename = "Movies.csv";
        AppController.dao.clearFile(filename);
        AppController.dao.openFile(filename, true);
        for (Movie m: movies) {
            // movieId, title, duration, director, cast, status, age rating, is3D, isBlockbuster
            String writeStr = String.format("%d,%s,%d,%s,%s,%s,%s,%d,%d",
                    m.getMovieId(), m.getTitle(), m.getDurationMinutes(), m.getDirector(), m.getCast(),
                    m.getShowStatus(), m.getAgeRating(), (m.is3D() ? 1 : 0), (m.isBlockbuster() ? 1 : 0));
            AppController.dao.writeText(writeStr);
        }
        AppController.dao.closeFile();
    }
    public void getMovie(LinkedList<Movie> movieList) {
        LinkedList<String> movieStr = DAO.readText(FILEPATH);
        if (movieStr == null || movieStr.size() == 0) { return;}
        String[] values;
        Movie m;
        for (int i = 0; i < movieStr.size(); i++) {
            values = movieStr.get(i).split(",");
            m = new Movie(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]), values[3],
                values[4], Movie.showStatusOptions.valueOf(values[5]), Movie.ageRatingOptions.valueOf(values[6]),
                (Integer.parseInt(values[7])==1), (Integer.parseInt(values[8])==1));
            movieList.add(m);
        }
    }
}
