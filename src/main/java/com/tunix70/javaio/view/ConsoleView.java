package com.tunix70.javaio.view;

import com.tunix70.javaio.controller.PostController;
import com.tunix70.javaio.controller.RegionController;
import com.tunix70.javaio.controller.UserController;
import com.tunix70.javaio.model.Region;

import java.util.Scanner;

public class ConsoleView {
        private RegionView regionView = new RegionView();
       public Scanner scanner;

       public void startApp(){
           mainMenu();
           selectMenu();
       }

       private void mainMenu(){
           System.out.println("*** MAIN MENU ***");
           System.out.println(" ================================= ");
           System.out.println("Choose next action:");
           System.out.println("1. USER\n2. POST\n3. REGION\n4. EXIT");
       }

       private void selectMenu(){
           String input;
           scanner = new Scanner(System.in);
           while (true){
               input = scanner.nextLine();
               if(input.equals("1")){
                   regionView.runner();
                   continue;
               }else if(input.equals("2")){
                   regionView.runner();
                   continue;
               }else if(input.equals("3")){
                   regionView.runner();
                   continue;
               }else if(input.equals("4")){
                   System.out.println("Thanks for using our program");
               }else{
                   System.out.println("Please, enter numbers from 1 to 4");
                   mainMenu();
               }
           }
       }

}
