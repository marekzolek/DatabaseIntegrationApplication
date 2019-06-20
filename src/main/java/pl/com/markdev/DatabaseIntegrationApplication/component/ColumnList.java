package pl.com.markdev.DatabaseIntegrationApplication.component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class ColumnList /*extends ArrayList<String>*/ {
    private ArrayList<String> columnList = new ArrayList<>();

    public ArrayList<String> getColumnList() {
        return columnList;
    }
}
