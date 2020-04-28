package main.java.memoranda.gym;

import static org.junit.Assert.assertEquals;

import main.java.memoranda.database.UserEntity;
import org.junit.Test;

public class GymTest {
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
}