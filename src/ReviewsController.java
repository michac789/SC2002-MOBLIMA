import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class ReviewsController {
    private int movieId;
    private double rating;

    private Scanner sc;

    private int sorted = 0;
    private LinkedList<Review> reviews;

    private ReviewDAO dao;
    public ReviewsController(int movieId) {
        this.movieId = movieId;
        sc = new Scanner(System.in);
        reviews = new LinkedList<Review>();
        dao = new ReviewDAO();
        dao.getReviews(movieId, reviews);
        sortByRating();
        System.out.println("Review for movieId " + movieId + " loaded.");
    }

    public void createReview(String movieName, int userId) {
        int reviewScore = -1;
        String reviewText = "";
        //Temporary Take in moviename, will use movie id ltr? Or implementation of creating review can take in moviename instead?

        System.out.printf("Creating Review for %s\n", movieName);
        do {
            System.out.print("Enter Movie Rating (0-5): ");
            reviewScore = sc.nextInt();
            sc.nextLine(); // Scanner Skipping reviewText Scanner
            if (reviewScore < 0 || reviewScore > 5) {
                System.out.println("Invalid Entry!");
            }else {
                break;
            }
        } while (true);
        System.out.print("Enter Review: \n");
        reviewText = sc.nextLine(); // Do i have to read comments that have newline??, if so need replace this
        reviewText = reviewText.replace(",", "\\comma");
        Review newReview = new Review(userId, reviewScore, reviewText);
        addReview(newReview);

        dao.saveReview(newReview, this.movieId);
    }

    // Keeps reviews linkedlist sorted at all times
    public void addReview(Review r) {
        if (reviews.size() == 0) {
            reviews.add(r);
        }else {
            for (int i=0; i < reviews.size();i++) {
                if (reviews.get(i).getRating() < r.getRating()) {
                    reviews.add(i,r);
                }
            }
        }
    }
//    public void saveReview(Review r) {
//        String filename = movieId + "_Reviews.csv";
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
//            String line = r.savetoCSV();
//            bw.write(line);
//            bw.close();
//        } catch (FileNotFoundException e) {
//            return;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void getReviews() {
//        String filename = this.movieId + "_Reviews.csv";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(filename));
//            String line;
//            String[] values;
//            Review r;
//            while ((line = br.readLine()) != null) {
//                values = line.split(",");
//                values[2] = values[2].substring(1, values[2].length()-1); // Remove first and last char, which are "" added for CSV purposes
//                values[2] = values[2].replace("\\comma", ",");
//                r = new Review(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2]);
//                reviews.add(r);
//            }
//        } catch (FileNotFoundException e) {
//            return;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void displayReviews(int displayBy) {
        if (sorted == 0) {sortByRating();}

        if (displayBy == 1) {
            // Descending
            for (int i = 0; i < reviews.size(); i++) {
                System.out.print(reviews.get(i));
            }
        } else {
            // Ascending
            for (int i = reviews.size()-1; i >= 0; i--) {
                System.out.print(reviews.get(i));
            }
        }
    }

    public void sortByRating() {
        // Sort by Descending Rating
        // Need by Alphabetical???
        Review temp;

        // Insertion Sort
        for (int i=1; i < reviews.size(); i++) {
            for (int j=i; j > 0; j--) {
                if (reviews.get(j).compareTo(reviews.get(j - 1)) > 0) {
                    temp = reviews.get(j - 1);
                    reviews.set(j - 1, reviews.get(j));
                    reviews.set(j, temp);
                }else {
                    break;
                }
            }
        }

        // Quicksort?

    }
}
