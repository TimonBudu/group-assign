import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Event {
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private ArrayList<String> participants;

    public Event(String name, String date, String startTime, String endTime) {
        this.name = name;
        this.date = checkDate(date);
        this.startTime = checkTime(startTime);
        this.endTime = checkTime(endTime);
        this.participants = new ArrayList<>();
    }

    private LocalDate checkDate(String dateStr) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr, dateFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private LocalTime checkTime(String timeStr) {
        try {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
            return LocalTime.parse(timeStr, timeFormat);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please use HH:MM.");
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", participants=" + participants +
                '}';
    }
}
