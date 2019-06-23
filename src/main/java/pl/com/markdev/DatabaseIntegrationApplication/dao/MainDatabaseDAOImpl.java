package pl.com.markdev.DatabaseIntegrationApplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.DatabaseMapper;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.MedicineMapperFromMainDatabase;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.TableMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MainDatabaseDAOImpl implements MainDatabaseDAO {

//    @Value("$(MAIN_DATABASE_URL)")
    private String mainDatabaseUrl = "jdbc:h2:tcp://localhost/~/DatabaseIntegrationApp";

//    @Value("$(MAIN_DATABASE_USERNAME)")
    private String mainDatabaseUsername = "sa";

//    @Value("$(MAIN_DATABASE_PASSWORD)")
    private String mainDatabasePassword = "";

    @Autowired
    private MedicineMapperFromMainDatabase medicineMapperFromMainDatabase;

    @Autowired
    private ColumnList columnList;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<DatabaseModel> databaseTables(){

        dataSource.setUrl(mainDatabaseUrl);
        dataSource.setUsername(mainDatabaseUsername);
        dataSource.setPassword(mainDatabasePassword);

        String SQL = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='TABLE'";
        List<DatabaseModel> databaseTables = new ArrayList();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            databaseTables = jdbc.query(SQL, new DatabaseMapper());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return databaseTables;

    }

    @Override
    public List<TableModel> columnNames(final String tableName){

        dataSource.setUrl(mainDatabaseUrl);
        dataSource.setUsername(mainDatabaseUsername);
        dataSource.setPassword(mainDatabasePassword);

        String SQL = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = '" + tableName + "'";
        List<TableModel> tableModels = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            tableModels = jdbc.query(SQL, new TableMapper());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModels;
    }

    @Override
    public void saveAll(List<MedicineModel> medicines, String tableName) {
        dataSource.setUrl(mainDatabaseUrl);
        dataSource.setUsername(mainDatabaseUsername);
        dataSource.setPassword(mainDatabasePassword);

        try (Connection conn = dataSource.getConnection()){

            String SQL;
                for (MedicineModel medicine : medicines) {
                    SQL = "INSERT INTO " + tableName + " (";
                    for (String columnName : columnList.getColumnList()) {
                        SQL += columnName + ", ";
                    }
                    SQL += ") VALUES (";
                    for (String columnName : columnList.getColumnList()) {
                        SQL += "'" + medicine.getMedicineModelMap().get(columnName) + "', ";
                    }
                    SQL += ");";
                    jdbc.execute(SQL);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MedicineModel> allMedicines(final String tableName) {

        dataSource.setUrl(mainDatabaseUrl);
        dataSource.setUsername(mainDatabaseUsername);
        dataSource.setPassword(mainDatabasePassword);

        String SQL = "SELECT * FROM " + tableName + ";";
        List<MedicineModel> medicines = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()){
            medicines = jdbc.query(SQL, medicineMapperFromMainDatabase);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }
}
