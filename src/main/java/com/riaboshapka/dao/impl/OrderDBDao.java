package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.OrderDao;
import com.riaboshapka.domain.Client;
import com.riaboshapka.domain.Order;
import com.riaboshapka.domain.Product;

import static com.riaboshapka.dao.impl.DataForConnectionToH2DB.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDao implements OrderDao {

    public OrderDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS ORDERS(ID BIGINT DEFAULT 1 PRIMARY KEY AUTO_INCREMENT," +
                            " CLIENT_ID BIGINT DEFAULT 1, CLIENT_NAME VARCHAR(20) DEFAULT NULL," +
                            " CLIENT_SURNAME VARCHAR(20) DEFAULT NULL, CLIENT_AGE INT DEFAULT 0," +
                            " CLIENT_PHONE VARCHAR(20) DEFAULT NULL, CLIENT_EMAIL VARCHAR(50) DEFAULT NULL," +
                            " PRODUCT_ID BIGINT DEFAULT 1, PRODUCT_NAME VARCHAR(20) DEFAULT NULL," +
                            " PRODUCT_PRICE DECIMAL DEFAULT NULL)"
            );
        } catch (SQLException e) {
            System.out.println("SOMETHING IS GOING WRONG!!!");
        }
    }

    @Override
    public boolean saveOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO ORDERS (CLIENT_ID, CLIENT_NAME, CLIENT_SURNAME, CLIENT_AGE, CLIENT_PHONE, CLIENT_EMAIL," +
                             " PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            System.out.println("Saving.... Please wait");
            for (Product product : order.getProducts()) {
                Client client = order.getClient();
                statement.setLong(1, client.getId());
                statement.setString(2, client.getName());
                statement.setString(3, client.getSurname());
                statement.setInt(4, client.getAge());
                statement.setString(5, client.getPhone());
                statement.setString(6, client.getEmail());
                statement.setLong(7, product.getId());
                statement.setString(8, product.getName());
                statement.setBigDecimal(9, product.getPrice());
                statement.execute();
            }
            System.out.println("Order Saved: " + "Order{" +
                    "client=" + order.getClient() +
                    ", products=" + order.getProducts() +
                    '}');
        } catch (SQLException e) {
            System.out.println("SOMETHING OF SAVING WAS GOING WRONG!!!");
        }
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> resultOrdersList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS")) {
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                long clientId = resultSet.getLong("CLIENT_ID");
                String clientName = resultSet.getString("CLIENT_NAME");
                String clientSurname = resultSet.getString("CLIENT_SURNAME");
                int clientAge = resultSet.getInt("CLIENT_AGE");
                String clientPhone = resultSet.getString("CLIENT_PHONE");
                String clientEmail = resultSet.getString("CLIENT_EMAIL");
                Client client = new Client(clientId, clientName, clientSurname, clientAge, clientPhone, clientEmail);
                long productId = resultSet.getLong("PRODUCT_ID");
                String productName = resultSet.getString("PRODUCT_NAME");
                BigDecimal productPrice = resultSet.getBigDecimal("PRODUCT_PRICE");
                List<Product> productsList = new ArrayList<>();
                productsList.add(new Product(productId, productName, productPrice));
                resultOrdersList.add(new Order(id, client, productsList));
            }
        } catch (SQLException e) {
            System.out.println("ORDERS DIDN'T FIND!!!");
        }
        return resultOrdersList;
    }

    @Override
    public boolean modifyOrder(long id, Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE ORDERS SET CLIENT_NAME = ?, CLIENT_SURNAME = ?, CLIENT_AGE = ?, CLIENT_PHONE = ?," +
                             " CLIENT_EMAIL = ?, PRODUCT_NAME = ?, PRODUCT_PRICE = ? WHERE ID = ?")) {
            System.out.println("Modifying.... Please wait");
            statement.setLong(8, id);
            Client client = order.getClient();
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            for (Product product : order.getProducts()) {
                statement.setString(6, product.getName());
                statement.setBigDecimal(7, product.getPrice());
                statement.execute();
            }
            System.out.println("Order Modified: " + order);
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! ORDER DIDN'T FIND FOR MODIFYING!!!");
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long orderId) {
        Order orderForDelete = findOrder(orderId);
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM ORDERS WHERE ID = ?")) {
            System.out.println("Deleting... Please wait");
            statement.setLong(1, orderId);
            System.out.println("Order Deleted: " + orderForDelete);
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! ORDER DIDN'T FIND FOR DELETING!!!");
        }
        return false;
    }

    private Order findOrder(long orderId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE ID = ?")) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("ID");
            long clientId = resultSet.getLong("CLIENT_ID");
            String clientName = resultSet.getString("CLIENT_NAME");
            String clientSurname = resultSet.getString("CLIENT_SURNAME");
            int clientAge = resultSet.getInt("CLIENT_AGE");
            String clientPhone = resultSet.getString("CLIENT_PHONE");
            String clientEmail = resultSet.getString("CLIENT_EMAIL");
            Client client = new Client(clientId, clientName, clientSurname, clientAge, clientPhone, clientEmail);
            long productId = resultSet.getLong("PRODUCT_ID");
            String productName = resultSet.getString("PRODUCT_NAME");
            BigDecimal productPrice = resultSet.getBigDecimal("PRODUCT_PRICE");
            List<Product> productsList = new ArrayList<>();
            productsList.add(new Product(productId, productName, productPrice));
            resultSet.close();
            return new Order(id, client, productsList);
        } catch (SQLException e) {
            System.out.println("ORDER DIDN'T FIND!!!");
        }
        return null;
    }
}
