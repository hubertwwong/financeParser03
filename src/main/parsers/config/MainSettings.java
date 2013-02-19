package main.parsers.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainSettings {

	public static final String CONFIG_FILE_NAME = "config.properties";
	
	public static String URL;
	public static String XBRL_KEY_PATH;
	public static String XBRL_DATA_PATH;
	
	// local settings
	public static String LOCAL_PREFIX;
	public static String LOCAL_KEY_PATH;
	public static String LOCAL_DATA_PATH;
	
	// ftp settings
	public static int FTP_NUM_TRIES;
	public static int FTP_TIMEOUT;
	public static int FTP_DELAY_AFTER_SUCCESS;
	
	// configuration.
	// load them in from config.properties file
	public static boolean load() {
		Properties prop = new Properties();
		 
    	try {
			//load a properties file
			prop.load(new FileInputStream(MainSettings.CONFIG_FILE_NAME));
			
			// ftp settings
			MainSettings.FTP_NUM_TRIES = new Integer(prop.getProperty("FTP_NUM_TRIES")).intValue();
			MainSettings.FTP_TIMEOUT = new Integer(prop.getProperty("FTP_TIMEOUT")).intValue();
			MainSettings.FTP_DELAY_AFTER_SUCCESS = new Integer(prop.getProperty("FTP_DELAY_AFTER_SUCCESS")).intValue();
			
			// load properties.
			MainSettings.URL = prop.getProperty("SEC_URL");
			MainSettings.XBRL_KEY_PATH = prop.getProperty("XBRL_KEY_PATH");
			MainSettings.XBRL_DATA_PATH = prop.getProperty("XBRL_DATA_PATH");
			MainSettings.LOCAL_PREFIX = prop.getProperty("LOCAL_PATH");
			
			// local data any key path.
			// for now, we'll try to mirror the sec path.
			MainSettings.LOCAL_KEY_PATH = MainSettings.LOCAL_PREFIX + MainSettings.XBRL_KEY_PATH;
			MainSettings.LOCAL_DATA_PATH = MainSettings.LOCAL_PREFIX + MainSettings.XBRL_DATA_PATH;
			
			// note
			System.out.println("config file loaded");
    	} 
    	catch (IOException ex) {
    		ex.printStackTrace();
        }
    	
    	return true;
	}
	
}
