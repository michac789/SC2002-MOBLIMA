package Controller;

import DAO.AdminDAO;

import java.util.List;

// import java.util.List;

public class AdminController { 
    public List <List<String>> adminList; 

    public boolean login (String username, String password){  
        for(int i = 0; i<this.adminList.size(); i++){ 
            if (username.equals(this.adminList.get(i).get(0)) && password.equals(this.adminList.get(i).get(1))){
                return true; 
            }

        } 
        System.out.println("Wrong password");
        return false; 
    }
 
    public boolean isAdminExist(String username) {
        


        return false; 

        
    }
    
}
