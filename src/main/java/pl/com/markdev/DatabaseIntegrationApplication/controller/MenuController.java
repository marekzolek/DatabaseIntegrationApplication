package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.MyDataSource;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MedicineDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.MedicineModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MyDataSource dataSource;

    @Autowired
    private MedicineDAO medicineDAO;

    private List<MedicineModel> medicines;

    @Autowired
    private MedicineController medicineController;

    @Autowired
    private AllMedicinesController allMedicinesController;

    @Autowired
    private AppForm appForm;

//    public static AppForm appForm = new AppForm();

    private JButton addNewDatabase;
    private JButton showAllData;
    private JPanel menuPanel;

    public void initView() {

        addNewDatabase = appForm.getAddNewDatabaseButton();
        showAllData = appForm.getShowAllDataButton();
        appForm.getCardLayout().show(appForm.getContPanel(), "menuPanel");

        addNewDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(), "connectPanel");
                medicineController.initView();
            }
        });

        showAllData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appForm.getCardLayout().show(appForm.getContPanel(),"allMedicinesPanel");
                allMedicinesController.initView();

                dataSource.setUrl("jdbc:h2:tcp://localhost/~/DatabaseIntegrationApp");
                dataSource.setUsername("sa");
                dataSource.setPassword("");

                medicines = medicineDAO.allMedicines();
                DefaultTableModel defaultTableModel = allMedicinesController.getDefaultTableModel();
                allMedicinesController.fillTable(medicines, defaultTableModel);
                allMedicinesController.getAllMedicinesTable().setModel(defaultTableModel);

            }
        });
    }
}
