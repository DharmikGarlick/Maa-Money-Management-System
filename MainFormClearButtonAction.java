import javax.swing.*;
import java.awt.event.*;

public class MainFormClearButtonAction implements ActionListener {
    private JTextField productField, unitPriceField, quantityField, moneyGivenField, moneyReturnedField;

    public MainFormClearButtonAction(JTextField productField, JTextField unitPriceField, JTextField quantityField, JTextField moneyGivenField, JTextField moneyReturnedField) {
        this.productField = productField;
        this.unitPriceField = unitPriceField;
        this.quantityField = quantityField;
        this.moneyGivenField = moneyGivenField;
        this.moneyReturnedField = moneyReturnedField;
    }

    public void actionPerformed(ActionEvent ae) {
        productField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
        moneyGivenField.setText("");
        moneyReturnedField.setText("");
    }
}