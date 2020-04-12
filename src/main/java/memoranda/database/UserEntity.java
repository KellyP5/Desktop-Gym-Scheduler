package main.java.memoranda.database;

/*
UserEntity is what is used for all SQL queries related to USER table.
 */
public class UserEntity {
    private String _firstName;
    private String _lastName;
    private String _password;
    private String _email;
    private RoleEntity _role;
    private BeltEntity _belt;
    private BeltEntity _trainingBelt;

    public UserEntity(String _firstName, String _lastName, String _password, String _email, RoleEntity _role) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._password = _password;
        this._email = _email;
        this._role = _role;
    }

    public UserEntity(String _firstName, String _lastName, String _password, String _email, RoleEntity _role, BeltEntity _belt, BeltEntity _trainingBelt) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._password = _password;
        this._email = _email;
        this._role = _role;
        this._belt = _belt;
        this._trainingBelt = _trainingBelt;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        this._email = email;
    }


    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public RoleEntity getRole() {
        return _role;
    }

    public void setRole(RoleEntity role) {
        this._role = role;
    }

    public BeltEntity getBelt() {
        return _belt;
    }

    public void setBelt(BeltEntity belt) {
        this._belt = belt;
    }

    public BeltEntity getTrainingBelt() {
        return _trainingBelt;
    }

    public void setTrainingBelt(BeltEntity trainingBelt) {
        this._trainingBelt = trainingBelt;
    }


}
