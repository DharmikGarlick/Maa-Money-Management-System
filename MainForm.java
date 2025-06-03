import javax.swing.*;

public class MainForm extends JFrame {
    private JTextField productField, unitPriceField, quantityField, moneyGivenField, moneyReturnedField;
    private JButton addButton, removeButton, viewButton, simpleViewButton, clearButton, reviewButton, logoutButton, clearListButton;

    public MainForm(Customer customer) {
        setTitle("Grocery Management - User: " + customer.getName());
        setSize(750, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel productLabel = new JLabel("Product:");
        productLabel.setBounds(40, 30, 100, 25);
        add(productLabel);

        productField = new JTextField();
        productField.setBounds(180, 30, 200, 25);
        add(productField);

        JLabel unitPriceLabel = new JLabel("Unit Price:");
        unitPriceLabel.setBounds(40, 70, 100, 25);
        add(unitPriceLabel);

        unitPriceField = new JTextField();
        unitPriceField.setBounds(180, 70, 200, 25);
        add(unitPriceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(40, 110, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(180, 110, 200, 25);
        add(quantityField);

        JLabel moneyGivenLabel = new JLabel("Money Given:");
        moneyGivenLabel.setBounds(40, 150, 100, 25);
        add(moneyGivenLabel);

        moneyGivenField = new JTextField();
        moneyGivenField.setBounds(180, 150, 200, 25);
        add(moneyGivenField);

        JLabel moneyReturnedLabel = new JLabel("Money Returned:");
        moneyReturnedLabel.setBounds(40, 190, 120, 25);
        add(moneyReturnedLabel);

        moneyReturnedField = new JTextField();
        moneyReturnedField.setBounds(180, 190, 200, 25);
        add(moneyReturnedField);

        addButton = new JButton("Add");
        addButton.setBounds(40, 250, 120, 35);
        add(addButton);
        addButton.addActionListener(new MainFormAddButtonAction(this, productField, quantityField, unitPriceField, moneyGivenField, moneyReturnedField, customer));

        removeButton = new JButton("Remove");
        removeButton.setBounds(180, 250, 120, 35);
        add(removeButton);
        removeButton.addActionListener(new MainFormRemoveButtonAction(this, productField, customer));

        viewButton = new JButton("View");
        viewButton.setBounds(320, 250, 120, 35);
        add(viewButton);
        viewButton.addActionListener(new MainFormViewButtonAction(customer));

        simpleViewButton = new JButton("Simple View");
        simpleViewButton.setBounds(460, 250, 140, 35);
        add(simpleViewButton);
        simpleViewButton.addActionListener(new MainFormSimpleViewButtonAction(customer));
        
        clearButton = new JButton("Clear");
        clearButton.setBounds(40, 310, 120, 35);
        add(clearButton);
        clearButton.addActionListener(new MainFormClearButtonAction(productField, unitPriceField, quantityField, moneyGivenField, moneyReturnedField));

        clearListButton = new JButton("Clear List");
        clearListButton.setBounds(180, 310, 120, 35);
        add(clearListButton);
        clearListButton.addActionListener(new MainFormClearListButtonAction(this, customer));

        reviewButton = new JButton("Review");
        reviewButton.setBounds(320, 310, 120, 35);
        add(reviewButton);
        reviewButton.addActionListener(new MainFormReviewButtonAction());

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(460, 310, 140, 35);
        add(logoutButton);
        logoutButton.addActionListener(new MainFormLogoutButtonAction(this));
    }
}