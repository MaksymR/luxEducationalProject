package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ProductDao;
import com.riaboshapka.domain.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDBDao implements ProductDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "test";
    private static final String PASSWORD = "test";

    public ProductDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(
                    "CREATE TABLE IF NOT EXISTS PRODUCTS(ID BIGINT DEFAULT 1 PRIMARY KEY AUTO_INCREMENT," +
                            " NAME VARCHAR(20) DEFAULT NULL, PRICE DECIMAL DEFAULT 0)"
            );

        } catch (SQLException e) {
            System.out.println("SOMETHING IS GOING WRONG!!!");
        }
    }

    @Override
    public boolean saveProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO PRODUCTS (NAME, PRICE) VALUES(?, ?)")) {
            System.out.println("Saving.... Please wait");
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            System.out.println("Product Saved: " + "Product{" +
                    "name='" + product.getName() + '\'' +
                    ", price=" + product.getPrice() +
                    '}');
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!!");
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> resultProductList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTS")) {
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                BigDecimal price = resultSet.getBigDecimal("PRICE");
                resultProductList.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            System.out.println("PRODUCTS DIDN'T FIND!!!");
        }
        return resultProductList;
    }

    @Override
    public boolean modifyProduct(long id, Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE PRODUCTS SET NAME = ?, PRICE = ? WHERE ID = ?")) {
            System.out.println("Modifying.... Please wait");
            statement.setLong(3, id);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            System.out.println("Product Modified: " + product);
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! PRODUCT DIDN'T FIND FOR MODIFYING!!!");
        }
        return false;
    }

    @Override
    public boolean deleteProduct(long productId) {
        Product productForDelete = findProduct(productId);
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM PRODUCTS WHERE ID = ?")) {
            System.out.println("Deleting... Please wait");
            statement.setLong(1, productId);
            System.out.println("Product Deleted: " + productForDelete);
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! PRODUCT DIDN'T FIND FOR DELETING!!!");
        }
        return false;
    }

    private Product findProduct(long productId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE ID = ?")) {
            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            BigDecimal price = resultSet.getBigDecimal("PRICE");
            resultSet.close();
            return new Product(id, name, price);
        } catch (SQLException e) {
            System.out.println("PRODUCT DIDN'T FIND!!!");
        }
        return null;
    }
}
