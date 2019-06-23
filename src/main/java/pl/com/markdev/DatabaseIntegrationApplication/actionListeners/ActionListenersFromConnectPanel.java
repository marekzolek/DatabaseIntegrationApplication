package pl.com.markdev.DatabaseIntegrationApplication.actionListeners;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.controller.FinalConnectPanelController;
import pl.com.markdev.DatabaseIntegrationApplication.controller.MenuPanelController;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.FinalAddMedicineTableService;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;
import pl.com.markdev.DatabaseIntegrationApplication.service.MergeTableService;
import pl.com.markdev.DatabaseIntegrationApplication.service.NewDatabaseService;
import pl.com.markdev.DatabaseIntegrationApplication.service.NewExcelDatabaseService;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ActionListenersFromConnectPanel {

    @Autowired
    private ColumnList columnList;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private AppForm appForm;

    @Autowired
    private MainDatabaseService mainDatabaseService;

    @Autowired
    private MergeTableService mergeTableService;

    @Autowired
    private NewDatabaseService newDatabaseService;

    @Autowired
    private NewExcelDatabaseService newExcelDatabaseService;

    @Autowired
    private FinalAddMedicineTableService finalAddMedicineTableService;

    @Autowired
    private FinalConnectPanelController finalConnectPanelController;

    @Autowired
    private MenuPanelController menuPanelController;

    public ActionListener connectButton() {

        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getUrl().setEditable(false);
                appForm.getUsername().setEditable(false);
                appForm.getPassword().setEditable(false);

                appForm.getMainDatabaseComboBox().setVisible(true);
                appForm.getTableSelectComboBox().setVisible(true);
                appForm.getSelectTableLabel().setVisible(true);
                appForm.getChooseTableButton().setVisible(true);
                appForm.getExcelFileRadioButton().setEnabled(false);
                appForm.getConnectButton().setEnabled(false);

                mainDatabaseService.fillTableComboBox();

                String url = appForm.getUrl().getText();
                String username = appForm.getUsername().getText();
                String password = appForm.getPassword().getText();

                if (appForm.getExcelFileRadioButton().isSelected()) {
                    newExcelDatabaseService.fillExcelTableComboBox(url);
                } else {
                    newDatabaseService.fillDatabaseTableComboBox(url, username, password);
                }

            }

        };
        return result;
    }

    public ActionListener chooseTableButton() {

        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                appForm.getAddColumnButton().setVisible(true);
                appForm.getMergeButton().setVisible(true);
                appForm.getColumnFromMainDatabaseComboBox().setVisible(true);
                appForm.getColumnFromOtherDatabaseComboBox().setVisible(true);
                appForm.getChooseTableButton().setEnabled(false);
                appForm.getMergeButton().setEnabled(true);

                String url = appForm.getUrl().getText();
                String username = appForm.getUsername().getText();
                String password = appForm.getPassword().getText();

                mainDatabaseService.fillColumnComboBox(appForm.getMainDatabaseComboBox().getSelectedItem().toString());

                if (appForm.getExcelFileRadioButton().isSelected()) {
                    newExcelDatabaseService.fillColumnComboBox(appForm.getTableSelectComboBox().getSelectedIndex(), appForm.getUrl().getText());
                } else {

                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                    newDatabaseService.fillColumnFromDatabase(appForm.getTableSelectComboBox().getSelectedItem().toString());
                }
            }
        };
        return result;
    }

    public ActionListener mergeButton(DefaultTableModel defaultTableModel) {

        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = appForm.getUrl().getText();
                String username = appForm.getUsername().getText();
                String password = appForm.getPassword().getText();

                String mainDatabaseColumn = appForm.getColumnFromMainDatabaseComboBox().getSelectedItem().toString();
                String newDatabaseColumn = appForm.getColumnFromOtherDatabaseComboBox().getSelectedItem().toString();

                mergeTableService.fillTable(mainDatabaseColumn, newDatabaseColumn, defaultTableModel);
                appForm.getMergeTable().setModel(defaultTableModel);
                appForm.getMergeTable().setVisible(true);

                List<String> tempMainColumns = mergeTableService.getColumnNamesFromMainDatabaseInTable(defaultTableModel);
                List<String> tempNewColumns = mergeTableService.getColumnNamesFromNewDatabaseInTable(defaultTableModel);

                mainDatabaseService.fillColumnComboBoxIfNotExistInMergeTable(tempMainColumns, appForm.getMainDatabaseComboBox().getSelectedItem().toString());

                if (appForm.getExcelFileRadioButton().isSelected()) {
                    newExcelDatabaseService.fillColumnComboBoxIfNotExistInMergeTable(tempNewColumns, appForm.getTableSelectComboBox().getSelectedIndex(), appForm.getUrl().getText());
                } else {

                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                    newDatabaseService.fillColumnComboBoxIfNotExistInMergeTable(tempNewColumns, appForm.getTableSelectComboBox().getSelectedItem().toString());
                }
            }
        };
        return result;
    }

    public ActionListener excelFileRadioButton() {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (appForm.getExcelFileRadioButton().isSelected()) {
                    appForm.getUsername().setEditable(false);
                    appForm.getPassword().setEditable(false);
                } else {
                    appForm.getUsername().setEditable(true);
                    appForm.getPassword().setEditable(true);
                }
            }
        };
        return result;
    }

    public ActionListener backButton() {

        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getColumnFromMainDatabaseComboBox().setVisible(false);
                appForm.getColumnFromOtherDatabaseComboBox().setVisible(false);
                appForm.getMainDatabaseComboBox().setVisible(false);
                appForm.getTableSelectComboBox().setVisible(false);
                appForm.getChooseTableButton().setVisible(false);
                appForm.getExcelFileRadioButton().setSelected(false);
                appForm.getEqualTable().setVisible(false);
                appForm.getSelectTableLabel().setVisible(false);
                appForm.getAddColumnButton().setVisible(false);
                appForm.getMergeButton().setVisible(false);
                appForm.getMergeButton().setEnabled(false);

                appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");
                menuPanelController.initController();
            }


        };
        return result;
    }

    public ActionListener nextButton() {
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = appForm.getUrl().getText();
                String username = appForm.getUsername().getText();
                String password = appForm.getPassword().getText();

                mergeTableService.fillCombineColumn();
                mainDatabaseService.fillColumnListWithId(appForm.getMainDatabaseComboBox().getSelectedItem().toString());

                List<MedicineModel> medicineModels;
                if (appForm.getExcelFileRadioButton().isSelected()) {

                    Map<String, Integer> excelColumnMap = newExcelDatabaseService.getColumnMap(appForm.getTableSelectComboBox().getSelectedIndex(), url);
                    List<String> excelColumnNames = newExcelDatabaseService.getColumnList(appForm.getTableSelectComboBox().getSelectedIndex(), url);
                    List<String> meds = newExcelDatabaseService.listMedicines(url, excelColumnNames, excelColumnMap);
                    medicineModels = newExcelDatabaseService.allMedicines(meds, excelColumnMap);
                } else {

                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);

                    medicineModels = newDatabaseService.allMedicines(appForm.getTableSelectComboBox().getSelectedItem().toString());

                }

                appForm.getCardLayout().show(appForm.getContPanel(), "finalConnectPanel");
                finalConnectPanelController.initController(medicineModels);
            }
        };
        return result;
    }
}
