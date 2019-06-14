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
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
    List<MedicineModel> medicineModels = new ArrayList<>();
    List<TableModel> columnNames = new ArrayList<>();

    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton connectButton;
    private JTable table1;
    private JRadioButton excelFile;
    private JButton saveButton;
    private JButton backButton;

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
    List<JComboBox> jComboBoxes = new ArrayList<>();



    public void initView() {

        urlField = appForm.getUrl();
        usernameField = appForm.getUsername();
        passwordField = appForm.getPassword();
        connectButton = appForm.getConnectButton();
        table1 = appForm.getAllFromAddMedicinesTable();
        excelFile = appForm.getExcelFileRadioButton();
        saveButton = appForm.getSaveFromAddButton();
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
                } else {
                    usernameField.setEditable(true);
                    passwordField.setEditable(true);
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataSource.setUrl("jdbc:h2:tcp://localhost/~/DatabaseIntegrationApp");
                dataSource.setUsername("sa");
                dataSource.setPassword("");

                medicineDAO.saveAll(medicineModels);
            }
        });
    }

    public void showAll() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                url = urlField.getText();
                username = usernameField.getText();
                password = passwordField.getText();


                if (excelFile.isSelected()) {
                    try {
                        FileInputStream excelDatabase = new FileInputStream(new File(url));
                        XSSFWorkbook workbook = new XSSFWorkbook(excelDatabase);
                        XSSFSheet sheet = workbook.getSheetAt(0);
                        Iterator<Row> rowIterator = sheet.iterator();

                        List<String> medsRow = new ArrayList<>();
                        String rowMed = "";
                        while (rowIterator.hasNext()) {
                            rowMed = "";

                            Row row = rowIterator.next();
                            Iterator<Cell> cellIterator = row.cellIterator();
                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();

                                switch (cell.getCellType()) {
                                    case Cell.CELL_TYPE_NUMERIC:
                                        rowMed = cell.getNumericCellValue() + "/t ";
                                        break;
                                    case Cell.CELL_TYPE_STRING:
                                        rowMed = rowMed + cell.getStringCellValue() + "/t";
                                        break;
                                }
                            }
                            medsRow.add(rowMed);
                        }

                        medicineModels = medicineDAO.allMedicinesFromExcel(medsRow);

                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {

                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                    appForm.getCardLayout().show(appForm.getContPanel(), "combinePanel");
                    combineMedicinesController.initView();


//                    medicineModels = medicineDAO.allMedicines();

                }
//                DefaultTableModel defaultTableModel = getDefaultTableModel();
//                fillTable(medicineModels, defaultTableModel);
//
//                table1.setModel(defaultTableModel);
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
}
