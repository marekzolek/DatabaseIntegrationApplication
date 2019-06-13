package pl.com.markdev.DatabaseIntegrationApplication;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import pl.com.markdev.DatabaseIntegrationApplication.cfg.DatabaseConnection;
import pl.com.markdev.DatabaseIntegrationApplication.controller.MenuController;
import pl.com.markdev.DatabaseIntegrationApplication.forms.AppForm;

public class App {
    public static void main(String[] args) {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConnection.class);

        MenuController menuController = ctx.getBean(MenuController.class);
        menuController.initView();
    }
}
