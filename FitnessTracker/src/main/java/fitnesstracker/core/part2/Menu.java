package fitnesstracker.core.part2;

/*
 * CPSC 219 Final Project - Fitness Tracker
 * Esha Paul 30130069
 * Thi Truc Ngoc Tran 30140586
 * Tutorial T03
 * Professor Jonathon Hudson
 */

import java.util.Scanner;
import fitnesstracker.core.data.*;
import fitnesstracker.core.util.*;

public class Menu {
    //Initialize a scanner to get input from user
    private static final Scanner scanner = new Scanner(System.in);
    // Create a message to display the menu options
    private static final String optMessage = """
            Welcome to fitness tracker!
            \tMenu (options)
            \t1. Enter a name of new user:
            \t2. Enter weight:
            \t3. Enter exercise duration :
            \t4. Enter new weight after 1st week:
            \t5. Enter water intake:
            \t6. Enter calories consumed:
            \t7. Print all people store:
            \t8. Print progress:
            \t9. Save data to CSV
            \t10 Load data from CSV
            \t11. Exit
             """;

    // Created as the menu loop
    public static void menuLoop() {
        // Displays the menu options to the user
        System.out.println(optMessage);
        // This line will read the user input
        String choice = scanner.nextLine();
        // The users input will be read and converted into an integer
        int option = Integer.valueOf(choice);
        // A while loop is created to loop until the user selects one of the menu
        //options other than 11. 11 exits the menu
        while (option != 11) {
            //6 switches
            switch (option) {
                // call this function when a new user is entered
                case 1 -> menuEnterNewUser();
                // call this function when entering weight
                case 2 -> menuEnterWeight();
                // call this function when entering exercise time
                case 3 -> menuEnterExerciseTime();
                // call this function when entering weight after a week
                case 4 -> menuEnterWeightAfterWeek();
                // call this function when entering water intake per glass
                case 5 -> menuEnterWaterIntake();
                // call this function when counting calories
                case 6-> menuEnterCalories();
                // call this function to see all the people stored as users
                case 7 ->menuAllPeopleStored();
                // call this function to see a users progress
                case 8 -> menuShowProgress();
                // call this function to save data to csv file
                case 9 -> menuSaveDataCSV();
                // call function to load data to csv file
                case 10 -> menuLoadDataCSV();
                default -> //exit switch
                        System.out.printf("Option %d no recognized!%n", option);
            }
            // Display menu options again
            System.out.println(optMessage);
            // Will read the next input given by the user
            choice = scanner.nextLine();
            // The users new input will be read and converted into an integer
            option = Integer.parseInt(choice);
        }
        // Display message when exiting the program (7)
        System.out.println("Thanks for using this program. \nBye!");
    }

    private static void menuSaveDataCSV() {
        System.out.println("Enter the name of the CSV file to save data: ");
        String fileName = scanner.nextLine();
        Writer.saveDataCSV(fileName, Data.getList());
    }

    private static void menuLoadDataCSV() {
        System.out.println("Enter the name of the CSV file to load data from: ");
        String fileName = scanner.nextLine();
        Reader.loadDataCSV(fileName, Data.getList());
    }

    /**
     * This function displays the progress of a users exercise, including their name and total exercise time. It
     * will prompt the user to enter the name of the user they would like to see progress for
     *
     * @return of the username entered by the user
     */
    private static String menuShowProgress() {
        // Input message displayed to enter username
        System.out.println("Enter the name of the user you would like to see progress for.");
        // Read the user's input as the username
        String userName = scanner.nextLine();
        //Find the user by their usernames
        Person user = Data.findUserFromName(userName);
        // If statement to check if username was entered
        if (user != null) {
            //Display the user's name and total exercise time and total water intake
            System.out.println(user.getName() + " - Total Exercise Time: " + user.getExerciseTime() + " in minutes" +
                    " Total Water Intake: " + user.getTotalWaterIntake() + " glass(s)" + " Total Calories Consumed: "
                    + user.getTotalCaloriesConsumed());
            // if no username was entered, enter else statement
        } else {
            //Notify the user if the specified user is not found
            System.out.println("User not found");
        }
        // return username
        return userName;
    }

    /**
     * This function prompts the user enter the name of the specific user and their age which adds them to the list
     * The name and age of the user are entered through standard input
     * The users name and age is read, a new Person object is created which then adds the user to the list
     * To store the user, a HashMap lookup is used with age as the key
     */
    private static void menuEnterNewUser() {
        // Input message displayed to enter username
        System.out.println("Enter the name of the new user");
        // Read the user's name
        String name = scanner.nextLine();
        // Input message displayed to enter the age of user
        System.out.println("Enter the age of the new user");
        // Read the user's age
        int age = Integer.parseInt(scanner.nextLine());
        // Create a new Person object
        Person newPerson = new Person(name, age);
        //Add the new user to the list
        Data.list.add(newPerson);
        // Add the new user to the lookup
        Data.lookup.put(age, newPerson);
        // Print message that user has been added
        System.out.println("User has been successfully added!");
    }

    /**
     * This function allows the user to log exercise duration for a specific user. Will be prompted to enter
     * the name of the user that details will be logged for then the time spent exercising.
     * An if else statement is applied to determine failure/success
     */
    private static void menuEnterExerciseTime() {
        // Input message displayed to enter username
        System.out.println("Enter the name of the user you are logging exercise time for");
        // read the users name
        String userName = scanner.nextLine();
        // Find the user using their name
        Person user = Data.getUserFromName(userName);

        // enter loop if a name is given
        if (user != null) {
            // display a input message to the user to input time spent exercising in minutes
            System.out.println("Enter the time spent exercising in minutes");
            // read exercise time in minutes
            double exerciseTime = Double.parseDouble(scanner.nextLine());
            // log workout duration for user
            user.logWorkout(exerciseTime);
            // Print output message for user and the time spent working out
            System.out.println("Exercise time recorded for " + userName + ".");
        } else {
            // if no user name was entered, print out user not found
            System.out.println("User not found.");
        }
    }

