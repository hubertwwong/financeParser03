package scratch.fetchers;

import java.io.FileOutputStream;
import java.util.ArrayList;

import main.parsers.config.MainSettings;
import main.util.file.FileMeta;
import main.util.file.FileUtils;
import main.util.ftp.FTPUtils;

import org.apache.commons.net.ftp.FTPClient;

public class KeyFilesFetcher {
	
    FTPClient client; 
    FileOutputStream fos;
    boolean status;
    
    public boolean run() {
    	this.client = FTPUtils.connectAnon(MainSettings.URL, 200, 2000);
    	this.getKeyFiles();
    	FTPUtils.disconnect(this.client);
    	return true;
    }
    
    public boolean tempGetKeyFiles() {
//    	FileUtils.createDirIfNotExist(SECEdgarSettings.LOCAL_KEY_PATH);
//    	ArrayList<FileMeta> ftpFilesMeta = FTPUtils.getFilesMeta(client, SECEdgarSettings.XBRL_KEY_PATH);
//    	ArrayList<FileMeta> localFilesMeta = FileUtils.getFileMeta(SECEdgarSettings.LOCAL_KEY_PATH);
//    	ArrayList<FileMeta> filesToDownload = FileUtils.filesToDownload(ftpFilesMeta, localFilesMeta);
//    	// System.out.println("final size outside " + filesToDownload.size());
//    	FileUtils.deleteFiles(SECEdgarSettings.LOCAL_KEY_PATH, filesToDownload);
//    	// need a method to clean.
//    	
//    	// downloading
//    	FTPUtils.downloadFiles(client, SECEdgarSettings.XBRL_KEY_PATH, filesToDownload, SECEdgarSettings.LOCAL_KEY_PATH);
//    	
//    	// testing out listing method.
//    	//FileMeta.printArray(filesToDownload);
//    	
//    	System.out.println("done");
    	return false;
    }
    
    /*
     * uses the sync directory function in ftp utils.
     * grabs all of the key files...
     */
    public boolean getKeyFiles() {
    	FTPUtils.syncDir(client, MainSettings.XBRL_KEY_PATH, MainSettings.LOCAL_KEY_PATH);
    	return false;
    }
    
    // TEMP CODE.
    // =======================================================================
    
    /**
     * a simple test to pull down a single file...
     * probably nothing else.
     * 
     * scrap this...
     * 
     * @return
     */
    public boolean getXBRLKeyFiles01() {
    	try {
    		this.fos = new FileOutputStream("c:\\temp\\xbrlrss-2012-09.xml");
    		this.status = this.client.changeWorkingDirectory(MainSettings.XBRL_KEY_PATH);
    		
    		// list
    		FTPUtils.list(this.client);
    		
    		// close out things.
    		this.fos.close();
    	}
    	catch (Exception e) { 
		      e.printStackTrace(); 
		}
    	
    	return this.status;
    }
    
    /**
     * tries to return
     * 
     * @return
     */
    public boolean getXBRLKeyFilesMeta() {
    	try {
    		this.status = this.client.changeWorkingDirectory(MainSettings.XBRL_KEY_PATH);
    		
    		// list files.
    		FTPUtils.getFilesMeta(this.client, "");
    		
    	}
    	catch (Exception e) { 
		      e.printStackTrace(); 
		}
    	
    	return this.status;
    }

}