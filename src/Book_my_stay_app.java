public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Hotel Room Initialization\n");

        // Initialize the objects
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables (as requested in Use Case 2)
        int singleAvail = 5;
        int doubleAvail = 3;
        int suiteAvail = 2;

        // Display results
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available: " + singleAvail + "\n");

        System.out.println("Double Room:");
        doubleRm.displayRoomDetails();
        System.out.println("Available: " + doubleAvail + "\n");

        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvail);
    }
}