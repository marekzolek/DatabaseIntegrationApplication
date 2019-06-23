package pl.com.markdev.DatabaseIntegrationApplication.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.NewDatabaseService;

import javax.swing.table.DefaultTableModel;
import java.util.List;

@Service
public class NewDatabaseServiceImpl implements NewDatabaseService {

    @Autowired
    private MyDataSource dataSource;
    @Autowired
    private AppForm appForm;

    @Autowired
    private MedicineDAO medicineDAO;

    @Override
    public void fillDatabaseTableComboBox(String url, String username, String password) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        List<DatabaseModel> databaseTables = medicineDAO.databaseTables();

        for (DatabaseModel databaseTable : databaseTables) {
            appForm.getTableSelectComboBox().addItem(databaseTable.getTableName());
        }
    }

    @Override
    public void fillColumnFromDatabase(String tableName) {

        appForm.getColumnFromOtherDatabaseComboBox().removeAllItems();
        List<TableModel> columnNames = medicineDAO.columnNames(appForm.getTableSelectComboBox().getSelectedItem().toString());
        for (TableModel columnName : columnNames) {
            appForm.getColumnFromOtherDatabaseComboBox().addItem(columnName.getColumnName());
        }
    }

    @Override
    public void fillColumnComboBoxIfNotExistInMergeTable(List<String> columnNamesInMergeTable, String tableName) {

        appForm.getColumnFromOtherDatabaseComboBox().removeAllItems();
        for (TableModel columnName : getColumnNames(tableName)) {
            if (!columnNamesInMergeTable.contains(columnName.getColumnName())) {
                appForm.getColumnFromOtherDatabaseComboBox().addItem(columnName.getColumnName());
            }
        }
    }

    @Override
    public List<TableModel> getColumnNames(String tableName) {
        List<TableModel> result = medicineDAO.columnNames(appForm.getTableSelectComboBox().getSelectedItem().toString());
        return result;
    }

    @Override
    public List<MedicineModel> allMedicines(final String tableName){
        List<MedicineModel> result = medicineDAO.allMedicines(tableName);
        return result;
    }
}
