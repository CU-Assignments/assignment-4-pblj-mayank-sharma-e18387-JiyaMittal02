import java.util.*;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    // Synchronized method to prevent double booking
    public synchronized boolean bookTicket(String customerName, int seatsRequested) {
        if (seatsRequested > availableSeats) {
            System.out.println(customerName + ": Booking failed! Not enough seats available.");
            return false;
        }
        System.out.println(customerName + ": Booking successful for " + seatsRequested + " seat(s).");
        availableSeats -= seatsRequested;
        return true;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

// Thread class for booking
class Customer extends Thread {
    private TicketBookingSystem system;
    private String customerName;
    private int seatsRequested;

    public Customer(TicketBookingSystem system, String customerName, int seatsRequested, int priority) {
        this.system = system;
        this.customerName = customerName;
        this.seatsRequested = seatsRequested;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        system.bookTicket(customerName, seatsRequested);
    }
}

// Main class
public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5); // 5 available seats

        // Creating VIP and Regular customers
        Customer vip1 = new Customer(system, "VIP-1", 2, Thread.MAX_PRIORITY);
        Customer vip2 = new Customer(system, "VIP-2", 1, Thread.MAX_PRIORITY);
        Customer regular1 = new Customer(system, "User-1", 1, Thread.NORM_PRIORITY);
        Customer regular2 = new Customer(system, "User-2", 2, Thread.NORM_PRIORITY);
        Customer regular3 = new Customer(system, "User-3", 1, Thread.MIN_PRIORITY);

        // Start booking process
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        regular3.start();
    }
}
