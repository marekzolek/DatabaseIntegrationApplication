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
        MedicineModel medicineModel = new MedicineModel();
        for (String columnName : combineColumn.getCombineColumn().keySet()) {
            medicineModel.getMedicineModelMap().put(columnName, resultSet.getString(combineColumn.getCombineColumn().get(columnName)));
        }
        return medicineModel;
    }
}
