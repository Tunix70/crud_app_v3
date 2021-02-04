package com.tunix70.javaio.view;

import com.tunix70.javaio.controller.RegionController;

import java.util.Scanner;

public class RegionView {
        private final RegionController regionController = new RegionController();
        public ConsoleView consoleView = new ConsoleView();
        private Scanner scanner;

        public void runner(){
                menu();
                selectMenu();
        }

        private void menu(){
                System.out.println("*** REGION MENU ***");
                System.out.println(" ================================= ");
                System.out.println("Choose next action:");
                System.out.println("1. CREATE\n2. READ ALL\n3. READ BY ID\n4. UPDATE\n5. DELETE\n6. RETURN");
        }

        private void selectMenu(){
                String input;
                scanner = new Scanner(System.in);
                while (true){
                        input = scanner.nextLine();
                        if(input.equals("1")){
                                regionView.runner();
                                break;
                        }else if(input.equals("2")){
                                regionController.getAll();
                                break;
                        }else if(input.equals("3")){
                                userView.runner();
                                break;
                        }else if(input.equals("4")){
                                System.out.println("*** Thanks for using our program ***");
                                break;
                        }else{
                                System.out.println("Please, enter numbers from 1 to 4");
                                menu();
                        }
                }
        }
}
