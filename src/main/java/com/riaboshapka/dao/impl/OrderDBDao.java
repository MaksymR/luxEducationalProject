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
                            " CLIENT_ID BIGINT DEFAULT 1, PRODUCT_ID BIGINT DEFAULT 1)");
        } catch (SQLException e) {
            System.out.println("SOMETHING IS GOING WRONG!!!");
        }
    }

    @Override
    public boolean saveOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO ORDERS (CLIENT_ID, PRODUCT_ID) VALUES(?, ?)")) {
            System.out.println("Saving.... Please wait");
            for (Product product : order.getProducts()) {
                Client client = order.getClient();
                statement.setLong(1, client.getId());
                statement.setLong(2, product.getId());
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
                long orderID = resultSet.getLong("ID");
                Client client = getClient(resultSet);
                Product product = getProduct(resultSet);
                List<Product> productsList = new ArrayList<>();
                productsList.add(product);
                resultOrdersList.add(new Order(orderID, client, productsList));
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
                     "UPDATE ORDERS SET CLIENT_ID = ?, PRODUCT_ID = ? WHERE ID = ?")) {
            System.out.println("Modifying.... Please wait");
            statement.setLong(3, id);
            Client client = order.getClient();
            statement.setLong(1, client.getId());
            for (Product product : order.getProducts()) {
                statement.setLong(2, product.getId());
                break;
            }
            System.out.println("Order Modified: " + order);
            statement.execute();
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
            statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! ORDER DIDN'T FIND FOR DELETING!!!");
        }
        return false;
    }

    public Order findOrder(long orderId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE ID = ?")) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("ID");
            Client client = getClient(resultSet);
            Product product = getProduct(resultSet);
            List<Product> productsList = new ArrayList<>();
            productsList.add(product);
            resultSet.close();
            return new Order(id, client, productsList);
        } catch (SQLException e) {
            System.out.println("ORDER DIDN'T FIND!!!");
        }
        return null;
    }

    private Client getClient(ResultSet resultSet) throws SQLException {
        long clientId = resultSet.getLong("CLIENT_ID");
        return findClient(clientId);
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        long productID = resultSet.getLong("PRODUCT_ID");
        return findProduct(productID);
    }

    public Client findClient(long clientId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE ID = ?")) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String surname = resultSet.getString("SURNAME");
            int age = resultSet.getInt("AGE");
            String phone = resultSet.getString("PHONE");
            String email = resultSet.getString("EMAIL");
            resultSet.close();
            return new Client(id, name, surname, age, phone, email);
        } catch (SQLException e) {
            System.out.println("CLIENT DIDN'T FIND!!!");
        }
        return null;
    }

    public Product findProduct(long productId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            BigDecimal price = resultSet.getBigDecimal("PRICE");
            resultSet.close();
            return new Product(id, name, price);
        } catch (SQLException e) {
            System.out.println("PRODUCT DIDN'T FIND!!!");
        }
        return null;
    }
}
