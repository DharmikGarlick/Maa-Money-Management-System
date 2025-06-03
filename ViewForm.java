import javax.swing.*;

public class ViewForm extends JFrame {
    private JTextArea textArea;

    public ViewForm(Customer customer) {
        setTitle("Grocery Data");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(20, 20, 550, 320);
        textArea.setText(getDisplayString(customer));
        add(textArea);
    }

    private String getDisplayString(Customer customer) {
        String result = "Product,Quantity,Unit Price,Total Price\n";
        String[][] shoppingData = Repository.getShoppingListData(customer);
        if (shoppingData.length == 0) {
            result += "No data found.\n";
        } else {
            for (int i = 0; i < shoppingData.length; i = i + 1) {
                String product = shoppingData[i][0];
                String quantity = shoppingData[i][1];
                String unitPrice = shoppingData[i][2];
                int totalPrice = 0;
                try {
                    totalPrice = Integer.parseInt(quantity) * Integer.parseInt(unitPrice);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid number format in shopping data: " + nfe.getMessage());
                }
                result += product + "," + quantity + "," + unitPrice + "," + totalPrice + "\n";
            }
        }
        result += "\n";
        String[] lastTransaction = Repository.getLastTransactionData(customer);
        result += "Last Transaction:\nMoney Given: " + lastTransaction[0] + " | Money Spent: " + lastTransaction[1] + " | Money Owed: " + lastTransaction[2] + "\n";
        return result;
    }
}