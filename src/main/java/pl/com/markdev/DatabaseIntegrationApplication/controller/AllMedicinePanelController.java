package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.actionListeners.ActionListenersFromAllMedicinesPanel;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;

import java.awt.event.ActionListener;

@Controller
public class AllMedicinePanelController {

    @Autowired
    private AppForm appForm;

    @Autowired
    private MainDatabaseService mainDatabaseService;

    @Autowired
    private ActionListenersFromAllMedicinesPanel actionListenersFromAllMedicinesPanel;


    public void initController(){
        mainDatabaseService.fillTablesComboBoxFromAll();
        removeActionListeners();
        appForm.getMainDatabaseTablesComboBox().addActionListener(actionListenersFromAllMedicinesPanel.mainDatabaseTablesComboBox());
        appForm.getBackFromAllButton().addActionListener(actionListenersFromAllMedicinesPanel.backButton());
    }

    private void removeActionListeners() {
        ActionListener[] actionListeners = appForm.getMainDatabaseTablesComboBox().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getMainDatabaseTablesComboBox().removeActionListener(actionListener);
        }
        actionListeners = appForm.getBackFromAllButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getBackFromAllButton().removeActionListener(actionListener);
        }
    }

}
