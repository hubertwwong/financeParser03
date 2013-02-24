package main.runners.parsers;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import main.fetchers.KeyFilesFetcher;
import main.fetchers.XBRLFetcher;
import main.parsers.config.MainSettings;

public class MainRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getKeys();
		getXBRLFiles();
	}

	/**
	 * get the key files from the sec website.
	 * the key files contains the paths on the SEC ftp site to all of the
	 * XBRL files.
	 * 
	 * typically grab the files.
	 * then you grab the actual zips.
	 */
	private static void getKeys() {
		// load config file.
		MainSettings.load();
		
		KeyFilesFetcher sekp = new KeyFilesFetcher(
				MainSettings.URL
				, MainSettings.FTP_NUM_TRIES
				, MainSettings.FTP_TIMEOUT
				, MainSettings.FTP_DELAY_AFTER_SUCCESS
				, MainSettings.XBRL_KEY_PATH
				, MainSettings.LOCAL_KEY_PATH
				);
		// run the program.
		sekp.run();
	}
	
	/**
	 * getting the actual zip files. what files to get is provided by the
	 * get keys method above.
	 */
	private static void getXBRLFiles() {
		// load config file.
		MainSettings.load();
		
		XBRLFetcher xf = new XBRLFetcher(
				MainSettings.URL
				, MainSettings.FTP_NUM_TRIES
				, MainSettings.FTP_TIMEOUT
				, MainSettings.FTP_DELAY_AFTER_SUCCESS
				, MainSettings.XBRL_DATA_PATH
				, MainSettings.LOCAL_KEY_PATH
				, MainSettings.LOCAL_DATA_PATH
				);
		// run the program.
		HashMap<String, String> result = xf.getXBRLFiles();
	}

}