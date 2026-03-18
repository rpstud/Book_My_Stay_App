import java.util.Map;
import java.util.HashMap;

// --- DOMAIN CLASSES (From UC2/UC3) ---
abstract class Room {
    String type;
    int beds;
    int size;
    double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }
}

class SingleRoom extends Room { public SingleRoom() { super("Single Room", 1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double Room", 2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite Room", 3, 750, 5000.0); } }

// --- INVENTORY CLASS (From UC3) ---
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void initializeRooms(int single, int dbl, int suite) {
        inventory.put("Single", single);
        inventory.put("Double", dbl);
        inventory.put("Suite", suite);
    }

    public Map<String, Integer> getRoomAvailability() {
        return inventory;
    }
}

// --- NEW CLASS FOR UC4 ---
class RoomSearchService {
    /**
     * Performs read-only access to inventory and room data.
     */
    public void searchAvailableRooms(RoomInventory inventory, Room single, Room dbl, Room suite) {
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single") > 0) {
            single.displayDetails();
            System.out.println("Available: " + availability.get("Single") + "\n");
        }

        if (availability.get("Double") > 0) {
            dbl.displayDetails();
            System.out.println("Available: " + availability.get("Double") + "\n");
        }

        if (availability.get("Suite") > 0) {
            suite.displayDetails();
            System.out.println("Available: " + availability.get("Suite") + "\n");
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Room Search\n");

        // Setup state
        RoomInventory inventory = new RoomInventory();
        inventory.initializeRooms(5, 3, 2);

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Execute UC4 Logic
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, single, dbl, suite);
    }
}