package org.district.automation.utility;

public class ConfigReader {

    public static String getString(String key) {
        try {
            return PropertyReader.fetchPropertyValue(key).toString();
        } catch (Exception e) {
            throw new RuntimeException("Property not found: " + key);
        }
    }

    public static boolean getBoolean(String key) {
        try {
            return Boolean.parseBoolean(
                    PropertyReader.fetchPropertyValue(key).toString()
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid boolean for key: " + key);
        }
    }
}
