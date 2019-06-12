package pl.com.markdev.DatabaseIntegrationApplication.forms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MedicineForm extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private MenuForm menuForm = new MenuForm();
    private JPanel medicinePanel;

    private JTextField url;
    private JTextField username;
    private JTextField password;
    private JButton connectButton;
    private JTable table1;
    private JRadioButton excelFile;

    public MedicineForm() {

        remove(menuForm.getContentPane());
        add(medicinePanel);
        revalidate();
        repaint();
        setSize(WIDTH,HEIGHT);
        setContentPane(medicinePanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        url.setText("D:\\Pulpit/medicinesExcel.xlsx");
        username.setText("root");
        password.setText("MarekZolek93!");

    }

    public JPanel getMedicinePanel() {
        return medicinePanel;
    }

    public void setMedicinePanel(JPanel medicinePanel) {
        this.medicinePanel = medicinePanel;
    }

    public JTextField getUrl() {
        return url;
    }

    public void setUrl(JTextField url) {
        this.url = url;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public void setConnectButton(JButton connectButton) {
        this.connectButton = connectButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JRadioButton getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(JRadioButton excelFile) {
        this.excelFile = excelFile;
    }
}
