package controller;
import java.util.ArrayList;
import java.util.Date;
import DAO.ShowtimeDAO;
import model.Showtime;

public class ShowtimeController {
    private ArrayList<Showtime> showtimes;
    private int cineplexId;
    private int cinemaId;

    private ShowtimeDAO showtimeDao = new ShowtimeDAO();

    public ShowtimeController(int cineplexId, int cinemaId, int height, int width) {
        this.cineplexId = cineplexId;
        this.cinemaId = cinemaId;
        this.showtimes = this.showtimeDao.load(cineplexId, cinemaId, height, width);
    }

    public void save() {
        this.showtimeDao.save(this.showtimes, cineplexId, cinemaId);
    }

    public ArrayList<Showtime> getAllShowtimes() {
        return this.showtimes;
    }

    public Showtime getShowtimeById(int id) {
        return this.showtimes.get(id - 1);
    }

    public int getShowtimeCount() { return this.showtimes.size();}

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

    public boolean isMovieExist(int movieId) {
        for(int i = 0; i<showtimes.size(); i++){
            Showtime showtime = showtimes.get(i);
            if(showtime.getMovieId() == movieId){
                return true;
            }
        }
        return false;

    }

    public boolean checkDuplicateShowtime(Date d) {
        for (Showtime s: this.showtimes) {
            if (s.getDate().compareTo(d) == 0) {
                return true;
            }
        }
        return false;
    }

    // Add shopwtime into showtimes LinkedList
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

    public void removeShowtimeById(int showtimeId) {
        this.showtimes.remove(showtimeId);
    }
}