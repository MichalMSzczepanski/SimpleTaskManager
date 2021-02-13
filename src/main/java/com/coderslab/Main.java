package com.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final String[] options = {"add", "remove", "list", "quit"};

        System.out.println("Please select an option");

        for(String optionsString : options){
            System.out.println(optionsString);
        }

        // PATH TO FILE WITH TASKS

        Path dataSource = Paths.get("/Users/mike/Dropbox/01podstawyJava/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");

        if(!Files.exists(dataSource)){
            System.out.println("ALERT - file not found");
        }

        try(Scanner dataSourceInput = new Scanner(dataSource)){

            // COUNT THE NUMBER OF TASKS AT THE MOMENT

            int taskAmount = 0;

            while(dataSourceInput.hasNextLine()){
                String taskName = dataSourceInput.nextLine(); // dodane bo inaczej nie wyswietlal sie napis "the number or registered tasks..."
                taskAmount++;
            }

            System.out.println("(the number of registered tasks is: " + taskAmount + ")");


            // TRANSFER STRINGS, VERSE BY VERSE INTO TABLE OF STRINGS AFTER SPLITTING THEM INTO SEPARATE STRINGS

            String[][] arrayOfTasks = new String[taskAmount][3];

            Path arrayCreation = Paths.get("/Users/mike/Dropbox/01podstawyJava/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");

            Scanner arrayCreationScanner = new Scanner(arrayCreation);

            while(arrayCreationScanner.hasNextLine()){

                for (int i = 0; i < arrayOfTasks.length; i++) {
                    String taskLine = arrayCreationScanner.nextLine();
                    String[] taskLineArray = taskLine.split(","); // french 2020 true
                    for (int j = 0; j < arrayOfTasks[i].length; j++) {
                        arrayOfTasks[i][j] = taskLineArray[j];
//                        System.out.println(arrayOfTasks[i][j]); // CHECKING IF THIS WORKS
                    }

                }

            }

            // CHOSE THE ACTION

            Scanner userInput = new Scanner(System.in);

            while(userInput.hasNextLine()) {

                String choice = userInput.nextLine();

                switch (choice) {
                    case "add":

                        arrayOfTasks = Arrays.copyOf(arrayOfTasks, arrayOfTasks.length + 1);

//                       // PRINT ALL TASKS TO VERIFY IF NEW LINE FOR NEW TASK ADDED
//
//                        for (int i = 0; i < arrayOfTasks.length; i++) {
//                            for (int j = 0; j < arrayOfTasks[i].length; j++) {
//                                System.out.print(arrayOfTasks[i][j] + " ");
//                            }
//                            System.out.println();
//                        }

                        String[] addedTask = new String[3];

                        // description
                        System.out.println("Please add task description");
                        addedTask[0] = userInput.nextLine();
                        // due date
                        System.out.println("Please add task due date");
                        addedTask[1] = userInput.nextLine();
                        // true/ false
                        System.out.println("Please add task priority");
                        addedTask[2] = userInput.nextLine();

                        arrayOfTasks[arrayOfTasks.length -1 ] = addedTask;

                        System.out.println("your added task is: " + Arrays.toString(arrayOfTasks[arrayOfTasks.length -1])); // validate if task was added

//                         PRINT ALL TASKS TO VERIFY IF NEW LINE FOR NEW TASK ADDED

//                        for (int i = 0; i < arrayOfTasks.length; i++) {
//                            for (int j = 0; j < arrayOfTasks[i].length; j++) {
//                                System.out.print(arrayOfTasks[i][j] + " ");
//                            }
//                            System.out.println();
//                        }

                        break;

                    case "remove":
                        System.out.println("Please select tak to remove");
                        Scanner taskToBeRemoved = new Scanner(System.in);
                        while(taskToBeRemoved.hasNextLine()) {
                            while (!taskToBeRemoved.hasNextInt()) {
                                taskToBeRemoved.next();
                                System.out.println("provided value is not a number, try again");
                                break;
                            }

                            int taskToRemove = taskToBeRemoved.nextInt();

                            if (taskToRemove >= 0 && taskToRemove < arrayOfTasks.length) {
                                arrayOfTasks = ArrayUtils.remove(arrayOfTasks, taskToRemove);
                                System.out.println("removed task number " + taskToRemove);
                                break;
                            } else {
                                System.out.println("provided value is below zero or reaches beyond the array, try again");
                            }
                        }
                            break;

                    case "list":

                        for (int i = 0; i < arrayOfTasks.length; i++) {
                            System.out.print(i + " : ");
                            for (int j = 0; j < arrayOfTasks[i].length; j++) {
                                System.out.print(arrayOfTasks[i][j] + " ");
                            }
                            System.out.println();
                        }
                }

                if (choice.equals("quit")) {



                    System.out.println();
                    System.out.println("any changes saved, bye!");
                    System.out.println();
                    break;
                }
            }

                System.out.println();
                System.out.println("Please select an option");
                for(String optionsString : options){
                    System.out.println(optionsString);
                }



        } catch (IOException e) {
            System.out.println("issue with file");
        }

    }


    // METHOD LISTING TASKS

    public static void listTasks(){
        // PRINTING TASKS FROM CSV FILE

//        Path dataSource = Paths.get("/Users/mike/Dropbox/01podstawyJava/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");
//        try(Scanner choiceListTasks = new Scanner(dataSource)) {
//            int taskCounter = 1;
//            while (choiceListTasks.hasNextLine()) {
//                String task = choiceListTasks.nextLine();
//                System.out.println(taskCounter + " : " + task);
//                taskCounter++;
//            }
//        } catch (IOException e){
//            System.out.println("file not found");
//        }

    }

    // METHOD ADDING TASK

    public static void addTask(){



    }

}
