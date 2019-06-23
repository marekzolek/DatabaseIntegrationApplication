package pl.com.markdev.DatabaseIntegrationApplication.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MainDatabaseDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.MainDatabaseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainDatabaseServiceImpl implements MainDatabaseService {

    @Autowired
    private MainDatabaseDAO mainDatabaseDAO;

    @Autowired
    private AppForm appForm;

    @Autowired
    private ColumnList columnList;

    @Override
    public void fillTableComboBox() {
        appForm.getMainDatabaseComboBox().removeAllItems();
        List<DatabaseModel> mainDatabaseTables = mainDatabaseDAO.databaseTables();
        for (DatabaseModel databaseTable : mainDatabaseTables) {
            appForm.getMainDatabaseComboBox().addItem(databaseTable.getTableName());
        }
    }

    @Override
    public void fillColumnComboBox(final String tableName) {
        appForm.getColumnFromMainDatabaseComboBox().removeAllItems();
        for (TableModel mainDatabaseColumnName : allColumns(tableName)) {
            appForm.getColumnFromMainDatabaseComboBox().addItem(mainDatabaseColumnName.getColumnName());
        }

    }

    @Override
    public void fillColumnList(final String tableName) {
        List<TableModel> tableModels = mainDatabaseDAO.columnNames(tableName);
        columnList.getColumnList().clear();
        for (int i = 1; i < tableModels.size(); i++) {
            columnList.getColumnList().add(tableModels.get(i).getColumnName());
        }
    }

    @Override
    public void fillColumnListWithId(final String tableName) {
        List<TableModel> tableModels = mainDatabaseDAO.columnNames(tableName);
        columnList.getColumnList().clear();
        for (int i = 0; i < tableModels.size(); i++) {
            columnList.getColumnList().add(tableModels.get(i).getColumnName());
        }
    }

    @Override
    public List<TableModel> allColumns(final String tableName) {
        List<TableModel> result = mainDatabaseDAO.columnNames(tableName);
        return result;
    }

    @Override
    public void saveAll(List<MedicineModel> medicineModels, String tableName) {
        mainDatabaseDAO.saveAll(medicineModels, tableName);
    }

    @Override
    public void fillColumnComboBoxIfNotExistInMergeTable(final List<String> columnNamesInMergeTable, final String tableName) {

        appForm.getColumnFromMainDatabaseComboBox().removeAllItems();
        for (TableModel mainDatabaseColumnName : allColumns(tableName)) {
            if (!columnNamesInMergeTable.contains(mainDatabaseColumnName.getColumnName())) {
                appForm.getColumnFromMainDatabaseComboBox().addItem(mainDatabaseColumnName.getColumnName());
            }
        }
    }

    @Override
    public List<MedicineModel> allMedicines(String tableName){
        List<MedicineModel> result = mainDatabaseDAO.allMedicines(tableName);
        return result;
    }

    @Override
    public void fillTablesComboBoxFromAll(){
        for (DatabaseModel databaseModel : mainDatabaseDAO.databaseTables()) {
            appForm.getMainDatabaseTablesComboBox().addItem(databaseModel.getTableName());
        }
    }
}
