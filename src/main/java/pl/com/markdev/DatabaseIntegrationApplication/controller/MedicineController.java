package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MainDatabaseDAO;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class MedicineController {
    @Autowired
    private FinalAddMedicineController finalAddMedicineController;

    @Autowired
    private MainDatabaseDAO mainDatabaseDAO;

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
    private ColumnList columnList;

    private String url;
    private String username;
    private String password;

    private JTextField urlField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton firstNextButton;
    private JRadioButton excelFile;
    private JButton backButton;

    private JComboBox tableSelectComboBox;
    private JTable mergeTable;
    private JButton connectButton;
    private JComboBox columnFromMainDatabaseComboBox;
    private JComboBox columnFromOtherDatabaseComboBox;
    private JButton addColumnButton;
    private JButton mergeButton;
    private JComboBox mainDatabaseComboBox;
    private JButton chooseTableButton;

    private JLabel equalTable;
    private JLabel equalColumn;
    private JLabel mainLabel;
    private JLabel addLabel;
    private JLabel selectTableLabel;


    List<DatabaseModel> mainDatabaseTables;
    List<DatabaseModel> databaseTables;
    List<TableModel> mainDatabaseColumnNames;
    List<TableModel> columnNames;

    private FileInputStream excelDatabase;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterator<Row> rowIterator;
    private List<String> medsRow;
    private String rowMed;
    private Row head;
    private Iterator<Cell> headCellIterator;
    private Map<String, Integer> excelColumnMap;
    private DefaultTableModel defaultTableModel;

    private int rowNumber;

    public void initView() {

        urlField = appForm.getUrl();
        urlField.setEditable(true);
        usernameField = appForm.getUsername();
        usernameField.setEditable(true);
        passwordField = appForm.getPassword();
        passwordField.setEditable(true);
        firstNextButton = appForm.getFirstNextButton();
        excelFile = appForm.getExcelFileRadioButton();
        excelFile.setEnabled(true);
        backButton = appForm.getBackFromAddButton();
        tableSelectComboBox = appForm.getTableSelectComboBox();
        mergeTable = appForm.getMergeTable();
        connectButton = appForm.getConnectButton();
        connectButton.setEnabled(true);
        columnFromMainDatabaseComboBox = appForm.getColumnFromMainDatabaseComboBox();
        columnFromOtherDatabaseComboBox = appForm.getColumnFromOtherDatabaseComboBox();
        addColumnButton = appForm.getAddColumnButton();
        mergeButton = appForm.getMergeButton();
        mainDatabaseComboBox = appForm.getMainDatabaseComboBox();
        equalTable = appForm.getEqualTable();
        equalColumn = appForm.getEqualColumn();
        mainLabel = appForm.getMainLabel();
        addLabel = appForm.getAddLabel();
        selectTableLabel = appForm.getSelectTableLabel();
        chooseTableButton = appForm.getChooseTableButton();

        defaultTableModel = getDefaultTableModel();

        excelColumnMap = new HashMap<>();
        rowNumber = 0;

        mainDatabaseComboBox.removeAllItems();
        mainDatabaseTables = new ArrayList<>();
        mainDatabaseTables = mainDatabaseDAO.databaseTables();

        for (DatabaseModel databaseTable : mainDatabaseTables) {
            mainDatabaseComboBox.addItem(databaseTable.getTableName());
        }

        chooseTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addColumnButton.setVisible(true);
                mergeButton.setVisible(true);
                columnFromMainDatabaseComboBox.setVisible(true);
                columnFromOtherDatabaseComboBox.setVisible(true);
                chooseTableButton.setEnabled(false);
                mergeButton.setEnabled(true);
                defaultTableModel.setRowCount(0);

                fillColumnFromMainDatabaseComboBox();

                fillColumnFromOtherDatabaseComboBox();

            }
        });

        mergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> tempMainList = new ArrayList<>();
                List<String> tempNewList = new ArrayList<>();
                String mainDatabaseColumn = columnFromMainDatabaseComboBox.getSelectedItem().toString();
                String newDatabaseColumn = columnFromOtherDatabaseComboBox.getSelectedItem().toString();

                fillTable(mainDatabaseColumn, newDatabaseColumn, defaultTableModel);
                mergeTable.setModel(defaultTableModel);
                mergeTable.setVisible(true);

                for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
                    tempNewList.add(defaultTableModel.getValueAt(i, 1).toString());
                }

                for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
                    tempMainList.add(defaultTableModel.getValueAt(i, 0).toString());
                }

                columnFromMainDatabaseComboBox.removeAllItems();
                columnFromOtherDatabaseComboBox.removeAllItems();
                if (excelFile.isSelected() && !excelFile.isEnabled()) {
                    fillColumnIfExcelComboBoxes(tempMainList, tempNewList);
                } else {
                    fillColumnIfDatabaseComboBoxes(tempMainList, tempNewList);
                }
            }

            private void fillColumnIfDatabaseComboBoxes(List<String> tempMainList, List<String> tempNewList) {
                for (TableModel columnName : columnNames) {
                    if (defaultTableModel.getRowCount() == 0) {
                        columnFromOtherDatabaseComboBox.addItem(columnName.getColumnName());
                    } else {
                        if (!tempNewList.contains(columnName.getColumnName())) {
                            columnFromOtherDatabaseComboBox.addItem(columnName.getColumnName());
                        }
                    }
                }

                for (TableModel mainDatabaseColumnName : mainDatabaseColumnNames) {
                    if (defaultTableModel.getRowCount() == 0) {
                        columnFromMainDatabaseComboBox.addItem(mainDatabaseColumnName.getColumnName());
                    } else {
                        if (!tempMainList.contains(mainDatabaseColumnName.getColumnName())) {
                            columnFromMainDatabaseComboBox.addItem(mainDatabaseColumnName.getColumnName());
                        }
                    }
                }
            }

            private void fillColumnIfExcelComboBoxes(List<String> tempMainList, List<String> tempNewList) {
                for (String columnName : excelColumnMap.keySet()) {
                    if (defaultTableModel.getRowCount() == 0) {
                        columnFromOtherDatabaseComboBox.addItem(columnName);
                    } else {
                        if (!tempNewList.contains(columnName)) {
                            columnFromOtherDatabaseComboBox.addItem(columnName);
                        }
                    }
                }

                for (TableModel mainDatabaseColumnName : mainDatabaseColumnNames) {
                    if (defaultTableModel.getRowCount() == 0) {
                        columnFromMainDatabaseComboBox.addItem(mainDatabaseColumnName.getColumnName());
                    } else {
                        if (!tempMainList.contains(mainDatabaseColumnName.getColumnName())) {
                            columnFromMainDatabaseComboBox.addItem(mainDatabaseColumnName.getColumnName());
                        }
                    }
                }
            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlField.setEditable(false);
                usernameField.setEditable(false);
                passwordField.setEditable(false);

                mainDatabaseComboBox.setVisible(true);
                tableSelectComboBox.setVisible(true);
                selectTableLabel.setVisible(true);
                chooseTableButton.setVisible(true);
                chooseTableButton.setEnabled(true);
                excelFile.setEnabled(false);
                connectButton.setEnabled(false);

                url = urlField.getText();
                username = usernameField.getText();
                password = passwordField.getText();

                if (excelFile.isSelected()) {
                    fillIfExcelTableComboBoxes();
                } else {
                    fillIfDatabaseTableComboBoxes();
                }
            }

            private void fillIfDatabaseTableComboBoxes() {
                dataSource.setUrl(url);
                dataSource.setUsername(username);
                dataSource.setPassword(password);

                databaseTables = new ArrayList<>();
                databaseTables = medicineDAO.databaseTables();

                for (DatabaseModel databaseTable : databaseTables) {
                        tableSelectComboBox.addItem(databaseTable.getTableName());
                }
            }

            private void fillIfExcelTableComboBoxes() {
                tableSelectComboBox.removeAllItems();
                try {
                    excelDatabase = new FileInputStream(new File(url));
                    workbook = new XSSFWorkbook(excelDatabase);

                    Iterator<XSSFSheet> sheetIterator = workbook.iterator();

                    while (sheetIterator.hasNext()) {
                        XSSFSheet sheet = sheetIterator.next();
                        tableSelectComboBox.addItem(sheet.getSheetName());
                    }
                    excelDatabase.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

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
//
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnFromMainDatabaseComboBox.setVisible(false);
                columnFromOtherDatabaseComboBox.setVisible(false);
                mainDatabaseComboBox.setVisible(false);
                tableSelectComboBox.setVisible(false);
                chooseTableButton.setVisible(false);
                excelFile.setSelected(false);
                equalTable.setVisible(false);
                selectTableLabel.setVisible(false);
                addColumnButton.setVisible(false);
                mergeButton.setVisible(false);
                mergeButton.setEnabled(false);
                appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");
                menuController.initView();
            }
        });

        firstNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                url = urlField.getText();
                username = usernameField.getText();
                password = passwordField.getText();

                for (int i = 0; i < mergeTable.getModel().getRowCount(); i++) {
                    combineColumn.put(mergeTable.getModel().getValueAt(i, 0).toString(),
                            mergeTable.getModel().getValueAt(i, 1).toString());
                }

                medsRow = new ArrayList<>();
                if (excelFile.isSelected()) {

                    sheet = workbook.getSheetAt(tableSelectComboBox.getSelectedIndex());
                    rowIterator = sheet.iterator();
                    while (rowIterator.hasNext()) {
                        rowMed = "";

                        Row row = rowIterator.next();
                        for (String columnName : columnList.getColumnList()) {
                            rowMed += row.getCell(excelColumnMap.get(combineColumn.get(columnName))) + "/t";
                        }
                        medsRow.add(rowMed);
                    }
                } else {

                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                }

                appForm.getCardLayout().show(appForm.getContPanel(), "finalConnectPanel");
                finalAddMedicineController.initView(medsRow, excelColumnMap.keySet().size(), excelColumnMap);
            }
        });
    }

    private void fillColumnFromOtherDatabaseComboBox() {
        columnFromOtherDatabaseComboBox.removeAllItems();
        columnNames = new ArrayList<>();
        if (appForm.getExcelFileRadioButton().isSelected()) {
            excelColumnMap = medicineDAO.columnNamesFromExcel(tableSelectComboBox.getSelectedIndex(), url);
            for (String columnName : excelColumnMap.keySet()) {
                columnFromOtherDatabaseComboBox.addItem(columnName);
            }

        } else {
            columnNames = medicineDAO.columnNames(tableSelectComboBox.getSelectedItem().toString());
            for (TableModel columnName : columnNames) {
                columnFromOtherDatabaseComboBox.addItem(columnName.getColumnName());
            }
        }
    }

    private void fillColumnFromMainDatabaseComboBox() {
        columnList.getColumnList().clear();
        columnFromMainDatabaseComboBox.removeAllItems();
        mainDatabaseColumnNames = new ArrayList<>();
        mainDatabaseColumnNames = mainDatabaseDAO.columnNames(mainDatabaseComboBox.getSelectedItem().toString());
        for (int i = 1; i < mainDatabaseColumnNames.size(); i++) {
            columnList.getColumnList().add(mainDatabaseColumnNames.get(i).getColumnName());
        }
        for (TableModel mainDatabaseColumnName : mainDatabaseColumnNames) {
            columnFromMainDatabaseComboBox.addItem(mainDatabaseColumnName.getColumnName());
        }
    }


    private DefaultTableModel getDefaultTableModel() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("MAIN DATABASE COLUMN");
        defaultTableModel.addColumn("NEW DATABASE COLUMN");
        return defaultTableModel;
    }

    private void fillTable(String mainDatabaseColumn, String newDatabaseColumn, DefaultTableModel defaultTableModel) {
        defaultTableModel.addRow(new String[]{mainDatabaseColumn, newDatabaseColumn});
    }
}
