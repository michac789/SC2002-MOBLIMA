package base;
import java.util.*;

public class BaseController {
    private ArrayList<BaseModel> instances = new ArrayList<BaseModel>();
    private BaseDAO dao = new BaseDAO();

    public BaseController() {
        instances = this.dao.load();
    }

    public void save() {
        this.dao.save(instances);
    }

    public int search(int id) {
        for (int i = 0; i < this.instances.size(); i++) {
            if (this.instances.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
