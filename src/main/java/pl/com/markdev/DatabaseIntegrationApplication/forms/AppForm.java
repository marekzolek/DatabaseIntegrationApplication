package pl.com.markdev.DatabaseIntegrationApplication.forms;

import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
    private JButton connectButton;

    private JPanel allMedicinesPanel;
    private JTable allMedicinesTable;
    private JButton backFromAllButton;

    private JPanel combinePanel;
    private JTextField urlTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JComboBox nameComboBox;
    private JComboBox manufacturerComboBox;
    private JComboBox internationalNameOfIngredientsComboBox;
    private JComboBox formComboBox;
    private JComboBox doseComboBox;
    private JComboBox quantityInPackageComboBox;
    private JLabel nameLabel;
    private JLabel manufacturerLabel;
    private JLabel internationalNameOfIngredientsLabel;
    private JLabel formLabel;
    private JLabel doseLabel;
    private JLabel quantityInPackageLabel;
    private JButton backFromSecondAddButton;
    private JButton nextButton;
    private JRadioButton excelFileRadioButtonSecondAdd;



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
        contPanel.add(combinePanel, "combinePanel");
        contPanel.add(finalConnectPanel, "finalConnectPanel");

        url.setText("D:\\Pulpit/medicinesExcel.xlsx");
//        url.setText("jdbc:mysql://localhost:3306/medicines");
//        username.setText("root");
//        password.setText("MarekZolek93!");

        excelFileRadioButtonSecondAdd = excelFileRadioButton;

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

    public JButton getConnectButton() {
        return connectButton;
    }

    public JTable getAllMedicinesTable() {
        return allMedicinesTable;
    }

    public JButton getBackFromAllButton() {
        return backFromAllButton;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JComboBox getNameComboBox() {
        return nameComboBox;
    }

    public JComboBox getManufacturerComboBox() {
        return manufacturerComboBox;
    }

    public JComboBox getInternationalNameOfIngredientsComboBox() {
        return internationalNameOfIngredientsComboBox;
    }

    public JComboBox getFormComboBox() {
        return formComboBox;
    }

    public JComboBox getDoseComboBox() {
        return doseComboBox;
    }

    public JComboBox getQuantityInPackageComboBox() {
        return quantityInPackageComboBox;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getManufacturerLabel() {
        return manufacturerLabel;
    }

    public JLabel getInternationalNameOfIngredientsLabel() {
        return internationalNameOfIngredientsLabel;
    }

    public JLabel getFormLabel() {
        return formLabel;
    }

    public JLabel getDoseLabel() {
        return doseLabel;
    }

    public JLabel getQuantityInPackageLabel() {
        return quantityInPackageLabel;
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

    public JRadioButton getExcelFileRadioButtonSecondAdd() {
        return excelFileRadioButtonSecondAdd;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JButton getBackFromSecondAddButton() {
        return backFromSecondAddButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }
}
