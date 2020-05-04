import main.java.memoranda.database.entities.GymClassEntity;
import main.java.memoranda.gym.Gym;
import main.java.memoranda.gym.Response;

import org.junit.*;
import java.util.ArrayList;

public class TestEnrollAndUnenroll {

    Gym gym;

    @Before
    public void setUp() {
        gym = Gym.getInstance();
        gym.createCustomer("sdlfjkj2345223523452@yahoo.com", "John", "smith", "foo");

    }

    @After
    public void tearDown() {
        //TODO clean up any changes gym instance made to the real db
        gym.deleteUser("sdlfjkj2345223523452@yahoo.com");
    }

    @Test
    public void testUnEnrollingUnknownUserIsFailure() {

        Response response = gym.unenrollCustomer("nonexistantemailformadeupuser@gmail.com", 3333);
        assert(response.isFailure());
    }

    @Test
    public void testEnrollingKnownUserIsSucess() {

        Response response = gym.enrollUser(1, "sdlfjkj2345223523452@yahoo.com");
        assert(response.isSuccess());
        Response enrollTest = gym.getClassesUserEnrolledInByEmail("sdlfjkj2345223523452@yahoo.com");
        ArrayList<GymClassEntity> classesEnrolled = (ArrayList<GymClassEntity>)enrollTest.getValue();
        assert(classesEnrolled.size() == 1);
        gym.unenrollCustomer("sdlfjkj2345223523452@yahoo.com", 1);
    }

    @Test
    public void testUnEnrollingKnownUserIsSuccess() {

        gym.enrollUser(1, "sdlfjkj2345223523452@yahoo.com");

        Response unenRollResponse = gym.unenrollCustomer("sdlfjkj2345223523452@yahoo.com", 1);
        assert(unenRollResponse.isSuccess());

        gym.unenrollCustomer("sdlfjkj2345223523452@yahoo.com", 1);
    }

    @Test
    public void testEnrollUserInManyClasses() {

        gym.enrollUser(1, "sdlfjkj2345223523452@yahoo.com");
        gym.enrollUser(2, "sdlfjkj2345223523452@yahoo.com");
        gym.enrollUser(4, "sdlfjkj2345223523452@yahoo.com");

        Response enrollTest = gym.getClassesUserEnrolledInByEmail("sdlfjkj2345223523452@yahoo.com");
        ArrayList<GymClassEntity> classesEnrolled = (ArrayList<GymClassEntity>)enrollTest.getValue();
        System.out.println("ENROLLED IN : " + classesEnrolled.size());
        assert(classesEnrolled.size() == 3);

        gym.unenrollCustomer("sdlfjkj2345223523452@yahoo.com", 1);
        gym.unenrollCustomer("sdlfjkj2345223523452@yahoo.com", 2);
        gym.unenrollCustomer("sdlfjkj2345223523452@yahoo.com", 4);
    }
}
