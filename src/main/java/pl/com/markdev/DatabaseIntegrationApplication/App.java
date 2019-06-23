package pl.com.markdev.DatabaseIntegrationApplication;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.DatabaseConnection;
import pl.com.markdev.DatabaseIntegrationApplication.controller.MenuPanelController;

public class App {
    public static void main(String[] args) {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConnection.class);

        MenuPanelController menuPanelController = ctx.getBean(MenuPanelController.class);
        menuPanelController.initController();
//        MenuController menuController = ctx.getBean(MenuController.class);
//        menuController.initView();
    }
}
