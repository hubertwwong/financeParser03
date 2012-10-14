package main.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.HashMap;

public class XBRLParser {
	
	/**
	 * tries to grab the first gaap value of each type.
	 * 
	 * @param doc
	 * @return
	 */
	public HashMap<String, String> getGAAPInfo(Document doc) {
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
	
	public HashMap<String, String> getDEIInfo(Document doc) {
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
	 * 
	 * @param doc
	 * @return
	 */
	public HashMap<String, String> getCompanyInfo(Document doc) {
		HashMap<String, String> results = new HashMap<String, String>();
		results.put(XBRLTags.DEI_SYMBOL, this.getDOMName(doc, XBRLTags.DEI_SYMBOL));
		results.put(XBRLTags.DEI_COMPANY_NAME, this.getDOMName(doc, XBRLTags.DEI_COMPANY_NAME));
		results.put(XBRLTags.DEI_DOC_TYPE, this.getDOMName(doc, XBRLTags.DEI_DOC_TYPE));
		results.put(XBRLTags.DEI_CIK, this.getDOMName(doc, XBRLTags.DEI_CIK));
		results.put(XBRLTags.DEI_FISCAL_PERIOD, this.getDOMName(doc, XBRLTags.DEI_FISCAL_PERIOD));
		results.put(XBRLTags.DEI_FISCAL_YEAR, this.getDOMName(doc, XBRLTags.DEI_FISCAL_YEAR));
		results.put(XBRLTags.DEI_DOC_PERIOD_END_DATE, this.getDOMName(doc, XBRLTags.DEI_DOC_PERIOD_END_DATE));
		results.put(XBRLTags.DEI_SHARES_OUTSTANDING, this.getDOMName(doc, XBRLTags.DEI_SHARES_OUTSTANDING));
		
		return results;
	}
	
	/**
	 * a simple helper function to fetch you a tag value when you select a tag
	 * name. if it can't find the tagName, it returns a null value.
	 * 
	 * @param doc
	 * @param tagName
	 * @return
	 */
	public String getDOMName(Document doc, String tagName) {
		NodeList nList = doc.getElementsByTagName(tagName);
		String textResult = null;
		
		// null checks.
		if (nList != null && nList.item(0) != null) {
			Node nNode = nList.item(0);
			System.out.println("tagname " + tagName + " textContent " + nNode.getTextContent());
			textResult = nNode.getTextContent();
		}
		
		return textResult;
	}
	
}
