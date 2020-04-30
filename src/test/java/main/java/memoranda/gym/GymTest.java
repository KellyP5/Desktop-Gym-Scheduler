package main.java.memoranda.gym;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import main.java.memoranda.database.entities.BeltEntity;
import main.java.memoranda.database.entities.UserEntity;
import org.junit.Test;

public class GymTest {

    @Test
    public void testCreateReadDeleteTrainer(){

        Gym gym = new Gym();
        // just to make sure we don't have this user already in the DB
        Response res = gym.deleteUser("unique13131312231@gmail.com");

        BeltEntity be = new BeltEntity("brown2");
        Response create = gym.createTrainer("unique13131312231@gmail.com",
            "fname",
            "lname",
            "1234",
            be);
        assertEquals(true,create.isSuccess());
        assertEquals(false,create.isFailure());
        assertEquals(null,create.getValue());// create methods don't return a value
        assertEquals("Success: Trainer created.",create.getMsg());

        Response read = gym.readGetUser("unique13131312231@gmail.com");
        assertEquals(true,read.isSuccess());
        assertEquals(false,read.isFailure());
        UserEntity ue = (UserEntity)read.getValue();//Read methods return an object, must be type casted.
        assertEquals(be,ue.getBelt());

        //clean up
        gym.deleteUser("unique13131312231@gmail.com");
    }

    @Test
    public void testCreateReadDeleteCustomer(){
        Gym gym = new Gym();

        //test the create
        Response createRes = gym.createCustomer("unique13131312231@gmail.com",
            "fname",
            "lname",
            "1234");
        assertEquals(createRes.getMsg(),"Success: Customer created.");

        //test the read
        Response readRes = gym.readGetUser("unique13131312231@gmail.com");
        String email = ((UserEntity)readRes.getValue()).getEmail();//type cast the generic
        assertEquals(email,"unique13131312231@gmail.com");

        //test the delete
        Response deleteRes = gym.deleteUser("unique13131312231@gmail.com");
        assertEquals(deleteRes.getMsg(),"Success: User deleted");
    }

    @Test
    public void testReadFailure(){
        Gym gym = new Gym();

        Response deleteRes = gym.deleteUser("unique13131312231@gmail.com");
        Response readRes = gym.readGetUser("unique13131312231@gmail.com");

        assertEquals(readRes.isFailure(),true);
        assertEquals(readRes.getMsg(),"Error: User does not exist");
    }

    @Test
    public void testAlreadyCreatedFailure(){
        Gym gym = new Gym();

        //just to make sure the user doesn't exist already
        Response deleteRes = gym.deleteUser("unique13131312231@gmail.com");

        //first add
        Response createRes1 = gym.createCustomer("unique13131312231@gmail.com",
            "fname",
            "lname",
            "1234");

        assertEquals(createRes1.isSuccess(),true);
        assertEquals(createRes1.getMsg(),"Success: Customer created.");

        //second add should fail
        Response createRes2 = gym.createCustomer("unique13131312231@gmail.com",
            "fname",
            "lname",
            "1234");
        assertEquals(createRes2.isSuccess(),false);
        assertEquals(createRes2.getMsg(),"Error: User already exists.");

        //just to make sure the test user isn't still in the db.
        Response deleteRes2 = gym.deleteUser("unique13131312231@gmail.com");
    }

    @Test
    public void testTrainerAvail(){
        Gym gym = new Gym();

        gym.deleteUser("1331331@gmail.com");
        gym.createTrainer("1331331@gmail.com","test1","test2","1234",new BeltEntity("black3"));
        //("MM/dd/yyyy");

        LocalDate localDate = LocalDate.of(1998,04,28);

        Response res = gym.createTrainerAvailability("1331331@gmail.com",5.0,13.0, localDate);

        assertEquals(true,res.isSuccess());
        assertEquals(false,res.isFailure());
        assertEquals(null,res.getValue());
        assertEquals("Success: Trainers availability has been added.",res.getMsg());
    }

    @Test
    public void testDeleteClass(){

        Gym gym = new Gym();
        Response res1 = gym.createTrainer("1331331@gmail.com","test1","test2","1234",new BeltEntity("black3"));

        LocalDate ld = LocalDate.of(1988,04,28);
        double startTime = 5.0;
        double endTime = 8.0;

        Response res2 = gym.createGroupClass(1337,ld,startTime,endTime,"1331331@gmail.com",20,
            new BeltEntity("white"), "1331331@gmail.com");

        Response res3 = gym.readGetClass(ld,startTime,1337);

        assertEquals("Success: Class found.",res3.getMsg());

        Response res4 = gym.deleteClass(ld,startTime,1337);

        assertEquals("Success: Class might be deleted.",res4.getMsg());

        Response res5 = gym.readGetClass(ld,startTime,1337);

        assertEquals("Error: Class not found.",res5.getMsg());

    }

}