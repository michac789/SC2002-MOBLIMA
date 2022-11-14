package model;

public abstract class User {
    /**
     * The username of user
     */
    private String username;
    /**
     * The password of the user
     */
    private String password;

    /**
     * The categories available for user
     */
    protected enum UserType { MOVIEGOER, ADMIN };
    /**
     * The user type based on UserType enum
     */
    private UserType userType;

    /**
     * Constructor for creating user
     * @param username The username of user
     * @param password The password of user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of user
     * @return The username of user
     */
    public String getUsername() { return this.username;}

    /**
     * Gets the password of user
     * @return The password of user
     */
    public String getPassword() { return this.password;}

    /**
     * Indicates whether a user has an admina access or not
     * @return The indicator whether user is able to access admin functionalities
     */
    public boolean hasAdminAccess() { return this.userType == UserType.ADMIN ? true : false;}

    /**
     * Sets the type of user
     * @param userType The type of user based on UserType enum
     */
    protected void setType(UserType userType) { this.userType = userType;}
}