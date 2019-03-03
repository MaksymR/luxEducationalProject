package com.riaboshapka.dao.impl;

import com.riaboshapka.dao.ClientDao;
import com.riaboshapka.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftSHop";
    private static final String LOGIN = "test";
    private static final String PASSWORD = "test";

    public ClientDBDao() {

        try (Connection connection = DriverManager.getConnection(
                DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE CLIENT(ID BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    " NAME VARCHAR(20), SURNAME VARCHAR(20)," +
                    " AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50))");
            //statement.executeUpdate("");      // update WHERE for modify use
            //statement.close();
            //connection.close();
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
    }

    @Override
    public boolean saveClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO CLIENT(NAME, SURNAME, AGE, PHONE, EMAIL)" +
                             " VALUES(?, ?, ?, ?, ?)")) {
            // set params
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getEmail());

            //sent params to DB;
             return statement.execute();

//            statement.execute("INSERT INTO CLIENT(NAME, SURNAME, AGE, PHONE, EMAIL) VALUES("
//                    + "'" + client.getName() + "', "
//                    + "'" + client.getSurname() + "', "
//                    + "'" + client.getAge() + "', "
//                    + "'" + client.getPhone() + "', "
//                    + "'" + client.getEmail() + "' "
//                    + ");");
            //statement.executeUpdate("");      // update WHERE for modify use
            //statement.close();
            //connection.close();
        } catch (SQLException e) {
            System.out.println("SOMETHING GOING WRONG!!!");
        }
        return false;
    }

    public Client findClient(long clientId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        PreparedStatement statement = connection.prepareStatement(
                "SELECT  * FROM  CLIENT WHERE ID=?")) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();     // cursor for database

            //ResultSet resultSet = statement.executeQuery("SELECT  * FROM  CLIENT WHERE ID='" + clientId + "';");
            resultSet.next();
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString("SURNAME");
            int age = resultSet.getInt("AGE");
            String phone = resultSet.getString(5);
            String email = resultSet.getString(6);
            resultSet.close();

            return new Client(id, name, surname, age, phone, email);
        } catch (SQLException e) {
            System.out.println("CLIENT DIDN'T FIND!!!");
        }
        return null;
    }

    @Override
    public boolean modifyClient(long id, Client client) {
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD)
             ){

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteClient(long id) {
        return false;
    }
}
