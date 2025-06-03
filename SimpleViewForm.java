import javax.swing.*;

public class SimpleViewForm extends JFrame {
    private JTextField owedField;

    public SimpleViewForm(Customer customer) {
        setTitle("Simple View - Money Owed");
        setSize(350, 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Money Owed:");
        label.setBounds(30, 25, 100, 25);
        add(label);

        owedField = new JTextField();
        owedField.setBounds(140, 25, 150, 25);
        owedField.setText(String.valueOf(Repository.calculateOwed(customer)));
        add(owedField);
        
        setVisible(true);
    }
}