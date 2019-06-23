package pl.com.markdev.DatabaseIntegrationApplication.actionListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.markdev.DatabaseIntegrationApplication.controller.AllMedicinePanelController;
import pl.com.markdev.DatabaseIntegrationApplication.controller.ConnectPanelController;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ActionListenersFromMenuPanel {

    @Autowired
    private AppForm appForm;

    @Autowired
    private ConnectPanelController connectPanelController;

    @Autowired
    private AllMedicinePanelController allMedicinePanelController;

    public ActionListener addNewDatabaseButton() {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "connectPanel");
                connectPanelController.initController();
            }
        };
        return result;
    }

    public ActionListener showAllDataButton() {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "allMedicinesPanel");
                allMedicinePanelController.initController();
            }
        };
        return result;
    }

}
