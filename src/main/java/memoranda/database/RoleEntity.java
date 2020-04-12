package main.java.memoranda.database;

/*
RoleEntity is for sql uses related to the Role field, which appears in the USER table.  Role is
used for permissions and visibility in the GUI.
 */
public class RoleEntity {
    public enum UserRole{
        admin,
        trainer,
        customer
    }
    public UserRole userRole;
    public RoleEntity(UserRole userRole){
        this.userRole = userRole;
    }
}
