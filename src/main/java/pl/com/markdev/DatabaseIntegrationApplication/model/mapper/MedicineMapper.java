package pl.com.markdev.DatabaseIntegrationApplication.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MedicineMapper implements RowMapper {

    @Autowired
    private CombineColumn combineColumn;

    @Override
    public MedicineModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MedicineModel medicineModel = new MedicineModel();
//        medicineModel.setId(resultSet.getLong(combineColumn.get("")));
        medicineModel.setName(resultSet.getString(combineColumn.get("name")));
        medicineModel.setManufacturer(resultSet.getString(combineColumn.get("manufacturer")));
        medicineModel.setInternationalNamesOfIngredients(resultSet.getString(combineColumn.get("international_name_of_ingredients")));
        medicineModel.setForm(resultSet.getString(combineColumn.get("form")));
        medicineModel.setDose(resultSet.getString(combineColumn.get("dose")));
        medicineModel.setQuantityInPackage(resultSet.getString(combineColumn.get("quantity_in_package")));
        return medicineModel;
    }
}
