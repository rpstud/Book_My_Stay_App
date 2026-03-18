import java.util.*;

// --- DOMAIN CLASSES ---
class Reservation {
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void setRoomId(String roomId) { this.roomId = roomId; }
    public String getRoomId() { return roomId; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

// --- NEW CLASS FOR UC7: Add-On Service ---
class Service {
    private String serviceName;
    private double price;

    public Service(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public double getPrice() { return price; }
}

// --- NEW CLASS FOR UC7: Add-On Service Manager ---
class AddOnServiceManager {
    // Maps Room ID to a List of Services (One-to-Many Relationship)
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    public void addService(String roomId, Service service) {
        // If list doesn't exist for this ID, create it; then add the service
        reservationServices.computeIfAbsent(roomId, k -> new ArrayList<>()).add(service);
    }

    public double calculateTotalServiceCost(String roomId) {
        List<Service> services = reservationServices.getOrDefault(roomId, new ArrayList<>());
        double total = 0;
        for (Service s : services) {
            total += s.getPrice();
        }
        return total;
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Add-On Service Selection");

        // 1. Simulate a confirmed reservation from UC6
        Reservation res = new Reservation("Abhi", "Single");
        res.setRoomId("Single-1"); // Assigned ID

        // 2. Setup Add-On Services
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Guest selects multiple services
        serviceManager.addService(res.getRoomId(), new Service("Breakfast", 500.0));
        serviceManager.addService(res.getRoomId(), new Service("Spa", 1000.0));

        // 3. Display Results
        System.out.println("Reservation ID: " + res.getRoomId());
        double totalCost = serviceManager.calculateTotalServiceCost(res.getRoomId());
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}