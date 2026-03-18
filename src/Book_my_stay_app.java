import java.util.*;

// --- DOMAIN CLASSES ---
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// --- NEW CLASS FOR UC8: Booking History ---
class BookingHistory {
    private List<Reservation> confirmedReservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

// --- NEW CLASS FOR UC8: Booking Report Service ---
class BookingReportService {
    public void generateReport(BookingHistory history) {
        System.out.println("Booking History Report");
        List<Reservation> historyList = history.getConfirmedReservations();

        if (historyList.isEmpty()) {
            System.out.println("No history available.");
        } else {
            for (Reservation res : historyList) {
                System.out.println(res);
            }
        }
    }
}

// --- MAIN APPLICATION ENTRY POINT ---
public class Book_my_stay_app {
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting\n");

        // 1. Setup History and Report Services
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // 2. Simulate confirming and adding reservations to history
        // (In a full app, these would come from the Allocation Service)
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // 3. Generate the report
        reportService.generateReport(history);
    }
}