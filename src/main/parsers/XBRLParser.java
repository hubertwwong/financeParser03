package main.parsers;

import main.parsers.config.XBRLTags;
import main.parsers.util.DOMUtils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.HashMap;

/**
 * some utility functions to parse out XBRL documents.
 * for now its pretty brute forte.
 * might wanna change it late.
 * 
 * @author Hubert
 *
 */
public class XBRLParser {
	
	/**
	 * tries to grab the first gaap value of each type.
	 * returns a key value pair
	 * key beinbg a xml tag
	 * value being the value
	 * 
	 * @param doc
	 * @return
	 */
	public static HashMap<String, String> getGAAPInfo(Document doc) {
		HashMap<String, String> results = new HashMap<String, String>();
		NodeList nList = doc.getElementsByTagNameNS("*", "*");
		
		for (int i=0 ; i<nList.getLength() ; i++) {
			Node nNode = nList.item(i);
			String curNodeTagName = nNode.getNodeName();
			String curNodeTextContent = nNode.getTextContent();
			
			// check to see if its a gaap value
			// also checks to see if its not in the hash table yet.
			if (curNodeTagName.matches("us-gaap:.*") 
					&& !curNodeTagName.matches("us-gaap:.*TextBlock")
					&& results.get(curNodeTagName) == null) {
				System.out.println("tagname [" + curNodeTagName + "]");
				System.out.println("textContent [" + curNodeTextContent + "]\n");
				results.put(curNodeTagName, curNodeTextContent);
			}
		}
		
		return results;
	}
	
	/**
	 * grabs a list of company info from the xml files...
	 * just occured to me that you probably don't need to do this multiple times...
	 * 
	 * @param doc
	 * @return
	 */
	public static HashMap<String, String> getDEIInfo(Document doc) {
		HashMap<String, String> results = new HashMap<String, String>();
		NodeList nList = doc.getElementsByTagNameNS("*", "*");
		
		for (int i=0 ; i<nList.getLength() ; i++) {
			Node nNode = nList.item(i);
			String curNodeTagName = nNode.getNodeName();
			String curNodeTextContent = nNode.getTextContent();
			
			// check to see if its a dei value. this is basic company info.
			// like ticker symbol, company name, etc...
			if (curNodeTagName.matches("dei:.*")) {
				//System.out.println("tagname [" + curNodeTagName + "]");
				//System.out.println("textContent [" + curNodeTextContent + "]\n");
				results.put(curNodeTagName, curNodeTextContent);
			}
		}
		
		return results;
	}
	
	/**
	 * returns company information in the filing....
	 * might wanna to covernt the sucker over.
	 * you need to pass it a 10-k or 10q
	 * 
	 * @param doc
	 * @return
	 */
	public static HashMap<String, String> getCompanyInfo(Document doc) {
		HashMap<String, String> results = new HashMap<String, String>();
		results.put(XBRLTags.DEI_SYMBOL, DOMUtils.getDOMName(doc, XBRLTags.DEI_SYMBOL));
		results.put(XBRLTags.DEI_COMPANY_NAME, DOMUtils.getDOMName(doc, XBRLTags.DEI_COMPANY_NAME));
		results.put(XBRLTags.DEI_DOC_TYPE, DOMUtils.getDOMName(doc, XBRLTags.DEI_DOC_TYPE));
		results.put(XBRLTags.DEI_CIK, DOMUtils.getDOMName(doc, XBRLTags.DEI_CIK));
		results.put(XBRLTags.DEI_FISCAL_PERIOD, DOMUtils.getDOMName(doc, XBRLTags.DEI_FISCAL_PERIOD));
		results.put(XBRLTags.DEI_FISCAL_YEAR, DOMUtils.getDOMName(doc, XBRLTags.DEI_FISCAL_YEAR));
		results.put(XBRLTags.DEI_DOC_PERIOD_END_DATE, DOMUtils.getDOMName(doc, XBRLTags.DEI_DOC_PERIOD_END_DATE));
		results.put(XBRLTags.DEI_SHARES_OUTSTANDING, DOMUtils.getDOMName(doc, XBRLTags.DEI_SHARES_OUTSTANDING));
		
		return results;
	}
	
}