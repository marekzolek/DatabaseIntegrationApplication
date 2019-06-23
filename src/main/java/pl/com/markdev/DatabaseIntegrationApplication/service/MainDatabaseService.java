package pl.com.markdev.DatabaseIntegrationApplication.service;

import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import java.util.List;

public interface MainDatabaseService {

    void fillTableComboBox();

    void fillColumnComboBox(final String tableName);

    void fillColumnList(final String tableName);

    void fillColumnListWithId(String tableName);

    List<TableModel> allColumns(final String tableName);

    void saveAll(final List<MedicineModel> medicineModels, final String tableName);

    void fillColumnComboBoxIfNotExistInMergeTable(final List<String> columnNamesInMergeTable, final String tableName);

    List<MedicineModel> allMedicines(String tableName);

    void fillTablesComboBoxFromAll();
}
