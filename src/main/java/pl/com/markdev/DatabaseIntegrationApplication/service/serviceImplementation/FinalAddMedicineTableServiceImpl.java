package pl.com.markdev.DatabaseIntegrationApplication.service.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.FinalAddMedicineTableService;

import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class FinalAddMedicineTableServiceImpl implements FinalAddMedicineTableService {

    @Autowired
    private ColumnList columnList;

    @Autowired
    private CombineColumn combineColumn;

    @Override
    public DefaultTableModel getDefaultTableModel() {
        DefaultTableModel result = new DefaultTableModel();
        for (String column : columnList.getColumnList()) {
            result.addColumn(column);
        }

        return result;
    }

    @Override
    public void fillTable(List<MedicineModel> medicineModels, DefaultTableModel defaultTableModel) {

        for (MedicineModel medicineModel : medicineModels) {
            Object[] objects = new Object[medicineModel.getMedicineModelMap().keySet().size()];
            for (int i = 0; i < medicineModel.getMedicineModelMap().keySet().size(); i++) {
                objects[defaultTableModel.findColumn(columnList.getColumnList().get(i))] = medicineModel.getMedicineModelMap().get(columnList.getColumnList().get(i));
            }
            defaultTableModel.addRow(objects);
        }
    }
}
