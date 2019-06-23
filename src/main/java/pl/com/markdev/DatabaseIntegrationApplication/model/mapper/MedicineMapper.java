package pl.com.markdev.DatabaseIntegrationApplication.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MedicineMapper implements RowMapper {

    @Autowired
    private CombineColumn combineColumn;

    @Override
    public MedicineModel mapRow(ResultSet resultSet, int i) throws SQLException {
        MedicineModel testMedicineModel = new MedicineModel();
        for (String newDatabaseColumn : combineColumn.getCombineColumn().values()) {
        testMedicineModel.getMedicineModelMap().put(newDatabaseColumn, resultSet.getString(newDatabaseColumn));
        }
        return testMedicineModel;
    }
}
