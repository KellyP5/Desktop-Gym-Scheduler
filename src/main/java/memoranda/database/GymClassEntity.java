package main.java.memoranda.database;

import java.time.LocalDateTime;
import java.util.Objects;

/*
GymClassEntity is what is returned by any SQL related queries related to the GymClass table.
This class is also used for inserts.
 */
public class GymClassEntity implements Comparable<GymClassEntity> {
    private int _Id;
    private int _roomNumber;
    private LocalDateTime _startDateTime;
    private LocalDateTime _endDateTime;
    private String _trainerEmail;
    private int _maxClassSize;
    private BeltEntity _minBeltEntityRequired;
    private String _createdByEmail;


    public GymClassEntity(int id, int _roomNumber, LocalDateTime _startDateTime,
                          LocalDateTime _endDateTime, String _trainerEmail, int _maxClassSize,
                          BeltEntity _minBeltEntityRequired, String _createdByEmail) {
        _Id = id;
        this._roomNumber = _roomNumber;
        this._startDateTime = _startDateTime;
        this._endDateTime = _endDateTime;
        this._trainerEmail = _trainerEmail;
        this._maxClassSize = _maxClassSize;
        this._minBeltEntityRequired = _minBeltEntityRequired;
        this._createdByEmail = _createdByEmail;
    }

    public int getId() {
        return _Id;
    }

    public void printGymClass() {
        System.out.println("class room: " + this._roomNumber + "\n start date: " + this._startDateTime);
    }

    public void setId(int _Id) {
        this._Id = _Id;
    }

    public int getRoomNumber() {
        return _roomNumber;
    }

    public void setRoomNumber(int _roomNumber) {
        this._roomNumber = _roomNumber;
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

    public String getTrainerEmail() {
        return _trainerEmail;
    }

    public void setTrainerEmail(String _trainerEmail) {
        this._trainerEmail = _trainerEmail;
    }

    public int getMaxClassSize() {
        return _maxClassSize;
    }

    public void setMaxClassSize(int _maxClassSize) {
        this._maxClassSize = _maxClassSize;
    }

    public BeltEntity getMinBeltEntityRequired() {
        return _minBeltEntityRequired;
    }

    public void setMinBeltEntityRequired(BeltEntity _minBeltEntityRequired) {
        this._minBeltEntityRequired = _minBeltEntityRequired;
    }

    public String getCreatedByEmail() {
        return _createdByEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymClassEntity that = (GymClassEntity) o;
        return _Id == that._Id &&
                _roomNumber == that._roomNumber &&
                _maxClassSize == that._maxClassSize &&
                _startDateTime.equals(that._startDateTime) &&
                _endDateTime.equals(that._endDateTime) &&
                _trainerEmail.equals(that._trainerEmail) &&
                _minBeltEntityRequired.equals(that._minBeltEntityRequired) &&
                _createdByEmail.equals(that._createdByEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_Id, _roomNumber, _startDateTime, _endDateTime, _trainerEmail, _maxClassSize, _minBeltEntityRequired, _createdByEmail);
    }

    public void setCreatedByEmail(String _createdByEmail) {
        this._createdByEmail = _createdByEmail;
    }

    @Override
    public int compareTo(GymClassEntity gymClassEntity) {
        return getStartDateTime().compareTo(gymClassEntity.getStartDateTime());
    }
}
