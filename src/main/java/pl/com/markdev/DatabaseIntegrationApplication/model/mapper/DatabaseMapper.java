package pl.com.markdev.DatabaseIntegrationApplication.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseMapper implements RowMapper {

    @Override
    public DatabaseModel mapRow(ResultSet resultSet, int i) throws SQLException {
        DatabaseModel databaseModel = new DatabaseModel();
        databaseModel.setTableName(resultSet.getString("TABLE_NAME"));
        return databaseModel;
    }
}
