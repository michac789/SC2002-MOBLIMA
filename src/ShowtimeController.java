import sun.awt.image.ImageWatched;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class ShowtimeController {
    private LinkedList<Showtime> showtimes;

    private int movieId;

    private static ShowtimeDAO showtimeDAO = new ShowtimeDAO();

    private Scanner sc;
    public ShowtimeController(int movieId) {
        this.movieId = movieId;
        showtimes = new LinkedList<Showtime>();
        sc = new Scanner(System.in);
    }

    // Cinema is linked to showtime
    // Showtime is linked to movie
    // CinemaController creates showtimes
    public void createShowtime() {
        // Maybe have user
        String movieTitle = "Batman";
        System.out.println("Creating new showtime for " + movieTitle);

        int timeslot, cinemaHall;
        String movieDate;
        DateFormat checkValidDate = new SimpleDateFormat("ddMMyyyy");
        checkValidDate.setLenient(false); //Strict date interpretation, falsely entered date results in null obj

        Date testDate;
        while (true) {
            System.out.println("Enter movie date(ddmmyyyy):");
            movieDate = sc.next();

            try {
                testDate = checkValidDate.parse(movieDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            if (testDate != null) {
                break;
            }
            System.out.println("Invalid movie date entered.");
        }

        while (true) {
            System.out.println("Enter movie showtime(hhmm):");
            timeslot = sc.nextInt();

            int hr,min;
            hr = timeslot/100;
            min = timeslot - (hr*100);

            if (hr >= 0 && hr <= 24) {
                if (min >= 0 && min <= 59) {
                    break;
                }
            }
            System.out.println("Invalid movie time entered.");
        }

        DateFormat formatDate = new SimpleDateFormat("ddMMyyyy:HHmm");

        String parseStr = movieDate + ":" + Integer.toString(timeslot);
        Date formattedDate;
        try {
            formattedDate = formatDate.parse(parseStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Enter cinema hall:");
        cinemaHall = sc.nextInt();  //Check if cinema hall valid

        if (checkDuplicateShowtime(formattedDate, cinemaHall)) {
            //Repeat??
        }

        Showtime s = new Showtime(this.movieId, cinemaHall, formattedDate);
        addShowtime(s);

        showtimeDAO.saveShowtime(this.movieId, s);
    }

    public void updateShowtime() throws ParseException {
        System.out.println("Select Showtime");
        Date todayDate = new Date();
        Date formattedDate;
        LinkedList<Date> dateList = new LinkedList<Date>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Get Unique Dates, After today's Date
        for (Showtime s: this.showtimes) {
            if (s.getDate().compareTo(todayDate) >= 0 ) {
                formattedDate = dateFormat.parse(dateFormat.format(s.getDate()));
                // After or Equsl to Curr time
                if (dateList.size() == 0) {
                    dateList.add(formattedDate);
                }else {
                    if (formattedDate.compareTo(dateList.getLast()) > 0) {
                        // Greater than last of dateList = new Date
                        dateList.add(formattedDate);
                    }
                }
            }
        }
        int i=0;
        System.out.println("Select a date to view showtimes:");
        for (Date d: dateList) {
            System.out.printf("%d: %s\n",i, dateFormat.format(d));
            i++;
        }
        int dateOption = sc.nextInt();
        int[] range = findShowtimes(dateList.get(dateOption));
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        int k=0;
        for (int j=range[0]; j < range[1];j++) {
            System.out.printf("%d: %s\t",k, timeFormat.format(showtimes.get(j)));
            k++;
        }
        int showtimeOption = sc.nextInt();

        System.out.printf("Editing showtime %s:\n", timeFormat.format(showtimes.get(showtimeOption)));
        System.out.printf("1. Edit showtime\n" +
                "2. Edit Movie shown\n" +
                "3. Remove Showtime\n");
        int editOption = sc.nextInt();

        DateFormat showtimeDateFormat = new SimpleDateFormat("dd/MM/yyyy:HHmm");
        switch(editOption) {
            case 1:
                System.out.print("Enter new showtime(hhmm):");
                int showtime = sc.nextInt();
                Date newDate = showtimeDateFormat.parse(dateFormat.format(dateList.get(dateOption)) + ":" + Integer.valueOf(showtime));
                this.showtimes.get(showtimeOption).setDate(newDate);
                break;
            case 2:
                Movie m = AppController.mc.movieSelection();
                this.showtimes.get(showtimeOption).setMovieId(m.getMovieId());
                break;
            case 3:
                System.out.printf("Confirm removal of %s showtime?(Yes,No):", timeFormat.format(this.showtimes.get(showtimeOption).getDate());
                String selection = sc.nextLine();
                if (selection.toLowerCase() == "yes") {
                    this.showtimes.remove(showtimeOption);
                    System.out.println("Successfully removed showtime.");
                    break;
                }
                break;
        }

        showtimeDAO.saveShowtime(this.showtimes);
    }

    public boolean checkDuplicateShowtime(Date d, int cinemaHall) {
        for (Showtime s: this.showtimes) {
            if (s.getCinemaId() == cinemaHall && s.getDate() == d) {
                return true;
            }
        }
    }
    public void getShowtime() {
        showtimeDAO.getShowtimes(this.movieId, this.showtimes);
        sortShowtimes();
    }


    public void displayShowtime() {
        // Display Today's Date
        Date todayDate = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        int[] range = findShowtimes(todayDate);
        Date showDate;
        if (range[0] != -1) {
            for (int i=range[0]; i < range[1];i++) {
                showDate = showtimes.get(i).getDate();
                System.out.printf("%s\t", df.format(showDate));
            }
        }
    }

    // Is there a way to dont duplicate the code???
    public void displayShowtime(Date d) {
        // Display based on Date
        DateFormat df = new SimpleDateFormat("HH:mm");
        int[] range = findShowtimes(d);
        Date showDate;
        if (range[0] != -1) {
            for (int i=range[0]; i < range[1];i++) {
                showDate = showtimes.get(i).getDate();
                System.out.printf("%s\t", df.format(showDate));
            }
        }
    }

    public int[] findShowtimes(Date d) {
        int[] rtn = new int[2];
        rtn[0] = -1;
        rtn[1] = showtimes.size();
        Date nextDay = new Date(d.getYear(), d.getMonth(), d.getDate()+1);
        for (int i = 0; i < showtimes.size(); i++) {
            if (d.compareTo(showtimes.get(i).getDate()) <= 0) {
                //Same or After Current Time
                rtn[0] = i;
                break;
            }
        }

        for (int i=rtn[0]+1; i < showtimes.size(); i++) {
            if (nextDay.compareTo(showtimes.get(i).getDate()) <= 0) {
                //Same or After Current Time
                rtn[1] = i;
                break;
            }
        }
        return rtn;
    }

    //1) << 12 Nov  | 13 Nov | 2) 14 Nov>>
    // Sort Showtimes and cleanup invalid showtimes(those that are before today's date)
    public void sortShowtimes(){
        if (this.showtimes.size() == 0) {
            return;
        }

        Date todaysDate = new Date();
        Showtime temp;

        // Ensure first showtime is not invalid
//        while (true) {
//            if (todaysDate.before(showtimes.get(0).getDate())) {
//                showtimes.remove(0);
//            }else {
//                break;
//            }
//        }

        for (int i=1; i < showtimes.size(); i++) {
            for (int j=i; j > 0; j--) {
                if (showtimes.get(j).compareTo(showtimes.get(j - 1)) == -1) {
                    temp = showtimes.get(j - 1);
                    showtimes.set(j - 1, showtimes.get(j));
                    showtimes.set(j, temp);
                }else {
                    break;
                }
            }
        }
    }

    // Add shopwtime into showtimes LinkedList
    public void addShowtime(Showtime s) {
        // Find where it fits in showtimes LinkedList
        for (int i=0; i < showtimes.size(); i++) {
            if (s.compareTo(showtimes.get(i)) < 0 ) {
                showtimes.add(i, s);
                return;
            }
        }
        showtimes.addLast(s);
    }
}
