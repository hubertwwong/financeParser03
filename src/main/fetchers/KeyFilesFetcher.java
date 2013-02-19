package main.fetchers;

import java.io.FileOutputStream;

import main.parsers.config.MainSettings;
import main.util.ftp.FTPUtils;

import org.apache.commons.net.ftp.FTPClient;

/**
 * fetch the key files that contains a list of xbrl file locations.
 * i think its sorted monthly list.
 * its a large list. keep that in mind.
 * 
 * to do.... a way to load connection object and settings.
 * 
 * @author Hubert
 *
 */
public class KeyFilesFetcher {
	
    FTPClient client; 
    FileOutputStream fos;
    boolean status;
    
    String url;
    int numTries;
    int timeout;
    int delay;
    
    String xbrlPath;
    String localPath;
    
    public KeyFilesFetcher(String url, int numTries, int timeout, int delay, String xbrlPath, String localPath) {
    	this.url = url;
    	this.numTries = numTries;
    	this.timeout = timeout;
    	this.delay = delay;
    	this.xbrlPath = xbrlPath;
    	this.localPath = localPath;
    }
    
    public boolean run() {
    	this.client = FTPUtils.connectAnon(this.url, this.numTries, this.timeout);
    	this.getKeyFiles();
    	FTPUtils.disconnect(this.client);
    	return true;
    }
    
    /*
     * uses the sync directory function in ftp utils.
     * grabs all of the key files...
     */
    public boolean getKeyFiles() {
    	FTPUtils.syncDir(this.client, this.xbrlPath, this.localPath, this.delay);
    	return false;
    }
    
}