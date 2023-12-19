import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Event {
    private String eventName;
    private Date eventDate;

    public Event(String eventName, Date eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }
}

class EventManager {
    private ArrayList<Event[]> eventsByMonth;

    public EventManager() {
        this.eventsByMonth = new ArrayList<>();
    }

    public void addEvent(String eventName, String dateStr) {
        try {
            Date eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            int month = eventDate.getMonth();

            if (isValidDate(eventDate) && !isEventClash(month, eventDate)) {
                Event[] eventsOfMonth = getOrCreateEventsOfMonth(month);
                eventsOfMonth[eventDate.getDate() - 1] = new Event(eventName, eventDate);
                System.out.println("Event added successfully.");
            } else {
                System.out.println("Invalid date or event clash. Please choose another date/time.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private boolean isValidDate(Date date) {
        return date.getHours() >= 8 && date.getHours() <= 17;
    }

    private boolean isEventClash(int month, Date newEventDate) {
        Event[] eventsOfMonth = getOrCreateEventsOfMonth(month);
        for (Event event : eventsOfMonth) {
            if (event != null && isClash(event.getEventDate(), newEventDate)) {
                return true;
            }
        }
        return false;
    }

    private boolean isClash(Date existingEventDate, Date newEventDate) {
        return existingEventDate.getDate() == newEventDate.getDate() &&
               existingEventDate.getMonth() == newEventDate.getMonth() &&
               existingEventDate.getYear() == newEventDate.getYear();
    }

    private Event[] getOrCreateEventsOfMonth(int month) {
        while (eventsByMonth.size() <= month) {
            eventsByMonth.add(new Event[31]); // Assuming maximum days in a month is 31
        }
        return eventsByMonth.get(month);
    }
}
