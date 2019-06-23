package pl.com.markdev.DatabaseIntegrationApplication.dao;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.DatabaseMapper;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.TableMapper;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.MedicineMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MedicineDAOImpl implements MedicineDAO {

    @Value("$(MAIN_DATABASE_URL)")
    private String mainDatabaseUrl;

    @Value("$(MAIN_DATABASE_USERNAME)")
    private String mainDatabaseUsername;

    @Value("$(MAIN_DATABASE_PASSWORD)")
    private String mainDatabasePassword;

    @Autowired
    private CombineColumn combineColumn;

    @Autowired
    private ColumnList columnList;

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private MedicineMapper medicineMapper;

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<DatabaseModel> databaseTables() {

        String SQL = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'";
        List<DatabaseModel> databaseTables = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {

            databaseTables = jdbc.query(SQL, new DatabaseMapper());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseTables;
    }

    @Override
    public Map<String, Integer> columnNamesFromExcel(final int tableIndex, final String url) {

        Map<String, Integer> excelColumnMap = new HashMap<>();
        try {

            FileInputStream excelDatabase = new FileInputStream(new File(url));
            XSSFWorkbook workbook = new XSSFWorkbook(excelDatabase);

            XSSFSheet sheet = workbook.getSheetAt(tableIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            Row head = rowIterator.next();
            Iterator<Cell> headCellIterator = head.cellIterator();
            int column = 0;
            while (headCellIterator.hasNext()) {
                Cell cell = headCellIterator.next();
                excelColumnMap.put(cell.getStringCellValue(), column++);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelColumnMap;
    }

    @Override
    public List<String> columnListFromExcel(final int tableIndex, final String url) {
        List<String> result = new ArrayList<>();
        try {

            FileInputStream excelDatabase = new FileInputStream(new File(url));
            XSSFWorkbook workbook = new XSSFWorkbook(excelDatabase);

            XSSFSheet sheet = workbook.getSheetAt(tableIndex);
            Iterator<Row> rowIterator = sheet.iterator();
            Row head = rowIterator.next();
            Iterator<Cell> headCellIterator = head.cellIterator();
            while (headCellIterator.hasNext()) {
                Cell cell = headCellIterator.next();
                result.add(cell.getStringCellValue());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TableModel> columnNames(final String tableName) {

        String SQL = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = '" + tableName + "';";
        List<TableModel> tableModels = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {

            tableModels = jdbc.query(SQL, new TableMapper());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModels;
    }

    @Override
    public List<MedicineModel> allMedicines(String tableName) {

        String SQL = "SELECT * FROM " + tableName + ";";
        List<MedicineModel> medicines = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {

            medicines = jdbc.query(SQL, medicineMapper);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }
}
