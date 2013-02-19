package main.parsers;

import java.util.ArrayList;
import java.util.HashMap;

import main.parsers.util.DOMUtils;
import main.util.dataStructs.DataStructUtils;
import main.util.file.FileMeta;
import main.util.file.FileUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * this class gives you a massive list of XBRL files to download.
 * takes a path of key files.
 * then parses through each file and pulles the XBRL file path.
 * 
 * @author Hubert
 *
 */
public class KeyFilesParser {

	/**
	 * takes a path to a list of keys files.
	 * 
	 * then parses each one using some helper method. getFTPFileNames
	 * and stiches the results down into a single method.
	 * 
	 * @return
	 */
	public static HashMap<String, String> getAllKeyFilesDownloadPaths(String pathOfKeyFiles) {
		// get key files meta data...
		ArrayList<FileMeta> keyFilesMeta = FileUtils.getDirFilesMeta(pathOfKeyFiles);
		HashMap<String, String> fileNames = new HashMap<String, String>();
		
		// cycle thru each meta file.
		// stores results into one big meta file...
		for(FileMeta keyFileMeta : keyFilesMeta) {
			String currentFileName = pathOfKeyFiles + "/" + keyFileMeta.getName();
			System.out.println(currentFileName);
			
			// stores current files.
			Document currentDoc = DOMUtils.loadXMLFile(currentFileName);
			HashMap<String, String> currentFileNames = KeyFilesParser.getFTPFileNames(currentDoc);
			
			// merging new files into current list.
			fileNames = DataStructUtils.merge(fileNames, currentFileNames);
		}
		
		return fileNames;
	}
	
	/**
	 * getFTPFilesName
	 * 
	 * pass it a monthly xml key file doc that contains files.
	 * returns a hash map file names and types.
	 * cleave off the prefix.
	 * 
	 * @param doc
	 * @return
	 */
	public static HashMap<String, String> getFTPFileNames(Document doc) {
		HashMap<String, String> results = new HashMap<String, String>();
		NodeList nList = doc.getElementsByTagNameNS("*", "*");
		
		// loop thru xml file.
		for (int i=0 ; i<nList.getLength() ; i++) {
			Node nNode = nList.item(i);
			String curNodeTagName = nNode.getNodeName();
			
			// look for item nodes
			if (curNodeTagName.matches("item.*")) {
				// fetch params that you want.
				Node enclosureNode = DOMUtils.getChildNodeByTagName(nNode.getChildNodes(), "enclosure");
				Node descriptionNode = DOMUtils.getChildNodeByTagName(nNode.getChildNodes(), "description");
				Node urlNode = DOMUtils.getAttribute(enclosureNode, "url");

				// checks if you have non null values.
				// cleaves off the first part of the url
				// and just returns the dir path and type.
				if (descriptionNode != null && urlNode != null) {
					String dirPath = urlNode.getTextContent().replaceAll("http://www.sec.gov/Archives/edgar/data", "");
					System.out.println(dirPath + " " + descriptionNode.getTextContent());
					results.put(dirPath, descriptionNode.getTextContent());
				}
			}
		}
		
		return results;
	}
	
	/**
	 * a simple helper files.
	 * to filter out doc types.
	 * takes an array of strings to denote the types.
	 * 
	 * @param fileNames
	 * @param types
	 * @return
	 */
	public static HashMap<String, String> filterDocTypes10K10Q(HashMap<String, String>fileNames) {
		String[] docTypes = {"10-K", "10-Q", "10-K/A"};
		return DataStructUtils.filter(fileNames, docTypes);
	}

	/**
	 * forgot what this was.
	 * 
	 * @param prefix
	 * @param relativePaths
	 * @return
	 */
//	public static ArrayList<String> prefixKeys(String prefix, HashMap<String, String> relativePaths) {
//		return null;
//	}
	
}