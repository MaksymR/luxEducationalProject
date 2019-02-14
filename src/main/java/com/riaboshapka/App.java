package com.riaboshapka;

import com.riaboshapka.view.MainMenu;

import java.io.IOException;

public class App {

    // the starting point of the entire application
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
        System.out.println("Create testMaster branch for try to complete \"pull request\" for checking");
    }

}
