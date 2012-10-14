package main.util.ftp;

import java.io.IOException;
import java.util.ArrayList;

import main.util.file.FileMeta;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPUtils {
	
	public static void list(FTPClient client) {
		System.out.println("> DIR");
		FTPUtils.listDir(client);
		System.out.println("> FILE");
		FTPUtils.listFiles(client);
	}
	
	public static ArrayList<FileMeta> getFilesMeta(FTPClient client) {
		ArrayList<FileMeta> fileMetaArray = new ArrayList<FileMeta>();
		
    	try {
    		FTPFile[] files = client.listFiles();
    		for(FTPFile file: files) {
    			if (!file.isFile()) {
                    continue;
                }
    			else {
    				// load meta item.
    				FileMeta currentFileMeta = new FileMeta();
    				currentFileMeta.setDate(file.getTimestamp());
    				currentFileMeta.setName(file.getName());
    				currentFileMeta.setSize(file.getSize());
    				System.out.println(currentFileMeta);
    			}
    		}
    	}
    	catch(IOException e) {
    		 e.printStackTrace();
    	}
    	
    	return fileMetaArray;
    }
	
	public static void listDir(FTPClient client) {
    	try {
    		FTPFile[] files = client.listDirectories();
    		for(FTPFile file: files) {	
    			if (!file.isDirectory()) {
                    continue;
                }
    			else {
    				System.out.println(file.getName());
    			}
    		}
    	}
    	catch(IOException e) {
    		 e.printStackTrace();
    	}
    }
	
	public static void listFiles(FTPClient client) {
    	try {
    		FTPFile[] files = client.listFiles();
    		for(FTPFile file: files) {
    			if (!file.isFile()) {
                    continue;
                }
    			else {
    				System.out.println(file.getName());
    			}
    		}
    	}
    	catch(IOException e) {
    		 e.printStackTrace();
    	}
    }
	
}
