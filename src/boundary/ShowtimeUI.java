package boundary;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import controller.AppController;
import controller.CinemaController;
import controller.ShowtimeController;
import model.Cinema;
import model.Movie;
import model.Showtime;

public class ShowtimeUI {
    public static void admin() {
        while (true) {
            int cineplexId = CineplexUI.promptValidCineplexId();
            if (cineplexId == -1) {
                break;
            }

            CinemaController cc = AppController.cc.getCineplexById(cineplexId).getController();
            while (true) {
                int cinemaId = CinemaUI.promptValidCinemaId(cc);
                if (cinemaId == -1) {
                    break;
                }
                Cinema selectedCinema =  cc.getCinemaByCode(cinemaId);
                ShowtimeController shc = selectedCinema.getController();
                while (true) {
                    UtilUI.printBlue("|=========|Showtime Admin Panel|=========|");
                    System.out.print(
                                    "(1) Display All Showtimes\n" +
                                    "(2) Create new Showtime\n" +
                                    "(3) Edit Showtime\n" +
                                    "(4) Exit\n");
                    int choice = UtilUI.getInt("Select Action: ");
                    switch (choice) {
                        case 1:
                            displayShowtimes(shc);
                            break;
                        case 2:
                            createShowtime(shc, cinemaId, cineplexId, selectedCinema.getHeight(), selectedCinema.getWidth(), selectedCinema.getSeatConfiguration());
                            break;
                        case 3:
                            editShowtime(shc);
                            break;
                        case 4:
                            return;
                        default:
                            UtilUI.printRed("Invalid action, try again!");
                            break;
                    }
                }
            }
        }
    }

    public static void editShowtime(ShowtimeController shc) {
        int showtimeId = promptValidShowtimeId(shc);
        if (showtimeId == -1) {
            return;
        }

        int option;
        while (true) {
            System.out.print(
                    "(1) Edit Showtime Date & Time\n" +
                    "(2) Edit Showing Movie\n" +
                    "(3) Remove Showtime\n" +
                    "(4) Exit\n");
            option = UtilUI.getInt("Select action: ");
            switch (option) {
                case 1:
                    Date formattedDate = promptValidShowtimeDate(shc);
                    shc.getShowtimeById(showtimeId).setDate(formattedDate);
                    showtimeId = shc.sortShowtimeEdited(showtimeId) + 1; //Get new sorted index for this showtime, add 1 as we reference showtime index from 1
                    break;
                case 2:
                    int movieId = MovieUI.promptValidMovieId(true, true, true); // TODO - careful!
                    shc.getShowtimeById(showtimeId).setMovieId(movieId);
                    break;
                case 3:
                    shc.removeShowtimeById(showtimeId);
                    return;
                case 4:
                    return;
            }
        }
    }

    public static void displayShowtimes(ShowtimeController shc) {
        ArrayList<Showtime> showtimes = shc.getAllShowtimes();
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.printf("%3d: %s\n", (i+1), showtimes.get(i));
        }
    }

    public static int promptValidShowtimeId(ShowtimeController shc) {
        displayShowtimes(shc);
        int showtimeId;
        while (true) {
            showtimeId = UtilUI.getInt("Enter showtime id: (enter -1 to exit)");
            if (showtimeId == -1) { return -1;}
            if (showtimeId >= 1 && showtimeId <= shc.getShowtimeCount()) {
                break;
            }
            UtilUI.printRed("Invalid Showtime ID");
        }
        return showtimeId;
    }

    public static void createShowtime(ShowtimeController shc, int cinemaId, int cineplexId, int height, int width, String seatConfiguration) {
        // Cinemahall should be given
        System.out.println("Creating new showtime:");

        int movieId = MovieUI.promptValidMovieId(true, true, false);
        Movie m = AppController.mc.getMovieById(movieId);

        Date formattedDate = promptValidShowtimeDate(shc);

        Showtime s = new Showtime(m.getMovieId(), formattedDate, height, width, cineplexId, cinemaId, seatConfiguration);
        shc.addShowtime(s);
    }

    public static Date promptValidShowtimeDate(ShowtimeController shc) {
        String timeslot, movieDate;
        DateFormat checkValidDate = new SimpleDateFormat("ddMMyyyy");
        checkValidDate.setLenient(false); //Strict date interpretation, falsely entered date results in null obj
        Date testDate;
        while (true) {
            while (true) {
                movieDate = UtilUI.getStr("Enter movie date(ddmmyyyy):");
                try {
                    testDate = checkValidDate.parse(movieDate);
                } catch (ParseException e) {
                    UtilUI.printRed("Invalid Date Entry");
                    continue;
                }

                if (testDate != null) {
                    break;
                }
                UtilUI.printRed("Invalid movie date entered.");
            }

            while (true) {
                timeslot = UtilUI.getStr("Enter movie showtime(hhmm):");

                if (timeslot.length() != 4) {
                    System.out.println("Invalid Entry");
                    continue;
                }
                int hr, min;
                hr = Integer.parseInt(timeslot.substring(0,1));
                min = Integer.parseInt(timeslot.substring(2,3));

                if (hr >= 0 && hr <= 24) {
                    if (min >= 0 && min <= 59) {
                        break;
                    }
                }
                UtilUI.printRed("Invalid movie time entered.");
            }

            DateFormat formatDate = new SimpleDateFormat("ddMMyyyy:HHmm");

            String parseStr = movieDate + ":" + timeslot;
            Date formattedDate;
            try {
                formattedDate = formatDate.parse(parseStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            if (!shc.checkDuplicateShowtime(formattedDate)) {
                return formattedDate;
            }
            UtilUI.printRed("Existing Showtime with same Time and Date already exist."); // Rerun prompts
        }
    }
}