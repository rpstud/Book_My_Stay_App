import java.util.*;
import java.io.*;

// --- UPDATED INVENTORY FOR UC12 ---
class RoomInventory {
    private Map<String, Integer> inventory = new LinkedHashMap<>();

    public void initializeDefault() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void setRoomCount(String type, int count) {
        inventory.put(type, count);
    }

    public Map<String, Integer> getAllInventory() {
        return inventory;
    }

    public void showInventory() {
        System.out.println("\nCurrent Inventory:");
        inventory.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

// --- NEW CLASS FOR UC12: File Persistence Service ---
class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getAllInventory().entrySet()) {
                writer.write(entry.getKey() + "-" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            inventory.initializeDefault();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    inventory.setRoomCount(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory. Reverting to defaults.");
            inventory.initializeDefault();
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();
        String storageFile = "inventory_state.txt";

        // 1. Try to recover state
        persistenceService.loadInventory(inventory, storageFile);

        // 2. Show state (either recovered or default)
        inventory.showInventory();

        // 3. Save state for next time
        persistenceService.saveInventory(inventory, storageFile);
    }
}