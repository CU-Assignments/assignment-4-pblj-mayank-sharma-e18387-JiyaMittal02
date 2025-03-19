import java.util.*;

public class CardCollection {
    private static Map<String, List<String>> cardCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    // Add a card to the collection
    private static void addCard() {
        System.out.print("Enter Card Symbol (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Name (e.g., Ace, King, Queen, 10): ");
        String card = scanner.nextLine();

        cardCollection.putIfAbsent(symbol, new ArrayList<>());
        cardCollection.get(symbol).add(card);

        System.out.println("Card added successfully!\n");
    }

    // Search for cards by symbol
    private static void searchCards() {
        System.out.print("Enter Symbol to search (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine();

        if (cardCollection.containsKey(symbol)) {
            System.out.println("Cards in " + symbol + ": " + cardCollection.get(symbol));
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
        System.out.println();
    }

    // Display all cards
    private static void displayAllCards() {
        if (cardCollection.isEmpty()) {
            System.out.println("No cards in the collection.\n");
            return;
        }
        System.out.println("\nAll Cards in Collection:");
        for (Map.Entry<String, List<String>> entry : cardCollection.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
    }

    // Main Menu
    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Card Collection System ===");
            System.out.println("1. Add a Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCard();
                case 2 -> searchCards();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.\n");
            }
        }
    }
}
