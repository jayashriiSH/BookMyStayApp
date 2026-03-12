import java.util.LinkedList;
import java.util.Queue;

/**
 * BookMyStayApp
 *
 * Demonstrates booking request handling with FIFO queue.
 *
 * Version: 5.0
 * Author: Jayashri
 */

abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() { return roomType; }
    public int getNumberOfBeds() { return numberOfBeds; }
    public double getPricePerNight() { return pricePerNight; }

    public abstract void displayRoomDetails();
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 50.0); }
    @Override
    public void displayRoomDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0); }
    @Override
    public void displayRoomDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 150.0); }
    @Override
    public void displayRoomDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

// Reservation request
class Reservation {
    private String guestName;
    private String requestedRoomType;

    public Reservation(String guestName, String requestedRoomType) {
        this.guestName = guestName;
        this.requestedRoomType = requestedRoomType;
    }

    public String getGuestName() { return guestName; }
    public String getRequestedRoomType() { return requestedRoomType; }

    public void displayRequest() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + requestedRoomType);
    }
}

// Booking request queue
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation); // FIFO
        System.out.println("Booking request received: " + reservation.getGuestName());
    }

    // Display all queued requests
    public void displayQueue() {
        System.out.println("\nCurrent Booking Request Queue:");
        for (Reservation res : requestQueue) {
            res.displayRequest();
        }
    }

    // Poll next request (not used for inventory yet)
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }
}

// Application entry point
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v5.0 ===\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate incoming requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display all requests
        bookingQueue.displayQueue();

        System.out.println("\nApplication execution completed successfully.");
    }
}