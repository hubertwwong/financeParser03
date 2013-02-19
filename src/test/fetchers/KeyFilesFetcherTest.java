package test.fetchers;

import static org.junit.Assert.*;

import main.fetchers.KeyFilesFetcher;
import main.parsers.config.MainSettings;

import org.junit.Test;

public class KeyFilesFetcherTest {
	
	@Test
	public void testGetEdgarKeys() {
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
		sekp.run();
		assertEquals(true, false);
	}

//	@Test
//	public void testGetXBRLKey() {
//		SECEdgar se = new SECEdgar();
//		
//		// connect.
//		boolean status = se.connect();
//		assertEquals(true, status);
//		
//		// pull key.
//		status = se.getXBRLKeyFiles();
//		assertEquals(true, status);
//		
//		se.disconnect();
//	}
	
//	@Test
//	public void testGetXBRLKeyMeta() {
//		SECEdgarKeyParser se = new SECEdgarKeyParser();
//		
//		// connect.
//		boolean status = se.connect();
//		assertEquals(true, status);
//		
//		// pull key.
//		status = se.getXBRLKeyFilesMeta();
//		assertEquals(true, status);
//		
//		se.disconnect();
//	}
	
//	@Test
//	public void testConnect() {
//		SECEdgar se = new SECEdgar();
//		
//		boolean status = se.connect();
//		assertEquals(true, status);
//		
//		se.disconnect();
//	}

}
