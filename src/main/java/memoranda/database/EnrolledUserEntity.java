package main.java.memoranda.database;

/*
Enrolled Entity is the entity used for all EnrolledUser related SQL.  This is whats returned for
any queries that try to determine who is in enrolled in what gym class.
 */
public class EnrolledUserEntity {
    private int _classId;
    private String _userEmail;

    public EnrolledUserEntity(int _classId, String _userEmail) {
        this._classId = _classId;
        this._userEmail = _userEmail;
    }

    public int getClassId() {
        return _classId;
    }

    public void setClassId(int _classId) {
        this._classId = _classId;
    }

    public String getUserEmail() {
        return _userEmail;
    }

    public void setUserEmail(String _userEmail) {
        this._userEmail = _userEmail;
    }
}
