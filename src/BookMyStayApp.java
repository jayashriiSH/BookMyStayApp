import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates booking cancellation with inventory rollback.
 *
 * @author Developer
 * @version 10.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation System\n");

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);
        CancellationService cancelService = new CancellationService(inventory);

        // Confirm bookings
        bookingService.allocateRoom("R101", "Single");
        bookingService.allocateRoom("R102", "Double");

        // Cancel booking
        cancelService.cancelBooking("R101", "Single");

        // Try invalid cancellation
        cancelService.cancelBooking("R999", "Suite");
    }
}

/**
 * CLASS - BookingService
 */
class BookingService {

    private RoomInventory inventory;
    private Map<String, String> activeBookings; // reservationId → roomType

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        activeBookings = new HashMap<>();
    }

    public void allocateRoom(String reservationId, String type) {

        int available = inventory.getAvailability(type);

        if (available <= 0) {
            System.out.println("Booking Failed: No rooms available for " + type);
            return;
        }

        inventory.updateAvailability(type, available - 1);
        activeBookings.put(reservationId, type);

        System.out.println("Booking Confirmed: " + reservationId + " (" + type + ")");
    }

    public boolean isValidBooking(String reservationId) {
        return activeBookings.containsKey(reservationId);
    }

    public String getRoomType(String reservationId) {
        return activeBookings.get(reservationId);
    }

    public void removeBooking(String reservationId) {
        activeBookings.remove(reservationId);
    }
}

/**
 * CLASS - CancellationService
 */
class CancellationService {

    private RoomInventory inventory;

    // Stack for rollback tracking
    private Stack<String> rollbackStack;

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
        rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId, String type) {

        // Validate booking existence
        if (inventory.getAvailability(type) == -1) {
            System.out.println("Invalid Room Type");
            return;
        }

        // Simulate validation (in real → check BookingService)
        if (reservationId == null || reservationId.isEmpty()) {
            System.out.println("Invalid Reservation ID");
            return;
        }

        // Rollback logic
        rollbackStack.push(reservationId);

        int available = inventory.getAvailability(type);
        inventory.updateAvailability(type, available + 1);

        System.out.println("Booking Cancelled: " + reservationId);
    }
}

/**
 * CLASS - RoomInventory
 */
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }

    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
    }
}