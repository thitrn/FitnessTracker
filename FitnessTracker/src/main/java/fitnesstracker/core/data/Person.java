import java.util.Objects;

public class Person implements Comparable<Person> {
    //Constants indicate the indices for our data array
    // index for age
    public static final int indexName = 0;
    //index for weight
    public static final int indexWeight = 1;
    // indec for age
    public static final int indexAge = 2;

    // An array is created to store users data
    private Object[] data;

    // constructor created to a new user with both name and age
    public Person(String name, int age) {
        // Create an array with 3 slots
        data = new Object[3];
        // store the users name at index
        data[indexName] = name;
        // store the users age at index
        data[indexAge] = age;
        // store the user weight at index
        data[indexWeight] = weight;
        //store the user water intake
        this.totalWaterIntake = 0;

    }

    // Get method is used to get the name from data array previously created
    public String getName() {
        return (String) data[indexName];

    }

    // usage of get method to get user age
    public Integer getAge() {
        return (int) data[indexAge];
    }

    // New attribute for weight
    private double weight;

    // Get and set for user's weight
    public double getWeight() {
        return (double) data[indexWeight];
    }

    // Using the set method to update user weight
    public void setWeight(double weight) {
        data[indexWeight] = weight;
    }

    // Constructor and other methods as previously defined
    public void setWeightAfterWeek(double weight) {
        setWeight(weight);
    }

    // Usage of the static method to show the list of users tracked with their name age and weight
    public static void menuAllPeopleStored() {
        //message printed to let user knwo what is going on
        System.out.println("List of all users:");
        for (Person user : Data.list) {
            System.out.println(user.getName() + " - Age: " + user.getAge() + " - Weight" + user.getWeight() + "kgs");
        }
    }

    // a new attribute created for exercise time
    private double exerciseTime;

    // Created to log the users workout and continue to add their new workout time to previous workout time
    public void logWorkout(double duration) {
        exerciseTime += duration;
    }

    //get method to retrieve the total exercise time
    public double getExerciseTime() {
        return exerciseTime;
    }


    // Compare person object using based on name and age if names are similar
    @Override
    public int compareTo(Person otherPerson) {
        // Compare based on name (case insensitive
        int nameComparison = this.getName().compareToIgnoreCase(otherPerson.getName());
        // using an if statement, if the names are not equal
        if (nameComparison != 0) {
            //return the name comparison
            return nameComparison;
        }

        // If names are the same, compare based on age
        return Integer.compare(this.getAge(), otherPerson.getAge());
    }
    private int totalWaterIntake;

    // logs the water intake for the user, uses parameter glass as the number of glasses of water consumed
    public void logWaterIntake(int glass) {
        // using and if statement; if the glass of water drank is greater than 0
        if (glass >= 0) {
            // then increment the total water intake by the provided amount
            totalWaterIntake += glass;
            // else statement if the number given was less than 0
        } else {
            //print message to display error if the input was less than 0
            System.out.println("Invalid water intake amount. Please enter a positive value.");
        }
    }
    // this will retrieve the total intake for a user and will reutrn the total intake in glasses.
    public int getTotalWaterIntake() {
        // return the total water intake for the specific user
        return totalWaterIntake;
    }

    // New attribute for calories log
    private int caloriesConsumed;

    // Log the calories consumed by the user
    public void logCalories(int calories) {
        // Check if the calories consumed is greater than 0
        if (calories > 0) {
            // Increment the total calories consumed
            caloriesConsumed += calories;
        } else {
            // Print an error message if the input is less than or equal to 0
            System.out.println("Invalid calories intake. Please enter a positive value.");
        }
    }
    // Get the total calories consumed by the user
    public int getTotalCaloriesConsumed() {
        // return total calories consumed
        return caloriesConsumed;
    }
    // equals methods
    @Override
    public boolean equals(Object o) {
        //will check if the compared object is the same as the object being compared to
        if (this == o) return true;
        // if compared object is null  or is from a different class
        // return false
        if (o == null || getClass() != o.getClass()) return false;
        // Case compared object to Person type
        Person person = (Person) o;
        //Start comparing the attributes for similarities
        // age must be the same
        return getAge() == person.getAge() &&
                // weight must be the same
                Double.compare(person.getWeight(), getWeight()) == 0 &&
                //exercuse time must be the same
                Double.compare(person.exerciseTime, exerciseTime) == 0 &&
                // name must be the same
                getName().equals(person.getName());
                //Objects.equals(getName(), person.getName());
    }
    //hashCode method
    @Override
    public int hashCode() {
        // will create a hash code based on the hash code attributes; name,age,weight,exercise time
        return Objects.hash(getName(), getAge(), getWeight(), exerciseTime);
    }
}