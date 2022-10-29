/*
    'User' class

    - Stores email, type (moviegoer or admin)
    - TODO: include a password?
*/

public class User { 
    private String email;
    protected enum userType { MOVIEGOER, ADMIN };
    private userType ut;

    // constructor
    public User(String email, userType ut) {
        this.email = email;
        this.ut = ut;
    }

    // accessors
    public String getEmail() { return email; }
    public boolean isAdmin () { return this.ut == userType.ADMIN ? true : false; }

    // mutators
    public void setEmail(String email) { this.email = email; }
    public void setType(userType ut) { this.ut = ut; }
}
