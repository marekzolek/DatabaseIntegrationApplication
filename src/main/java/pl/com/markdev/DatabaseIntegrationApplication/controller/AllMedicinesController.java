package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MainDatabaseDAO;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class AllMedicinesController {

    @Autowired
    private CombineColumn combineColumn;

    @Autowired
    private ColumnList columnList;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MenuController menuController;

    @Autowired
    private MedicineDAO medicineDAO;

    @Autowired
    private MainDatabaseDAO mainDatabaseDAO;

    private JTable allMedicinesTable;
    private JButton backButton;
    private JComboBox mainDatabaseTablesComboBox;

    private List<MedicineModel> medicines;
    private List<TableModel> columnNames;

    public void initView() {

        allMedicinesTable = appForm.getAllMedicinesTable();
        backButton = appForm.getBackFromAllButton();
        mainDatabaseTablesComboBox = appForm.getMainDatabaseTablesComboBox();

        List<String> tempTablesList = new ArrayList<>();
        for (int i = 0; i < mainDatabaseTablesComboBox.getItemCount(); i++) {
            tempTablesList.add(mainDatabaseTablesComboBox.getItemAt(i).toString());
        }

        for (DatabaseModel databaseModel : mainDatabaseDAO.databaseTables()) {
            if (!tempTablesList.contains(databaseModel.getTableName())){
                mainDatabaseTablesComboBox.addItem(databaseModel.getTableName());
            }
        }

        mainDatabaseTablesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnNames = new ArrayList<>();
                columnNames = mainDatabaseDAO.columnNames(mainDatabaseTablesComboBox.getSelectedItem().toString());
                columnList.getColumnList().clear();
                for (TableModel columnName : columnNames) {
                    columnList.getColumnList().add(columnName.getColumnName());
                }
                medicines = new ArrayList<>();
                medicines = mainDatabaseDAO.allMedicines(mainDatabaseTablesComboBox.getSelectedItem().toString());
                DefaultTableModel defaultTableModel = getDefaultTableModel();
                fillTable(medicines, defaultTableModel);
                allMedicinesTable.setModel(defaultTableModel);
                allMedicinesTable.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                allMedicinesTable.setVisible(false);
                appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");
                menuController.initView();
            }
        });
    }

    private DefaultTableModel getDefaultTableModel() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String column : columnList.getColumnList()) {
            defaultTableModel.addColumn(column);
        }
        return defaultTableModel;
    }

    private void fillTable(List<MedicineModel> medicineModels, DefaultTableModel defaultTableModel) {
        for (MedicineModel medicineModel : medicineModels) {


            Object[] objects = new Object[medicineModel.keySet().size()];
            Iterator<String> iterator = combineColumn.keySet().iterator();
            for (int i = 0; i < medicineModel.keySet().size(); i++) {
                objects[defaultTableModel.findColumn(columnList.getColumnList().get(i))] = medicineModel.get(columnList.getColumnList().get(i));
            }
            defaultTableModel.addRow(objects);
        }
    }
}
