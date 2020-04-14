package main.java.memoranda.database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
TrainerAvailabilityEntity is whats returned and used for all SQL queries related to the
TrainerAvailability Table. This shows what the trainers availability is to be scheduled for gym
classes.
 */
public class TrainerAvailabilityEntity {
    private LocalDateTime _startDateTime;
    private LocalDateTime _endDateTime;


    public TrainerAvailabilityEntity(LocalDateTime _startDateTime, LocalDateTime _endDateTime) {
        this._startDateTime = _startDateTime;
        this._endDateTime = _endDateTime;
    }

    public TrainerAvailabilityEntity(int year, int month, int day, int startHR, int startMIN, int endHR, int endMIN){
        LocalDate localDate = LocalDate.of(year,month,day);
        LocalTime lst = LocalTime.of(startHR,startMIN);
        LocalTime localEndTime = LocalTime.of(endHR,endMIN);
        this._startDateTime = LocalDateTime.of(localDate,lst);
        this._startDateTime = LocalDateTime.of(localDate, localEndTime);
    }

    public LocalDateTime getStartDateTime() {
        return _startDateTime;
    }

    public void setStartDateTime(LocalDateTime _startDateTime) {
        this._startDateTime = _startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return _endDateTime;
    }

    public void setEndDateTime(LocalDateTime _endDateTime) {
        this._endDateTime = _endDateTime;
    }
}
