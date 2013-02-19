package test.parsers;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.parsers.XBRLParser;
import main.parsers.config.MainSettings;
import main.parsers.config.XBRLTags;
import main.parsers.util.DOMUtils;

import org.junit.Test;
import org.w3c.dom.Document;

import scratch.runners.XBRLRunner;

public class XBRLParserTest {
	
//	@Test
//	public void testGetDEIInfo() {
//		XBRLRunner xr = new XBRLRunner();
//		HashMap<String, String> results = xr.runGetDEIInfoForAAPL();
//		assertNotNull(results);
//		assertEquals("AAPL", results.get(XBRLTags.DEI_SYMBOL));
//	}
	
//	@Test
//	public void testGetGAAPInfo() {
//		XBRLRunner xr = new XBRLRunner();
//		HashMap<String, String> results = xr.runGetGAAPForAPPL();
//		assertNotNull(results);
//		assertEquals("896000000", results.get("us-gaap:Goodwill"));
//	}
	
//	@Test
//	public void testBasicXBRL03() {
//		XBRLParser xp = new XBRLParser();
//		HashMap<String, String> results = xp.runXBRL03();
//		assertNotNull(results);
//		assertEquals("AAPL", results.get(XBRLTags.DEI_SYMBOL));
//		assertEquals("APPLE INC", results.get(XBRLTags.DEI_COMPANY_NAME));
//		assertEquals("0000320193", results.get(XBRLTags.DEI_CIK));
//		assertEquals("Q3", results.get(XBRLTags.DEI_FISCAL_PERIOD));
//		assertEquals("2012", results.get(XBRLTags.DEI_FISCAL_YEAR));
//		assertEquals("2012-06-30", results.get(XBRLTags.DEI_DOC_PERIOD_END_DATE));
//		assertEquals("937406000", results.get(XBRLTags.DEI_SHARES_OUTSTANDING));
//	}
	
//	@Test
//	public void testBasicXBRL02() {
//		XBRLParser xp = new XBRLParser();
//		xp.runXBRL02();
//		fail("bbb");
//	}
	
//	@Test
//	public void testBasicXBRL01() {
//		XBRLParser xp = new XBRLParser();
//		xp.runXBRL01();
//		fail("aaa");
//	}
	
//	@Test
//	public void testParseTest01() {
//		XBRLParser xp = new XBRLParser();
//		xp.parseTest01();
//		fail("aaa");
//	}

}