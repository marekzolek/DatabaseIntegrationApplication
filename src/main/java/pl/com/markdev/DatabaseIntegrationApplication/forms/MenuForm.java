package pl.com.markdev.DatabaseIntegrationApplication.forms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuForm extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private JPanel menuPanel;
    private JButton addNewDatabase;
    private JButton showAllData;

    public MenuForm() {
        setSize(WIDTH,HEIGHT);
        setContentPane(menuPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getAddNewDatabase() {
        return addNewDatabase;
    }

    public void setAddNewDatabase(JButton addNewDatabase) {
        this.addNewDatabase = addNewDatabase;
    }

    public JButton getShowAllData() {
        return showAllData;
    }

    public void setShowAllData(JButton showAllData) {
        this.showAllData = showAllData;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }
}
