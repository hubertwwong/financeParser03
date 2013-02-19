package test.parsers;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.parsers.KeyFilesParser;
import main.parsers.config.MainSettings;
import main.parsers.util.DOMUtils;

import org.junit.Test;
import org.w3c.dom.Document;

public class KeyFilesParserTest {

	@Test
	public void testDocTypeFilter() {
		HashMap<String, String> results = new HashMap<String, String>();
		results.put("aaaa", "10-K");
		results.put("bbbb", "10-Q");
		results.put("cccc", "497");
		HashMap<String, String> finalResults = KeyFilesParser.filterDocTypes10K10Q(results);
		
		assertEquals(3, results.size());
		assertEquals(2, finalResults.size());
		assertNull(finalResults.get("cccc"));
	}
	
//	@Test
//	public void testGetListOfAllXBRLFilesToDownload() {
//		String filename = SECEdgarSettings.LOCAL_KEY_PATH;
//		HashMap<String, String> results = KeyFilesParser.getKeyFilesDownloadPaths(filename);
//		
//		assertNotNull(results);
//		assertEquals(true, results.size() > 1000);
//	}
	
	@Test
	public void testFTPFilesNames() {
		// load file
		String fileName = MainSettings.LOCAL_KEY_PATH + "/xbrlrss-2012-03.xml";
		Document doc = DOMUtils.loadXMLFile(fileName);
		
		// parse it.
		HashMap<String, String> results = KeyFilesParser.getFTPFileNames(doc);
		assertNotNull(results);
		//assertEquals("AAPL", results.get(XBRLTags.DEI_SYMBOL));
	}

}