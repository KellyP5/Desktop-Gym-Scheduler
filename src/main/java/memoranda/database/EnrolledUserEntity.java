package main.java.memoranda.database;

public class EnrolledUserEntity {
    private int classId;
    private String userEmail;

    public EnrolledUserEntity(int classId, String userEmail) {
        this.classId = classId;
        this.userEmail = userEmail;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
