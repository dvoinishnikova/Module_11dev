package org.example.utils;

import org.flywaydb.core.Flyway;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MigrationUtil {

    public  void migration() {
        try (FileReader proper = new FileReader("db.properties")) {
            Properties properties = new Properties();
            properties.load(proper);

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            Flyway flyway = Flyway.configure()
                    .dataSource(url, username, password)
                    .load();

            flyway.migrate();
            System.out.println("Database migration successful!");
        } catch (IOException e) {
            System.err.println("Error reading db.properties file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during database migration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
