package com.riaboshapka.view;

/*
 * menu of communication and output information to the administrator and the client
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private AdminMenu adminMenu = new AdminMenu();
    private ClientMenu clientMenu = new ClientMenu();

    public void showMenu() throws IOException {

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");

            switch (br.readLine()) {
                case "1":
                    adminMenu.show();
                    break;
                case "2":
                    System.out.println("SHOW CLIENT MENU");
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!!!");
                    break;
            }
        }

    }

}
