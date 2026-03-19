/*
package ru.vlad.NauJava;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.vlad.NauJava.service.BookingService;
import java.util.Scanner;

@Component
public class ConsoleInterface implements CommandLineRunner {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private BookingService bookingService;

    @PostConstruct
    public void printAppInfo() {
        System.out.println("--- " + appName + " v" + appVersion + " started ---");
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Commands: add [id] [name] [seats], book [id], view [id], exit");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];

            if (command.equals("exit")) break;

            try {
                switch (command) {
                    case "add":
                        bookingService.addSession(Long.parseLong(parts[1]), parts[2], Integer.parseInt(parts[3]));
                        System.out.println("Session added.");
                        break;
                    case "book":
                        boolean success = bookingService.bookTicket(Long.parseLong(parts[1]));
                        System.out.println(success ? "Booked" : "No seats or not found.");
                        break;
                    case "view":
                        var s = bookingService.getSession(Long.parseLong(parts[1]));
                        System.out.println(s != null ? s.getMovieTitle() + ": " + s.getAvailableSeats() + " seats left" : "Not found");
                        break;
                    default:
                        System.out.println("Unknown command");
                }
            } catch (Exception e) {
                System.out.println("Error: Check your arguments.");
            }
        }
    }
}
*/