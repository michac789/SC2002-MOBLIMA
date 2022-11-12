package DAO;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Booking;

public class BookingDAO extends BaseDAO {
    private String BASEPATH = "src/database/User/Booking/";

    public void save(ArrayList<Booking> instances, int movieGoerId) {
        String FILEPATH = BASEPATH + movieGoerId + ".csv";
        emptyFile(FILEPATH);
        String header = "userId,movieTitle,cineplexLocation,cinemaCode,datetime,seatCode,price";
        writeLine(FILEPATH, header);
        String writeStr = "";
        for (int i = 0; i < instances.size(); i++) {
            Booking instance = instances.get(i);
            writeStr = String.format("%d,%s,%s,%d,%s,%s,%.2f",
                instance.getUserId(), instance.getTitle(),
                instance.getLocation(), instance.getCinemaCode(),
                instance.getDatetime(), instance.getSeat(), instance.getPrice()
            );
            writeLine(FILEPATH, writeStr);
        }
    }
    
    public ArrayList<Booking> load(int movieGoerId) {
        String FILEPATH = BASEPATH + movieGoerId + ".csv";
        LinkedList<String> instances = this.getData(FILEPATH);
        ArrayList<Booking> returnList = new ArrayList<Booking>();
        for (int i = 1; i < instances.size(); i++) {
            String[] x = instances.get(i).split(",");
            Booking newInstance = new Booking(
                Integer.parseInt(x[0]), x[1], x[2],
                Integer.parseInt(x[3]), x[4], x[5],
                Float.parseFloat(x[6])
            );
            returnList.add(newInstance);
        }
        return returnList;
    }
}