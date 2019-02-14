package com.riaboshapka.view;

import com.riaboshapka.dao.impl.DataBank;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Product;
import com.riaboshapka.services.OrderService;
import com.riaboshapka.services.impl.OrderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClientMenu {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private OrderService orderService = new OrderServiceImpl();


    public void show() throws IOException {

        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    showOrder();
                    break;
                case "3":
                    deleteOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input!!!");
            }
        }

    }

    private void showOrder() {
        System.out.println(DataBank.orderMap.values());
    }

    private void showMenu() {
        System.out.println("1. Create an order list");
        System.out.println("2. Get the order list");
        System.out.println("3. Delete the order list");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }


    private Product createProduct(int productNumber) {
        Product product = DataBank.productMap.get(productNumber);
        return product;
    }

    private List<Product> createProductsList() throws IOException {
        boolean isAdding = true;
        List<Product> productList = new ArrayList<>();
        showProductList();
        while (isAdding) {
            System.out.println("Choose product to add an order: ");
            int productNumber = Integer.parseInt(br.readLine());
            productList.add(createProduct(productNumber));
            System.out.println("Do you want to add next product?");
            System.out.println("Press \"y\" or \"n\" ");
            if (br.readLine().equals("n")) {
                isAdding = false;
            }

        }
        return productList;
    }

    private void createOrder() throws IOException {
        System.out.println("Enter clients ID: ");
        int clientID = Integer.parseInt(br.readLine());
        Client client = DataBank.clientMap.get(clientID);
        List<Product> productsList = createProductsList();
        orderService.createOrder(client, productsList);
    }

    private void showProductList() {
        System.out.println("List of products: ");
        for (int i = 0; i < DataBank.productMap.size(); i++) {
            String count = String.valueOf(i);
            System.out.println(count + DataBank.productMap.get(i));
        }
    }

    private void deleteOrder() {
        orderService.deleteOrder();
    }
}
