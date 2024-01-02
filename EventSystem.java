import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventSystem {
    private List<Event> events;
    private String filename;

    public EventSystem(String filename) {
        this.filename = filename;
        this.events = new ArrayList<>();
        loadEvents();
    }

    public boolean addEvent(Event event) {
        for (Event e : events) {
            if (e.getDate().equals(event.getDate()) &&
                    e.getStart().isBefore(event.getEnd()) &&
                    e.getEnd().isAfter(event.getStart())) {
                return false;
            }
        }
        events.add(event);
        saveEvents(); // Save events after adding a new one
        return true;
    }

    public List<Event> getEvents() {
        return events;
    }

    private void loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            events = (List<Event>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading events from file: " + e.getMessage());
        }
    }

    private void saveEvents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(events);
        } catch (IOException e) {
            System.out.println("Error saving events to file: " + e.getMessage());
        }
    }

    public List<Event> getEventsOnDate(LocalDate date) {
        List<Event> eventsOnDate = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                eventsOnDate.add(event);
            }
        }
        return eventsOnDate;
    }
}