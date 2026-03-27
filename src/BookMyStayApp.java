import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates error handling and validation in booking system.
 *
 * @author Developer
 * @version 9.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Validation System\n");

        RoomInventory inventory = new RoomInventory();

        BookingService service = new BookingService(inventory);

        try {
            // Valid booking
            service.allocateRoom(new Reservation("Alice", "Single"));

            // Invalid room type
            service.allocateRoom(new Reservation("Bob", "Luxury"));

            // Force invalid state (no rooms left)
            service.allocateRoom(new Reservation("Charlie", "Suite"));

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/**
 * CUSTOM EXCEPTION
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
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

    public void allocateRoom(Reservation reservation) throws InvalidBookingException {

        String type = reservation.getRoomType();

        // Validate room type
        if (!inventory.isValidRoomType(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }

        int available = inventory.getAvailability(type);

        // Validate availability
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + type);
        }

        // Allocate room
        inventory.updateAvailability(type, available - 1);

        System.out.println("Booking Confirmed for " +
                reservation.getGuestName() + " (" + type + ")");
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
        return inventory.getOrDefault(type, 0);
    }

    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
    }

    public boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
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