package pl.com.markdev.DatabaseIntegrationApplication.service.serviceImplementation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.component.CombineColumn;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.service.NewExcelDatabaseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewExcelDatabaseServiceImpl implements NewExcelDatabaseService {

    @Autowired
    private AppForm appForm;

    @Autowired
    private MedicineDAO medicineDAO;

    @Autowired
    private CombineColumn combineColumn;

    @Autowired
    private ColumnList columnList;

    @Override
    public void fillExcelTableComboBox(final String url) {
        appForm.getTableSelectComboBox().removeAllItems();
        try {
            FileInputStream excelDatabase = new FileInputStream(new File(url));
            XSSFWorkbook workbook = new XSSFWorkbook(excelDatabase);

            Iterator<XSSFSheet> sheetIterator = workbook.iterator();

            while (sheetIterator.hasNext()) {
                XSSFSheet sheet = sheetIterator.next();
                appForm.getTableSelectComboBox().addItem(sheet.getSheetName());
            }
            excelDatabase.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fillColumnComboBox(Integer tableIndex, String url) {

        appForm.getColumnFromOtherDatabaseComboBox().removeAllItems();
        for (String columnName : getColumnMap(tableIndex, url).keySet()) {
            appForm.getColumnFromOtherDatabaseComboBox().addItem(columnName);
        }
    }

    @Override
    public void fillColumnComboBoxIfNotExistInMergeTable(List<String> columnNamesInMergeTable, Integer tableNameIndex, String url) {

        appForm.getColumnFromOtherDatabaseComboBox().removeAllItems();
        for (String columnName : getColumnMap(tableNameIndex, url).keySet()) {
            if (!columnNamesInMergeTable.contains(columnName)) {
                appForm.getColumnFromOtherDatabaseComboBox().addItem(columnName);
            }
        }
    }

    @Override
    public Map<String, Integer> getColumnMap(Integer tableNameIndex, String url) {
        Map<String, Integer> result = medicineDAO.columnNamesFromExcel(tableNameIndex, url);
        return result;
    }

    @Override
    public List<String> getColumnList(final Integer tableIndex, final String url) {
        List<String> result = medicineDAO.columnListFromExcel(tableIndex, url);

        return result;
    }

    @Override
    public List<String> listMedicines(String url, List<String> excelColumnList, Map<String, Integer> excelColumnMap) {

        List<String> result = new ArrayList<>();
        String rowMed;
        try {
            FileInputStream excelDatabase = new FileInputStream(new File(url));
            XSSFWorkbook workbook = new XSSFWorkbook(excelDatabase);
            XSSFSheet sheet = workbook.getSheetAt(appForm.getTableSelectComboBox().getSelectedIndex());
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                rowMed = "";

                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                for (String columnName : excelColumnList) {
                    rowMed += row.getCell(excelColumnMap.get(columnName)) + "/t";
                }
                result.add(rowMed);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MedicineModel> allMedicines(List<String> rows, Map<String, Integer> excelColumnMap) {

        List<MedicineModel> result = new ArrayList<>();

        String[] rowMedicine = new String[columnList.getColumnList().size()];

        for (int i = 1; i < rows.size(); i++) {
            String[] med = rows.get(i).split("/t");
            List<String> collect = Arrays.stream(med).collect(Collectors.toList());
            for (int j = 0; j < rowMedicine.length; j++) {
                if (j >= collect.size()) {
                    rowMedicine[j] = "";//na koncu nie wstawiałpustego miejsca
                } else {
                    rowMedicine[j] = collect.get(j).replace("'", "`");
                }
            }

            MedicineModel medicineModel = new MedicineModel();

            for (String columnName : columnList.getColumnList()) {
                for (String excelColumnName : excelColumnMap.keySet()) {
                    if (combineColumn.getCombineColumn().containsKey(columnName) && combineColumn.getCombineColumn().get(columnName).equals(excelColumnName)) {
                        medicineModel.getMedicineModelMap().put(columnName, rowMedicine[excelColumnMap.get(excelColumnName)]);
                    }
                }
            }
            result.add(medicineModel);

        }
        return result;
    }
}
