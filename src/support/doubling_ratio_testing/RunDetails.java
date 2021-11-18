package algsex.support.doubling_ratio_testing;

import java.util.HashMap;
import java.util.HashMap;

public class RunDetails {
    HashMap<String, Object> properties = new HashMap<>();

    public void put(String statId, Object value) {
        properties.put(statId, value);
    }

    public Object get(String statId) {
        return properties.get(statId);
    }
}