package algsex.support.doubling_ratio_testing;

import java.util.HashMap;
import java.util.HashMap;

public class RunDetails {
    HashMap<String, Integer> integers = new HashMap<>();
    HashMap<String, Double> doubles = new HashMap<>();

    public void setInt(String key, int value) { integers.put(key, value); }
    public int getInt(String key) { return integers.get(key); }
    public void setDouble(String key, double value) { doubles.put(key, value); }
    public double getDouble(String key) { return doubles.get(key); }
}