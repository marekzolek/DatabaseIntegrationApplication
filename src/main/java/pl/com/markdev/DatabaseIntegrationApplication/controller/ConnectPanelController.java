package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.actionListeners.ActionListenersFromConnectPanel;
import pl.com.markdev.DatabaseIntegrationApplication.service.MergeTableService;

import java.awt.event.ActionListener;

@Controller
public class ConnectPanelController {

    @Autowired
    private AppForm appForm;

    @Autowired
    private ActionListenersFromConnectPanel actionListenersFromConnectPanel;

    @Autowired
    private MergeTableService mergeTableService;

    public void initController(){
        removeActionListeners();
        appForm.getConnectButton().addActionListener(actionListenersFromConnectPanel.connectButton());
        appForm.getMergeButton().addActionListener(actionListenersFromConnectPanel.mergeButton(mergeTableService.getDefaultTableModel()));
        appForm.getChooseTableButton().addActionListener(actionListenersFromConnectPanel.chooseTableButton());
        appForm.getExcelFileRadioButton().addActionListener(actionListenersFromConnectPanel.excelFileRadioButton());
        appForm.getBackFromAddButton().addActionListener(actionListenersFromConnectPanel.backButton());
        appForm.getFirstNextButton().addActionListener(actionListenersFromConnectPanel.nextButton());
    }

    private void removeActionListeners() {

//        Arrays.stream(appForm.getConnectButton().getActionListeners()).collect(Collectors.toList()).clear();
        ActionListener[] actionListeners = appForm.getConnectButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getConnectButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getMergeButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getMergeButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getChooseTableButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getChooseTableButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getExcelFileRadioButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getExcelFileRadioButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getBackFromAddButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getBackFromAddButton().removeActionListener(actionListener);
        }
        actionListeners = appForm.getFirstNextButton().getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            appForm.getFirstNextButton().removeActionListener(actionListener);
        }
    }


}
