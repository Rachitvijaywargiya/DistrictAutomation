package org.district.automation.utility;

public class ConfigReader {

    public static String getString(String key) {
        try {
            return Utility.fetchPropertyValue(key).toString();
        } catch (Exception e) {
            throw new RuntimeException("Property not found: " + key);
        }
    }

    public static int getInt(String key) {
        try {
            return Integer.parseInt(Utility.fetchPropertyValue(key).toString());
        } catch (Exception e) {
            throw new RuntimeException("Invalid integer for key: " + key);
        }
    }

    public static boolean getBoolean(String key) {
        try {
            return Boolean.parseBoolean(
                    Utility.fetchPropertyValue(key).toString()
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid boolean for key: " + key);
        }
    }
}
