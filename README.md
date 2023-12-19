import java.time.LocalDate;     
import java.time.LocalTime;        
import java.time.format.DateTimeFormatter;      
import java.time.format.DateTimeParseException;    
import java.util.ArrayList;     

public class Event {
    private String name;        //Muhammad bin Mohd Idham 2220471
    private LocalDate date;     //Muhammad bin Mohd Idham 2220471
    private LocalTime startTime;        //Muhammad bin Mohd Idham 2220471
    private LocalTime endTime;      //Muhammad bin Mohd Idham 2220471
    private ArrayList<String> participants;     //Muhammad bin Mohd Idham 2220471

    public Event(String name, String date, String startTime, String endTime) {
        this.name = name;       //Muhammad bin Mohd Idham 2220471
        this.date = checkDate(date);        //Muhammad bin Mohd Idham 2220471
        this.startTime = checkTime(startTime);      //Muhammad bin Mohd Idham 2220471
        this.endTime = checkTime(endTime);      //Muhammad bin Mohd Idham 2220471
        this.participants = new ArrayList<>();      //Muhammad bin Mohd Idham 2220471
    }

    private LocalDate checkDate(String dateStr) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");       //Muhammad bin Mohd Idham 2220471
            return LocalDate.parse(dateStr, dateFormat);        //Muhammad bin Mohd Idham 2220471
        } catch (DateTimeParseException e) {        //Muhammad bin Mohd Idham 2220471
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");      //Muhammad bin Mohd Idham 2220471
        }
    }

    private LocalTime checkTime(String timeStr) {
        try {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");        //Muhammad bin Mohd Idham 2220471
            return LocalTime.parse(timeStr, timeFormat);        //Muhammad bin Mohd Idham 2220471
        } catch (DateTimeParseException e) {        //Muhammad bin Mohd Idham 2220471
            throw new IllegalArgumentException("Invalid time format. Please use HH:MM.");       //Muhammad bin Mohd Idham 2220471
        }
    }

    @Override
    public String toString() {
        return "Event{" +       //Muhammad bin Mohd Idham 2220471
                "name='" + name + '\'' +        //Muhammad bin Mohd Idham 2220471
                ", date=" + date +      //Muhammad bin Mohd Idham 2220471
                ", startTime=" + startTime +        //Muhammad bin Mohd Idham 2220471
                ", endTime=" + endTime +        //Muhammad bin Mohd Idham 2220471
                ", participants=" + participants +      //Muhammad bin Mohd Idham 2220471
                '}';
    }
}
