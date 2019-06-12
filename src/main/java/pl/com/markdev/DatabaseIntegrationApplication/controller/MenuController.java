package pl.com.markdev.DatabaseIntegrationApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.markdev.DatabaseIntegrationApplication.forms.MenuForm;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class MenuController {

    @Autowired
    private MedicineController medicineController;

    private MenuForm menuForm;

    private JButton addNewDatabase;
    private JButton showAllData;

    public MenuController() {

        menuForm = new MenuForm();

        addNewDatabase = menuForm.getAddNewDatabase();
        showAllData = menuForm.getShowAllData();


        addNewDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menuForm.getContentPane().removeAll();
                menuForm.setContentPane(medicineController.getMedicinePanel());
                menuForm.revalidate();
                menuForm.repaint();
            }
        });
    }

    public void showMenu(){
        menuForm.setVisible(true);
    }
}
