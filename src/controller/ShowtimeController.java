package controller;
import java.util.ArrayList;
import java.util.Date;
import DAO.ShowtimeDAO;
import model.Showtime;
/**
 * Controller for Showtime objects,
 * each corresponds to a unique movie
 * @version 1.0
 * @since 2022-11-13
 */
public class ShowtimeController {
    /**
     * Contains an arraylist of showtimes
     */
    private ArrayList<Showtime> showtimes;
    /**
     * The corresponding cineplex ID
     */
    private int cineplexId;
    /**
     * The corresponding of cinema ID
     */
    private int cinemaId;
    /**
     * Showtime database access object
     */
    private ShowtimeDAO showtimeDao = new ShowtimeDAO();

    /**
     * Constructor for Showtime Controller
     * @param cineplexId The cineplex ID
     * @param cinemaId The cinema ID
     * @param height The height of seating layout
     * @param width The width of seating layout
     */
    public ShowtimeController(int cineplexId, int cinemaId, int height, int width) {
        this.cineplexId = cineplexId;
        this.cinemaId = cinemaId;
        this.showtimes = this.showtimeDao.load(cineplexId, cinemaId, height, width);
    }

    /**
     * Saves all local changes regarding showtimes to database
     */
    public void save() {
        this.showtimeDao.save(this.showtimes, cineplexId, cinemaId);
    }

    /**
     * Gets all show times in the particular cinema
     * @return ArrayList of Showtime objects
     */
    public ArrayList<Showtime> getAllShowtimes() {
        return this.showtimes;
    }

    /**
     * Gets showtime by ID
     * @param id The ID of showtime
     * @return The Showtime object with the specified ID
     */
    public Showtime getShowtimeById(int id) {
        return this.showtimes.get(id - 1);
    }

    /**
     * Gets the number of showtime
     * @return Integer of number of showtimes
     */
    public int getShowtimeCount() { return this.showtimes.size();}

    /**
     * Displays Showtime object by movie ID
     * @param movieId The movie ID
     * @return The ArrayList of integer displaying ID of showtime
     */
    public ArrayList<Integer> displaShowtimeByMovieId(int movieId){
        ArrayList<Integer> validIds = new ArrayList<Integer>();
        for (int i = 0; i < showtimes.size(); i++) {
            if (showtimes.get(i).getMovieId() == movieId) {
                validIds.add(i + 1);
                System.out.println("ID " + (i + 1) + ": " + showtimes.get(i));
            }
        }
        return validIds;
    }

    /**
     * The utility function to check whether movie exists in specific showtime
     * @param movieId The movie ID
     * @return True of False depending on the the existence of movie
     */
    public boolean isMovieExist(int movieId) {
        for(int i = 0; i<showtimes.size(); i++){
            Showtime showtime = showtimes.get(i);
            if(showtime.getMovieId() == movieId){
                return true;
            }
        }
        return false;

    }

    /**
     * Checks whether Showtime is duplicated or not
     * @param d The date of the showtime
     * @return The indicator whether it is duplicated or not
     */
    public boolean checkDuplicateShowtime(Date d) {
        for (Showtime s: this.showtimes) {
            if (s.getDate().compareTo(d) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the showtime to linked list
     * @param s Showtime object to be added
     */
    public void addShowtime(Showtime s) {
        // Find where it fits in showtimes LinkedList
        for (int i=0; i < this.showtimes.size(); i++) {
            if (s.compareTo(this.showtimes.get(i)) < 0 ) {
                this.showtimes.add(i, s);
                return;
            }
        }
        this.showtimes.add(s);
    }

    /**
     * Sorts showtime object date after Date/Time Edit in showtimes array
     * Checks for direction to sort Showtime based on new date and neighbors
     * @param index The index to be checked
     * @return The new index
     */
    public int sortShowtimeEdited(int index) {
        index = index-1;
        if (index == 0) {
            // Can only go up
            return sortShowtimeUpwards(index);
        }else if(index == this.showtimes.size()-1) {
            // Can only go down
            return sortShowtimeDownwards(index);
        }else {
            // Can go up or down, check neighbours first
            if (this.showtimes.get(index).compareTo(this.showtimes.get(index-1)) < 0) {
                // Smaller than below neighbour, go down
                return sortShowtimeDownwards(index);
            }else {
                return sortShowtimeUpwards(index);
            }
        }
    }

    /**
     * Sorts ArrayList of showtimes downwards from starting index to find new sorted position
     * @param index The index to be checked
     * @return The new index
     */
    public int sortShowtimeDownwards(int index) {
        for (int i=index-1; i >= 0 ; i--) {
            if (this.showtimes.get(index).compareTo(this.showtimes.get(i)) < 0) {
                if (i != 0) { // Not first item yet, if reach first item, then index is smallest item
                    continue;
                }
            }
            Showtime temp = this.showtimes.get(index);
            this.showtimes.remove(index);
            this.showtimes.add(i, temp);
            return i;
        }
        return index;
    }

    /**
     * Sorts ArrayList of showtimes upwards from starting index to find new sorted position
     * @param index The index to be checked
     * @return The new index
     */
    public int sortShowtimeUpwards(int index) {
        int replaceIndex = 0; //Because removing index, shifts the array down,
        for (int i=index+1; i < this.showtimes.size() ; i++) {
            if (this.showtimes.get(index).compareTo(this.showtimes.get(i)) > 0) {
                if (i != this.showtimes.size()-1) { // Not first item yet, if reach first item, then index is smallest item
                    continue;
                }else {
                    replaceIndex = i; //Add as last item
                    break;
                }
            }
            replaceIndex = i-1;
            break;
        }
        Showtime temp = this.showtimes.get(index);
        this.showtimes.remove(index);
        this.showtimes.add(replaceIndex, temp);
        return replaceIndex;
    }

    /**
     * Removes showtime by its ID
     * @param showtimeId The ID of removed Showtime
     */
    public void removeShowtimeById(int showtimeId) {
        this.showtimes.remove(showtimeId-1);
    }
}