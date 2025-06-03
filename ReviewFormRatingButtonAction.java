import javax.swing.*;
import java.awt.event.*;

public class ReviewFormRatingButtonAction implements ActionListener {
    private JRadioButton excellent, good, average, poor, terrible;
    private JFrame parent;

    public ReviewFormRatingButtonAction(JFrame parent, JRadioButton excellent, JRadioButton good, JRadioButton average, JRadioButton poor, JRadioButton terrible) {
        this.parent = parent;
        this.excellent = excellent;
        this.good = good;
        this.average = average;
        this.poor = poor;
        this.terrible = terrible;
    }

    public void actionPerformed(ActionEvent ae) {
        String message = "Thank you for your review!";

        if (excellent.isSelected()) message += " (Excellent)";
        else if (good.isSelected()) message += " (Good)";
        else if (average.isSelected()) message += " (Average)";
        else if (poor.isSelected()) message += " (Poor)";
        else if (terrible.isSelected()) message += " (Terrible)";
        else message = "Please select a review option.";

        JOptionPane.showMessageDialog(parent, message);

        if (!message.equals("Please select a review option.")) parent.dispose();
    }
}