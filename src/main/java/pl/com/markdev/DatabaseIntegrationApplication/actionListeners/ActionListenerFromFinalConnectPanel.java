package pl.com.markdev.DatabaseIntegrationApplication.actionListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.markdev.DatabaseIntegrationApplication.controller.AllMedicinePanelController;
import pl.com.markdev.DatabaseIntegrationApplication.controller.ConnectPanelController;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Component
public class ActionListenerFromFinalConnectPanel {

    @Autowired
    private AppForm appForm;

    @Autowired
    private MainDatabaseService mainDatabaseService;

    @Autowired
    private AllMedicinePanelController allMedicinePanelController;

    @Autowired
    private ConnectPanelController connectPanelController;

    public ActionListener saveButton(List<MedicineModel> medicineModels) {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainDatabaseService.saveAll(medicineModels, appForm.getMainDatabaseComboBox().getSelectedItem().toString());
                appForm.getCardLayout().show(appForm.getContPanel(), "allMedicinesPanel");
                allMedicinePanelController.initController();

            }
        };
        return result;
    }

    public ActionListener backFromFinalAddButton(){
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "connectPanel");
                connectPanelController.initController();
            }
        };
        return result;
    }
}
