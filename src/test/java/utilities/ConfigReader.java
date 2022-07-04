package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        try{
            String pathToPropertiesFile = "config.properties";
            FileInputStream fis = new FileInputStream(pathToPropertiesFile);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }





        public static String getProperty(String key){  // wrapper method
          return  properties.getProperty(key);
        }

}
