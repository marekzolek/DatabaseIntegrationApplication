package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CombineMedicinesController {

    @Autowired
    private AppForm appForm;

    @Autowired
    private MedicineDAO medicineDAO;

    @Autowired
    private CombineColumn combineColumn;

    @Autowired
    private MedicineController medicineController;

    @Autowired
    private FinalAddMedicineController finalAddMedicineController;

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

    private List<JComboBox> jComboBoxes;
    private List<TableModel> columnNames;

    public void initView() {

        urlTextField = appForm.getUrlTextField();
        usernameTextField = appForm.getUsernameTextField();
        passwordTextField = appForm.getPasswordTextField();
        nameComboBox = appForm.getNameComboBox();
        manufacturerComboBox = appForm.getManufacturerComboBox();
        internationalNameOfIngredientsComboBox = appForm.getInternationalNameOfIngredientsComboBox();
        formComboBox = appForm.getFormComboBox();
        doseComboBox = appForm.getDoseComboBox();
        quantityInPackageComboBox = appForm.getQuantityInPackageComboBox();
        nameLabel = appForm.getNameLabel();
        manufacturerLabel = appForm.getManufacturerLabel();
        internationalNameOfIngredientsLabel = appForm.getInternationalNameOfIngredientsLabel();
        formLabel = appForm.getFormLabel();
        doseLabel = appForm.getDoseLabel();
        quantityInPackageLabel = appForm.getQuantityInPackageLabel();
        backFromSecondAddButton = appForm.getBackFromAddButton();
        nextButton = appForm.getNextButton();
        excelFileRadioButtonSecondAdd = appForm.getExcelFileRadioButtonSecondAdd();

        excelFileRadioButtonSecondAdd.setVisible(false);

        urlTextField.setText(appForm.getUrl().getText());
        urlTextField.setEditable(false);

        usernameTextField.setText(appForm.getUsername().getText());
        usernameTextField.setEditable(false);

        passwordTextField.setText(appForm.getPassword().getText());
        passwordTextField.setEditable(false);

        jComboBoxes = new ArrayList<>();
        jComboBoxes.add(nameComboBox);
        jComboBoxes.add(manufacturerComboBox);
        jComboBoxes.add(internationalNameOfIngredientsComboBox);
        jComboBoxes.add(formComboBox);
        jComboBoxes.add(doseComboBox);
        jComboBoxes.add(quantityInPackageComboBox);

        fillComboBoxes();

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combineColumn.put(nameLabel.getText(),nameComboBox.getSelectedItem().toString());
                combineColumn.put(manufacturerLabel.getText(),manufacturerComboBox.getSelectedItem().toString());
                combineColumn.put(internationalNameOfIngredientsLabel.getText(),internationalNameOfIngredientsComboBox.getSelectedItem().toString());
                combineColumn.put(formLabel.getText(),formComboBox.getSelectedItem().toString());
                combineColumn.put(doseLabel.getText(),doseComboBox.getSelectedItem().toString());
                combineColumn.put(quantityInPackageLabel.getText(),quantityInPackageComboBox.getSelectedItem().toString());
                appForm.getCardLayout().show(appForm.getContPanel(), "finalConnectPanel");
                finalAddMedicineController.initView();
            }
        });

        backFromSecondAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "connectPanel");
                medicineController.initView();
            }
        });


    }

    private void fillComboBoxes() {
        columnNames = medicineDAO.columnNames();

        for (JComboBox jComboBox : jComboBoxes) {
            for (TableModel tableModel : columnNames) {
                jComboBox.addItem(tableModel.getColumnName());
            }
        }
    }
}
