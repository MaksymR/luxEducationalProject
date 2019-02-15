package com.riaboshapka;

import com.riaboshapka.view.MainMenu;

import java.io.IOException;

public class App {

    // the starting point of the entire application
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
        System.out.println("classwork_15_02_2019");
    }

}
