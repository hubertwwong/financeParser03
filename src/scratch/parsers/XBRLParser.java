package scratch.parsers;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XBRLParser {
	
//	public HashMap<String, String> runXBRL04() {
//		String filename = "tmp/aapl-20120630.xml";
//		Document doc = this.loadFile(filename);
//		return this.parseXBRL04(doc);
//	}
//	
//	public HashMap<String, String> parseXBRL04(Document doc) {
//		HashMap<String, String> results = new HashMap<String, String>();
//		results = this.getGAAPInfo(doc);
//		return results;
//	}
	
//	public HashMap<String, String> runXBRL03() {
//		String filename = "tmp/aapl-20120630.xml";
//		Document doc = this.loadFile(filename);
//		return this.parseXBRL03(doc);
//	}
//	
//	public HashMap<String, String> parseXBRL03(Document doc) {
//		HashMap<String, String> results = new HashMap<String, String>();
//		results = this.getCompanyInfo(doc);
//		return results;
//	}
//	
//	public boolean runXBRL02() {
//		String filename = "tmp/aapl-20120630.xml";
//		Document doc = this.loadFile(filename);
//		this.parseXBRL02(doc);
//		return false;
//	}
//	
//	public boolean parseXBRL02(Document doc) {
//		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//		NodeList nList = doc.getElementsByTagName("us-gaap:Goodwill");
//		
//		for (int temp = 0; temp < nList.getLength(); temp++) {
//			 Node nNode = nList.item(temp);
//			 System.out.println("node name " + nNode.getNodeName());
//			 System.out.println("node value " + nNode.getNodeValue());
//			 System.out.println("node text content " + nNode.getTextContent());
//			 System.out.println("node text attribute " + nNode.getAttributes().getNamedItem("decimals").getTextContent());
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * simple loader function that loads a test xml file.
//	 * and tries to retrieves some elements.
//	 * 
//	 * @return
//	 */
//	public boolean runXBRL01() {
//		String filename = "tmp/test01.xml";
//		Document doc = this.loadFile(filename);
//		this.parseXBRL01(doc);
//		return false;
//	}
//	
//	public boolean parseXBRL01(Document doc) {
//		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//		NodeList nList = doc.getElementsByTagName("staff");
//		System.out.println("-----------------------");
// 
//		for (int temp = 0; temp < nList.getLength(); temp++) {
// 
//		   Node nNode = nList.item(temp);
//		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
// 
//		      Element eElement = (Element) nNode;
//		      System.out.println("First Name : " + this.getTagValue("firstname", eElement));
//		      System.out.println("Last Name : " + this.getTagValue("lastname", eElement));
//	          System.out.println("Nick Name : " + this.getTagValue("nickname", eElement));
//		      System.out.println("Salary : " + this.getTagValue("salary", eElement));
//		   }
//		}
//		
//		return false;
//	}
	
//	/* 
//	 * testing parsing of example file.
//	 * So just ignore this for now...
//	 * */
//	public boolean parseTest01() {
//		try {			 
//			File fXmlFile = new File("tmp/test01.xml");
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(fXmlFile);
//			doc.getDocumentElement().normalize();
//	 
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//			NodeList nList = doc.getElementsByTagName("staff");
//			System.out.println("-----------------------");
//	 
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//	 
//			   Node nNode = nList.item(temp);
//			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//	 
//			      Element eElement = (Element) nNode;
//			      System.out.println("First Name : " + this.getTagValue("firstname", eElement));
//			      System.out.println("Last Name : " + this.getTagValue("lastname", eElement));
//		          System.out.println("Nick Name : " + this.getTagValue("nickname", eElement));
//			      System.out.println("Salary : " + this.getTagValue("salary", eElement));
//			   }
//			}
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
	
}
