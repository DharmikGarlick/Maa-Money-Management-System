public class Customer {
    private String id;
    private String name;
    private String address;
    private Grocery[] groceries = new Grocery[100];
    private int groceryCount = 0;

    public Customer(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}

    public String getId() {return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}

    public void addGrocery(Grocery grocery) {
        if (groceryCount < groceries.length) {
            groceries[groceryCount] = grocery;
            groceryCount = groceryCount + 1;
        }
    }
    
    public Grocery[] getGroceries() {
        Grocery[] result = new Grocery[groceryCount];
        for (int i = 0; i < groceryCount; i = i + 1) result[i] = groceries[i];
        return result;
    }
    public void clearGroceries() {groceryCount = 0;}
}