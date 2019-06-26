package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.actionListeners.ActionListenersFromAddNewColumnForm;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AddNewColumnForm;

import java.awt.event.ActionListener;

@Controller
public class AddNewColumnController {

    @Autowired
    private AddNewColumnForm addNewColumnForm;

    @Autowired
    private ActionListenersFromAddNewColumnForm actionListenersFromAddNewColumnForm;

    public void initController(){
        removeActionListeners();
        addNewColumnForm.getOkButton().addActionListener(actionListenersFromAddNewColumnForm.okButton());
        addNewColumnForm.getCancelButton().addActionListener(actionListenersFromAddNewColumnForm.cancelButton());
    }

    private void removeActionListeners() {

        ActionListener[] actionListeners = addNewColumnForm.getOkButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            addNewColumnForm.getOkButton().removeActionListener(actionListener);
        }
        actionListeners = addNewColumnForm.getCancelButton().getActionListeners();

        for (ActionListener actionListener : actionListeners) {
            addNewColumnForm.getCancelButton().removeActionListener(actionListener);
        }
    }
}
