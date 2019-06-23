package pl.com.markdev.DatabaseIntegrationApplication.service;

import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface NewDatabaseService {

    void fillDatabaseTableComboBox(final String url, final String username, final String password);

    void fillColumnFromDatabase(final String tableName);

    void fillColumnComboBoxIfNotExistInMergeTable(List<String> columnNamesInMergeTable, String tableName);

    List<TableModel> getColumnNames(final String tableName);

    List<MedicineModel> allMedicines(String tableName);
}
