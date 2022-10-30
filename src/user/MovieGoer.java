package User;
enum Category { 
    ADULT, 
    SENIOR_CITIZEN, 
    CHILD
}; 

public class MovieGoer extends User {
    private String email; 
    private String phoneNumber; 
    private Category ageCategory; 


    public MovieGoer (String username, String phoneNumber, String email, Category ageCategory){ 
        super(username);
        setType(UserType.MOVIEGOER); 
        this.phoneNumber = phoneNumber; 
        this.ageCategory = ageCategory; 
        this.email = email; 
    }

    

}
