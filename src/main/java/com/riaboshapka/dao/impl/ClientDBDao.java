package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    private static final String LOGIN = "test";
    private static final String PASSWORD = "test";

    public ClientDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute(
                    "CREATE TABLE IF NOT EXISTS CLIENTS(ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                            " NAME VARCHAR(20), SURNAME VARCHAR(20), AGE INT, PHONE VARCHAR(20)," +
                            " EMAIL VARCHAR(50))"
            );

        } catch (SQLException e) {
            System.out.println("SOMETHING IS GOING WRONG!!!");
        }
    }

    @Override
    public boolean saveClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO CLIENTS (NAME, SURNAME, AGE, PHONE, EMAIL) VALUES(?, ?, ?, ?, ?)")) {
            System.out.println("Saving.... Please wait");
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            System.out.println("Client Saved: " + client);
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!!");
        }
        return false;
    }

    @Override
    public boolean modifyClient(long id, Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE CLIENTS SET NAME = ?, SURNAME = ?, AGE = ?, PHONE = ?, EMAIL = ? WHERE ID = ?")) {
            System.out.println("Modifying.... Please wait");
            statement.setLong(6, id);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());
            System.out.println("Client Saved: " + client);
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! CLIENT DIDN'T FIND FOR MODIFYING!!!");
        }
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> resultClientsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENTS")) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString("SURNAME");
                    int age = resultSet.getInt("AGE");
                    String phone = resultSet.getString("PHONE");
                    String email = resultSet.getString("EMAIL");
                    resultClientsList.add(new Client(id, name, surname, age, phone, email));
                }
            }
        } catch (SQLException e) {
            System.out.println("CLIENTS DIDN'T FIND!!!");
        }
        return resultClientsList;
    }

    @Override
    public boolean deleteClient(long clientId) {
        Client client = findClient(clientId);
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM CLIENTS WHERE ID = ?")) {
            System.out.println("Deleting... Please wait");
            statement.setLong(1, clientId);
            System.out.println("Client Deleted: " + client);
            return statement.execute();
        } catch (SQLException e) {
            System.out.println("SOMETHING WAS GOING WRONG!!! CLIENT DIDN'T FIND FOR DELETING!!!");
        }
        return false;
    }

    private Client findClient(long clientId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTS WHERE ID = ?")) {
            statement.setLong(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                return new Client(id, name, surname, age, phone, email);
            }
        } catch (SQLException e) {
            System.out.println("CLIENT DIDN'T FIND!!!");
        }
        return null;
    }
}
