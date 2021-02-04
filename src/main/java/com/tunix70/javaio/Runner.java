package com.tunix70.javaio;

import com.tunix70.javaio.controller.PostController;

import com.tunix70.javaio.model.Post;
import com.tunix70.javaio.view.ConsoleView;


import java.io.IOException;


public class Runner {
    public static void main(String[] args) throws IOException {
        ConsoleView consoleView = new ConsoleView();
        consoleView.startApp();

    }
}
