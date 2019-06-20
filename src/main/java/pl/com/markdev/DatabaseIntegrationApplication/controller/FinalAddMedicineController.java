package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MainDatabaseDAO;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class FinalAddMedicineController {

    @Autowired
    private ColumnList columnList;

    @Autowired
    private CombineColumn combineColumn;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private AllMedicinesController allMedicinesController;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MedicineDAO medicineDAO;

    @Autowired
    private MainDatabaseDAO mainDatabaseDAO;

    @Autowired
    private MedicineController medicineController;

    private JButton saveFromAddButton;
    private JButton backFromFinalAddButton;
    private JTextField urlFinalAdd;
    private JTextField usernameFinalAdd;
    private JTextField passwordFinalAdd;
    private JRadioButton excelFileRadioButtonFinalAdd;
    private JTable allFromAddMedicinesTable;

    private List<MedicineModel> medicineModels;

    public void initView(List<String> rows, int numberOfColumnsInExcel, Map<String, Integer> excelColumnMap){

        saveFromAddButton = appForm.getSaveFromAddButton();
        backFromFinalAddButton = appForm.getBackFromFinalAddButton();
        urlFinalAdd = appForm.getUrlFinalAdd();
        usernameFinalAdd = appForm.getUsernameFinalAdd();
        passwordFinalAdd = appForm.getPasswordFinalAdd();
        excelFileRadioButtonFinalAdd = appForm.getExcelFileRadioButtonFinalAdd();
        allFromAddMedicinesTable = appForm.getAllFromAddMedicinesTable();

        excelFileRadioButtonFinalAdd.setVisible(false);

        urlFinalAdd.setText(appForm.getUrl().getText());
        urlFinalAdd.setEditable(false);

        usernameFinalAdd.setText(appForm.getUsername().getText());
        usernameFinalAdd.setEditable(false);

        passwordFinalAdd.setText(appForm.getPassword().getText());
        passwordFinalAdd.setEditable(false);

        medicineModels = new ArrayList<>();
        if (appForm.getExcelFileRadioButton().isSelected()){
            medicineModels = medicineDAO.allMedicinesFromExcel(rows,numberOfColumnsInExcel, excelColumnMap);
        } else {
            medicineModels = medicineDAO.allMedicines(appForm.getTableSelectComboBox().getSelectedItem().toString());
        }
        DefaultTableModel defaultTableModel = getDefaultTableModel();
        fillTable(medicineModels,defaultTableModel);
        allFromAddMedicinesTable.setModel(defaultTableModel);

        saveFromAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                appForm.getCardLayout().show(appForm.getContPanel(), "allMedicinesPanel");
                allMedicinesController.initView();
                mainDatabaseDAO.saveAll(medicineModels, appForm.getMainDatabaseComboBox().getSelectedItem().toString());

            }
        });

        backFromFinalAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "connectPanel");
                medicineController.initView();
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
