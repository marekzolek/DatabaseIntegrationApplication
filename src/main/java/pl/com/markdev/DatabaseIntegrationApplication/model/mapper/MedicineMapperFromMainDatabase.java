package pl.com.markdev.DatabaseIntegrationApplication.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MedicineMapperFromMainDatabase implements RowMapper {

    @Autowired
    private ColumnList columnList;

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        MedicineModel medicineModel = new MedicineModel();
        for (int numerOfColumn = 0; numerOfColumn < columnList.getColumnList().size(); numerOfColumn++) {
            if (numerOfColumn == 0) {
                medicineModel.getMedicineModelMap().put(columnList.getColumnList().get(numerOfColumn), String.valueOf(resultSet.getLong(columnList.getColumnList().get(numerOfColumn))));
            } else{
                medicineModel.getMedicineModelMap().put(columnList.getColumnList().get(numerOfColumn), resultSet.getString(columnList.getColumnList().get(numerOfColumn)));
            }
        }
        return medicineModel;
    }
}
