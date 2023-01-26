package APIs;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseAPI {
    protected String BaseURL;
    protected Utils utils;
    protected Properties config = new Properties();

    public BaseAPI(){
        utils = new Utils();
        try{
            config.load(new FileInputStream("config.properties"));
        }
        catch (Exception e){
            System.out.println(e);
        }
        BaseURL = config.getProperty("baseURL");
    }
}
