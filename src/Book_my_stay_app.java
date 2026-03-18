import java.util.*;

// --- CUSTOM EXCEPTION ---
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// --- DOMAIN CLASSES (from previous UC) ---
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void initializeRooms() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public boolean isValidRoomType(String type) {
        return inventory.containsKey(type);
    }
}

// --- NEW CLASS FOR UC9: Reservation Validator ---
class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // Rule 1: Name cannot be empty
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Rule 2: Room type must exist in our system (Case Sensitive)
        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected. Note: It is case sensitive");
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Booking Validation");
        Scanner scanner = new Scanner(System.in);

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        inventory.initializeRooms();
        ReservationValidator validator = new ReservationValidator();

        try {
            // Accept User Input
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String type = scanner.nextLine();

            // Validate Input (Fail-Fast)
            validator.validate(name, type, inventory);

            // If we reach here, validation passed
            System.out.println("Validation successful for " + name);

        } catch (InvalidBookingException e) {
            // Handle domain-specific validation errors gracefully
            System.out.println("Booking failed: " + e.getMessage());
        } catch (Exception e) {
            // Catch-all for unexpected errors
            System.out.println("An unexpected error occurred.");
        } finally {
            scanner.close();
            System.out.println("System remains stable.");
        }
    }
}