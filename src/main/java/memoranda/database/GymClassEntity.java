package main.java.memoranda.database;

import java.time.LocalDateTime;

/*
GymClassEntity is what is returned by any SQL related queries related to the GymClass table.  This class is also used
for inserts.
 */
public class GymClassEntity {
    private int Id;
    private int roomNumber;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String trainerEmail;
    private int maxClassSize;
    private BeltEntity minBeltEntityRequired;
    private String createdByEmail;


    public GymClassEntity(int id, int roomNumber, LocalDateTime startDateTime, LocalDateTime endDateTime, String trainerEmail, int maxClassSize, BeltEntity minBeltEntityRequired, String createdByEmail) {
        Id = id;
        this.roomNumber = roomNumber;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.trainerEmail = trainerEmail;
        this.maxClassSize = maxClassSize;
        this.minBeltEntityRequired = minBeltEntityRequired;
        this.createdByEmail = createdByEmail;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }

    public int getMaxClassSize() {
        return maxClassSize;
    }

    public void setMaxClassSize(int maxClassSize) {
        this.maxClassSize = maxClassSize;
    }

    public BeltEntity getMinBeltEntityRequired() {
        return minBeltEntityRequired;
    }

    public void setMinBeltEntityRequired(BeltEntity minBeltEntityRequired) {
        this.minBeltEntityRequired = minBeltEntityRequired;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }
}
