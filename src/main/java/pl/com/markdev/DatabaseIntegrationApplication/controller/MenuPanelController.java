package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.actionListeners.ActionListenersFromMenuPanel;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;

import java.awt.event.ActionListener;

@Controller
public class MenuPanelController {

    @Autowired
    private AppForm appForm;

    @Autowired
    private ActionListenersFromMenuPanel actionListenersFromMenuPanel;


    public void initController() {
        removeActionListeners();
        appForm.getAddNewDatabaseButton().addActionListener(actionListenersFromMenuPanel.addNewDatabaseButton());
        appForm.getShowAllDataButton().addActionListener(actionListenersFromMenuPanel.showAllDataButton());

    }

    private void removeActionListeners(){
        ActionListener[] actionListeners = appForm.getAddNewDatabaseButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getAddNewDatabaseButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getShowAllDataButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getShowAllDataButton().removeActionListener(actionListener);
        }
    }
}
