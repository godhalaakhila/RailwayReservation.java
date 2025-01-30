import java.util.HashMap;
import java.util.Scanner;

public class RailwayReservationSystem {
    // Variables for trains and users
    static String[] trains = {"Train A", "Train B", "Train C", "Train D", "Train E"};
    static int[] seats = {100, 100, 100, 100, 100};
    static HashMap<String, String> users = new HashMap<>(); // Stores username and password
    static HashMap<String, String> bookings = new HashMap<>(); // Stores username and train name for booking

    // Scanner for input
    static Scanner scanner = new Scanner(System.in);

    // Function to register a user
    public static void registerUser() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists! Please try another.");
            return;
        }
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        users.put(username, password);
        System.out.println("Registration successful!");
    }

    // Function to login a user
    public static boolean loginUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    // Forgot password feature
    public static void forgotPassword() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Your password is: " + users.get(username));
        } else {
            System.out.println("Username not found!");
        }
    }

    // Function to book a ticket
    public static void bookTicket(String username) {
        if (bookings.containsKey(username)) {
            System.out.println("You have already booked a ticket for " + bookings.get(username) + ".");
            return;
        }

        System.out.println("\nAvailable Trains:");
        for (int i = 0; i < trains.length; i++) {
            System.out.println((i + 1) + ". " + trains[i] + " - Seats Available: " + seats[i]);
        }

        System.out.print("Select a train (1-5): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice < 1 || choice > 5) {
            System.out.println("Invalid train selection!");
            return;
        }

        if (seats[choice - 1] > 0) {
            seats[choice - 1]--;
            bookings.put(username, trains[choice - 1]);
            System.out.println("Booking confirmed! Train: " + trains[choice - 1]);
        } else {
            System.out.println("Seats are not available in " + trains[choice - 1] + ".");
            System.out.println("Remaining trains with available seats:");
            boolean alternativeFound = false;
            for (int i = 0; i < trains.length; i++) {
                if (i != (choice - 1) && seats[i] > 0) {
                    System.out.println(trains[i] + " - Seats Available: " + seats[i]);
                    alternativeFound = true;
                }
            }
            if (!alternativeFound) {
                System.out.println("No seats available in any train.");
            }
        }
    }

    // Function to cancel a ticket
    public static void cancelTicket(String username) {
        if (bookings.containsKey(username)) {
            String trainName = bookings.get(username);
            for (int i = 0; i < trains.length; i++) {
                if (trains[i].equals(trainName)) {
                    seats[i]++;
                    bookings.remove(username);
                    System.out.println("Your ticket for " + trainName + " has been canceled.");
                    return;
                }
            }
        } else {
            System.out.println("No booking found for your username.");
        }
    }

    // Main menu
    public static void menu() {
        while (true) {
            System.out.println("\n--- Railway Reservation System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    if (loginUser()) {
                        userMenu();
                    }
                    break;
                case 3:
                    forgotPassword();
                    break;
                case 4:
                    System.out.println("Thank you for using the Railway Reservation System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // User menu after login
    public static void userMenu() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Logout");
            System.out.print("Enter your choice (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookTicket(username);
                    break;
                case 2:
                    cancelTicket(username);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Main function
    public static void main(String[] args) {
        menu();
    }
} 
    

