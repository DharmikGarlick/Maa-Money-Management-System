import javax.swing.*;
import java.awt.event.*;

public class MainFormRemoveButtonAction implements ActionListener {
    private JFrame parent;
    private JTextField productField;
    private Customer customer;

    public MainFormRemoveButtonAction(JFrame parent, JTextField productField, Customer customer) {
        this.parent = parent;
        this.productField = productField;
        this.customer = customer;
    }

    public void actionPerformed(ActionEvent ae) {
        String productToRemove = productField.getText().trim();
        
        if (productToRemove.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Enter product name to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean found = Repository.delete(customer, productToRemove, parent);

        if (found) JOptionPane.showMessageDialog(parent, "Product removed successfully!");
        else JOptionPane.showMessageDialog(parent, "Product not found.");
    }
}