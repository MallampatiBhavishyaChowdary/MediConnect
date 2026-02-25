package com.edutech.progressive.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnectionManager {

    private static final Properties properties = new Properties();
    private static volatile boolean loaded = false;

    private DatabaseConnectionManager() { }

    private static void loadProperties() {
        if (loaded) return;
        synchronized (DatabaseConnectionManager.class) {
            if (loaded) return;
            try (InputStream in = DatabaseConnectionManager.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (in == null) {
                    throw new RuntimeException("application.properties not found on classpath");
                }
                properties.load(in);

                String driver = properties.getProperty("spring.datasource.driver-class-name");
                if (driver != null && !driver.isBlank()) {
                    Class.forName(driver);
                }
                loaded = true;
            } catch (Exception e) {
                throw new RuntimeException("Failed to load database properties", e);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        loadProperties();
        String url  = properties.getProperty("spring.datasource.url");
        String user = properties.getProperty("spring.datasource.username");
        String pass = properties.getProperty("spring.datasource.password");

        if (url == null || user == null) {
            throw new SQLException("DB URL/username missing in application.properties");
        }
        return DriverManager.getConnection(url, user, pass);
        // MySQL driver is auto-registered in modern versions; explicit Class.forName above is kept for safety.
    }
}