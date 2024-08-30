package itstep.learning.db;

import com.google.inject.Inject;

import java.sql.*;

public class DbDemo {

    @Inject
    private Connection connection;

    public void run()
    {
        System.out.println("Db Demo");

        try
        {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SHOW DATABASES");

            while (res.next())
            {
                System.out.println(res.getString(1));
            }

            res.close();
            statement.close();
        }
        catch (SQLException ex)
        {
           System.err.println(ex.getMessage());
        }
    }
}
