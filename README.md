import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventSystem eventSystem = new EventSystem("events.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter event name (or 'quit' to stop):");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter event date (YYYY MM DD):");
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();
            LocalDate date = LocalDate.of(year, month, day);

            System.out.println("Enter event start time (HH MM):");
            int startHour = scanner.nextInt();
            int startMinute = scanner.nextInt();
            LocalTime startTime = LocalTime.of(startHour, startMinute);

            System.out.println("Enter event end time (HH MM):");
            int endHour = scanner.nextInt();
            int endMinute = scanner.nextInt();
            LocalTime endTime = LocalTime.of(endHour, endMinute);

            Event event = new Event(name, date, startTime, endTime);

            if (eventSystem.addEvent(event)) {
                System.out.println("Event added successfully.");
            } else {
                System.out.println("Event could not be added due to a time conflict.");
            }

            scanner.nextLine(); // consume the newline character
        }

        for (Event e : eventSystem.getEvents()) {
            System.out.println(e);
        }

        System.out.println("Enter a date to search for events (YYYY MM DD) or 'quit' to exit:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            String[] dateParts = input.split("\\s+");
            if (dateParts.length == 3) {
                try {
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[2]);

                    LocalDate searchDate = LocalDate.of(year, month, day);

                    List<Event> eventsOnDate = eventSystem.getEventsOnDate(searchDate);

                    if (!eventsOnDate.isEmpty()) {
                        System.out.println("Events on " + searchDate + ":");
                        for (Event event : eventsOnDate) {
                            System.out.println(event);
                        }
                    } else {
                        System.out.println("No events on " + searchDate);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid date format. Please enter date in the format 'YYYY MM DD'.");
                }
            } else {
                System.out.println("Invalid input. Please enter a date or 'quit' to exit.");
            }
        }

        scanner.close();
    }
}
