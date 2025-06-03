public class Grocery {
    private String productName;
    private double unitPrice;
    private int quantity;

    public Grocery(String productName, double unitPrice, int quantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
    public void setProductName(String productName) {this.productName = productName;}
    public void setUnitPrice(double unitPrice) {this.unitPrice = unitPrice;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public String getProductName() {return productName;}
    public double getUnitPrice() {return unitPrice;}
    public int getQuantity() {return quantity;}
}