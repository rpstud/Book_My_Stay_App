import java.util.*;

// --- REUSE INVENTORY FROM PREVIOUS UC ---
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void initialize() {
        inventory.put("Single", 5);
    }

    public void increment(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public int getCount(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// --- NEW CLASS FOR UC10: Cancellation Service ---
class CancellationService {
    // Stack for LIFO rollback tracking
    private Stack<String> releasedRoomIds = new Stack<>();
    // Maps Reservation ID -> Room Type for restoration
    private Map<String, String> reservationRoomTypeMap = new HashMap<>();

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (reservationRoomTypeMap.containsKey(reservationId)) {
            String roomType = reservationRoomTypeMap.get(reservationId);

            // 1. Restore Inventory
            inventory.increment(roomType);

            // 2. Add to Rollback Stack
            releasedRoomIds.push(reservationId);

            // 3. Remove from active map
            reservationRoomTypeMap.remove(reservationId);

            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType+ "\n");
        } else {
            System.out.println("Cancellation failed: Reservation ID not found.");
        }
    }

    public void showRollbackHistory() {
        System.out.println("Rollback History (Most Recent First):");
        // Stack naturally prints in LIFO order if we pop,
        // but for display we can iterate or peek.
        if (releasedRoomIds.isEmpty()) {
            System.out.println("No cancellations recorded.");
        } else {
            // Making a copy to show history without destroying the stack
            Stack<String> temp = (Stack<String>) releasedRoomIds.clone();
            while (!temp.isEmpty()) {
                System.out.println("Released Reservation ID: " + temp.pop()+ "\n");
            }
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");

        // 1. Setup
        RoomInventory inventory = new RoomInventory();
        inventory.initialize();
        CancellationService cancellationService = new CancellationService();

        // 2. Simulate a confirmed booking (Single-1)
        String resId = "Single-1";
        cancellationService.registerBooking(resId, "Single");

        // 3. Perform Cancellation
        cancellationService.cancelBooking(resId, inventory);

        // 4. Show Results
        cancellationService.showRollbackHistory();
        System.out.println("Updated Single Room Availability: " + inventory.getCount("Single"));
    }
}