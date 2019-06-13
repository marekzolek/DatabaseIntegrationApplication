package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Controller
public class AllMedicinesController {

    @Autowired
    private AppForm appForm;

    @Autowired
    private MenuController menuController;

    private JTable allMedicinesTable;
    private JButton backButton;
    private JPanel allMedicinesPanel;

    public void initView() {

        allMedicinesTable = appForm.getAllMedicinesTable();
        backButton = appForm.getBackFromAllButton();

        appForm.getCardLayout().show(appForm.getContPanel(),"allMedicinesPanel");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");
                menuController.initView();
            }
        });
    }

    protected DefaultTableModel getDefaultTableModel() {
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

    protected void fillTable(List<MedicineModel> medicineModels, DefaultTableModel defaultTableModel) {
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

    public JTable getAllMedicinesTable() {
        return allMedicinesTable;
    }
}
