package main.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class FileUtils {
	
	/**
	 * compares 2 list of files.
	 * returns a list of files that you need to downloads.
	 * first compares the dates.
	 * then compares file sizes.
	 * 
	 * @param newFiles
	 * @param oldFiles
	 * @return
	 */
	public static ArrayList<FileMeta> filesToDownload(ArrayList<FileMeta> ftpFiles, ArrayList<FileMeta> localFiles) {
		ArrayList<FileMeta> results = new ArrayList<FileMeta>();
		
		// cycle thru each new file.
		for(FileMeta currentFTPFile: ftpFiles) {
			// check for a match..
			FileMeta currentLocalFile = FileMeta.compareByName(currentFTPFile, localFiles);
			
			// if it can't find a local file add it in.
			if (currentLocalFile == null) {
				results.add(currentFTPFile);
				System.out.println("adding new " + currentFTPFile + " size " + results.size());
			}
			else {
				long sizeDiff = FileMeta.compareBySize(currentFTPFile, currentLocalFile);
				// check for exact match
				if (sizeDiff != 0) {
					results.add(currentFTPFile);
					System.out.println("adding diff " + currentFTPFile + " size " + results.size());
				}
				else {
					System.out.println("adding failed");
				}
			}
			
		}
		
		// printing results
		
		System.out.println("final size " + results.size());
		return results;
	}
	
	/**
	 * compare the file sizes.
	 * 
	 * 4 codes.
	 * 2 = new file exist but not old file.
	 * 0 = 2 files have same dates.
	 * -1 = old file of same name is newer.
	 * 1 = new file of same name is newer.
	 * 
	 * @param newFiles
	 * @param oldFiles
	 * @return
	 */
	public static int[] compareDirSizes(ArrayList<FileMeta> newFiles, ArrayList<FileMeta> oldFiles) {
		int[] results = new int[newFiles.size()];
		int i = 0;	// index.
		
		// cycle thru each new file.
		for(FileMeta currentNewFile: newFiles) {
			FileMeta currentOldFile = FileMeta.compareByName(currentNewFile, oldFiles);
			
			// check for new files and not old file.
			if (currentOldFile == null) {
				results[i] = 2;
			}
			else {
				// debug
//				System.out.println(currentNewFile.toString());
//				System.out.println(currentOldFile.toString());
				
				long returnCode = FileMeta.compareBySize(currentNewFile, currentOldFile);
				if (returnCode > 0) {
					results[i] = 1;
				}
				else if (returnCode < 0) {
					results[i] = -1;
				}
				else {
					results[i] = 0;
				}
			}
			
			// index file.
			i++;
		}
		
		return results;
	}
	
	/**
	 * compare the dates.
	 * 
	 * 4 codes.
	 * 2 = new file exist but not old file.
	 * 0 = 2 files have same dates.
	 * -1 = old file of same name is newer.
	 * 1 = new file of same name is newer.
	 * 
	 * @param newFiles
	 * @param oldFiles
	 * @return
	 */
	public static int[] compareDirDates(ArrayList<FileMeta> newFiles, ArrayList<FileMeta> oldFiles) {
		int[] results = new int[newFiles.size()];
		int i = 0;	// index.
		
		// cycle thru each new file.
		for(FileMeta currentNewFile: newFiles) {
			FileMeta currentOldFile = FileMeta.compareByName(currentNewFile, oldFiles);
			
			// check for new files and not old file.
			if (currentOldFile == null) {
				results[i] = 2;
			}
			else {
				// debug
				// System.out.println(currentNewFile.toString());
				// System.out.println(currentOldFile.toString());
				
				int returnCode = FileMeta.compareByDates(currentNewFile, currentOldFile);
				if (returnCode > 0) {
					results[i] = 1;
				}
				else if (returnCode < 0) {
					results[i] = -1;
				}
				else {
					results[i] = 0;
				}
			}
			
			// index file.
			i++;
		}
		
		return results;
	}

	/**
	 * given a dir name, return file file meta of all files.
	 * 
	 * @param dirName
	 * @return
	 */
	public static ArrayList<FileMeta> getDirFilesMeta(String dirName) {
		String path = dirName;
		ArrayList<FileMeta> filesMetaList = new ArrayList<FileMeta>();
		System.out.println("local " + dirName);
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
	 
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				FileMeta fm = new FileMeta();
				// load file data.
				Calendar curFileDate = Calendar.getInstance();
				curFileDate.setTimeInMillis(listOfFiles[i].lastModified());
				
				// load file meta object object
				fm.setName(listOfFiles[i].getName());
				fm.setSize(listOfFiles[i].length());
				fm.setDate(curFileDate);
				filesMetaList.add(fm);
				
				// debug
				//System.out.println(fm.toString());
			}
		}
		
		// return results
		return filesMetaList;
	}
	
	/**
	 * get meta data for a single file.
	 * if the file does not exist, return null.
	 * else return true.
	 * 
	 * @param fileName
	 * @return
	 */
	public static FileMeta getFileMeta(String fullPathFileName) {
		FileMeta fm = null;
		
		File aFile = new File(fullPathFileName);
		
		// if file exist load the contents of the file.
		if (aFile.exists()) {
			fm = new FileMeta();
			fm.setPath(aFile.getAbsolutePath());
			fm.setName(aFile.getName());
			fm.setSize(aFile.length());
			Calendar curFileDate = Calendar.getInstance();
			curFileDate.setTimeInMillis(aFile.lastModified());
			fm.setDate(curFileDate);
		}
		
		// return results
		return fm;
	}

	/**
	 * given a full path file name
	 * return the path.
	 * 
	 * for now, just assume "/" is the only thing you can use.
	 * might need to fix that later.
	 * 
	 * @param fullPathFielName
	 * @return
	 */
	public static String getJustPath(String fullPathFileName) {
		String pattern = "[/][^/]+$";
		String path = fullPathFileName.replaceAll(pattern, "");
		
		return path;
	}
	
	/**
	 * given a full path file name
	 * return the filename.
	 * 
	 * @param fullPathFileName
	 * @return
	 */
	public static String getJustFilename(String fullPathFileName) {
		String pattern = "[^/]*[/]";
		String path = fullPathFileName.replaceAll(pattern, "");
		
		return path;
	}
	
	
	/**
	 * debug method.
	 * basically print out files for a directory.
	 * 
	 * @param dirName
	 */
	public static void listDir(String dirName) {
		String files;
		File folder = new File(dirName);
		File[] listOfFiles = folder.listFiles(); 
	 
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				System.out.println(files);
			}
		}
	}
	
	/**
	 * creates a directory if it doesn't exist.
	 * returns true if it has to do it.
	 * returns false if it doesn't
	 * 
	 * @param dirName
	 * @return
	 */
	public static boolean createDirIfNotExist(String dirName) {
		File folder = new File(dirName);
		boolean status;
		
		if (!folder.exists()) {
			// need to use mkdirs for nested directories.
			status = folder.mkdirs();
			System.out.println(folder.toString() + " " + status);
			return status;
		}
		
		return false;
	}
	
	/**
	 * given a directory and a list of file names,
	 * tries to delete said files.
	 * 
	 * @param path
	 * @param filesToDownload
	 * @return
	 */
	public static boolean deleteFiles(String path, ArrayList<FileMeta> filesToDelete) {
		File folder = new File(path);
		boolean status = true;
		
		if (folder.exists()) {
			// fetch local dir.
			ArrayList<FileMeta> localFiles = FileUtils.getDirFilesMeta(path);
			
			// cycle the files to delete
			for(FileMeta fileToDelete : filesToDelete) {
				if (fileToDelete.existsByName(localFiles)) {
					File deleteThisFile = new File(path + "/" + fileToDelete.getName());
					deleteThisFile.delete();
				}
			}
			
		}
		
		return status;
	}
	
}