package fitnesstracker.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import fitnesstracker.core.data.*;
import fitnesstracker.core.util.*;

public class Reader {

    /**
     * This method loads data from the CSV file and then creates a list of Person objects
     *
     * @param fileName - the name of CSV file where data is saved
     * @param list     - list of person objects whose data is saved
     */
    public static void loadDataCSV(String fileName, List<Person> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // go through each line in the CSV file
            while ((line = reader.readLine()) != null) {
                // split the line into an array of data using comma separators
                String[] data = line.split(",");
                try {
                    // get data from the array at different indices
                    // get name at indices 0
                    String name = data[0];
                    // get age at indices 1
                    int age = Integer.parseInt(data[1]);
                    // get weight at indices 2
                    double weight = Double.parseDouble(data[2]);
                    // get exercise time at indices 3
                    double exerciseTime = Double.parseDouble(data[3]);
                    // get total water intake at indices 4
                    int totalWaterIntake = parseInteger(data[4]);
                    // get total calories at indices 5
                    int totalCaloriesConsumed = parseInteger(data[5]);
                    // create a new Person object and set the attributes
                    Person person = new Person(name, age);
                    // set the weight
                    person.setWeight(weight);
                    // set the exercise time
                    person.logWorkout(exerciseTime);
                    // set the total water intake
                    person.logWaterIntake(totalWaterIntake);
                    // set total calories
                    person.logCalories(totalCaloriesConsumed);
                    // add Person object to the list
                    list.add(person);
                } catch (NumberFormatException e) {
                    // Handle the case where a number couldn't be parsed
                    System.out.println("Error parsing data in line: " + line);
                    e.printStackTrace();
                }
            }
            // display a message to notify the user that the data has been loaded successfully
            System.out.println("Data loaded from " + fileName);
        } catch (IOException e) {
            // print stack trace in case of an exception
            e.printStackTrace();
        }
    }

    // Custom method to parse an integer with additional handling for non-numeric input
    private static int parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Handle the case where a number couldn't be parsed
            System.out.println("Error parsing integer: " + input);
            e.printStackTrace();
            return 0; // You may want to provide a default value or handle this differently
        }
    }
}
