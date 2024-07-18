package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Habit;
import model.HabitList;

// A class representing an application that allows users to add and check off habits

// Certain methods from this class are based on code found in Lab 3: FlashCard Reviewer

public class HabitTracker {
    private Scanner scanner;
    private HabitList hl;
    private ArrayList<Habit> habits;
    
    // EFFECTS: creates HabitTracker console application
    public HabitTracker() {
        hl = new HabitList();
        scanner = new Scanner(System.in);
        printHabits(hl);
        getResponse();

    }

    //MODIFIES: this 
    //EFFECTS: Prints out the habits currently in the habit list along with their
    //corresponding check mark value. Informs the user if there are no habits added
    public void printHabits(HabitList hl) {
        habits = hl.getHabits();
        if (habits.isEmpty()) {
            divider(); 
            System.out.println("You currently don't have any habits to display");
            divider();
        } else {
            divider(); 
            System.out.println("Your added habits are:");
            for (Habit hab : habits) {
                System.out.println(hab.getHabitAndMark()); 
            }
            divider(); 
        }

    }

    //EFFECTS: Displays the menu and processes the response from the user
    public void getResponse() {
        displayOptions();
        String response = this.scanner.nextLine();
        getSelectedOption(response);

    }

    //EFFECTS: Displays the options the user has access to
    public void displayOptions() {
        System.out.println("What would you like to do?");
        System.out.println("Add: add a new habit to the tracker");
        System.out.println("Delete: delete a habit from the habit tracker");
        System.out.println("Complete: mark a habit as complete");
        System.out.println("Incomplete: mark a habit as incomplete");
        System.out.println("Streak: view the number of days a habits been marked as complete");
        System.out.println("Quit: quit the application");
        divider();
    }

    //EFFECTS: Runs method corresponding to option chosen by user
    public void getSelectedOption(String input) {
        if (input.equals("Add")) {
            addNewHabit();

        } else if (input.equals("Delete")) {
            deleteHabit();
        } else if (input.equals("Complete")) {
            completeHabit();
        } else if (input.equals("Incomplete")) {
            incompleteHabit();
        } else if (input.equals("Streak")) {
            viewStreak();
        } else if (input.equals("Quit")) {

            quitApp();
        } else {
            System.out.println("Not an available option.");
            printHabits(hl);
            getResponse();
        }
    }

    //MODIFIES: this
    //EFFECTS: Adds and displays a new habit to the habit tracker
    public void addNewHabit() {
        System.out.println("What would you like to name your habit?");
        String name = scanner.nextLine();
        Habit h = new Habit(name);
        hl.addHabit(h);
        System.out.println("Habit successfully added!");
        printHabits(hl);
        getResponse();
    }

    //MODIFIES: this
    //EFFECTS: Removes a habit from the habit tracker
    //If habit does not exist, alerts the user. 
    public void deleteHabit() {
        System.out.println("What habit would you like to delete?");
        String name = scanner.nextLine();
        Habit h = hl.findHabit(name);
        if (h == null) {
            System.out.println("Invalid input");
            printHabits(hl);
            getResponse();
        } else {
            hl.removeHabit(h);
            System.out.println("Habit successfully removed!");
            printHabits(hl);
            getResponse();
        }

    }

    //EFFECTS: stops running the console application
    public void quitApp() {
        System.out.println("Goodbye");
        System.exit(0);
    }

    //MODIFIES: this, Habit
    //EFFECTS: marks the habit as complete and adds a checkmark.  
    //If habit does not exist or has already been completed, alerts the user
    public void completeHabit() {
        System.out.println("What habit would you like to mark as complete?");
        String name = scanner.nextLine();
        Habit hab = hl.findHabit(name);
        if (hab == null) {
            System.out.println("Invalid input");
        } else if (hab.getHabitStatus()) {
            System.out.println("This habit has already been marked as complete");
        } else {
            hab.markHabitAsComplete();
            System.out.println("Habit successfully marked as completed");
        }
        printHabits(hl);
        getResponse();
    }

    //MODIFIES: this, Habit
    //EFFECTS: marks habit as incomplete and removes the checkmark.
    //If habit does not exist or is already marked as incomplete, alerts the user
    public void incompleteHabit() {
        System.out.println("What habit would you like to mark as incomplete?");
        String name = scanner.nextLine();
        Habit hab = hl.findHabit(name);
        if (hab == null) {
            System.out.println("Invalid input");
        } else if (hab.getHabitStatus() == false) {
            System.out.println("This habit is already marked as incomplete");
        } else {
            hab.markHabitAsIncomplete();
            System.out.println("Habit successfully marked as incompleted");
        }
        printHabits(hl);
        getResponse();
    }

    //EFFECTS: Displays the number of days a habit has been completed
    //If habit does not exist, alerts the user
    public void viewStreak() {
        System.out.println("What habits streak would you like to view?");
        String name = scanner.nextLine();

        Habit hab = hl.findHabit(name);
        if (hab == null) {
            System.out.println("Invalid Input");
        } else {
            int days = hab.getDaysCompleted();
            System.out.println("You have have completed this habit for " + days
                    + " days. Great job!");
        }
        printHabits(hl);
        getResponse();
    }

    //EFFECTS: Divider for separating what is displayed on the console. 
    private void divider() {
        System.out.println("------------------------------------");
    }


}
