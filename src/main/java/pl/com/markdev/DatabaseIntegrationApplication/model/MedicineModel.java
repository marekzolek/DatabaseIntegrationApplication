package pl.com.markdev.DatabaseIntegrationApplication.model;

public class MedicineModel {

    private Long id;
    private String name;
    private String manufacturer;
    private String internationalNamesOfIngredients;
    private String form;
    private String dose;
    private String quantityInPackage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getInternationalNamesOfIngredients() {
        return internationalNamesOfIngredients;
    }

    public void setInternationalNamesOfIngredients(String internationalNamesOfIngredients) {
        this.internationalNamesOfIngredients = internationalNamesOfIngredients;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getQuantityInPackage() {
        return quantityInPackage;
    }

    public void setQuantityInPackage(String quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    @Override
    public String toString() {
        return "MedicineModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", internationalNamesOfIngredients='" + internationalNamesOfIngredients + '\'' +
                ", form='" + form + '\'' +
                ", dose='" + dose + '\'' +
                ", quantityInPackage='" + quantityInPackage + '\'' +
                '}';
    }
}
