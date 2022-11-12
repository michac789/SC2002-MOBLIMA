package model;

public abstract class User { 
    private String username;
    private String password;
    protected enum UserType { MOVIEGOER, ADMIN }; 
    private UserType userType;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return this.username;}
    public String getPassword() { return this.password;}

    public boolean hasAdminAccess() { return this.userType == UserType.ADMIN ? true : false;} 
    protected void setType(UserType userType) { this.userType = userType;}
}