import javax.swing.*;

public class ReviewForm extends JFrame {
    public ReviewForm() {
        setTitle("Review");
        setSize(300, 270);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Please leave a review:");
        label.setBounds(70, 10, 200, 25);
        add(label);

        ButtonGroup group = new ButtonGroup();
        JRadioButton excellent = new JRadioButton("Excellent");
        JRadioButton good = new JRadioButton("Good");
        JRadioButton average = new JRadioButton("Average");
        JRadioButton poor = new JRadioButton("Poor");
        JRadioButton terrible = new JRadioButton("Terrible");

        group.add(excellent);
        group.add(good);
        group.add(average);
        group.add(poor);
        group.add(terrible);

        excellent.setBounds(90, 40, 120, 25);
        good.setBounds(90, 65, 120, 25);
        average.setBounds(90, 90, 120, 25);
        poor.setBounds(90, 115, 120, 25);
        terrible.setBounds(90, 140, 120, 25);

        add(excellent);
        add(good);
        add(average);
        add(poor);
        add(terrible);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(100, 180, 100, 30);
        add(submitButton);
        submitButton.addActionListener(new ReviewFormRatingButtonAction(this, excellent, good, average, poor, terrible));
    }
}