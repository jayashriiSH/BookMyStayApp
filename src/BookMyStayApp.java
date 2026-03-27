import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates booking history tracking and reporting.
 *
 * @author Developer
 * @version 8.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking History & Reports\n");

        // Booking history
        BookingHistory history = new BookingHistory();

        // Sample confirmed reservations
        Reservation r1 = new Reservation("Alice", "Single");
        Reservation r2 = new Reservation("Bob", "Double");
        Reservation r3 = new Reservation("Charlie", "Suite");

        // Add to history (simulate confirmed bookings)
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Report service
        BookingReportService report = new BookingReportService();

        // Display all bookings
        report.showAllBookings(history);

        // Summary
        report.generateSummary(history);
    }
}

/**
 * CLASS - BookingHistory
 * Stores confirmed reservations
 */
class BookingHistory {

    private List<Reservation> bookings;

    public BookingHistory() {
        bookings = new ArrayList<>();
    }

    public void addReservation(Reservation r) {
        bookings.add(r);
    }

    public List<Reservation> getAllBookings() {
        return bookings;
    }
}

/**
 * CLASS - BookingReportService
 */
class BookingReportService {

    // Display all bookings
    public void showAllBookings(BookingHistory history) {

        System.out.println("All Bookings:\n");

        for (Reservation r : history.getAllBookings()) {
            System.out.println("Guest: " + r.getGuestName()
                    + " | Room Type: " + r.getRoomType());
        }

        System.out.println();
    }

    // Generate summary
    public void generateSummary(BookingHistory history) {

        System.out.println("Booking Summary:\n");

        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : history.getAllBookings()) {
            String type = r.getRoomType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        for (String type : countMap.keySet()) {
            System.out.println(type + " Rooms Booked: " + countMap.get(type));
        }
    }
}

/**
 * CLASS - Reservation
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}