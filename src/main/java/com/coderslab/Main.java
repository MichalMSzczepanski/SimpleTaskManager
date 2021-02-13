package com.coderslab;

import java.io.IOException;
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

            int taskAmount = 0;

            while(dataSourceInput.hasNextLine()){
                String taskName = dataSourceInput.nextLine(); // dodane bo inaczej nie wyswietlal sie napis "the number or registered tasks..."
                taskAmount++;
            }

            System.out.println("(the number of registered tasks is: " + taskAmount + ")");

            // TRANSFER STRINGS, VERSE BY VERSE INTO TABLE OF STRINGS AFTER SPLITING THEM INTO SEPARATE STRINGS

            String[][] arrayOfTasks = new String[taskAmount][3];

            Path arrayCreation = Paths.get("/Users/mike/Dropbox/01podstawyJava/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");

            Scanner arrayCreationScanner = new Scanner(arrayCreation);

            while(arrayCreationScanner.hasNextLine()){

                for (int i = 0; i < arrayOfTasks.length; i++) {
                    String taskLine = arrayCreationScanner.nextLine();
                    String[] taskLineArray = taskLine.split(",");
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

                switch (choice){
                    case "add":
                        System.out.println("chose add");
                        break;
                    case "remove":
                        System.out.println("chose remove");
                        break;
                    case "list":
                        listTasks();
                }

                if(choice.equals("quit")){
                    System.out.println();
                    System.out.println("bye!");
                    System.out.println();
                    break;
                }

                System.out.println();
                System.out.println("Please select an option");
                for(String optionsString : options){
                    System.out.println(optionsString);
                }

            }

        } catch (IOException e) {
            System.out.println("issue with file");
        }

    }

    public static void listTasks(){
        Path dataSource = Paths.get("/Users/mike/Dropbox/01podstawyJava/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");
        try(Scanner choiceListTasks = new Scanner(dataSource)) {
            while (choiceListTasks.hasNextLine()) {
                String task = choiceListTasks.nextLine();
                System.out.println(task);
            }
        } catch (IOException e){
            System.out.println("file not found");
        }

    }

}
