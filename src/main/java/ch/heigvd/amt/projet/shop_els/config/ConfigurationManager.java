package ch.heigvd.amt.projet.shop_els.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final String configurationProperties = "config/config.properties";
    private String path;

    public ConfigurationManager() throws IOException {
        Properties properties;
        try {
            // Getting the path to the image directory
            properties = new Properties();
            properties.load(new FileInputStream(configurationProperties));
            this.path = properties.getProperty("img_path");
            System.out.println(this.path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPath(){ return path; }
/*
    private void loadPropertiesFile() throws IOException{
        Properties properties;
        try {
            // Getting the path to the image directory
            properties = new Properties();
            properties.load(new FileInputStream(configurationProperties));
            this.path = properties.getProperty("path");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
