package com.riaboshapka;

import com.riaboshapka.dao.impl.*;
import com.riaboshapka.services.ClientService;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.ProductService;
import com.riaboshapka.services.impl.ClientServiceImpl;
import com.riaboshapka.services.impl.OrderServiceImpl;
import com.riaboshapka.services.impl.ProductServiceImpl;
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
        ClientDBDao clientDBDao = new ClientDBDao();
        ProductDBDao productDBDao = new ProductDBDao();
        OrderDBDao orderDBDao = new OrderDBDao();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDBDao, validationService);
        ProductService productService = new ProductServiceImpl(productDBDao);
        OrderService orderService = new OrderServiceImpl(orderDBDao);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService, productService, orderService);
        ClientMenu clientMenu = new ClientMenu(br, clientService, productService, orderService);

        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
        System.out.println("Create testMaster branch for try to complete \"pull request\" for checking");
    }

}
