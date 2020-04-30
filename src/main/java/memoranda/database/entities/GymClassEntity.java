package main.java.memoranda.database.entities;

import main.java.memoranda.ui.App;

import java.sql.SQLException;
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
    private int _numStudents;

    public GymClassEntity(int id, int _roomNumber, LocalDateTime _startDateTime,
                          LocalDateTime _endDateTime, String _trainerEmail, int _maxClassSize,
                          BeltEntity _minBeltEntityRequired, String _createdByEmail) throws SQLException {
        _Id = id;
        this._roomNumber = _roomNumber;
        this._startDateTime = _startDateTime;
        this._endDateTime = _endDateTime;
        this._trainerEmail = _trainerEmail;
        this._maxClassSize = _maxClassSize;
        this._minBeltEntityRequired = _minBeltEntityRequired;
        this._createdByEmail = _createdByEmail;
        //this._numStudents = getNumberOfStudentsEnrolledInClass(id);
    }

    public int getId() {
        return _Id;
    }

    public int getNumberOfStudentsEnrolledInClass(int classId) throws SQLException {
        if (App.conn.getDrq().getNumberOfStudentsEnrolledInClass(classId) != 0) {
            this._numStudents = App.conn.getDrq().getNumberOfStudentsEnrolledInClass(classId);
        } else {
            this._numStudents = 0;
        }
        return this._numStudents;
    }

    public int getNumStudents() {
        return this._numStudents;
    }

    public void setNumStudents(int num) {
        this._numStudents = num;
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

    /**
     * Converts gym start time to string.
     * @return String of converted time
     */
    public String getStartTimeAsString() {
        String s = _startDateTime.toString();
        String hour = s.substring(11, 13);
        String minute = s.substring(14,16);
        s = hour + minute;
        System.out.println(s);
        return s;
    }

    @Override
    public int compareTo(GymClassEntity gymClassEntity) {
        return getStartDateTime().compareTo(gymClassEntity.getStartDateTime());
    }

    /**
     * Prints out a class. Used for debugging Gym Class Entities and related
     * queries.
     */
    public void printClass() {
        System.out.println("ID: " + this._Id
        + "\nroomNumber: " + this._roomNumber
            + "\nmaxClassSize " + this._maxClassSize
            + "\nstartDateTime " + this._startDateTime.toString()
            + "\nendDateTime " + this._endDateTime.toString()
            + "\ntrainer email " + this._trainerEmail
            + "\nmin belt required " + this._minBeltEntityRequired.toString()
            + "\ncreated by e-mail " + this._createdByEmail);
    }
}
