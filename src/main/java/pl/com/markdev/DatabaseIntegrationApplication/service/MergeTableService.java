package pl.com.markdev.DatabaseIntegrationApplication.service;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public interface MergeTableService {

    DefaultTableModel getDefaultTableModel();

    void fillTable(final String mainDatabaseColumn, final String newDatabaseColumn, final DefaultTableModel defaultTableModel);

    List<String> getColumnNamesFromMainDatabaseInTable(final DefaultTableModel defaultTableModel);

    List<String> getColumnNamesFromNewDatabaseInTable(final DefaultTableModel defaultTableModel);

    void fillCombineColumn();
}
