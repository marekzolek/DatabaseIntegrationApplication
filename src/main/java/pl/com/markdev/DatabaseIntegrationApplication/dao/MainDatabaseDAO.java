package pl.com.markdev.DatabaseIntegrationApplication.dao;

import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import java.util.List;

public interface MainDatabaseDAO {

    List<DatabaseModel> databaseTables();

    List<TableModel> columnNames(String tableName);

    void saveAll(List<MedicineModel> medicines, String tableName);

    List<MedicineModel> allMedicines(final String tableName);

    void addNawColumn(String columnName, String tableName);
}
