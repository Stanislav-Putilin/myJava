package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import itstep.learning.services.file.WorkerWithFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbModule extends AbstractModule {

    private Connection connection;
    private final WorkerWithFile workerWithFile;

    public DbModule()
    {
        writeConnectionParamsToFile();
        workerWithFile = new WorkerWithFile();
    }

    @Provides
    private Connection getConnection()
    {
        if(connection == null)
        {
            Map<String,String> fileData = workerWithFile.readConnectionDataFromFile();
            System.out.println(fileData.get("user") + " " + fileData.get("password"));
            try
            {
                Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mysqlDriver);

                connection = DriverManager.getConnection(fileData.get("url"),
                        fileData.get("user"), fileData.get("password"));

            }
            catch (SQLException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        return connection;
    }

    private void writeConnectionParamsToFile() {
        String filePath = "db.ini";
        File configFile = new File(filePath);
        try {
            File parentDir = configFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!configFile.isFile()) {
                configFile.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
                writer.write("dbms=mysql\n");
                writer.write("host=localhost\n");
                writer.write("port=3308\n");
                writer.write("schema=java_pv222\n");
                writer.write("user=user221\n");
                writer.write("password=pass222\n");
                writer.write("useUnicode=true\n");
                writer.write("characterEncoding=utf8\n");
            }

            System.out.println("Файл конфигурации успешно создан по пути: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла конфигурации: " + e.getMessage());
        }
    }

    private String readStream(InputStream in, Charset charset) throws IOException {

        byte[] buffer = new byte[4096];

        try(ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
            BufferedInputStream bis = new BufferedInputStream(in) )
        {
            int len;
            while((len = bis.read(buffer)) != -1)
            {
                byteBuilder.write(buffer, 0, len);
            }
            return byteBuilder.toString(charset.name());
        }
    }

    private String readStream(InputStream in) throws IOException{
        return readStream(in, StandardCharsets.UTF_8);
    }
}