package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbModule extends AbstractModule {

    private Connection connection = null;
    private Driver driver = null;

    @Provides
    private Connection getConnection()
    {
        if(connection == null)
        {
            try
            {
                Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mysqlDriver);

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/java_pv222" +
                                "?useUnicode=true&characterEncoding=utf8",
                        "user221","pass222");

            }
            catch (SQLException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        return connection;
    }
}