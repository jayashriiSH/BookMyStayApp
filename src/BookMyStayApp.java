import java.util.HashMap;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates centralized room inventory management
 * using HashMap.
 *
 * @author Developer
 * @version 3.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Centralized Room Inventory\n");

        // Create inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating inventory...\n");
        inventory.updateAvailability("Single", 3);

        inventory.displayInventory();
    }
}

/**
 * CLASS - RoomInventory
 * Manages room availability using HashMap
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display inventory
    public void displayInventory() {
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Rooms Available: " + inventory.get(roomType));
        }
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