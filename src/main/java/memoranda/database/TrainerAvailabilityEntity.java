package main.java.memoranda.database;

import java.time.LocalDateTime;

/*
TrainerAvailabilityEntity is whats returned and used for all SQL queries related to the TrainerAvailability Table.
This shows what the trainers availability is to be scheduled for gym classes.
 */
public class TrainerAvailabilityEntity {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TrainerAvailabilityEntity(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
