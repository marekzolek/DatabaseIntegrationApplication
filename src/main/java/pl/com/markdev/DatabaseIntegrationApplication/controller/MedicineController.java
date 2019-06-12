package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.MedicineForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MedicineController {

    private MedicineForm medicineForm;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private MedicineDAO medicineDAO;

    private String url;
    private String username;
    private String password;
    List<MedicineModel> medicineModels = new ArrayList<>();

    private JPanel medicinePanel;
    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton connectButton;
    private JTable table1;
    private JRadioButton excelFile;

    public MedicineController() {

        medicineForm = new MedicineForm();

        urlField = medicineForm.getUrl();
        usernameField = medicineForm.getUsername();
        passwordField = medicineForm.getPassword();
        connectButton = medicineForm.getConnectButton();
        table1 = medicineForm.getTable1();
        excelFile = medicineForm.getExcelFile();

        excelFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (excelFile.isSelected()) {
                    usernameField.setEditable(false);
//                    usernameField.setText("");
                    passwordField.setEditable(false);
//                    passwordField.setText("");
                } else {
                    usernameField.setEditable(true);
                    passwordField.setEditable(true);
                }
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

                if (excelFile.isSelected()){
                    try {
                        FileInputStream excelDatabase = new FileInputStream(new File(url));
                        XSSFWorkbook workbook = new XSSFWorkbook (excelDatabase);
                        XSSFSheet sheet = workbook.getSheetAt(0);
                        Iterator<Row> rowIterator = sheet.iterator();

                        List<String> medsRow = new ArrayList<>();
                        String rowMed = "";
                        while (rowIterator.hasNext()){
                            rowMed = "";

                            Row row = rowIterator.next();
                            Iterator<Cell> cellIterator = row.cellIterator();
                            while (cellIterator.hasNext()){
                                Cell cell = cellIterator.next();

                                switch (cell.getCellType()){
                                    case Cell.CELL_TYPE_NUMERIC:
                                        rowMed = cell.getNumericCellValue() + "/t ";
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        rowMed = rowMed + cell.getStringCellValue()+ "/t";
                                        break;
                                }
                            }
                            medsRow.add(rowMed);
                        }

                        medicineModels = medicineDAO.allMedicinesFromExcel(medsRow);

                        dataSource.setUrl("jdbc:h2:tcp://localhost/~/DatabaseIntegrationApp");
                        dataSource.setUsername("sa");
                        dataSource.setPassword("");

                        medicineDAO.saveAll(medicineModels);


                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {


                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                    medicineModels = medicineDAO.allMedicines();

                    dataSource.setUrl("jdbc:h2:tcp://localhost/~/DatabaseIntegrationApp");
                    dataSource.setUsername("sa");
                    dataSource.setPassword("");

                    medicineDAO.saveAll(medicineModels);
                }
                DefaultTableModel defaultTableModel = getDefaultTableModel();
                fillTable(medicineModels, defaultTableModel);

                table1.setModel(defaultTableModel);
            }

        });
    }

    private DefaultTableModel getDefaultTableModel() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("NAME");
        defaultTableModel.addColumn("MANUFACTURER");
        defaultTableModel.addColumn("INTERNATIONAL_NAME_OF_INGREDIENTS");
        defaultTableModel.addColumn("FORM");
        defaultTableModel.addColumn("DOSE");
        defaultTableModel.addColumn("QUANTITY_IN_PACKAGE");
        return defaultTableModel;
    }

    private void fillTable(List<MedicineModel> medicineModels, DefaultTableModel defaultTableModel) {
        for (MedicineModel medicineModel : medicineModels) {
            defaultTableModel.addRow(new Object[]{
                    medicineModel.getId(),
                    medicineModel.getName(),
                    medicineModel.getManufacturer(),
                    medicineModel.getInternationalNamesOfIngredients(),
                    medicineModel.getForm(),
                    medicineModel.getDose(),
                    medicineModel.getQuantityInPackage()
            });
        }
    }

    public JPanel getMedicinePanel() {
        medicinePanel = medicineForm.getMedicinePanel();
        return medicinePanel;
    }
}
