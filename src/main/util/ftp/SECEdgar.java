package main.util.ftp;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class SECEdgar {
	
    public static final String URL = "ftp.sec.gov";
    public static final String USER_NAME = "anonymous";
    public static final String PASSWORD = "";
    public static final String XBRL_KEY_PATH = "/edgar/monthly/";
    public static final String XBRL_KEY_FILE_NAME = "xbrlrss-2012-09.xml";
	
	FTPClient client; 
    FileOutputStream fos;
    boolean status;
    
    public boolean getXBRLKeyFiles() {
    	try {
    		this.fos = new FileOutputStream("c:\\temp\\xbrlrss-2012-09.xml");
    		this.status = this.client.changeWorkingDirectory(SECEdgar.XBRL_KEY_PATH);
    		
    		// list
    		FTPUtils.list(this.client);
    		
    		//this.client.setFileType(FTPClient.BINARY_FILE_TYPE);
    		//this.client.setFileType(FTPClient.ASCII_FILE_TYPE);
    		//this.client.retrieveFile(SECEdgar.XBRL_KEY_FILE_NAME, this.fos);
    		//System.out.println(this.client.getReplyString());
    		
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
    		this.status = this.client.changeWorkingDirectory(SECEdgar.XBRL_KEY_PATH);
    		
    		// list files.
    		FTPUtils.getFilesMeta(this.client);
    		
    	}
    	catch (Exception e) { 
		      e.printStackTrace(); 
		}
    	
    	return this.status;
    }
    
    /*
     * CONNECTION FILES.
     *************************************************************************
     */
    
    /**
     * connect to the ftp site.
     * 
     * @return
     */
	public boolean connect() {
		this.client = new FTPClient();
		
		try {
			// connect to site.
			this.client.connect(SECEdgar.URL);
			this.status = this.client.login(SECEdgar.USER_NAME, SECEdgar.PASSWORD);
			this.client.enterLocalPassiveMode();
		} 
		catch (IOException e) { 
		      e.printStackTrace(); 
		}
		
		return this.status;
	}
	
	/**
	 * disconnects when done.
	 * always call this.
	 */
	public void disconnect() {
		try {
			this.client.disconnect();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
