/*
    'Senior' class

    - Inherits from MovieGoer class
*/

public class Senior extends MovieGoer {
    
    // constructor
    public Senior(String name, String mobile, String email, userType ut, movieGoerType mgt) {
        super(name, mobile, email, ut, mgt);
    }

    // accessor
    public boolean isSenior() { return this.mgt == movieGoerType.SENIOR ? true : false;}
}
