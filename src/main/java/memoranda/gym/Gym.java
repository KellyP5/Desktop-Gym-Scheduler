package main.java.memoranda.gym;

import main.java.memoranda.database.RoleEntity;
import main.java.memoranda.database.SqlConnection;
import main.java.memoranda.database.UserEntity;

import java.sql.SQLException;

public class Gym {

    private UserEntity user;//Could be Admin/Trainer/User, this is the user that is currently accessing the system.

    private SqlConnection conn;


    private enum UserRole{
        admin,
        trainer,
        customer
    }

    private enum Rank{
        white,
        yellow,
        orange,
        purple,
        blue,
        blue_stripe,
        green,
        green_stripe,
        brown1,
        brown2,
        brown3,
        black1,
        black2,
        black3
    }

    public Gym(){

        this.user = null;
        this.conn = null;

        try{
            this.conn = SqlConnection.getInstance();
        }
        catch(SQLException ecp){
            ecp.printStackTrace();
        }
    }

    public boolean login(){

        //insert login code
        //assign this.user;

        //TODO
        return false;
    }

    UserRole getUserRole() {
        try{
            if(isAdmin()){
                return UserRole.admin;
            }
            else if(isTrainer()){
                return UserRole.trainer;
            }
            else{
                return UserRole.customer;
            }
        }
        catch (Exception ecp){
            ecp.printStackTrace();
        }

        return null;
    }

    boolean isAdmin() throws Exception{
        if(this.user==null){
            throw new Exception("User is not instantiated, null value.");
        }
        else if(this.user.getRole().userRole==RoleEntity.UserRole.admin){
            return true;
        }
        return false;
    }

    boolean isTrainer() throws Exception{
        if(this.user==null){
            throw new Exception("User is not instantiated, null value.");
        }
        else if(this.user.getRole().userRole==RoleEntity.UserRole.trainer){
            return true;
        }
        return false;
    }

    boolean isCustomer() throws Exception{
        if(this.user==null){
            throw new Exception("User is not instantiated, null value.");
        }
        else if(this.user.getRole().userRole==RoleEntity.UserRole.customer){
            return true;
        }
        return false;
    }




}
