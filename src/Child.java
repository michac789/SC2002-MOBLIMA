/*
    'Child' class

    - Inherits from MovieGoer class
*/

public class Child extends MovieGoer {
    
    // constructor
    public Child(String name, String mobile, String email, userType ut, movieGoerType mgt) {
        super(name, mobile, email, ut, mgt);
    }

    // accessor
    public boolean isChild() { return this.mgt == movieGoerType.CHILD ? true : false;}
}
