package com.tunix70.javaio.view;

import com.tunix70.javaio.controller.PostController;
import com.tunix70.javaio.controller.RegionController;
import com.tunix70.javaio.controller.UserController;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;
import com.tunix70.javaio.model.Role;
import com.tunix70.javaio.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
        private UserController userController = new UserController();
        private PostController postController = new PostController();
        private RegionController regionController = new RegionController();
        private Scanner scanner;

        public void runner() {
            menu();
            selectMenu();
        }

        private void menu() {
            System.out.println("\n*** USER MENU ***");
            System.out.println(" ================================= ");
            System.out.println("Choose next action:");
            System.out.println("1. CREATE\n2. READ ALL\n3. READ BY ID\n4. UPDATE\n5. DELETE\n6. RETURN");
        }

        private void selectMenu() {
            String input;
            scanner = new Scanner(System.in);
            while (true) {
                input = scanner.nextLine();
                if (input.equals("1")) {
                    userController.save(createNewUser());
                    System.out.println("New Post successfully entered");
                    runner();
                    break;
                } else if (input.equals("2")) {
                    userController.getAll();
                    runner();
                    break;
                } else if (input.equals("3")) {
                    System.out.println("Enter the User number");
                    Long num = Long.parseLong(scanner.nextLine());
                    System.out.println(userController.getById(num));
                    runner();
                    break;
                } else if (input.equals("4")) {
                    userController.update(updateUser());
                    System.out.println("User successfully updated");
                    runner();
                    break;
                } else if (input.equals("5")) {
                    System.out.println("Enter the User number to delete");
                    Long num = Long.parseLong(scanner.nextLine());
                    System.out.println(userController.getById(num));
                    runner();
                    break;
                } else if (input.equals("6")) {
                    ConsoleView.mainMenu();
                    break;
                } else {
                    System.out.println("Please, enter numbers from 1 to 6");
                    menu();
                }
            }
        }

        private User createNewUser() {
            PostController postController = new PostController();
            RegionController regionController = new RegionController();

            System.out.println("Enter first name User");
            String firstName = selectName();

            System.out.println("Enter last name User");
            String lastName = selectName();

            System.out.println(postController.getAll());
            List<Post> postList = selectPost();

            System.out.println(regionController.getAll());
            Region region = selectRegion();

            Role userRole = selectRole();

            User newUser = new User(null, firstName, lastName, postList, region, userRole);
            return newUser;
        }

        private User updateUser(){
            System.out.println("Enter the User number, which will be updated");
            Long num = Long.parseLong(scanner.nextLine());
            User editUser = userController.getById(num);
            System.out.println("Which parameter do you want to update:\n" +
                    "1. Update first name\n" +
                    "2. Update last name\n" +
                    "3. Update Post list\n" +
                    "4. Update Region\n" +
                    "5. Update Role" +
                    "6. Return");

            Long input = Long.parseLong(scanner.nextLine());
             while(true) {
                if (input == 1) {
                    System.out.println("Enter first name User");
                    editUser.setFirstName(selectName());
                    break;
                } else if (input == 2) {
                    System.out.println("Enter first name User");
                    editUser.setLastName(selectName());
                    break;
                } else if (input == 3) {
                    editUser.setPost(selectPost());
                    break;
                } else if (input == 4) {
                    editUser.setRegion(selectRegion());
                    break;
                } else if (input == 5) {
                    editUser.setRole(selectRole());
                    break;
                } else if (input == 6) {
                    selectMenu();
                    break;
                } else System.out.println("Please, enter numbers from 1 to 6");
            }
             return editUser;
        }

        private String selectName(){
            String name = scanner.nextLine();
            return name;
        }

        private List<Post> selectPost(){
            System.out.println("Enter the numbers of Posts the User's" +
                    "\n when you select numbers, please press " + 0);
            System.out.println(postController.getAll());
            List<Post> postList = new ArrayList<>();

            Long inputPost;
            scanner = new Scanner(System.in);
            while (true) {
                inputPost = Long.parseLong(scanner.nextLine());
                if(inputPost != 0) {
                    postList.add(postController.getById(inputPost));
                }else break;
            }
            return postList;
        }

        private Region selectRegion(){
            System.out.println("Enter the number of Region");
            Long inputRegion = Long.parseLong(scanner.nextLine());
            Region region = regionController.getById(inputRegion);
            return region;
        }

        private Role selectRole(){
            System.out.println("Enter the number of Role");
            System.out.println(
                    "1.    ADMIN,\n" +
                    "2.    MODERATOR,\n" +
                    "3.    USER");
            Long inputRole = Long.parseLong(scanner.nextLine());
            Role userRole;
            while(true) {
                if (inputRole == 1) {
                    userRole = Role.ADMIN;
                    break;
                } else if (inputRole == 2) {
                    userRole = Role.MODERATOR;
                    break;
                } else if (inputRole == 3) {
                    userRole = Role.USER;
                    break;
                } else System.out.println("Please, enter numbers from 1 to 3");
            }
            return userRole;
        }
    }
