package main.java.memoranda.database;

import main.java.memoranda.database.BeltEntity;
import java.time.LocalDateTime;

public class GymClassEntity {
    private int Id;
    private int roomNumber;
    private LocalDateTime startDateTime;
    private float duration;
    private String trainerEmail;
    private int maxClassSize;
    private BeltEntity minBeltEntityRequired;
    private String createdByEmail;


    public GymClassEntity(int id, int roomNumber, LocalDateTime startDateTime, float duration, String trainerEmail, int maxClassSize, BeltEntity minBeltEntityRequired, String createdByEmail) {
        Id = id;
        this.roomNumber = roomNumber;
        this.startDateTime = startDateTime;
        this.duration = duration;
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

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
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
