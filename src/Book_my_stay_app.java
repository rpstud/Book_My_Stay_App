import java.util.*;

// --- REUSE DOMAIN CLASSES ---
class Reservation {
    private String guestName;
    private String roomType;
    public Reservation(String name, String type) { this.guestName = name; this.roomType = type; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();
    public void init() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }
    public boolean isAvailable(String type) { return inventory.getOrDefault(type, 0) > 0; }
    public void decrement(String type) { inventory.put(type, inventory.get(type) - 1); }
    public void showInventory() {
        System.out.println("\nRemaining Inventory:");
        inventory.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

class RoomAllocationService {
    private int counter = 1;
    public void allocateRoom(Reservation res, RoomInventory inv) {
        if (inv.isAvailable(res.getRoomType())) {
            inv.decrement(res.getRoomType());
            System.out.println("Booking confirmed for Guest: " + res.getGuestName() +
                    ", Room ID: " + res.getRoomType() + "-" + (counter++));
        }
    }
}

// --- NEW CLASS FOR UC11: Concurrent Booking Processor ---
class ConcurrentBookingProcessor implements Runnable {
    private final Queue<Reservation> bookingQueue;
    private final RoomInventory inventory;
    private final RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(Queue<Reservation> q, RoomInventory inv, RoomAllocationService service) {
        this.bookingQueue = q;
        this.inventory = inv;
        this.allocationService = service;
    }

    @Override
    public void run() {
        while (true) {
            Reservation reservation = null;
            // Critical Section 1: Accessing the shared Queue
            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) break;
                reservation = bookingQueue.poll();
            }

            if (reservation != null) {
                // Critical Section 2: Updating shared Inventory and Allocation
                synchronized (inventory) {
                    allocationService.allocateRoom(reservation, inventory);
                }
            }

            try { Thread.sleep(100); } catch (InterruptedException e) { break; }
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation");

        // 1. Setup Shared Resources
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Abhi", "Single"));
        bookingQueue.add(new Reservation("Vanmathi", "Double"));
        bookingQueue.add(new Reservation("Kural", "Suite"));
        bookingQueue.add(new Reservation("Subha", "Single"));

        RoomInventory inventory = new RoomInventory();
        inventory.init();
        RoomAllocationService allocationService = new RoomAllocationService();

        // 2. Create Threads
        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));

        // 3. Start Processing
        t1.start();
        t2.start();

        try {
            t1.join(); // Wait for threads to finish
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // 4. Final State
        inventory.showInventory();
    }
}