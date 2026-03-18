import java.util.*;

// --- DOMAIN CLASSES ---
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
}

class SingleRoom extends Room { public SingleRoom() { super("Single Room", 1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double Room", 2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite Room", 3, 750, 5000.0); } }

// --- NEW CLASS FOR UC5: Reservation ---
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Processing booking for Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// --- NEW CLASS FOR UC5: Booking Request Queue ---
class BookingRequestQueue {
    // Using LinkedList as the implementation for the Queue (FIFO)
    private Queue<Reservation> requestQueue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Added to Queue: " + reservation);
    }

    public void displayQueue() {
        System.out.println("\nCurrent Booking Request Queue (FIFO Order):");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            for (Reservation res : requestQueue) {
                System.out.println(res);
            }
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Booking Request Queue - Use Case 5\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate guests submitting requests in order
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Display the queue to verify FIFO order
        bookingQueue.displayQueue();

        System.out.println("\nRequests are now waiting for the allocation system.");
    }
}