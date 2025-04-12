import db.DBConnection;

import java.sql.*;

public class UserController {
    private final Connection connection;


    public UserController() {
        connection = DBConnection.getConnection();
    }

    //Creating the table
    public void createTable(){
        System.out.println("Attempting to create table...");
        try {
            PreparedStatement createUserTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User" +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "  username VARCHAR(45) NULL," +
                    "  password VARCHAR(45) NULL," +
                    "  PRIMARY KEY (id));");
            createUserTable.executeUpdate();
            System.out.println("Table created, or is already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not create table.");
        }
    }

    //Showing a table
    public void showTable(){
        System.out.println("Attempting to show table...");
        try{
            PreparedStatement showUserTable = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = showUserTable.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User table is empty.");
            }

            while(resultSet.next()){
                System.out.print("ID: " + resultSet.getString("ID"));
                System.out.print(", Username: " + resultSet.getString("USERNAME"));
                System.out.println(", Password: " + resultSet.getString("PASSWORD"));
                System.out.println("----------------------------------------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Could not show table.");
        }
    }

    //Adding to the table
    public void addUser(String username, String password){
        System.out.println("Attempting to add user...");
        try{
            PreparedStatement addUser1 = connection.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)");
            addUser1.setString(1, username);
            addUser1.setString(2, password);
            addUser1.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not add user.");
        }
    }

    //Deleting from the table
    public void deleteUser(int userId){
        System.out.println("Attempting to delete user...");
        try{
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM User WHERE (`id` = (?))");
            deleteUser.setInt(1, userId);
            deleteUser.executeUpdate();
            System.out.println("User deleted");
        } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Could not delete user.");
        }
    }

    //Updating a user
    public void updateUser (int userID,  String username, String password){
        System.out.println("Attempting to update user...");
        try{
            PreparedStatement updateUser = connection.prepareStatement("UPDATE User SET USERNAME = (?), PASSWORD = (?) WHERE ID = (?)");
            updateUser.setString(1, username);
            updateUser.setString(2, password);
            updateUser.setInt(3, userID);
            updateUser.executeUpdate();
            System.out.println("User updated");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update user.");
        }
    }

    //Showing a user
    public void viewUser(int userId){
        System.out.println("Attempting to view user...");
        try {
            PreparedStatement viewUser = connection.prepareStatement("SELECT * FROM User WHERE ID = (?)");
            viewUser.setInt(1, userId);
            ResultSet resultSet = viewUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User with ID of " + userId + " not found.");
            }

            if(resultSet.next()){
                System.out.print("ID: " + resultSet.getString("ID"));
                System.out.print(", Username: " + resultSet.getString("USERNAME"));
                System.out.println(", Password: " + resultSet.getString("PASSWORD"));
                System.out.println("----------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not find user.");
        }
    }

}
