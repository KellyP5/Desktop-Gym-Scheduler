package main.java.memoranda.gym;

import static org.junit.Assert.assertEquals;

import main.java.memoranda.database.BeltEntity;
import main.java.memoranda.database.UserEntity;
import org.junit.Test;

public class GymTest {

    @Test
    public void testCreateReadDeleteTrainer(){

        Gym gym = new Gym();
        // just to make sure we don't have this user already in the DB
        gym.deleteUser("unique13131312231@gmail.com");

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

}