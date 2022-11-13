package model;
/**
 * Represents the admin object
 * @version 1.0
 * @since 2022-11-13
 */
public class Admin extends User {
    /**
     * Admin constructor to create the admin object
     * @param username The username of the admin
     * @param password The password of the admin
     */
    public Admin (String username, String password) { 
        super(username, password);  
        setType(UserType.ADMIN);
    }
}