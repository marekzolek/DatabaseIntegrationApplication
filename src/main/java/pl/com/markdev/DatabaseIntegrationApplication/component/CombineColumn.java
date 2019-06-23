package pl.com.markdev.DatabaseIntegrationApplication.component;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CombineColumn {
    private Map<String, String> combineColumn = new HashMap<>();

    public Map<String, String> getCombineColumn() {
        return combineColumn;
    }
}
