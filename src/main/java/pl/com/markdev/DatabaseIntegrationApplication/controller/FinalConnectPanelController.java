package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.actionListeners.ActionListenerFromFinalConnectPanel;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.FinalAddMedicineTableService;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;

@Controller
public class FinalConnectPanelController {

    @Autowired
    private ActionListenerFromFinalConnectPanel actionListenerFromFinalConnectPanel;

    @Autowired
    private FinalAddMedicineTableService finalAddMedicineTableService;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MainDatabaseService mainDatabaseService;

    public void initController(List<MedicineModel> medicineModels){

        mainDatabaseService.fillColumnList(appForm.getMainDatabaseComboBox().getSelectedItem().toString());
        DefaultTableModel defaultTableModel = finalAddMedicineTableService.getDefaultTableModel();
        finalAddMedicineTableService.fillTable(medicineModels, defaultTableModel);
        appForm.getAllFromAddMedicinesTable().setModel(defaultTableModel);

        removeActionListeners();
        appForm.getBackFromFinalAddButton().addActionListener(actionListenerFromFinalConnectPanel.backFromFinalAddButton());
        appForm.getSaveFromAddButton().addActionListener(actionListenerFromFinalConnectPanel.saveButton(medicineModels));
    }

    private void removeActionListeners(){
        ActionListener[] actionListeners = appForm.getSaveFromAddButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getSaveFromAddButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getBackFromFinalAddButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getBackFromFinalAddButton().removeActionListener(actionListener);
        }
    }
}
