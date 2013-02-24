package main.fetchers;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.parsers.KeyFilesParser;
import main.util.file.FileUtils;
import main.util.ftp.FTPUtils;

import org.apache.commons.net.ftp.FTPClient;

/**
 * uses key file fetcher and parser to gather a list of files.
 * the from that list, grab a list of xbrl files.
 * 
 * probably need to plan things out..
 * 
 * @author Hubert
 *
 */
public class XBRLFetcher {

    FTPClient client; 
    FileOutputStream fos;
    boolean status;
    String url;
    int numTries;
    int timeout;
    int delay;
    String xbrlDataPath;
    String localKeyPath;
    String localDataPath;
    
    public XBRLFetcher(String url, int numTries, int timeout, int delay, String xbrlDataPath, String localKeyPath, String localDataPath) {
    	this.url = url;
    	this.numTries = numTries;
    	this.timeout = timeout;
    	this.delay = delay;
    	this.xbrlDataPath = xbrlDataPath;
    	this.localKeyPath = localKeyPath;
    	this.localDataPath = localDataPath;
    	
    	this.client = FTPUtils.connectAnon(this.url, this.numTries, this.timeout);
    }
    
    public boolean run() {
    	//this.getKeyFiles();
    	//FTPUtils.disconnect(this.client);
    	return true;
    }
	
    /**
     * uses a list of key files grabbed by the key file fetcher.
     * probably a directory where the files is located.
     * for each file stich a list of files to download.
     * by calling methods in key file parser.
     * 
     * @param localKeyPath
     * @return
     */
	public HashMap<String, String> getXBRLFiles() {
		// get a list of files to download
		System.out.println("fetcing key files.");
		HashMap<String, String> result = KeyFilesParser.getAllKeyFilesDownloadPaths(this.localKeyPath);
		
		// filter out to 10-k / 10-q for now.
		System.out.println("filtering for 10-k / 10-q / 10-ka files.");
		result = KeyFilesParser.filterDocTypes10K10Q(result);
		
		// grab files..
		System.out.println("grabbing files.");
		int maxNumOfFiles = result.size();
		int currentFileNum = 1;
		for (Map.Entry<String, String> entry : result.entrySet()) {
			String key = entry.getKey();
		    String value = entry.getValue();
		    
		    // parse out the directory and the file..
		    String relativePath = FileUtils.getJustPath(key);
		    String ftpFullPath = this.xbrlDataPath + relativePath;
		    String localFullPath = localDataPath + relativePath;
		    String filename = FileUtils.getJustFilename(key);
		    
		    // debugging
		    System.out.println("\ncurrently on");
		    System.out.println(ftpFullPath);
		    System.out.println(localFullPath);
		    System.out.println(filename);
		    System.out.println("");
		    
		    // grab each file.
		    FTPUtils.syncFile(this.client, ftpFullPath, filename, localFullPath, filename);
		    
		    // delay
		    try {
		    	System.out.printf("Sleeping %d seconds. current progress: %d of %d. percent: %f\n"
		    			, this.delay
		    			, currentFileNum
		    			, maxNumOfFiles
		    			, (((double)currentFileNum/(double)maxNumOfFiles)*100));
				Thread.sleep(this.delay * 1000);
			} 
		    catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    // increment currentFile.
		    currentFileNum++;
		}
		
		return result;
	}
	
	/**
	 * so figure out things.
	 * so you have a parsing file..
	 * 1. grab a list of all files to download from the key files.
	 * 2. filter and prep the download files.
	 *    - this basically to go figure what directory needs.
	 *    - if the directory for the file does not exist, create it an return it.
	 *    - if the directory is there, figure out if the file is there.
	 *    - if its there and if its created delete from the list.
	 *    - if its there and if its doesn't match, delete the file.
	 * 3. returns a list of stuff i download.
	 */
}