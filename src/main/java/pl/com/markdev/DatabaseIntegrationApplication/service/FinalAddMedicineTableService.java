package pl.com.markdev.DatabaseIntegrationApplication.service;

import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public interface FinalAddMedicineTableService {

    DefaultTableModel getDefaultTableModel();

    void fillTable(final List<MedicineModel> medicineModels, DefaultTableModel defaultTableModel);
}
