import javax.swing.*;
import java.awt.event.*;

public class MainFormLogoutButtonAction implements ActionListener {
    private JFrame parent;

    public MainFormLogoutButtonAction(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent ae) {
        parent.dispose();
        new LoginForm().setVisible(true);
    }
}