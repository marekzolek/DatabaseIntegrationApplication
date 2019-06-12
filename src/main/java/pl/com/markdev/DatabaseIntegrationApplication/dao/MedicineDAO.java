package pl.com.markdev.DatabaseIntegrationApplication.dao;

import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import java.util.List;

public interface MedicineDAO {

    List<MedicineModel> allMedicines();

    List<MedicineModel> allMedicinesFromExcel(List<String> rows);

    void saveAll(List<MedicineModel> medicines);
}
