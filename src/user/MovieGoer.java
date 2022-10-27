package User;
enum Category { 
    ADULT, 
    SENIOR_CITIZEN, 
    CHILD
}; 

public class MovieGoer extends User {
    private String phoneNumber; 
    private Category ageCategory; 
    // need email check? 
    private String email; 
    public MovieGoer (String username, String phoneNumber, String email, Category ageCategory){ 
        super(username);
        this.phoneNumber = phoneNumber; 
        this.ageCategory = ageCategory; 
        this.email = email; 
    }

    public boolean hasAdminAccess() {
        return true; 
    }

}
