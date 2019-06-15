package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CombineMedicinesController {

    @Autowired
    private MyDataSource dataSource;

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

    private FileInputStream excelDatabase;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterator<Row> rowIterator;
    private List<String> medsRow;
    private String rowMed;
    private Row head;
    private Iterator<Cell> headCellIterator;

    private Map<String, Integer> excelColumnMap;


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
        backFromSecondAddButton = appForm.getBackFromSecondAddButton();
        nextButton = appForm.getNextButton();
        excelFileRadioButtonSecondAdd = appForm.getExcelFileRadioButtonSecondAdd();

        excelFileRadioButtonSecondAdd.setVisible(false);

        urlTextField.setText(appForm.getUrl().getText());
        urlTextField.setEditable(false);

        usernameTextField.setText(appForm.getUsername().getText());
        usernameTextField.setEditable(false);

        passwordTextField.setText(appForm.getPassword().getText());
        passwordTextField.setEditable(false);

        columnNames = new ArrayList<>();

        jComboBoxes = new ArrayList<>();
        jComboBoxes.add(nameComboBox);
        jComboBoxes.add(manufacturerComboBox);
        jComboBoxes.add(internationalNameOfIngredientsComboBox);
        jComboBoxes.add(formComboBox);
        jComboBoxes.add(doseComboBox);
        jComboBoxes.add(quantityInPackageComboBox);


        if (appForm.getExcelFileRadioButton().isSelected()) {
            fillComboBoxesFromExcel();

        } else {
            columnNames = medicineDAO.columnNames();
            fillComboBoxes();
        }


        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fillCombineColumn();
                if(appForm.getExcelFileRadioButton().isSelected()) {
                    fillMedsRowList();
                }


                appForm.getCardLayout().show(appForm.getContPanel(), "finalConnectPanel");
                finalAddMedicineController.initView(medsRow);

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


    private void fillCombineColumn() {
        combineColumn.put(nameLabel.getText(), nameComboBox.getSelectedItem().toString());
        combineColumn.put(manufacturerLabel.getText(), manufacturerComboBox.getSelectedItem().toString());
        combineColumn.put(internationalNameOfIngredientsLabel.getText(), internationalNameOfIngredientsComboBox.getSelectedItem().toString());
        combineColumn.put(formLabel.getText(), formComboBox.getSelectedItem().toString());
        combineColumn.put(doseLabel.getText(), doseComboBox.getSelectedItem().toString());
        combineColumn.put(quantityInPackageLabel.getText(), quantityInPackageComboBox.getSelectedItem().toString());
    }

    private void fillComboBoxesFromExcel() {
        try {
        excelDatabase = new FileInputStream(new File(urlTextField.getText()));
        workbook = new XSSFWorkbook(excelDatabase);
        sheet = workbook.getSheetAt(0);
        rowIterator = sheet.iterator();
        medsRow = new ArrayList<>();
        excelColumnMap = new HashMap<>();

        head = rowIterator.next();
        headCellIterator = head.cellIterator();
        int column = 0;
        while (headCellIterator.hasNext()) {
            Cell cell = headCellIterator.next();
            excelColumnMap.put(cell.getStringCellValue(), column++);
            for (JComboBox jComboBox : jComboBoxes) {
                jComboBox.addItem(cell.getStringCellValue());
            }

        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillMedsRowList() {
        while (rowIterator.hasNext()) {
            rowMed = "";

            Row row = rowIterator.next();
            rowMed = rowMed
                    + row.getCell(excelColumnMap.get(combineColumn.get("name"))) + "/t"
                    + row.getCell(excelColumnMap.get(combineColumn.get("manufacturer"))) + "/t"
                    + row.getCell(excelColumnMap.get(combineColumn.get("international_name_of_ingredients"))) + "/t"
                    + row.getCell(excelColumnMap.get(combineColumn.get("form"))) + "/t"
                    + row.getCell(excelColumnMap.get(combineColumn.get("dose"))) + "/t"
                    + row.getCell(excelColumnMap.get(combineColumn.get("quantity_in_package")));

            medsRow.add(rowMed);
        }
    }

    private void fillComboBoxes() {

        for (JComboBox jComboBox : jComboBoxes) {
            jComboBox.removeAllItems();
        }

        for (JComboBox jComboBox : jComboBoxes) {
            for (TableModel tableModel : columnNames) {
                jComboBox.addItem(tableModel.getColumnName());
            }
        }
    }
}

