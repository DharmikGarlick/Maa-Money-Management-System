import javax.swing.*;
import java.awt.event.*;

public class MainFormClearListButtonAction implements ActionListener {
    private JFrame parent;
    private Customer customer;

    public MainFormClearListButtonAction(JFrame parent, Customer customer) {
        this.parent = parent;
        this.customer = customer;
    }

    public void actionPerformed(ActionEvent ae) {
        Repository.clear(customer);
        JOptionPane.showMessageDialog(parent, "Shopping list and transactions cleared!");
    }
}