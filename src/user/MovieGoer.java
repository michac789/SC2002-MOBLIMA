package user;
enum Category {
    ADULT,
    SENIOR_CITIZEN,
    CHILD
};

public class MovieGoer extends User {
    private String email; 
    private String phoneNumber; 
    private int age;
    private Category ageCategory;

    public MovieGoer (String username, String phoneNumber, String email, int age){ 
        super(username);
        setType(UserType.MOVIEGOER); 
        this.phoneNumber = phoneNumber; 
        this.email = email;
        this.age = age;
        // this.ageCategory = ageCategory; 
    }

    public String getEmail() { return this.email;}
    public String getPhoneNumber() { return this.phoneNumber;}
    public int getAge() { return this.age;}
}