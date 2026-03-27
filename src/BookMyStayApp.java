import java.util.HashMap;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates room search with read-only access to inventory.
 *
 * @author Developer
 * @version 4.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Search Results\n");

        // Inventory
        RoomInventory inventory = new RoomInventory();

        // Room objects
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Search (READ ONLY)
        searchRooms("Single", single, inventory);
        searchRooms("Double", dbl, inventory);
        searchRooms("Suite", suite, inventory);
    }

    // SEARCH FUNCTION (IMPORTANT)
    public static void searchRooms(String type, Room room, RoomInventory inventory) {

        int available = inventory.getAvailability(type);

        // Only show available rooms
        if (available > 0) {
            System.out.println(type + " Room:");
            room.displayRoomDetails();
            System.out.println("Available: " + available + "\n");
        }
    }
}

/**
 * CLASS - RoomInventory
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 0); // Example: unavailable
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/**
 * ABSTRACT CLASS - Room
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

/**
 * CLASS - SingleRoom
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * CLASS - DoubleRoom
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * CLASS - SuiteRoom
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}