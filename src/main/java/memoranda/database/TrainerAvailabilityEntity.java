package main.java.memoranda.database;

import java.time.LocalDateTime;

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
