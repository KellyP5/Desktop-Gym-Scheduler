package main.java.memoranda.database;

/*
UserEntity is what is used for all SQL queries related to USER table.
 */
public class UserEntity {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private RoleEntity role;
    private BeltEntity belt;
    private BeltEntity trainingBelt;

    public UserEntity(String firstName, String lastName, String password, String email, RoleEntity role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserEntity(String firstName, String lastName, String password, String email, RoleEntity role, BeltEntity belt, BeltEntity trainingBelt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.belt = belt;
        this.trainingBelt = trainingBelt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public BeltEntity getBelt() {
        return belt;
    }

    public void setBelt(BeltEntity belt) {
        this.belt = belt;
    }

    public BeltEntity getTrainingBelt() {
        return trainingBelt;
    }

    public void setTrainingBelt(BeltEntity trainingBelt) {
        this.trainingBelt = trainingBelt;
    }


}