    /**
     * This function allows a user to log the weight of a specific user. User will be prompted to enter the name
     * for the person they would like to enter weight for in kgs. An output message will be displayed with the name and
     * weight of the user. An if else statement is used to determine failure/success
     */
    private static String menuEnterWeight() {
        System.out.println("Enter the name of the user you are logging weight for");
        String userName = scanner.nextLine();
        System.out.println("Enter the weight (in kg):");
        double weight = Double.parseDouble(scanner.nextLine());
        double updatedWeight = Data.setWeight(userName, weight);
        String output;
        if (updatedWeight != -1) {
            output= ("New weight recorded for " + userName + ". Updated weight is now " + updatedWeight + "kgs");
        } else {
            output=("User not found");
        }
        return output;
    }

    /**
     * This function allows the user to enter their new weight of a specific user after a week and provides feedback
     * on their progress.
     *
     * @return An empty string message is returned indicating if the progress or if the user is not found a
     * message will be displayed
     */
    private static String menuEnterWeightAfterWeek() {
        // prompt the user to enter the name of the user for weight updates
        System.out.println("Enter the name of the user for weight update after a week");
        // read the users input for the username
        String userName = scanner.nextLine();
        // prompt the user to input the new weight after a week
        System.out.println("Enter the new weight after one week (in kg):");
        // read input for new weight
        double newWeight = Double.parseDouble(scanner.nextLine());
        //Find user by name using the data class file
        Person user = Data.getUserFromName(userName);
        // Use if statement to check if the user is found
        if (user != null) {
            // get user initial weight
            double initialWeight = user.getWeight();
            // set new weight
            user.setWeightAfterWeek(newWeight);
            // get updated weight after setting it
            double updatedWeight = user.getWeight();
            // Use if conditional to check if the new weight is larger than your initial weight for feedback
            if (newWeight > initialWeight) {
                // if new weight is greater than inital weight then feedback will be returned to increase exercise
                System.out.println("Increase exercise duration");
                // else if conditional used if the new weight is less than initial weight is less than display keep going
            } else if (newWeight < initialWeight) {
                // display message
                System.out.println("Keep going!");
                // else conditional to check if weight did not change
            } else {
                // display feedback message
                System.out.println("Weight remained the same.");
            }
            // else conditional for outer statement if no user found
        } else {
            // display message
            System.out.println("User not found.");
        }

        // return an empty string to indicate completion
        return "";
    }

    /**
     * This function asks the user to enter a name of the user they want to track water intake for
     * It will then log the intake of water in glasses for the specific user
     */
    private static void menuEnterWaterIntake() {
        // Input message for the user to enter the name of the user to track water intake
        System.out.println("Enter the name of the user to track water intake");
        // Read the user's input for the username
        String userName = scanner.nextLine();
        // Find the user's input name for the username
        Person user = Data.getUserFromName(userName);

        // using an if statement to check if the user is found
        if (user != null) {
            //asks the user to enter the amount of water the user drank per glass
            System.out.println("Enter the amount of water you drank (per glass)");
            int waterIntake;

            // loops asking for input until a valid integer is entered
            while (true) {
                try {
                    // Read the user's input for water intake using the Scanner
                    waterIntake = Integer.parseInt(scanner.nextLine());
                    break; // Exit the loop if parsing is successful
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }

            // Log the water intake for the specific user
            user.logWaterIntake(waterIntake);
            // print statement to record water intake for user
            System.out.println("Water intake recorded for " + userName + ".");
        } else {
            // print statement to display the message if the user is not found
            System.out.println("User not found");
        }
    }

    /**
     * This function allows the user to log the calories consumed by a specific user.
     * The user will be prompted to enter the name and the calories consumed.
     */
    private static void menuEnterCalories() {
        // Input message displayed to enter username
        System.out.println("Enter the name of the user for calories log");
        // read the user's input for the username
        String userName = scanner.nextLine();
        // Find the user using their name
        Person user = Data.getUserFromName(userName);

        // enter loop if a name is given
        if (user != null) {
            // display an input message to the user to input calories consumed
            System.out.println("Enter the calories consumed:");
            // read calories consumed
            int calories = Integer.parseInt(scanner.nextLine());
            // log calories for user
            user.logCalories(calories);
            // Print output message for user and the calories consumed
            System.out.println("Calories recorded for " + userName + ".");
        } else {
            // if no username was entered, print out user not found
            System.out.println("User not found.");
        }
    }

    /**
     * This function displays a list of all the users stored within the program with their age and current weight
     * a for each loop is used to go through the list and an output message is displayed at the end with users name age \
     * weight
     */
    private static void menuAllPeopleStored() {
        // Prints out a message to inform the user about the purpose of output message.
        System.out.println("List of all users stored in program with age and their current weight");
        // A for each loop is created to iterate through each person in the list and display their information
        for (Person user : Data.list) {
            // Get name
            String name = user.getName();
            // Get age
            int age = user.getAge();
            // Get weight
            double weight = user.getWeight();
            // Print output messgage with all information of stored users with their assosiated weight and age
            System.out.println(name + " - Age:" + age + "- Weight: " + weight + "kgs");
        }
    }

}
