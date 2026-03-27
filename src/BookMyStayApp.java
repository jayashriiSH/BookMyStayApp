import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates booking request handling using FIFO queue.
 *
 * @author Developer
 * @version 5.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue\n");

        // Queue for booking requests
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Add booking requests (arrival order)
        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Double"));
        bookingQueue.add(new Reservation("Charlie", "Suite"));

        // Display queue (NO PROCESSING)
        System.out.println("Requests in Queue (FIFO Order):\n");

        for (Reservation r : bookingQueue) {
            System.out.println("Guest: " + r.getGuestName() +
                    " | Room Type: " + r.getRoomType());
        }
    }
}

/**
 * CLASS - Reservation
 * Represents a booking request
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