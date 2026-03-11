import java.util.Map;

public class Book_my_stay_app {

    public static void main(String[] args) {
        System.out.println("Hotel Room Inventory Status\n");

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Initialize Room Objects (reusing classes from UC2)
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display Status for Single Room
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Single Room") + "\n");

        // Display Status for Double Room
        System.out.println("Double Room:");
        doubleRm.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Double Room") + "\n");

        // Display Status for Suite Room
        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Suite Room"));
    }
}