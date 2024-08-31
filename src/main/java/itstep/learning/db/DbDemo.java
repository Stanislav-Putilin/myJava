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
            String insertQuery = "INSERT INTO launching_application (event_datetime) VALUES (NOW())";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.executeUpdate();
            insertStatement.close();
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM launching_application");
            while (res.next()) {
                System.out.println("ID: " + res.getInt("id") + ", Event Datetime: " + res.getTimestamp("event_datetime"));
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