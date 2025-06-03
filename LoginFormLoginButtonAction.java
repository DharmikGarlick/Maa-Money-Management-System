import javax.swing.*;
import java.awt.event.*;

public class LoginFormLoginButtonAction implements ActionListener {
    private JFrame parent;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFormLoginButtonAction(JFrame parent, JTextField usernameField, JPasswordField passwordField) {
        this.parent = parent;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    public void actionPerformed(ActionEvent ae) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        Customer customer = Repository.getCustomerFromLogin(username, password);

        if (customer != null) {
            JOptionPane.showMessageDialog(parent, "Login successful!");
            parent.dispose();

            new MainForm(customer).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(parent, "Invalid username or password.");
        }
    }
}