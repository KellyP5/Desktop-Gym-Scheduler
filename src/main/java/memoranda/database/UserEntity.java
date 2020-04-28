/**
 * This class represents the User entity in the database, the user
 * of the program. It contains all necessary information such as name, email,
 * password (used for logging in), their role (trainer, admin, student), and their
 * belt types (belt color, and trainer belt color if applicable)
 *
 */
package main.java.memoranda.database;

import java.util.Objects;

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
    private String _imageUrl;

    /**
     * Constructor for a user who is not a trainer (student)
     */
    public UserEntity(String _firstName, String _lastName, String _password, String _email,
                      RoleEntity _role, String imageUrl) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._password = _password;
        this._email = _email;
        this._role = _role;
        this._imageUrl = imageUrl;
    }

    /**
     * Constructor for a user who IS a trainer
     */
    public UserEntity(String _firstName, String _lastName, String _password, String _email,
                      RoleEntity _role, BeltEntity _belt, BeltEntity _trainingBelt, String imageUrl) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._password = _password;
        this._email = _email;
        this._role = _role;
        this._belt = _belt;
        this._trainingBelt = _trainingBelt;
        this._imageUrl = imageUrl;
    }

    /**
     * Gets the user's email
     *
     * @return The email as a string
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Sets the url for the image of the user's photo
     * @param url The url to use
     */
    public void setImageUrl(String url) {
        this._imageUrl = url;
    }

    /**
     * Gets the url for the image of the user's photo
     * @return The string of the URL
     */
    public String getImageUrl() {
        return this._imageUrl;
    }

    /**
     * Sets the user's email
     *
     * @param email The string to set the user's email to
     */
    public void setEmail(String email) {
        this._email = email;
    }

    /**
     * Gets the user's first name
     *
     * @return Returns the name as a string
     */
    public String getFirstName() {
        return _firstName;
    }

    /**
     * Sets the user's first name
     *
     * @param firstName The string to set the first name to
     */
    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    /**
     * Gets the user's last name
     *
     * @return The last name as a string
     */
    public String getLastName() {
        return _lastName;
    }

    /**
     * Sets the user's last name
     *
     * @param lastName The string to set the last name to
     */
    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    /**
     * Gets the user's password
     *
     * @return The password as a string
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Sets the user's password
     *
     * @param password The string to set the password to
     */
    public void setPassword(String password) {
        this._password = password;
    }

    /**
     * Gets the user's role (trainer, student, admin)
     *
     * @return Returns the RoleEntity of the user
     */
    public RoleEntity getRole() {
        return _role;
    }

    /**
     * Sets the user's role
     *
     * @param role The RoleEntity of the user
     */
    public void setRole(RoleEntity role) {
        this._role = role;
    }

    /**
     * Gets the user's belt
     *
     * @return The BeltEntity of the user (color)
     */
    public BeltEntity getBelt() {
        if (_belt == null) {
            return new BeltEntity(BeltEntity.Rank.white);
        } else {
            return _belt;
        }
    }

    /**
     * Sets the user's belt
     *
     * @param belt The BeltEntity of the user
     */
    public void setBelt(BeltEntity belt) {
        this._belt = belt;
    }

    /**
     * Gets the user's training belt if they are a trainer
     * (The belt level they are allowed to train at)
     *
     * @return The trainer's BeltEntity
     */
    public BeltEntity getTrainingBelt() {
        return _trainingBelt;
    }

    /**
     * Sets the trainer's training belt
     *
     * @param trainingBelt The BeltEntity that the trainer is allowed to train in
     */
    public void setTrainingBelt(BeltEntity trainingBelt) {
        this._trainingBelt = trainingBelt;
    }

    /**
     * Checks if two users are equal to each other
     *
     * @param o The user to compare
     * @return Returns true if the users are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return _firstName.equals(that._firstName) &&
                _lastName.equals(that._lastName) &&
                _password.equals(that._password) &&
                _email.equals(that._email) &&
                _role.equals(that._role) &&
                Objects.equals(_belt, that._belt) &&
                Objects.equals(_trainingBelt, that._trainingBelt);
    }

    /**
     * Generates a hashcode for a user
     *
     * @return Returns the hashcode as an integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(_firstName, _lastName, _password, _email, _role, _belt, _trainingBelt);
    }
}
