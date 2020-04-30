package main.java.memoranda.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/*
TrainerAvailabilityEntity is whats returned and used for all SQL queries related to the
TrainerAvailability Table. This shows what the trainers availability is to be scheduled for gym
classes.
 */
public class TrainerAvailabilityEntity {
    private String _email;

    private LocalDateTime _startDateTime;
    private LocalDateTime _endDateTime;

    public TrainerAvailabilityEntity(String email,LocalDate localDate,
                                     double startTime, double entTime) {

        this._email = email;

        LocalTime localStartTime = LocalTime.of((int)startTime, 0);
        LocalTime localEndTime = LocalTime.of((int)entTime, 0);

        this._startDateTime = LocalDateTime.of(localDate, localStartTime);
        this._endDateTime = LocalDateTime.of(localDate, localEndTime);
    }

    public TrainerAvailabilityEntity(LocalDateTime _startDateTime, LocalDateTime _endDateTime) {
        this._startDateTime = _startDateTime;
        this._endDateTime = _endDateTime;
    }

    public TrainerAvailabilityEntity(int year, int month, int day, int startHR, int startMIN,
                                     int endHR, int endMIN) {
        LocalDate localDate = LocalDate.of(year, month, day);
        LocalTime lst = LocalTime.of(startHR, startMIN);
        LocalTime localEndTime = LocalTime.of(endHR, endMIN);
        this._startDateTime = LocalDateTime.of(localDate, lst);
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

    /**
     * Returns the LocalDate from LocalDateTime
     *
     * @return the value
     */
    public LocalDate getLocalDate() {


/*        LocalDate d = LocalDate.of(1998,04,28);
        String s = d.format(SqlConstants.DBDATEFORMAT);
        LocalDate localDate =  LocalDate.parse(s,SqlConstants.DBDATEFORMAT);
        */
        return LocalDate.from(this._startDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrainerAvailabilityEntity that = (TrainerAvailabilityEntity) o;

        LocalDate thisLocalDate = this.getLocalDate();
        LocalDate thatLocalDate = that.getLocalDate();

        LocalTime thisStartTime = this.getStartDateTime().toLocalTime();
        LocalTime thatStartTime = that.getStartDateTime().toLocalTime();

        LocalTime thisEndTime = this.getEndDateTime().toLocalTime();
        LocalTime thatEndTime = that.getEndDateTime().toLocalTime();

        return thisLocalDate.equals(thatLocalDate) &&
            thisStartTime.equals(thatStartTime) &&
            thisEndTime.equals(thatEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_startDateTime, _endDateTime);
    }
}
