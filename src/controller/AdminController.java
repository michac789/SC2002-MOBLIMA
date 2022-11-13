package controller;
import java.util.ArrayList;
import DAO.AdminDAO;
import model.Admin;

/**
 * Controller to perform various admin functionality such as login,
 * checking if user exist and register new admin account
 * @version 1.0
 * @since 2022-11-13
 */
public class AdminController {
    /**
     * Contains an arraylist of all admin objects
     */
    private ArrayList<Admin> adminList = new ArrayList<Admin>();
    /**
     * AdminDAO to initiate saving and getting data from database
     */
    private AdminDAO adminDao = new AdminDAO();

    /**
     * Constructor to create AdminController object
     * adminList automatically loaded from the database once constructed
     */
    public AdminController() {
        this.adminList = this.adminDao.load();
    }

    /**
     * Save method to automatically save all local changes in admin objects
     * to the database, called upon terminating program
     */
    public void save() {
        this.adminDao.save(adminList);
    }

    /**
     * Authentication purpose,
     * ensures that Admin object with the username exist,
     * make sure password entered matches the real password
     * @param username The username of Admin
     * @param password The password entered by the Admin User
     * @return True if authentication successful, otherwise false
     */
    public boolean login(String username, String password){  
        for(int i = 0; i < this.adminList.size(); i++){ 
            if (username.equals(this.adminList.get(i).getUsername()) &&
                    password.equals(this.adminList.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if an admin object with a particular username exist
     * @param username Newly entered admin username
     * @return True if such admin object exist, otherwise false
     */
    public boolean isAdminExist(String username) {
        for(int i = 0; i < this.adminList.size(); i++) {
            if (username.equals(this.adminList.get(i).getUsername())){
                return true;
            }
        }
        return false;
    }

    /**
     * Create mew admin account and append to adminList
     * @param username New admin username entered
     * @param password New admin password entered
     */
    public void createAccount(String username, String password) {
        adminList.add(new Admin(username, password));
    }
}