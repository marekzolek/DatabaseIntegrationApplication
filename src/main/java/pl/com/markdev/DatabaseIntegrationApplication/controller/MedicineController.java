package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Controller
public class MedicineController {

    @Autowired
    private CombineColumn combineColumn;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MenuController menuController;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private MedicineDAO medicineDAO;

    @Autowired
    private CombineMedicinesController combineMedicinesController;

    private String url;
    private String username;
    private String password;

    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton connectButton;
    private JRadioButton excelFile;
    private JButton backButton;

    public void initView() {

        urlField = appForm.getUrl();
        usernameField = appForm.getUsername();
        passwordField = appForm.getPassword();
        connectButton = appForm.getConnectButton();
        excelFile = appForm.getExcelFileRadioButton();
        backButton = appForm.getBackFromAddButton();

        excelFile.setVisible(true);

        excelFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (excelFile.isSelected()) {
                    usernameField.setEditable(false);
//                    usernameField.setText("");
                    passwordField.setEditable(false);
//                    passwordField.setText("");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");
                menuController.initView();
            }
        });

        showAll();
    }

    public void showAll() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                url = urlField.getText();
                username = usernameField.getText();
                password = passwordField.getText();


                if (excelFile.isSelected()) {

                    appForm.getCardLayout().show(appForm.getContPanel(), "combinePanel");
                    combineMedicinesController.initView();
                } else {

                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                    appForm.getCardLayout().show(appForm.getContPanel(), "combinePanel");
                    combineMedicinesController.initView();
                }
            }
        });
    }
}
