package test.fetchers;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.fetchers.XBRLFetcher;
import main.parsers.config.MainSettings;

import org.junit.Test;

public class XBRLFetcherTest {

	@Test
	public void testFetchList01() {
		// load config file.
		MainSettings.load();
		
		// run stuff
		XBRLFetcher xf = new XBRLFetcher(
				MainSettings.URL
				, MainSettings.FTP_NUM_TRIES
				, MainSettings.FTP_TIMEOUT
				, MainSettings.FTP_DELAY_AFTER_SUCCESS
				, MainSettings.XBRL_DATA_PATH
				, MainSettings.LOCAL_KEY_PATH
				, MainSettings.LOCAL_DATA_PATH
				);
		HashMap<String, String> result = xf.getXBRLFiles();
		
		assertNotNull(result);
	}

}
