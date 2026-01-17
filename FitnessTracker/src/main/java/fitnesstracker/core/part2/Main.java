package fitnesstracker.core.part2;/*

 /*
 * CPSC 219 Final Project - Fitness Tracker
 * Esha Paul 30130069
 * Thi Truc Ngoc Tran 30140586
 * Tutorial T03
 * Professor Jonathon Hudson
 */

import java.util.ArrayList;
import java.util.List;
public class Main {
    /**
     * A main method is created to act as a starting point for the fitness tracker program
     * @param args Command line arguments passed to the program
     */
    public static void main(String[] args) {
        // an if statement is used to check if there are any command line arguments
        if (args.length > 0) {
            //if there are command line arguments, use the first argument as the file name
            String fileName = args[0];
            //create a new list to avoid modifying an existing list
            List<Person> dataList = new ArrayList<>();
            // load the data from CSV file using the file name and new data list
            Reader.loadDataCSV(fileName, dataList);
            // Assign to main list because we want to use this loaded data list
            Data.list.addAll(dataList);
        }
        // continue with the program by entering the menu loop
        Menu.menuLoop();
    }
}