import java.awt.event.*;

public class MainFormViewButtonAction implements ActionListener {
    private Customer customer;

    public MainFormViewButtonAction(Customer customer) {
        this.customer = customer;
    }

    public void actionPerformed(ActionEvent ae) {
        new ViewForm(customer).setVisible(true);
    }
}