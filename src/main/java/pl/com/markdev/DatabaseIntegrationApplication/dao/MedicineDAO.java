package pl.com.markdev.DatabaseIntegrationApplication.dao;

import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import java.util.List;

public interface MedicineDAO {

    List<TableModel> columnNames();

    List<MedicineModel> allMedicines();

    List<MedicineModel> allMedicinesFromExcel(List<String> rows);

    void saveAll(List<MedicineModel> medicines);
}
