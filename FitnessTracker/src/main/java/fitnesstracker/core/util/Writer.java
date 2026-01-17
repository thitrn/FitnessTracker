import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// this class will contain methods for both saving data in csv format and loading data in csv format
public class Writer {

    /**
     * This will save the data of a list of person objects to a CSV file
     *
     * @param fileName - the name of CSV file where data is saved
     * @param list     - list of person objects whose data is saved
     */
    public static void saveDataCSV(String fileName, List<Person> list) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // go through each person in the list using a foor each loop
            for (Person person : list) {
                // write persons data to CSV file fro name, age, weight etc
                writer.write((person.getName() + "," + person.getAge() + "," + person.getWeight() + "," +
                        person.getExerciseTime() + "," + person.getTotalWaterIntake() + ","
                        + person.getTotalCaloriesConsumed()));
            }

            // display a message that data has been saved successfully
        System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            // print stack trace in case of an exception
            e.printStackTrace();
        }
    }
}

