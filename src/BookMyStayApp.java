import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates reservation confirmation and room allocation.
 *
 * @author Developer
 * @version 6.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation System\n");

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Booking Queue
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single"));
        queue.add(new Reservation("Bob", "Double"));
        queue.add(new Reservation("Charlie", "Single"));

        // Allocation Service
        BookingService service = new BookingService(inventory);

        // Process all requests
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            service.allocateRoom(r);
        }
    }
}

/**
 * CLASS - BookingService
 * Handles allocation + prevents double booking
 */
class BookingService {

    private RoomInventory inventory;

    // Track allocated rooms
    private Map<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation) {

        String type = reservation.getRoomType();
        int available = inventory.getAvailability(type);

        // Check availability
        if (available <= 0) {
            System.out.println("Booking Failed for " + reservation.getGuestName()
                    + " (No rooms available)");
            return;
        }

        // Generate unique room ID
        String roomId = generateRoomId(type);

        // Store in set (prevent duplicates)
        allocatedRooms.putIfAbsent(type, new HashSet<>());
        allocatedRooms.get(type).add(roomId);

        // Update inventory
        inventory.updateAvailability(type, available - 1);

        // Confirm booking
        System.out.println("Booking Confirmed:");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + type);
        System.out.println("Room ID: " + roomId + "\n");
    }

    private String generateRoomId(String type) {
        return type.substring(0, 1).toUpperCase() + UUID.randomUUID().toString().substring(0, 4);
    }
}

/**
 * CLASS - RoomInventory
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
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