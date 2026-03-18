import java.util.*;

// --- DOMAIN CLASSES ---
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// --- INVENTORY SERVICE (Updated for UC6) ---
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();
    // Tracks assigned IDs per room type to prevent double-booking
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();

    public void initializeRooms(int single, int dbl, int suite) {
        inventory.put("Single", single);
        inventory.put("Double", dbl);
        inventory.put("Suite", suite);

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    public boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    public String allocateRoom(String type) {
        int currentCount = inventory.get(type);
        inventory.put(type, currentCount - 1); // Decrement inventory

        // Generate a unique ID (e.g., Single-1, Single-2)
        Set<String> assignedSet = allocatedRooms.get(type);
        String roomId = type + "-" + (assignedSet.size() + 1);
        assignedSet.add(roomId); // Set ensures uniqueness

        return roomId;
    }
}

// --- NEW CLASS FOR UC6: Room Allocation Service ---
class RoomAllocationService {
    public void processAllocations(Queue<Reservation> queue, RoomInventory inventory) {
        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {
            Reservation request = queue.poll(); // Dequeue in FIFO order
            String type = request.getRoomType();

            if (inventory.isAvailable(type)) {
                String roomId = inventory.allocateRoom(type);
                System.out.println("Booking confirmed for Guest: " + request.getGuestName() + ", Room ID: " + roomId);
            } else {
                System.out.println("Booking failed for Guest: " + request.getGuestName() + ". No " + type + " rooms available.");
            }
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        // 1. Setup Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.initializeRooms(5, 3, 2);

        // 2. Setup Request Queue (from UC5)
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Subha", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Suite"));

        // 3. Process Allocations (UC6 Logic)
        RoomAllocationService allocationService = new RoomAllocationService();
        allocationService.processAllocations(bookingQueue, inventory);
    }
}