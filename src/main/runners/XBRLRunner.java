package main.runners;

import java.util.HashMap;

import main.parsers.ParserUtils;
import main.parsers.XBRLParser;

import org.w3c.dom.Document;

public class XBRLRunner {
	
	public HashMap<String, String> runGetDEIInfoForAAPL() {
		HashMap<String, String> results = new HashMap<String, String>();
		
		String filename = "tmp/aapl-20120630.xml";
		Document doc = ParserUtils.loadXMLFile(filename);
		XBRLParser xp = new XBRLParser();
		results = xp.getDEIInfo(doc);
		
		return results;
	}
	
	public HashMap<String, String> runGetGAAPForAPPL() {
		HashMap<String, String> results = new HashMap<String, String>();
		
		String filename = "tmp/aapl-20120630.xml";
		Document doc = ParserUtils.loadXMLFile(filename);
		XBRLParser xp = new XBRLParser();
		results = xp.getGAAPInfo(doc);
		
		return results;
	}
		
}
