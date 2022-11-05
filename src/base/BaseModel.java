package base;

public class BaseModel {
    static int modelIdTracker = 0;
    static int modelCount = 0;
    private int id;

    public BaseModel() {
        modelCount++;
        this.id = modelIdTracker;
    }

    public int getId() { return this.id;}
}
