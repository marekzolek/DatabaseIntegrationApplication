package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.markdev.DatabaseIntegrationApplication.component.ColumnList;
import pl.com.markdev.DatabaseIntegrationApplication.dao.MainDatabaseDAO;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;
import pl.com.markdev.DatabaseIntegrationApplication.model.DatabaseModel;
import pl.com.markdev.DatabaseIntegrationApplication.model.TableModel;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Controller
public class MenuController {

    @Autowired
    private MedicineController medicineController;

    @Autowired
    private AllMedicinesController allMedicinesController;

    @Autowired
    private AppForm appForm;

    private JButton addNewDatabase;
    private JButton showAllData;

    public void initView() {

        addNewDatabase = appForm.getAddNewDatabaseButton();
        showAllData = appForm.getShowAllDataButton();

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
                appForm.getCardLayout().show(appForm.getContPanel(), "allMedicinesPanel");
                allMedicinesController.initView();
            }
        });
    }
}
