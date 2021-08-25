package com.practice.blogTest.model;

import java.sql.*;

public class DBConnection {

    String URL = "jdbc:mysql://localhost:3306/blog_test?";
    String USER = "user=root";
    String PASSWORD = "password=password";
    String DB_URL = URL + USER + "&" + PASSWORD;
    String INSERT_QUERY = "INSERT INTO posts (post, author_name) VALUES (?, ?)";
    String DELETE_QUERY = "DELETE FROM posts WHERE id = ";
    String SELECT_QUERY = "SELECT * FROM posts WHERE id = ";
    String UPDATE_QUERY = "UPDATE posts SET post = ? WHERE id = ?";

    public void testConnection() {

        try(Connection connection = DriverManager.getConnection(DB_URL)) {

            if (connection.isValid(2)) {
                System.out.println("DB is ready to go!");
            } else {
                System.out.println("Something is wrong...");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void insertData(String post, String authorName) {

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setString(1, post);
            preparedStatement.setString(2, authorName);

            preparedStatement.executeUpdate();
            System.out.println("data inserted");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteData(int id) {

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY + id)) {
            preparedStatement.executeUpdate();

            System.out.println("Deleted record with id of " + id);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void retrieveData(int id) {

        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY + id)) {

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()) {
                String post = result.getString(2);
                String author = result.getString(3);
                System.out.println(post);
                System.out.println(author);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateData(int id, String updatedInfo) {
        try(Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, updatedInfo);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            System.out.println("The record with an id of " + id + " was updated");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
