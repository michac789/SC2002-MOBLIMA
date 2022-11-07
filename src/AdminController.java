import java.util.ArrayList;
import user.Admin;

public class AdminController { 
    private ArrayList<Admin> adminList = new ArrayList<Admin>();
    private AdminDAO adminDao = new AdminDAO();


    public AdminController() {
        this.adminDao.load();
    }

    public void save() {
        this.adminDao.save(adminList);
    }

    public boolean login (String username, String password){  
        for(int i = 0; i < this.adminList.size(); i++){ 
            if (username.equals(this.adminList.get(i).getUsername()) &&
                    password.equals(this.adminList.get(i).getPassword())){
                return true;
            }
        } 
        System.out.println("Wrong password or username does not exist");
        return false; 
    }

    public boolean isAdminExist(String username) {
        for(int i = 0; i < this.adminList.size(); i++) {
            if (username.equals(this.adminList.get(i).getUsername())){
                return true;
            }
        }
        return false;
    }
    public void createAccount(String username, String password) {
        adminList.add(new Admin(username, password));
    }
}