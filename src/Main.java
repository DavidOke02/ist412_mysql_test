import db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        //This is just testing that the db connects, look in the DBConnection to alter the connection details
        Connection connection = DBConnection.getConnection();
        System.out.println("Database connection established!.");

        //This is just calling stuff to test the login_schema db, look in the UserController for the actual code implementation
        UserController userController = new UserController();
        userController.createTable(); //Will make the table
        userController.showTable(); //Will show everything in table (Will be empty)
        userController.addUser("user1", "password1"); //Will add user1
        userController.addUser("user2", "password2"); //Will add user2
        userController.showTable(); //Will show everything in table (Should have 2 entries)
        userController.updateUser(1, "admin", "ist"); //Changes user 1's info
        userController.showTable(); //Will show table with user1's info updated
        userController.deleteUser(1); //Will delete user1
        userController.viewUser(1); //Will give an error cause user1 was deleted
        userController.viewUser(2); //Will show user2
        userController.showTable(); //Will show everything in table (Should only show user2)

        //I made a PracticeController that's completely blank for y'all to experiment and make your own tables, feel free to rename it if you want
        //PracticeController practiceController = new PracticeController(); - Uncomment this when you want to start testing the controller
    }
}