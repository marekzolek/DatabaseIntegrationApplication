package pl.com.markdev.DatabaseIntegrationApplication.service;

import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import java.util.List;
import java.util.Map;

public interface NewExcelDatabaseService {

    void fillExcelTableComboBox(final String url);

    void fillColumnComboBox(final Integer tableIndex, final String url);

    void fillColumnComboBoxIfNotExistInMergeTable(List<String> columnNamesInMergeTable, Integer tableNameIndex, String url);

    Map<String, Integer> getColumnMap(final Integer tableNameIndex, final String url);

    List<String> getColumnList(Integer tableIndex, String url);

    List<String> listMedicines(String url, List<String> columnList, Map<String, Integer> excelColumnMap);

    List<MedicineModel> allMedicines(List<String> rows, Map<String, Integer> excelColumnMap);
}
