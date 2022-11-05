package DAO;

import java.io.IOException;

public class AdminDAO extends DAO {
    public void createAdmin(String username, String password){

        String path = "database/AdminUsers.csv";
        try {
            openFile(path, true);
            writeText(username + "," + password);
            System.out.println("Successfully create the account");
            closeFile();
        } catch (IOException e) {
            // need to give error message
            System.out.println(e.getMessage());
            System.out.println("Unable to create admin due to unexpected error.");
        }


    }









}
