import java.awt.event.*;

public class MainFormReviewButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
        new ReviewForm().setVisible(true);
    }
}