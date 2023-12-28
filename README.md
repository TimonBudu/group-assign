import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

class Event implements Serializable {
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStart(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEnd(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return startTime;
    }

    public LocalTime getEnd() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Event{name='" + name + "', date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
}
