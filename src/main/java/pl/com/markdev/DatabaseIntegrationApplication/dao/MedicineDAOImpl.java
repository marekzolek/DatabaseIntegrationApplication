package pl.com.markdev.DatabaseIntegrationApplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.mapper.MedicineMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MedicineDAOImpl implements MedicineDAO {

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<MedicineModel> allMedicines() {
        String SQL = "SELECT * FROM medicines";
        List<MedicineModel> medicines = new ArrayList<>();

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            medicines = jdbc.query(SQL, new MedicineMapper());

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
        return medicines;
    }

    @Override
    public List<MedicineModel> allMedicinesFromExcel(List<String> rows) {

        List<MedicineModel> result = new ArrayList<>();
        String[] rowMedicine = new String[7];


        for (String row : rows) {
            String[] med = row.split("/t");
            List<String> collect = Arrays.stream(med).collect(Collectors.toList());
            for (int i = 0; i < rowMedicine.length; i++) {
                if (i >= collect.size()) {
                    rowMedicine[i] = "";
                } else {
                    rowMedicine[i] = collect.get(i).replace("'", "`");
                }
            }
            MedicineModel medicineModel = new MedicineModel();
            medicineModel.setId((long) Math.round(Double.parseDouble(rowMedicine[0])));
            medicineModel.setName(rowMedicine[1]);
            medicineModel.setManufacturer(rowMedicine[2]);
            medicineModel.setInternationalNamesOfIngredients(rowMedicine[3]);
            medicineModel.setForm(rowMedicine[4]);
            medicineModel.setDose(rowMedicine[5]);
            medicineModel.setQuantityInPackage(rowMedicine[6]);
            result.add(medicineModel);
        }

        return result;
    }

    @Override
    public void saveAll(List<MedicineModel> medicines) {

        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            String SQL;
            for (MedicineModel medicine : medicines) {
                SQL = "INSERT INTO MEDICINES (NAME, MANUFACTURER, INTERNATIONAL_NAME_OF_INGREDIENTS, FORM, DOSE, QUANTITY_IN_PACKAGE) VALUES("
                        + "'" + medicine.getName() + "', "
                        + "'" + medicine.getManufacturer() + "', "
                        + "'" + medicine.getInternationalNamesOfIngredients() + "', "
                        + "'" + medicine.getForm() + "', "
                        + "'" + medicine.getDose() + "', "
                        + "'" + medicine.getQuantityInPackage() + "');";
                jdbc.execute(SQL);
            }
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
    }
}
