import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventSystem eventSystem = new EventSystem("events.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter event name (or 'quit' to stop):");
                String name = scanner.nextLine();

                if (name.equalsIgnoreCase("quit")) {
                    break;
                }

                LocalDate date;
                LocalTime startTime;
                LocalTime endTime;

                while (true) {
                    try {
                        System.out.println("Enter event date (YYYY MM DD):");
                        int year = scanner.nextInt();
                        int month = scanner.nextInt();
                        int day = scanner.nextInt();
                        date = LocalDate.of(year, month, day);
                        break; // exit the loop if date input is successful
                    } catch (Exception e) {
                        System.out.println("Invalid date input. Please enter a valid date.");
                        scanner.nextLine(); // consume the newline character
                    }
                }

                while (true) {
                    try {
                        System.out.println("Enter event start time (HH MM):");
                        int startHour = scanner.nextInt();
                        int startMinute = scanner.nextInt();
                        startTime = LocalTime.of(startHour, startMinute);
                        break; // exit the loop if start time input is successful
                    } catch (Exception e) {
                        System.out.println("Invalid start time input. Please enter a valid time.");
                        scanner.nextLine(); // consume the newline character
                    }
                }

                while (true) {
                    try {
                        System.out.println("Enter event end time (HH MM):");
                        int endHour = scanner.nextInt();
                        int endMinute = scanner.nextInt();

                        // Validate that end time is greater than start time
                        if (endHour < startTime.getHour() || (endHour == startTime.getHour() && endMinute <= startTime.getMinute())) {
                            System.out.println("Invalid end time. Please enter a time greater than the start time.");
                            scanner.nextLine(); // consume the newline character
                            continue; // restart the loop for end time input
                        }

                        endTime = LocalTime.of(endHour, endMinute);
                        break; // exit the loop if end time input is successful
                    } catch (Exception e) {
                        System.out.println("Invalid end time input. Please enter a valid time.");
                        scanner.nextLine(); // consume the newline character
                    }
                }

                Event event = new Event(name, date, startTime, endTime);

                if (eventSystem.addEvent(event)) {
                    System.out.println("Event added successfully.");
                } else {
                    System.out.println("Event could not be added due to a time conflict.");
                }

                scanner.nextLine(); // consume the newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid data.");
                scanner.nextLine(); // consume the newline character
            }
        }

        for (Event e : eventSystem.getEvents()) {
            System.out.println(e);
        }

        System.out.println("Enter a date to search for events (YYYY MM DD) or 'quit' to exit:");
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                String[] dateParts = input.split("\\s+");
                if (dateParts.length == 3) {
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
                } else {
                    System.out.println("Invalid input. Please enter a date or 'quit' to exit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid date format. Please enter date in the format 'YYYY MM DD'.");
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }

    }
}
