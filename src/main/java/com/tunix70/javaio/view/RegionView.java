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
                System.out.println("\n*** REGION MENU ***");
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
                                System.out.println(regionController.getAll());
                                runner();
                                break;
                        }else if(input.equals("3")){
                                System.out.println("Enter the Region number");
                                Long num = Long.parseLong(scanner.nextLine());
                                System.out.println(regionController.getById(num));
                                runner();
                                break;
                        }else if(input.equals("4")){
                                System.out.println("Enter the Region number, which will be updated");
                                Long num = Long.parseLong(scanner.nextLine());
//написать отдельные методы для подтверждения региона и поста
                                if(num != null) {
                                        System.out.println("Enter the name new Region");
                                        String newName = scanner.nextLine();
                                        regionController.update(new Region(num, newName));
                                        System.out.println("Region successfully updated");
                                }else {
                                        System.out.println("Region is not found, please enter existing Region");
                                }
                                runner();
                                break;
                        }else if(input.equals("5")){
                                System.out.println("Enter the Region number to delete");
                                Long num = Long.parseLong(scanner.nextLine());
                                System.out.println(regionController.getById(num));
                                runner();
                                break;
                        }else if(input.equals("6")){
                                ConsoleView.mainMenu();
                                break;
                        }else{
                                System.out.println("Please, enter numbers from 1 to 6");
                                menu();
                        }
                }
        }
}