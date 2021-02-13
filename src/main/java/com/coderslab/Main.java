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

        Path dataSource = Paths.get("/Users/mike/Dropbox/01podstawyJava/warsztat/05_attachment_Zasoby do projektu.pl/tasks.csv");

        if(!Files.exists(dataSource)){
            System.out.println("ALERT - file not found");
        }

        try(Scanner dataSourceInput = new Scanner(dataSource)){

//            int taskAmount = 0;

//            while(dataSourceInput.hasNextLine()){
//                taskAmount++;
//            }

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
                        System.out.println("chose list");
                        while(dataSourceInput.hasNextLine()){
                            String task = dataSourceInput.nextLine();
                            System.out.println(task);
                        }
                    default:
                        System.out.println("please select a correct choice");
                }

                if(choice.equals("quit")){
                    System.out.println("bye!");
                    break;
                }

            }

        } catch (IOException e) {
            System.out.println("issue with file");
        }

    }

}
