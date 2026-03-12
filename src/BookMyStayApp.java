import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management
 * in the Book My Stay Hotel Booking System.
 *
 * Version: 3.1
 * Author: Jayashri
 */

// Abstract Room class (same as Use Case 2)
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

// Concrete room classes
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

// Centralized Room Inventory
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add room type with initial count
    public void registerRoom(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Retrieve availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability after booking/cancellation
    public void updateAvailability(String roomType, int delta) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + delta);
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

// Application entry point
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v3.1 ===\n");

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details
        single.displayRoomDetails();
        doubleRoom.displayRoomDetails();
        suite.displayRoomDetails();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom(single.getRoomType(), 5);
        inventory.registerRoom(doubleRoom.getRoomType(), 3);
        inventory.registerRoom(suite.getRoomType(), 2);

        // Display inventory
        inventory.displayInventory();

        // Example of updating inventory
        System.out.println("\nBooking 1 Double Room...");
        inventory.updateAvailability(doubleRoom.getRoomType(), -1);

        inventory.displayInventory();

        System.out.println("\nApplication execution completed successfully.");
    }
}