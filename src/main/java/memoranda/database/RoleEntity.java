package main.java.memoranda.database;

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
