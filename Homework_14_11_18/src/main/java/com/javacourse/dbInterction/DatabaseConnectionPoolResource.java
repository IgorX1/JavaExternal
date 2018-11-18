package com.javacourse.dbInterction;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import static com.javacourse.App.logger;

public class DatabaseConnectionPoolResource {
    private static BasicDataSource ds = new BasicDataSource();
    private static Properties property = new Properties();

    static {
        FileInputStream fis =null;
        try {
            fis = new FileInputStream("src/main/resources/database.properties");
            property.load(fis);
            ds.setUrl(property.getProperty("db.host"));
            ds.setUsername(property.getProperty("db.user"));
            ds.setPassword(property.getProperty("db.password"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private DatabaseConnectionPoolResource(){}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
