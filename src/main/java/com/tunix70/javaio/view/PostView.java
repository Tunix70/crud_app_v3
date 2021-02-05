package com.tunix70.javaio.view;

import com.tunix70.javaio.controller.PostController;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.Region;

import java.util.Scanner;

public class PostView {
    private PostController postController = new PostController();
    private Scanner scanner;

    public void runner() {
        menu();
        selectMenu();
    }

    private void menu() {
        System.out.println("\n*** POST MENU ***");
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
                System.out.println("Enter new name Post");
                String name = scanner.nextLine();
                postController.save(new Post(null, name, null, null));
                System.out.println("New Post successfully entered");
                runner();
                break;
            } else if (input.equals("2")) {
                postController.getAll();
                runner();
                break;
            } else if (input.equals("3")) {
                System.out.println("Enter the Post number");
                Long num = Long.parseLong(scanner.nextLine());
                System.out.println(postController.getById(num));
                runner();
                break;
            } else if (input.equals("4")) {
                System.out.println("Enter the Post number, which will be updated");
                Long num = Long.parseLong(scanner.nextLine());
                Post editPost = postController.getById(num);
                System.out.println("Enter the text of the new Post");
                String newText = scanner.nextLine();
                editPost.setContent(newText);
                postController.update(editPost);
                System.out.println("Post successfully updated");
                runner();
                break;
            } else if (input.equals("5")) {
                System.out.println("Enter the Post number");
                Long num = Long.parseLong(scanner.nextLine());
                System.out.println(postController.getById(num));
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
}
