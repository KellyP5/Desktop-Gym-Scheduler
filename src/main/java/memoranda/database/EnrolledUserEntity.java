package main.java.memoranda.database;

/*
Enrolled Entity is the entity used for all EnrolledUser related SQL.  This is whats returned for any queries
that try to determine who is in enrolled in what gym class.
 */
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
