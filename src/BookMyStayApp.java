/**
 * UseCase2RoomInitialization
 *
 * This class demonstrates basic room types and static availability
 * in the Book My Stay Hotel Booking System.
 * It introduces abstract classes, inheritance, polymorphism, and encapsulation.
 *
 * Version: 2.1
 * Author: Jayashri
 */
abstract class Room {
    // Common attributes for all rooms
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    // Getter methods (encapsulation)
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Abstract method to display room details
    public abstract void displayRoomDetails();
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 50.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 90.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 150.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

// Application entry point
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App v2.1 ===\n");

        // Initialize availability (static representation)
        int singleRoomsAvailable = 5;
        int doubleRoomsAvailable = 3;
        int suiteRoomsAvailable = 2;

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Display room details and availability
        System.out.println("Room Types and Availability:\n");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomsAvailable + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomsAvailable + "\n");

        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomsAvailable + "\n");

        System.out.println("Application execution completed successfully.");
    }
}