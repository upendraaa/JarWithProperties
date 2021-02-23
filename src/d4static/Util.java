package d4static;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Util {


    private static final String FILE_NAME = "Sample.properties";
    private static final String JAR_FILE_NAME = "JarSample.properties";


    public static final int STANDALONE = 1;
    public static final int JAR = 2;

    public void read(int options){
        if(options == STANDALONE){
            readProps();
        }else {
            readPropsJar();
        }
    }

    public void write(int options,String key,String value){
        if(options == STANDALONE){
            writeProps(key,value);
        }else{
            writePropsJar(key,value);
        }
    }

    /*
      Read properties for Standalone project
      It will not work if we create JAR file of this project
     */
    private void readProps(){
        Properties props = new Properties();
        try {
            FileReader reader = new FileReader(FILE_NAME);
            props.load(reader);
            readPropertiesFile(props);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void  readPropertiesFile(Properties properties){
        for (Map.Entry<Object, Object> entry:properties.entrySet()){
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }
    }

    /*
      Write properties for Standalone project
      It will not work if we create JAR file of this project
     */

    private void writeProps(String key, String value){

        try {
            FileReader reader = new FileReader(FILE_NAME);
            Properties prop = new Properties();
            prop.load(reader);
            prop.setProperty(key, value);
            FileOutputStream out = new FileOutputStream(FILE_NAME);
            prop.store(out, "Key added/updated");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read Properties while creating the JAR
     */

    private void readPropsJar(){
        Properties prop = new Properties();
        try {
            prop.load(Util.class.getClassLoader().getResourceAsStream(JAR_FILE_NAME));
            readPropertiesFile(prop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read Properties while creating the JAR
     */

    public static void writePropsJar(String key,String value) {

        try {
            Properties prop = new Properties();
            prop.load(Util.class.getClassLoader().getResourceAsStream(JAR_FILE_NAME));
            prop.setProperty(key, value);
            FileOutputStream fos = new FileOutputStream(Util.class.getClassLoader().getResource(JAR_FILE_NAME).getPath());
            prop.store(fos, null);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
