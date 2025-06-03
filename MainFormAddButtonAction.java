import javax.swing.*;
import java.awt.event.*;

public class MainFormAddButtonAction implements ActionListener {
    private JFrame parent;
    private JTextField productField, quantityField, unitPriceField, moneyGivenField, moneyReturnedField;
    private Customer customer;

    public MainFormAddButtonAction(JFrame parent, JTextField productField, JTextField quantityField, JTextField unitPriceField, JTextField moneyGivenField, JTextField moneyReturnedField, Customer customer) {
        this.parent = parent;
        this.productField = productField;
        this.quantityField = quantityField;
        this.unitPriceField = unitPriceField;
        this.moneyGivenField = moneyGivenField;
        this.moneyReturnedField = moneyReturnedField;
        this.customer = customer;
    }

    public void actionPerformed(ActionEvent ae) {
        String product = productField.getText().trim();
        String quantity = quantityField.getText().trim();
        String unitPrice = unitPriceField.getText().trim();
        String moneyGiven = moneyGivenField.getText().trim();
        String moneyReturned = moneyReturnedField.getText().trim();
        String shoppingFile = Repository.getShoppingListFile(customer.getId());
        String transactionFile = Repository.getTransactionsFile(customer.getId());

        Repository shoppingRepo = new Repository(shoppingFile);
        Repository transactionRepo = new Repository(transactionFile);

        boolean hasProductInfo = !product.isEmpty() && !quantity.isEmpty() && !unitPrice.isEmpty();
        boolean hasTransactionInfo = !moneyGiven.isEmpty() && !moneyReturned.isEmpty();

        if (hasProductInfo && !hasTransactionInfo) {
            int quantityInt, unitPriceInt;

            try {
                quantityInt = Integer.parseInt(quantity);
                unitPriceInt = Integer.parseInt(unitPrice);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(parent, "Quantity and Unit Price must be integers.");
                return;
            }
            String shoppingLine = product + "," + quantityInt + "," + unitPriceInt;
            shoppingRepo.insert(shoppingLine);
            String lastTransaction = transactionRepo.getLast();
            if (lastTransaction != null) {
                String[] parts = lastTransaction.split(",");
                String moneyGivenString = parts[0].replace("Money Given:", "").trim();
                String moneyReturnedString = parts[1].replace("Money Returned:", "").trim();
                
                int moneyGivenValue = 0, moneyReturnedValue = 0;

                try {
                     moneyGivenValue = Integer.parseInt(moneyGivenString);
                    moneyReturnedValue = Integer.parseInt(moneyReturnedString);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(parent, "Money Given and Money Returned must be integers.");
                }
                int newTotal = Repository.calculateShoppingListTotal(customer.getId());
                int newOwed = moneyGivenValue - moneyReturnedValue - newTotal;
                
                String newTransaction = "Money Given: " + moneyGivenValue + ", Money Returned: " + moneyReturnedValue + ", Total Price: " + newTotal + ", Money Owed: " + newOwed;
                String allTransactions = transactionRepo.getAll();
                
                int lastNewline = allTransactions.lastIndexOf("\n");
                
                String updatedTransactions;
                if (lastNewline == -1) updatedTransactions = newTransaction;
                else updatedTransactions = allTransactions.substring(0, lastNewline + 1) + newTransaction;
                
                transactionRepo.update(updatedTransactions);    
            }
            JOptionPane.showMessageDialog(parent, "Grocery item added successfully!");
            clearFields();
        } else if (!hasProductInfo && hasTransactionInfo) {
            int given, returned, totalPrice = 0;
            
            try {
                given = Integer.parseInt(moneyGiven);
                returned = Integer.parseInt(moneyReturned);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(parent, "Money Given and Money Returned must be integers."); 
                return;
            }
            String lastLine = shoppingRepo.getLast();
            if (lastLine != null) {
                String[] parts = lastLine.split(",");

                    try {
                        int q = Integer.parseInt(parts[1]);
                        int up = Integer.parseInt(parts[2]);
                        
                        totalPrice = q * up;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid number format in last line: " + lastLine + " - " + nfe.getMessage());
                    }
            }
            int owed = given - returned - totalPrice;

            transactionRepo.insert("Money Given: " + given + ", Money Returned: " + returned + ", Total Price: " + totalPrice + ", Money Owed: " + owed);
            
            JOptionPane.showMessageDialog(parent, "Transaction added successfully!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(parent, "Please fill either (product, quantity, unit price) or (money given, money returned).");
        }
    }

    private void clearFields() {
        productField.setText("");
        quantityField.setText("");
        unitPriceField.setText("");
        moneyGivenField.setText("");
        moneyReturnedField.setText("");
    }
}
