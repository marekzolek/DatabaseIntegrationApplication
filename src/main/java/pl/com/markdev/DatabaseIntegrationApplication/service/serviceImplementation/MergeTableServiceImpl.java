package pl.com.markdev.DatabaseIntegrationApplication.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.service.MergeTableService;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MergeTableServiceImpl implements MergeTableService {

    @Autowired
    private AppForm appForm;

    @Autowired
    private CombineColumn combineColumn;

    @Override
    public DefaultTableModel getDefaultTableModel() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("MAIN DATABASE COLUMN");
        defaultTableModel.addColumn("NEW DATABASE COLUMN");
        return defaultTableModel;
    }

    @Override
    public void fillTable(String mainDatabaseColumn, String newDatabaseColumn, DefaultTableModel defaultTableModel) {
        defaultTableModel.addRow(new String[]{mainDatabaseColumn, newDatabaseColumn});

    }

    @Override
    public List<String> getColumnNamesFromMainDatabaseInTable(DefaultTableModel defaultTableModel) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            result.add(defaultTableModel.getValueAt(i, 0).toString());
        }
        return result;
    }

    @Override
    public List<String> getColumnNamesFromNewDatabaseInTable(DefaultTableModel defaultTableModel) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            result.add(defaultTableModel.getValueAt(i, 1).toString());
        }
        return result;
    }

    @Override
    public void fillCombineColumn(){

        for (int i = 0; i < appForm.getMergeTable().getModel().getRowCount(); i++) {
            combineColumn.getCombineColumn().put(appForm.getMergeTable().getModel().getValueAt(i, 0).toString(),
                    appForm.getMergeTable().getModel().getValueAt(i, 1).toString());

        }
    }
}
