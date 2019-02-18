package com.riaboshapka;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.dao.impl.ClientDaoImpl;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.validators.ValidationService;
import com.riaboshapka.validators.impl.ValidationServiceImpl;
import com.riaboshapka.view.AdminMenu;
import com.riaboshapka.view.ClientMenu;
import com.riaboshapka.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    // a start point of the entire application
    public static void main(String[] args) throws IOException {

        // configured all necessary dependencies for this application
        // realizing of the dependency injection
        ClientDao clientDao = ClientDaoImpl.getInstance();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService);
        ClientMenu clientMenu = new ClientMenu(br, clientService);

        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }

}
