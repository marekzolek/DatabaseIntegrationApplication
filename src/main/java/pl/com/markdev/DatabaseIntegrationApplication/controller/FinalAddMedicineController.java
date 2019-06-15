package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
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
import java.util.List;

@Controller
public class FinalAddMedicineController {

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private AllMedicinesController allMedicinesController;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MedicineDAO medicineDAO;

    @Autowired
    private CombineMedicinesController combineMedicinesController;

    private JButton saveFromAddButton;
    private JButton backFromFinalAddButton;
    private JTextField urlFinalAdd;
    private JTextField usernameFinalAdd;
    private JTextField passwordFinalAdd;
    private JRadioButton excelFileRadioButtonFinalAdd;
    private JTable allFromAddMedicinesTable;

    private List<MedicineModel> medicineModels;

    public void initView(List<String> rows){

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
            medicineModels = medicineDAO.allMedicinesFromExcel(rows);
        } else {
            medicineModels = medicineDAO.allMedicines();
        }
        DefaultTableModel defaultTableModel = getDefaultTableModel();
        fillTable(medicineModels,defaultTableModel);

        allFromAddMedicinesTable.setModel(defaultTableModel);

        saveFromAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                appForm.getCardLayout().show(appForm.getContPanel(), "allMedicinesPanel");
                allMedicinesController.initView();
                medicineDAO.saveAll(medicineModels);

            }
        });

        backFromFinalAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "combinePanel");
                combineMedicinesController.initView();
            }
        });
    }

    private DefaultTableModel getDefaultTableModel() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
//        defaultTableModel.addColumn("ID");
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
//                    medicineModel.getId(),
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
