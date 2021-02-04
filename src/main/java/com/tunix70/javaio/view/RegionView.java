package com.tunix70.javaio.view;

import com.tunix70.javaio.controller.RegionController;
import com.tunix70.javaio.model.Region;

import java.util.Scanner;

public class RegionView {
        private final RegionController regionController = new RegionController();
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
                                System.out.println("Enter new name Region");
                                String name = scanner.nextLine();
                                regionController.save(new Region(null, name));
                                System.out.println("New Region successfully entered");
                                runner();
                                break;
                        }else if(input.equals("2")){
                                regionController.getAll();
                                runner();
                                break;
                        }else if(input.equals("3")){
                                enterPick();
                                regionController.getById(enterPick());
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

        private Long enterPick(){
                System.out.println("Enter ");
                return null;
        }
}
