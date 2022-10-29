/*
    'Adult' class

    - Inherits from MovieGoer class
*/

public class Adult extends MovieGoer {
    
    // constructor
    public Adult(String name, String mobile, String email, userType ut, movieGoerType mgt) {
        super(name, mobile, email, ut, mgt);
    }

    // accessor
    public boolean isAdult() { return this.mgt == movieGoerType.ADULT ? true : false;}
}
