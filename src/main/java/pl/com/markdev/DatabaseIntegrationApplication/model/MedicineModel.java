package pl.com.markdev.DatabaseIntegrationApplication.model;

import java.util.HashMap;
import java.util.Map;

public class MedicineModel {

    private Map<String, String> medicineModelMap = new HashMap<>();

    public Map<String, String> getMedicineModelMap() {
        return medicineModelMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineModel that = (MedicineModel) o;

        return medicineModelMap != null ? medicineModelMap.equals(that.medicineModelMap) : that.medicineModelMap == null;
    }

    @Override
    public int hashCode() {
        return medicineModelMap != null ? medicineModelMap.hashCode() : 0;
    }
}
