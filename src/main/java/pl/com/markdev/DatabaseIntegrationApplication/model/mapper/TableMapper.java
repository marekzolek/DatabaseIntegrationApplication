package pl.com.markdev.DatabaseIntegrationApplication.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableMapper implements RowMapper {
    @Override
    public TableModel mapRow(ResultSet resultSet, int i) throws SQLException {
        TableModel tableModel = new TableModel();
        tableModel.setColumnName(resultSet.getString("COLUMN_NAME"));
        return tableModel;
    }
}
