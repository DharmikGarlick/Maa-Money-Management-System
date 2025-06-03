import java.awt.event.*;

public class MainFormSimpleViewButtonAction implements ActionListener {
    private Customer customer;

    public MainFormSimpleViewButtonAction(Customer customer) {
        this.customer = customer;
    }

    public void actionPerformed(ActionEvent ae) {
        new SimpleViewForm(customer).setVisible(true);
    }
}