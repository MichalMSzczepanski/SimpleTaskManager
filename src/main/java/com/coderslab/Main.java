package com.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final String[] options = {"add", "remove", "list", "quit"};

//        System.out.println("Please select an option");
//
//        for(String optionsString : options){
//            System.out.println(optionsString);
//        }

        // PATH TO FILE WITH TASKS

        Path dataSource = Paths.get("/Users/mike/Dropbox/01podstawyJava/ONL_JEE_W_04_Podstawy_Java/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");

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

            // TRANSFER STRINGS, VERSE BY VERSE INTO TABLE OF STRINGS AFTER SPLITTING THEM INTO SEPARATE STRINGS

            String[][] arrayOfTasks = new String[taskAmount][3];

            Path arrayCreation = Paths.get("/Users/mike/Dropbox/01podstawyJava/ONL_JEE_W_04_Podstawy_Java/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");

            Scanner arrayCreationScanner = new Scanner(arrayCreation);

            while(arrayCreationScanner.hasNextLine()){

                for (int i = 0; i < arrayOfTasks.length; i++) {
                    String taskLine = arrayCreationScanner.nextLine();
                    String[] taskLineArray = taskLine.split(", ");
                    for (int j = 0; j < arrayOfTasks[i].length; j++) {
                        arrayOfTasks[i][j] = taskLineArray[j];
                    }

                }

            }

            // CHOSE THE ACTION

            callOptions(options, taskAmount);

            Scanner userInput = new Scanner(System.in);

            while(userInput.hasNextLine()) {

                String choice = userInput.nextLine();

                switch (choice) {
                    case "add":

                        arrayOfTasks = Arrays.copyOf(arrayOfTasks, arrayOfTasks.length + 1);

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

                        callOptions(options, taskAmount);

                        break;

                    case "remove":
                        System.out.println("Please select task to remove");
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

                                callOptions(options, taskAmount);

                                break;

                            } else {
                                System.out.println("provided value is below zero or reaches beyond the array, try again");
                            }
                        }
                            break;

                    case "list":

                        for (int i = 0; i < arrayOfTasks.length; i++) {
                            String[] finalTaskArray = arrayOfTasks[i];
                            String finalString = String.join(", ", finalTaskArray);
                            System.out.println(i + " : " + finalString);
                        }

                        callOptions(options, taskAmount);

                }

                if (choice.equals("quit")) {

                    PrintWriter printWriter = new PrintWriter("/Users/mike/Dropbox/01podstawyJava/ONL_JEE_W_04_Podstawy_Java/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");
                    for (int i = 0; i < arrayOfTasks.length; i++) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < arrayOfTasks[i].length - 1; j++) {
                            stringBuilder.append(arrayOfTasks[i][j] + ", ");
                        }
                        stringBuilder.append(arrayOfTasks[i][2]);
                        printWriter.println(stringBuilder);
                    }

                    printWriter.close();

                    System.out.println();
                    System.out.println(ConsoleColors.RED + "any changes saved, bye!");
                    System.out.print(ConsoleColors.RESET);
                    System.out.println();
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("issue with file");
        }

    }

    private static void callOptions(String[] options, int taskAmount) {
        System.out.println();
        System.out.println(ConsoleColors.BLUE + "Please select an option");
        System.out.print(ConsoleColors.RESET);
        for(String optionsString : options){
            System.out.println(optionsString);
        }
        System.out.println("(the current number of registered tasks is: " + taskAmount + ")");
    }

}
