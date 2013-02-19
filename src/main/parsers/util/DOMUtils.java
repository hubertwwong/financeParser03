package main.parsers.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMUtils {
	
	/**
	 * a simple filename to XML loader
	 * loads from a file and returns a document object.
	 * 
	 * @param filename
	 * @return
	 */
	public static Document loadXMLFile(String filename) {
		try {
			File fXmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			return doc;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * a simple utility function that pulls down the first child node.
	 * forgot what i was using this for...
	 * i think it was because of how the xml thing was working.
	 * 
	 * @param sTag
	 * @param eElement
	 * @return
	 */
	public static String getTagFirstValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}
	
	/**
	 * a simple helper that returns a node with a given tag value.
	 * 
	 * @return
	 */
	public static Node getChildNodeByTagName(NodeList nList, String tagName) {
		Node result = null;
		
		// loop thru children nodes.
		for(int i=0 ; i<nList.getLength() ; i++) {
			Node currentNode = nList.item(i);
			
			if (currentNode.getNodeName().equals(tagName)) {
				result = currentNode;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * a simple helper that reutrns an attribute as a node.
	 * checks the node value.
	 * 
	 * @param n
	 * @param attName
	 * @return
	 */
	public static Node getAttribute(Node n, String attName) {
		Node result = null;
		
		if (n != null) { 
			result = n.getAttributes().getNamedItem(attName);
		}
		
		return result;
	}

	/**
	 * a simple helper function to fetch you a tag value when you select a tag
	 * name. if it can't find the tagName, it returns a null value.
	 * 
	 * @param doc
	 * @param tagName
	 * @return
	 */
	public static String getDOMName(Document doc, String tagName) {
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
