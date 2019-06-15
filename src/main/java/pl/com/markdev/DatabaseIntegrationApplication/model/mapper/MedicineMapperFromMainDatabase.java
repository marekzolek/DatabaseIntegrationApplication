package pl.com.markdev.DatabaseIntegrationApplication.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMapperFromMainDatabase implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        MedicineModel medicineModel = new MedicineModel();
        medicineModel.setId(resultSet.getLong("id"));
        medicineModel.setName(resultSet.getString("name"));
        medicineModel.setManufacturer(resultSet.getString("manufacturer"));
        medicineModel.setInternationalNamesOfIngredients(resultSet.getString("international_name_of_ingredients"));
        medicineModel.setForm(resultSet.getString("form"));
        medicineModel.setDose(resultSet.getString("dose"));
        medicineModel.setQuantityInPackage(resultSet.getString("quantity_in_package"));
        return medicineModel;
    }
}
