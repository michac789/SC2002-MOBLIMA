package user;
enum Category {
    ADULT,
    SENIOR_CITIZEN,
    CHILD
};

public class MovieGoer extends User {
    private static int movieGoerCount = 0;
    private int id;
    private String email; 
    private String phoneNumber; 
    private int age;

    public MovieGoer(String username, String phoneNumber, String email, int age){ 
        super(username);
        setType(UserType.MOVIEGOER);
        this.id = ++movieGoerCount;
        this.phoneNumber = phoneNumber; 
        this.email = email;
        this.age = age;
    }

    public int getId() { return this.id;}
    public String getEmail() { return this.email;}
    public String getPhoneNumber() { return this.phoneNumber;}
    public int getAge() { return this.age;}
    public Category getAgeCategory() {
        return (this.age <= 19 ? Category.CHILD :
            (this.age <= 59 ? Category.ADULT : Category.SENIOR_CITIZEN)
        );
    }
}