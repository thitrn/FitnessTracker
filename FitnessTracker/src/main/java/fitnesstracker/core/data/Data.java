import java.nio.channels.ScatteringByteChannel;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Data {

    // create array list to store list of person Objects
    static final ArrayList<Person> list = new ArrayList<>();
    // create hashmap to store list of person Objects by age
    static final HashMap<Integer, Person> lookup = new HashMap<>();

    /**
     * add a new user to list and lookup by name/age
     *
     * @param name - the name of new user
     * @param age  -the age of new user
     * @return true will be returned if the user was successfully stored
     */
    public static boolean menuEnterNewUser(String name, int age) {
        // create new object for a users given name and age
        Person newPerson = new Person(name, age);
        // add user to list
        list.add(newPerson);
        // store user in the lookup hashmap with age as the key
        lookup.put(age, newPerson);
        // return true to indicate successful storage
        return true;
    }

    /**
     * This funcion will log the exercise time for a specific user by name
     *
     * @param name         - name of user is taken as a parameter
     * @param exerciseTime - exercise time is a parameter in minutes
     * @return true is returned when exercise time is successfully logged
     */
    public static boolean menuEnterExerciseTime(String name, double exerciseTime) {
        // find user by their name
        Person user = findUserFromName(name);
        // use if statement if user is found
        if (user != null) {
            // log workout duration
            user.logWorkout(exerciseTime);
            return true;
            // else statement (no user was found)
        } else {
            // print out that user was not found
            System.out.println("User not found.");
        }
        // return false to indicate failure/success
        return false;
    }

    /**
     * This function will find a user by name
     *
     * @param name - name of user is a parameter
     * @return The person object in list matching the name will be returned
     * and null will be returned otherwise
     */
    public static Person findUserFromName(String name) {
        // enter a for each loop and iterate through Person object
        for (Person person : list) {
            // if matching user is found
            if (person.getName().equalsIgnoreCase(name)) {
                // return Person object
                return person;
            }
        }
        // if no matching user was found then print no user found
        System.out.println("User not found.");
        return null;
    }

    /**
     * set the weight for the user who is selected by name
     *
     * @param name   name of user is a parameter
     * @param weight weight is the second parameter: type double
     * @return a negative double is returned because a users weight can not be negative
     */
    public static double setWeight(String name, double weight) {
        Person user = getUserFromName(name);
        // if user is found
        if (user != null) {
            //set user weight
            user.setWeight(weight);
            // return updated weight
            return user.getWeight();
            //else statement to -1.0 to indicate failure
            //used -1,0 because weight can not be negative
            // followed consistency with using a double type
        } else {
            return -1.0;
        }
    }

    /**
     * set the new weight of user after a week
     *
     * @param name   is a parameter
     * @param weight weight is the second parameter in type double
     * @return a message is returned to indicate weight has been updated
     * or if user is not found
     */
    public static String setWeightAfterWeek(String name, double weight) {
        // find the user
        Person user = getUserFromName(name);
        // use if statement if the user is found
        if (user != null) {
            // set weight for the user
            user.setWeightAfterWeek(weight);
            // return the weight of user with their name
            return "Weight after a week recorded for " + name + ".";
            // use else statement if the user was not found
        } else {
            // return user not found
            return "User not found.";
        }
    }

    /**
     * In this piece of code the Person object will be returned based on the provided name
     *
     * @param name - the parameter for this is the name of the person
     * @return Person object with matching name or null if the user is not found
     */
    public static Person getPerson(String name) {
        for (Person person : list) {
            //check if the name given of the current user matches provided name
            // make this case-insensitive
            if (person.getName().equalsIgnoreCase(name)) {
                // return Person object
                return person;
            }
        }
        // if person not found return null
        return null;
    }

    /**
     * This will get a list of users filtered by name in an Arraylist
     *
     * @param name - is taken as the parameter
     * @return An Arraylist is returned of Person objects matching the provided name
     */
    public static ArrayList<Person> getListFilteredByName(String name) {
        // create new arraylist
        ArrayList<Person> filtered = new ArrayList<>();
        // use a for each loop to iterate through every Person in list
        for (Person entry : list) {
            // if statement to see if a matching user is found
            if (entry.getName().equals(name)) ;
            // add to filtered list
            filtered.add(entry);
        }
        // return filtered user list
        return filtered;
    }

    /**
     * This function gets a person object from their username
     *
     * @param userName - username of the person who is being retrieved
     * @return a Person object with matching username will be returned if matched
     * if not, null is returned
     */
    public static Person getUserFromName(String userName) {
        // use a for each loop to go through each Person object in list
        for (Person person : list) {
            // if statement when a matching user is found return Person object
            if (person.getName().equalsIgnoreCase(userName)) {
                // return person object
                return person;
            }
        }
        // if no matching Person object is found return null
        return null;
    }

    public static List<Person> getList() {
        return list;
    }

    public void saveDataCSV(String fileName) {
        Writer.saveDataCSV(fileName, list);
    }

    public void loadDataCSV(String fileName) {
        Reader.loadDataCSV(fileName, list);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserInputAsInteger() {
        while (true) {
            try (Scanner scanner = new Scanner(System.in)) {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid, Please enter a valid integer");
            }
        }
    }
}
