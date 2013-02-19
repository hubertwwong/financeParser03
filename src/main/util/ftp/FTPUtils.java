package main.util.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import main.util.file.FileMeta;
import main.util.file.FileUtils;
import main.util.timer.TimerUtil;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPUtils {
	
	// high level download
	// =======================================================================
	
	/**
	 * syncDir
	 * 
	 * syncs a ftp dir with a local diretory.
	 * useful for the key file.
	 * 
	 * @param client
	 * @param ftpDirName
	 * @param ftpFileNames
	 * @param localDirName
	 * 
	 * @return
	 */
	public static boolean syncDir(FTPClient client, String ftpPath, String localPath, int delay) {
		// creates local directory if it does not exist.
		FileUtils.createDirIfNotExist(localPath);
		
		// fetch meta info for ftp directory in question and local directory.
    	ArrayList<FileMeta> ftpFilesMeta = FTPUtils.getFilesMeta(client, ftpPath);
    	ArrayList<FileMeta> localFilesMeta = FileUtils.getDirFilesMeta(localPath);
    	
    	// figures out what is different on the ftp and local directory
    	ArrayList<FileMeta> filesToDownload = FileUtils.filesToDownload(ftpFilesMeta, localFilesMeta);
    	
    	// delete the files that that you plan to download on the local store.
    	// not sure how resumes works.
    	FileUtils.deleteFiles(localPath, filesToDownload);
    	
    	// downloading
    	FTPUtils.downloadFiles(client, ftpPath, filesToDownload, localPath, delay);
    	
    	System.out.println("sync dir done");
    	
    	return false;
	}
	
	/**
	 * syncFile...
	 * 
	 * only checks by file sizes...
	 * dates seems to be off..
	 * not sure why...
	 * 
	 * @param client
	 * @param ftpDirName
	 * @param ftpFileName
	 * @param localDirName
	 * @param localFileName
	 * @return
	 */
	public static boolean syncFile(FTPClient client, String ftpPath, String ftpFilename, String localPath, String localFilename) {
		FileOutputStream fos;
		boolean status = false;
		
		try {
			// change working directory
			System.out.println("change dir to [" + ftpPath + "]");
			status = client.changeWorkingDirectory(ftpPath);
    		
			// navigate to correct directory
    		if (status == true) {
    			System.out.println("changing dir");
    			FTPFile[] files = client.listFiles();
    			System.out.println("change dir successful");
    			System.out.println(client.printWorkingDirectory());
    			
    			for(FTPFile file: files) {
	    			// check for target file.
	    			if (file.isFile() && file.getName().equals(ftpFilename)) {
	    				System.out.println("found the file");
	    				
	    				// get ftp file meta info.
	    				FileMeta ftpFM = new FileMeta();
	                    ftpFM.setName(file.getName());
	                    ftpFM.setSize(file.getSize());
	                    ftpFM.setDate(file.getTimestamp());
	                    
	                    // check local file..
	                    FileMeta localFM = FileUtils.getFileMeta(localPath + "/" + localFilename);
	                    
	                    // compare.
	                    // if null, basically file does not exist.
	                    if (localFM == null) {
	                    	System.out.println("not downloaded " + localPath + "/" + localFilename);
	                    	
	                    	// create directory if does not exist.
	                    	FileUtils.createDirIfNotExist(localPath);
	                    	
	                    	// grab file.
	                    	fos = new FileOutputStream(localPath + "/" + localFilename);
		        			client.setFileType(FTPClient.BINARY_FILE_TYPE);
		        			status = client.retrieveFile(ftpPath + "/" + ftpFilename, fos);
		        			System.out.println(status + " | " + ftpPath + "/" + ftpFilename);
		        			fos.close();
	                    }
	                    else if (localFM != null && !ftpFM.equalsBySize(localFM)) {
	                    	System.out.println("diff file sizes " + localPath + "/" + localFilename);
	                    	
	                    	// delete the file.
	                    	ArrayList<FileMeta> fmList = new ArrayList<FileMeta>();
	                    	fmList.add(localFM);
	                    	FileUtils.deleteFiles(localPath, fmList);
	                    	
	                    	// grab file.
	                    	fos = new FileOutputStream(localPath + "/" + localFilename);
		        			client.setFileType(FTPClient.BINARY_FILE_TYPE);
		        			status = client.retrieveFile(ftpPath + "/" + ftpFilename, fos);
		        			System.out.println(status + " | " + ftpPath + "/" + ftpFilename);
		        			fos.close();
	                    }
	                    else {
	                    	System.out.println("synced already " + localPath + "/" + localFilename);
	                    }
	                }
	    			else {
	    				System.out.println("file not found. looping to next one.");
	    			}
	    		}
    		}
    		else {
    			System.out.println("can't change directory");
    		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	// low level download
	// =======================================================================

	/**
	 * downloadFiles
	 * 
	 * download a list of files...
	 * returns a list of failures of file meta files.
	 * not sure how to actually test the failed download feature
	 * 
	 * @param client
	 * @param ftpDirName
	 * @param ftpFileNames
	 * @param localDirName
	 * @return
	 */
	public static ArrayList<FileMeta> downloadFiles(FTPClient client, String ftpDirName, ArrayList<FileMeta> ftpFileNames, String localDirName, int delay) {
		ArrayList<FileMeta> failedDownloads = new ArrayList<FileMeta>();
		int numFiles = ftpFileNames.size();
		int curFileCount = 0;
		
		for(FileMeta currentFileMeta: ftpFileNames) {
			boolean currentStatus = FTPUtils.downloadFile(client, ftpDirName, currentFileMeta.getName(), localDirName, currentFileMeta.getName());
			System.out.println(curFileCount + "/" + numFiles + " | " + currentFileMeta.getName());
			curFileCount++;
			
			// simple toggle to report if some error has occured.
			// push the file meta..
			if (currentStatus == false) {
				failedDownloads.add(currentFileMeta);
			}
			
			// run the delay.
			// useful so you don't spam sever.
			try {
				System.out.println("Sleeping " + delay + " secs");
				Thread.sleep(delay * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return failedDownloads;
	}
	
	/**
	 * download a single file...
	 * don't think you actually check if the local file exist...
	 * 
	 * @param client
	 * @param ftpDirName
	 * @param ftpFileName
	 * @param localDirName
	 * @param localFileName
	 * @return
	 */
	public static boolean downloadFile(FTPClient client, String ftpDirName, String ftpFileName, String localDirName, String localFileName) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(localDirName + "/" + localFileName);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			// figure out ftp files.
			boolean status = client.retrieveFile(ftpDirName + "/" + ftpFileName, fos);
			System.out.println(status + " | " + ftpDirName + "/" + ftpFileName);
			
			fos.close();
			return status;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * pulls the meta data of a client.
	 * 
	 * @param client
	 * @return
	 */
	public static ArrayList<FileMeta> getFilesMeta(FTPClient client, String dirName) {
		boolean status;
		ArrayList<FileMeta> fileMetaArray = new ArrayList<FileMeta>();
		System.out.println("ftp " + dirName);
		
    	try {
    		// change dir to one specified.
    		status = client.changeWorkingDirectory(dirName);
    		
    		// if it can't change the directory, return null.
    		if (!status) {
    			return null;
    		}
    		
    		// pull all files.
    		FTPFile[] files = client.listFiles();
    		
    		// filters out the files and not the directories.
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
    				
    				// store it in the results array.
    				fileMetaArray.add(currentFileMeta);
    				
    				// debug
    				//System.out.println(currentFileMeta);
    			}
    		}
    	}
    	catch(IOException e) {
    		 e.printStackTrace();
    	}
    	
    	return fileMetaArray;
    }
	
	// Listing directories.
	// =======================================================================
	
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

	/**
	 * given a path and a filename,
	 * tries to obtain meta info
	 * not sure how to return path.
	 * 
	 * @param client
	 * @param path
	 * @param fileName
	 */
	public static FileMeta getFileMeta(FTPClient client, String path, String fileName) {
    	boolean status = false;
    	FileMeta fm = null;
		
		try {
			// change working directory.
    		status = client.changeWorkingDirectory(path);
    		
    		if (status == true) {
    			FTPFile[] files = client.listFiles();
	    		for(FTPFile file: files) {
	    			// check for target file.
	    			if (file.isFile() && file.getName().equals(fileName)) {
	    				fm = new FileMeta();
	                    fm.setName(file.getName());
	                    fm.setSize(file.getSize());
	                    fm.setDate(file.getTimestamp());
	                }
	    		}
    		}
    	}
    	catch(IOException e) {
    		 e.printStackTrace();
    	}
    	
    	return fm;
    }

	/**
	 * just a print statement.
	 * 
	 * @param client
	 */
	public static void list(FTPClient client) {
		System.out.println("> DIR");
		FTPUtils.listDir(client);
		System.out.println("> FILE");
		FTPUtils.listFiles(client);
	}
	
	// CONNECTION UTILITIES.
	// =======================================================================
	
    /**
     * connect to the ftp site.
     * 
     * @return
     */
	public static FTPClient connect(String url, String user, String password, int numTries, int delay) {
		FTPClient client = new FTPClient();
		int numTriesLeft = numTries;
		
		while(true) {
			try {
				// connect to site.
				client.connect(url);
				boolean status = client.login(user, password);
				
				// check connection
				// if status is true, return it.
				if (status) {
					System.out.println("Connection sucessful");
					client.enterLocalPassiveMode();
					return client;
				}
				else {
					System.out.println("Connection unsucessful");
					return null;
				}
			} 
			catch (Exception e) {
				System.out.println("Connection exception");
				e.printStackTrace();
				
				// check if you finished number of tries.
				if (numTriesLeft == 0) {
					break;
				}
				else {
					// try again if the connection failed.
					TimerUtil.sleep(delay);
					numTriesLeft--;
				}
			}
		}
			
		return client;
	}
	
	/**
	 * a helper connection method that tries to connect anonymousely.
	 * 
	 * @param url
	 * @return
	 */
	public static FTPClient connectAnon(String url, int numTries, int delay) {
		return FTPUtils.connect(url, "anonymous", "", numTries, delay);
	}
	
	/**
	 * disconnects when done.
	 * always call this.
	 */
	public static boolean disconnect(FTPClient client) {
		try {
			client.disconnect();
			return true;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
