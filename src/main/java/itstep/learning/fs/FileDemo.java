package itstep.learning.fs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FileDemo {

    public  void run()
    {
        try(InputStream rs =
        this.getClass().getClassLoader().getResourceAsStream("db.ini")){
            String content = readStream(rs);
            String[] lines = content.split("\n");
            Map<String,String> ini = new HashMap<>();

            for(String line : lines)
            {
                String[] parts = line.split("=");
                ini.put(parts[0].trim(),parts[1].trim());
            }
            System.out.println(ini.get("user"));

            System.out.println(String.format(("jdbc:%s://%s:%s/%s"),
                    ini.get("user"),
                    ini.get("user"),
                    ini.get("user"),
                    ini.get("host")));
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }

    }

    public  void runFile()
    {
        System.out.println("Demo FS");

        File file = new File("text.txt");

        try(InputStream is = new FileInputStream(file))
        {
            System.out.println(readStream(is));
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }


    private String readStream(InputStream in, Charset charset) throws IOException{

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