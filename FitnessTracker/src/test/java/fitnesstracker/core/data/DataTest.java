import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataTest {

    @Test
    void testMenuEnterNewUser() {
        // Test the menuEnterNewUser method in the Data class
        String name = "Crystal";
        int age = 19;
        boolean result = Data.menuEnterNewUser(name, age);

        assertTrue(result, "Failed to add a new user.");
    }
    @Test
    void testSetWeight() {
        // Test the setWeight method in the Data class
        Data.menuEnterNewUser("Esha", 21);
        assertEquals(54.5, Data.setWeight("Esha", 54.5));
    }
    @Test
    void menuEnterExerciseTime() {
        // Test the menuEnterExerciseTime method in the Data class
        Data.menuEnterNewUser("Esha", 21);
        assertTrue(Data.menuEnterExerciseTime("Esha", 60));
        assertEquals(60, Data.findUserFromName("Esha").getExerciseTime());
    }

    @Test
    void testGetPerson() {
        Data.menuEnterNewUser("Esha", 21);
        assertNotNull(Data.getPerson("Esha"));
    }
    // this test will check if the correct user is being retrieved from getUserFromName
    @Test
    void findUserFromName() {
        Data.menuEnterNewUser("Crystal", 21);
        assertNotNull((Data.findUserFromName("Crystal")));
    }
    // This test will check if the list of users will be filtered by name has the expected size
    @Test
    void getListFilteredByName() {
        Data.menuEnterNewUser("Esha", 21);
        Data.menuEnterNewUser("Esha", 15);
        Data.menuEnterNewUser("Esha",20);
        Data.menuEnterNewUser("Esha", 23);
        Data.menuEnterNewUser("Esha", 42);
        Data.menuEnterNewUser("Esha", 31);
        assertEquals(6, Data.getListFilteredByName("Esha").size());
    }
    // checks if set weight returns -1.0 when trying to set the weight for a user that does not exist
    @Test
    void userNotFoundSetWeight() {
        assertEquals(-1.0, Data.setWeight("User not found", 50.0));
    }

    // tests to check if equals to picks up on the same person/age for a user is stored
    @Test
    void equalsToTest() {
        // create person object with the same name and age
        Person person1 = new Person("Esha", 21);
        Person person2 = new Person("Esha", 21);
        // use assertTtur to assert that equals method returns true for these objects
        assertTrue(person1.equals(person2));
    }
    // Test to check if the hashcode method generates different hash codes for users with a different name and age
    @Test
    void hashCodeTest() {
        // create person objects with different name and age
        Person person1 = new Person("Esha", 21);
        Person person2 = new Person("Crystal", 22);
        // use asert not equals to ansure hashcodes are not equal
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

}



