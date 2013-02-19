package scratch.runners;

import java.util.HashMap;

import main.parsers.XBRLParser;
import main.parsers.util.DOMUtils;

import org.w3c.dom.Document;

public class XBRLRunner {
	
	public HashMap<String, String> runGetDEIInfoForAAPL() {
		HashMap<String, String> results = new HashMap<String, String>();
		
		String filename = "tmp/aapl-20120630.xml";
		Document doc = DOMUtils.loadXMLFile(filename);
		results = XBRLParser.getDEIInfo(doc);
		
		return results;
	}
	
	public HashMap<String, String> runGetGAAPForAPPL() {
		HashMap<String, String> results = new HashMap<String, String>();
		
		String filename = "tmp/aapl-20120630.xml";
		Document doc = DOMUtils.loadXMLFile(filename);
		results = XBRLParser.getGAAPInfo(doc);
		
		return results;
	}
		
}
