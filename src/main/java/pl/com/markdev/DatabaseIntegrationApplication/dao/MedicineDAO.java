package pl.com.markdev.DatabaseIntegrationApplication.dao;

import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import java.util.List;
import java.util.Map;

public interface MedicineDAO {

    List<DatabaseModel> databaseTables();

    Map<String, Integer> columnNamesFromExcel(int tableIndex, String url);

    List<String> columnListFromExcel(int tableIndex, String url);

    List<TableModel> columnNames(final String tableName);

    List<MedicineModel> allMedicines(final String tableName);
}
