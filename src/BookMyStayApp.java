import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates concurrent booking with thread safety.
 *
 * @author Developer
 * @version 11.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        RoomInventory inventory = new RoomInventory();

        // Shared booking service
        BookingService service = new BookingService(inventory);

        // Threads (simulate multiple users)
        Thread t1 = new Thread(() -> service.allocateRoom("Alice", "Single"));
        Thread t2 = new Thread(() -> service.allocateRoom("Bob", "Single"));
        Thread t3 = new Thread(() -> service.allocateRoom("Charlie", "Single"));

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * CLASS - BookingService
 */
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // CRITICAL SECTION (synchronized)
    public synchronized void allocateRoom(String guest, String type) {

        int available = inventory.getAvailability(type);

        if (available <= 0) {
            System.out.println("Booking Failed for " + guest + " (No rooms)");
            return;
        }

        // Simulate delay (race condition scenario)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Update inventory safely
        inventory.updateAvailability(type, available - 1);

        System.out.println("Booking Confirmed for " + guest);
    }
}

/**
 * CLASS - RoomInventory
 */
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 1); // only 1 room
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
    }
}