package main.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class FileUtils {
	
	/**
	 * compare files in 2 directories.
	 * 
	 * possible status.
	 * new - new file on new list but not on old list.
	 * updated - found files in both directory. but the one in the newFiles is newer.
	 * 
	 * @param newFiles
	 * @param oldFiles
	 * @return
	 */
	public static ArrayList<String> compareDirs(ArrayList<FileMeta> newFiles, ArrayList<FileMeta> oldFiles) {
		return null;
	}

	public static ArrayList<FileMeta> getFileMeta(String dirName) {
		String path = dirName;
		ArrayList<FileMeta> filesMetaList = new ArrayList<FileMeta>();
		
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
	
	public static void listDir(String dirName) {
		// Directory path here
		String path = dirName;
 
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
	 
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				System.out.println(files);
			}
		}
	}
	
}
