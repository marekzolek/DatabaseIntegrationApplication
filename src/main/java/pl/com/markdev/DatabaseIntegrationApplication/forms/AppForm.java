package pl.com.markdev.DatabaseIntegrationApplication.forms;

import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.CardLayout;

@Component
public class AppForm extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private JPanel contPanel;

    private JPanel menuPanel;
    private JButton addNewDatabaseButton;
    private JButton showAllDataButton;

    private JPanel connectPanel;
    private JTextField url;
    private JTextField username;
    private JTextField password;
    private JRadioButton excelFileRadioButton;
    private JButton backFromAddButton;
    private JButton firstNextButton;
    private JComboBox tableSelectComboBox;
    private JTable mergeTable;
    private JButton connectButton;
    private JComboBox columnFromMainDatabaseComboBox;
    private JComboBox columnFromOtherDatabaseComboBox;
    private JButton addColumnButton;
    private JButton mergeButton;
    private JComboBox mainDatabaseComboBox;
    private JLabel equalTable;
    private JLabel equalColumn;
    private JLabel mainLabel;
    private JLabel addLabel;
    private JLabel selectTableLabel;
    private JButton chooseTableButton;

    private JPanel allMedicinesPanel;
    private JTable allMedicinesTable;
    private JButton backFromAllButton;
    private JComboBox mainDatabaseTablesComboBox;

    private JPanel finalConnectPanel;
    private JButton saveFromAddButton;
    private JButton backFromFinalAddButton;
    private JTextField urlFinalAdd;
    private JTextField usernameFinalAdd;
    private JTextField passwordFinalAdd;
    private JRadioButton excelFileRadioButtonFinalAdd;
    private JTable allFromAddMedicinesTable;

    CardLayout cardLayout = new CardLayout();

    public AppForm(){

        contPanel.setLayout(cardLayout);

        contPanel.add(menuPanel, "menuPanel");
        contPanel.add(allMedicinesPanel, "allMedicinesPanel");
        contPanel.add(connectPanel, "connectPanel");
        contPanel.add(finalConnectPanel, "finalConnectPanel");

        url.setText("D:\\Pulpit/medicinesExcel.xlsx");
//        url.setText("jdbc:mysql://localhost:3306/medicines");
//        username.setText("root");
//        password.setText("MarekZolek93!");

        columnFromMainDatabaseComboBox.setVisible(false);
        columnFromOtherDatabaseComboBox.setVisible(false);
        mainDatabaseComboBox.setVisible(false);
        allMedicinesTable.setVisible(false);
        addColumnButton.setVisible(false);
        mergeButton.setVisible(false);
        equalTable.setVisible(false);
        equalColumn.setVisible(false);
        mainLabel.setVisible(false);
        addLabel.setVisible(false);
        selectTableLabel.setVisible(false);
        tableSelectComboBox.setVisible(false);
        chooseTableButton.setVisible(false);
        mergeButton.setEnabled(false);

        add(contPanel);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public JPanel getContPanel() {
        return contPanel;
    }

    public JButton getAddNewDatabaseButton() {
        return addNewDatabaseButton;
    }

    public JButton getShowAllDataButton() {
        return showAllDataButton;
    }

    public JTextField getUrl() {
        return url;
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getPassword() {
        return password;
    }

    public JRadioButton getExcelFileRadioButton() {
        return excelFileRadioButton;
    }

    public JButton getBackFromAddButton() {
        return backFromAddButton;
    }

    public JButton getFirstNextButton() {
        return firstNextButton;
    }

    public JTable getAllMedicinesTable() {
        return allMedicinesTable;
    }

    public JButton getBackFromAllButton() {
        return backFromAllButton;
    }

    public JButton getSaveFromAddButton() {
        return saveFromAddButton;
    }

    public JButton getBackFromFinalAddButton() {
        return backFromFinalAddButton;
    }

    public JTextField getUrlFinalAdd() {
        return urlFinalAdd;
    }

    public JTextField getUsernameFinalAdd() {
        return usernameFinalAdd;
    }

    public JTextField getPasswordFinalAdd() {
        return passwordFinalAdd;
    }

    public JRadioButton getExcelFileRadioButtonFinalAdd() {
        return excelFileRadioButtonFinalAdd;
    }

    public JTable getAllFromAddMedicinesTable() {
        return allFromAddMedicinesTable;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JComboBox getTableSelectComboBox() {
        return tableSelectComboBox;
    }

    public JTable getMergeTable() {
        return mergeTable;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JComboBox getColumnFromMainDatabaseComboBox() {
        return columnFromMainDatabaseComboBox;
    }

    public JComboBox getColumnFromOtherDatabaseComboBox() {
        return columnFromOtherDatabaseComboBox;
    }

    public JButton getAddColumnButton() {
        return addColumnButton;
    }

    public JButton getMergeButton() {
        return mergeButton;
    }

    public JComboBox getMainDatabaseComboBox() {
        return mainDatabaseComboBox;
    }

    public JLabel getEqualTable() {
        return equalTable;
    }

    public JLabel getEqualColumn() {
        return equalColumn;
    }

    public JLabel getMainLabel() {
        return mainLabel;
    }

    public JLabel getAddLabel() {
        return addLabel;
    }

    public JLabel getSelectTableLabel() {
        return selectTableLabel;
    }

    public JComboBox getMainDatabaseTablesComboBox() {
        return mainDatabaseTablesComboBox;
    }

    public JButton getChooseTableButton() {
        return chooseTableButton;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public JPanel getConnectPanel() {
        return connectPanel;
    }

    public JPanel getAllMedicinesPanel() {
        return allMedicinesPanel;
    }

    public JPanel getFinalConnectPanel() {
        return finalConnectPanel;
    }
}
