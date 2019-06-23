package pl.com.markdev.DatabaseIntegrationApplication.actionListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class ActionListenersFromAllMedicinesPanel {

    @Autowired
    private AppForm appForm;

    @Autowired
    private ColumnList columnList;

    @Autowired
    private MainDatabaseService mainDatabaseService;



    public ActionListener mainDatabaseTablesComboBox(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                mainDatabaseService.fillColumnListWithId(appForm.getMainDatabaseTablesComboBox().getSelectedItem().toString());

                List<MedicineModel> medicineModels = mainDatabaseService.allMedicines(appForm.getMainDatabaseTablesComboBox().getSelectedItem().toString());
                DefaultTableModel defaultTableModel = getDefaultTableModel();
                fillTable(medicineModels,defaultTableModel);
                appForm.getAllMedicinesTable().setModel(defaultTableModel);
                appForm.getAllMedicinesTable().setVisible(true);

            }
        };
        return actionListener;
    }

    public ActionListener backButton(){
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getMainDatabaseTablesComboBox().removeAllItems();
                appForm.getAllFromAddMedicinesTable().setVisible(false);
                appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");
            }
        };
        return result;
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


            Object[] objects = new Object[medicineModel.getMedicineModelMap().keySet().size()];
            for (int i = 0; i < medicineModel.getMedicineModelMap().keySet().size(); i++) {
                objects[defaultTableModel.findColumn(columnList.getColumnList().get(i))] = medicineModel.getMedicineModelMap().get(columnList.getColumnList().get(i));
            }
            defaultTableModel.addRow(objects);
        }
    }
}
