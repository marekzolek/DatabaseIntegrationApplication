package pl.com.markdev.DatabaseIntegrationApplication.forms;

import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@Component
public class AddNewColumnForm extends JFrame {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JPanel panel1;
    private JTextField newColumnNameTextField;
    private JButton okButton;
    private JButton cancelButton;

    public AddNewColumnForm() {
        add(panel1);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(false);
    }

    public JTextField getNewColumnNameTextField() {
        return newColumnNameTextField;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
