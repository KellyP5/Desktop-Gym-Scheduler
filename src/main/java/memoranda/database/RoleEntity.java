package main.java.memoranda.database;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRole);
    }
}
