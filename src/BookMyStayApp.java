import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Description:
 * Demonstrates add-on service selection for reservations.
 *
 * @author Developer
 * @version 7.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Services Selection\n");

        // Sample reservation IDs (from UC6)
        String res1 = "R101";
        String res2 = "R102";

        // Service Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(res1, new AddOnService("Breakfast", 500));
        manager.addService(res1, new AddOnService("Airport Pickup", 1200));

        manager.addService(res2, new AddOnService("Extra Bed", 800));

        // Display services
        manager.displayServices(res1);
        manager.displayServices(res2);
    }
}

/**
 * CLASS - AddOnService
 */
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * CLASS - AddOnServiceManager
 */
class AddOnServiceManager {

    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Display services + cost
    public void displayServices(String reservationId) {

        System.out.println("Reservation ID: " + reservationId);

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services\n");
            return;
        }

        double total = 0;

        for (AddOnService s : services) {
            System.out.println("Service: " + s.getServiceName() +
                    " | Cost: " + s.getCost());
            total += s.getCost();
        }

        System.out.println("Total Add-On Cost: " + total + "\n");
    }
}