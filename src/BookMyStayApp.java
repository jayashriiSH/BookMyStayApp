import java.io.*;
import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates data persistence and recovery using serialization.
 *
 * @author Developer
 * @version 12.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Persistence & Recovery System\n");

        PersistenceService service = new PersistenceService();

        // Try loading previous state
        SystemState state = service.loadState();

        if (state == null) {
            System.out.println("No previous data found. Initializing new system...\n");

            state = new SystemState();

            state.inventory.put("Single", 2);
            state.inventory.put("Double", 1);

            state.bookings.add("R101 - Single");
            state.bookings.add("R102 - Double");
        } else {
            System.out.println("System recovered from file!\n");
        }

        // Display state
        System.out.println("Inventory:");
        for (String key : state.inventory.keySet()) {
            System.out.println(key + ": " + state.inventory.get(key));
        }

        System.out.println("\nBookings:");
        for (String b : state.bookings) {
            System.out.println(b);
        }

        // Save state before exit
        service.saveState(state);

        System.out.println("\nState saved successfully.");
    }
}

/**
 * CLASS - SystemState
 * Stores inventory + booking history
 */
class SystemState implements Serializable {

    public Map<String, Integer> inventory;
    public List<String> bookings;

    public SystemState() {
        inventory = new HashMap<>();
        bookings = new ArrayList<>();
    }
}

/**
 * CLASS - PersistenceService
 */
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // SAVE
    public void saveState(SystemState state) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // LOAD
    public SystemState loadState() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {
            return null; // first run
        } catch (Exception e) {
            System.out.println("Error loading state: " + e.getMessage());
            return null;
        }
    }
}