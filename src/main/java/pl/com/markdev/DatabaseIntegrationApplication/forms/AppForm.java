package pl.com.markdev.DatabaseIntegrationApplication.forms;

import org.springframework.stereotype.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AppForm extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private JPanel contPanel;

    private JPanel menuPanel;
    private JButton addNewDatabaseButton;
    private JButton showAllDataButton;

    private JPanel connectPanel;
    private JTextField username;
    private JTextField url;
    private JRadioButton excelFileRadioButton;
    private JTextField password;
    private JButton backFromAddButton;
    private JButton saveFromAddButton;
    private JButton connectButton;
    private JTable allFromAddMedicinesTable;

    private JPanel allMedicinesPanel;
    private JTable allMedicinesTable;
    private JButton backFromAllButton;

    CardLayout cardLayout = new CardLayout();

    public AppForm(){

        saveFromAddButton.setVisible(false);
        contPanel.setLayout(cardLayout);

        contPanel.add(menuPanel, "menuPanel");
        contPanel.add(allMedicinesPanel, "allMedicinesPanel");
        contPanel.add(connectPanel, "connectPanel");

        url.setText("D:\\Pulpit/medicinesExcel.xlsx");

        add(contPanel);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    public JButton getAddNewDatabaseButton() {
        return addNewDatabaseButton;
    }

    public JButton getShowAllDataButton() {
        return showAllDataButton;
    }

    public JTextField getUsername() {
        return username;
    }

    public JTextField getUrl() {
        return url;
    }

    public JRadioButton getExcelFileRadioButton() {
        return excelFileRadioButton;
    }

    public JTextField getPassword() {
        return password;
    }

    public JTable getAllFromAddMedicinesTable() {
        return allFromAddMedicinesTable;
    }

    public JButton getBackFromAddButton() {
        return backFromAddButton;
    }

    public JButton getSaveFromAddButton() {
        return saveFromAddButton;
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

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public JPanel getAllMedicinesPanel() {
        return allMedicinesPanel;
    }

    public JPanel getConnectPanel() {
        return connectPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getContPanel() {
        return contPanel;
    }
}
