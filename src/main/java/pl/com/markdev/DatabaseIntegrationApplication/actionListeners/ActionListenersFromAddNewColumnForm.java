package pl.com.markdev.DatabaseIntegrationApplication.actionListeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AddNewColumnForm;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ActionListenersFromAddNewColumnForm {

    @Autowired
    private AddNewColumnForm addNewColumnForm;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MainDatabaseService mainDatabaseService;


    public ActionListener okButton() {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tableName = appForm.getMainDatabaseComboBox().getSelectedItem().toString();
                String newColumnName = addNewColumnForm.getNewColumnNameTextField().getText().toUpperCase();
                mainDatabaseService.addNewColumn(newColumnName, tableName);
                mainDatabaseService.fillColumnComboBox(tableName);
                addNewColumnForm.dispose();
            }
        };
        return result;
    }

    public ActionListener cancelButton() {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewColumnForm.dispose();

            }
        };
        return result;
    }
}
