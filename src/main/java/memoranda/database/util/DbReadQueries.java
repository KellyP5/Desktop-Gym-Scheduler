package main.java.memoranda.database.util;

import main.java.memoranda.database.*;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/*
Class for all read related queries
 */
public class DbReadQueries {

    private String _dbUrl;


    public DbReadQueries(String dbUrl) {
        this._dbUrl = dbUrl;
    }

    /*
    gets all of a USER's information based on the email provided
     */
    public UserEntity getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM user WHERE Email=?";

        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,email);
        ResultSet rs  = pstmt.executeQuery();
        UserEntity userEntity = _getUserFromResultSet(rs);

        pstmt.close();
        conn.close();
        return userEntity;
    }

    /*
    returns list of all users in the USER table
     */
    public ArrayList<UserEntity> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM user";

        Connection conn = DriverManager.getConnection(_dbUrl);
        Statement statement  = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<UserEntity> users = new ArrayList<>();
        while(rs.next()){
            users.add(_getUserFromResultSet(rs));
        }
        statement.close();
        conn.close();
        return users;
    }
    /*
    returns all classes a specific user is enrolled in
     */
    public ArrayList<GymClassEntity> getClassesUserEnrolledInByEmail(String email)
            throws SQLException {
        String sql = "SELECT * FROM GYMCLASS " +
                     "INNER JOIN ENROLLEDUSER on ENROLLEDUSER.ClassId = GYMCLASS.Id " +
                     "WHERE ENROLLEDUSER.UserEmail=?";

        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,email);
        ResultSet rs  = pstmt.executeQuery();

        ArrayList<GymClassEntity> gymClasses = new ArrayList<>();
        while(rs.next()){
            gymClasses.add(_getGymClassFromResultSet(rs));
        }
        pstmt.close();
        conn.close();
        return gymClasses;
    }
    /*
    returns all of a specific trainers Availability, based on email
     */
    public ArrayList<TrainerAvailabilityEntity> getTrainerDateTimeAvailabilityByEmail(String email)
            throws SQLException {
        String sql = "SELECT * FROM TRAINERAVAILABILITY WHERE TRAINERAVAILABILITY.TrainerEmail=?";

        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,email);
        ResultSet rs  = pstmt.executeQuery();

        ArrayList<TrainerAvailabilityEntity> trainerAvailabilities = new ArrayList<>();
        while(rs.next()){
            LocalDateTime startDateTime = _getLocalDateTimeFromDbFields(
                    rs.getString("StartDate"),
                    rs.getDouble("StartTime"));
            LocalDateTime stopDateTime = _getLocalDateTimeFromDbFields(
                    rs.getString("StartDate"),
                    rs.getDouble("EndTime"));
            trainerAvailabilities.add(new TrainerAvailabilityEntity(startDateTime, stopDateTime));
        }

        pstmt.close();
        conn.close();
        return trainerAvailabilities;
    }
    /*
    returns all classes on a specific date, expects the date to be in the format MM/dd/yyyy
     */
    public ArrayList<GymClassEntity> getAllClassesByDate(LocalDate localDate) throws SQLException {
        String strDate = localDate.format(SqlConstants.DBDATEFORMAT);

        String sql = "SELECT * FROM GYMCLASS WHERE StartDate=?";

        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,strDate);
        ResultSet rs  = pstmt.executeQuery();

        ArrayList<GymClassEntity> gymClasses = new ArrayList<>();
        while(rs.next()){
            gymClasses.add(_getGymClassFromResultSet(rs));
        }

        pstmt.close();
        conn.close();
        return gymClasses;
    }
    /*
    gets all users based on a role provided, ex: get all admins, etc.
     */
    public ArrayList<UserEntity> getAllUsersOfCertainRole(RoleEntity role) throws SQLException {
        String sql = "SELECT * FROM USER WHERE Role=?";

        Connection conn = DriverManager.getConnection(_dbUrl);
        PreparedStatement pstmt  = conn.prepareStatement(sql);
        pstmt.setString(1,role.userRole.name().toLowerCase());
        ResultSet rs  = pstmt.executeQuery();

        ArrayList<UserEntity> users = new ArrayList<>();
        while(rs.next()){
            users.add(_getUserFromResultSet(rs));
        }

        pstmt.close();
        conn.close();
        return users;
    }
    /*
    helper method for creating and returning a GymClassEntity from a result set
     */
    private GymClassEntity _getGymClassFromResultSet(ResultSet rs) throws SQLException {

        LocalDateTime startDateTime = _getLocalDateTimeFromDbFields(
                rs.getString("StartDate"),
                rs.getDouble("StartTime"));

        LocalDateTime endDateTime = _getLocalDateTimeFromDbFields(
                rs.getString("StartDate"),
                rs.getDouble("EndTime"));

        BeltEntity minBeltRequired = new BeltEntity(
                BeltEntity.Rank.valueOf(rs.getString("MinBeltRequired")));

        return new GymClassEntity(
                rs.getInt("Id"),
                rs.getInt("RoomNumber"),
                startDateTime,
                endDateTime,
                rs.getString("TrainerEmail"),
                rs.getInt("MaxClassSize"),
                minBeltRequired,
                rs.getString("CreatedByEmail")
        );
    }
    /*
    helper method for getting a LocalDateTime from a string representing the date with the format
    MM/dd/yyyy and a double, which represents the time on a 24 hour period.  Ex: 13.59 is 13:59,
    which is 1:59pm
     */
    private LocalDateTime _getLocalDateTimeFromDbFields(String strDate, double time) {
        //get LocalDate
        LocalDate localDate = LocalDate.parse(strDate, SqlConstants.DBDATEFORMAT);

        //get LocalTime
        String strTime = String.valueOf(time);
        String[] timeParts = strTime.split("\\.");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        //need to account for a multiple of 10 minutes (10, 20, 30, 40) which will only be shown as x.1, x.2, x.3, etc.
        if (timeParts[1].length() == 1){
            minutes *= 10;
        }
        LocalTime localTime = LocalTime.of(hours, minutes);

        return LocalDateTime.of(localDate, localTime);
    }
    /*
    helper method for creating and returning a UserEntity from the result set provided
     */
    private UserEntity _getUserFromResultSet(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        String strBelt = rs.getString("Belt");
        BeltEntity belt = null;
        if (strBelt != null){
            belt = new BeltEntity(BeltEntity.Rank.valueOf(strBelt.toLowerCase()));
        }
        String strTrainingBelt = rs.getString("TrainingBelt");
        BeltEntity trainingBelt = null;
        if(strTrainingBelt != null){
            trainingBelt = new BeltEntity(BeltEntity.Rank.valueOf(strTrainingBelt.toLowerCase()));
        }
        String strRole = rs.getString("Role");
        RoleEntity role = new RoleEntity(RoleEntity.UserRole.valueOf(strRole.toLowerCase()));

        return new UserEntity(rs.getString("FirstName"),
                                        rs.getString("LastName"),
                                        rs.getString("Password"),
                                        rs.getString("Email"),
                                        role,
                                        belt,
                                        trainingBelt);
    }
}
