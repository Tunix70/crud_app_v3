package com.tunix70.javaio.view;

import com.tunix70.javaio.controller.PostController;
import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.model.PostStatus;
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
                    System.out.println("Enter new content Post");
                    String content = scanner.nextLine();
                    postController.save(new Post(null, content, null, null, selectRole()));
                    System.out.println("New Post successfully entered");
                    runner();
                    break;
                } else if (input.equals("2")) {
                    System.out.println(postController.getAll());
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
                    System.out.println("Enter the Role of the new Post");
                    editPost.setPostStatus(selectRole());
                    System.out.println("Post successfully updated");
                    runner();
                    break;
                } else if (input.equals("5")) {
                    System.out.println("Enter the Post number to delete");
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

    private PostStatus selectRole(){
        System.out.println("Enter the number of Role");
        System.out.println("1.    ACTIVE,\n" +
                           "2.    DELETED.\n");
        Long inputRole = Long.parseLong(scanner.nextLine());
        PostStatus writerPostStatus;
        while(true) {
            if (inputRole == 1) {
                writerPostStatus = PostStatus.ACTIVE;
                break;
            } else if (inputRole == 2) {
                writerPostStatus = PostStatus.DELETED;
                break;
            } else System.out.println("Please, enter numbers from 1 to 3");
        }
        return writerPostStatus;
    }
}
