package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String getPropertyValue(String key) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("Resource/Config/config.properties")) {
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }}
