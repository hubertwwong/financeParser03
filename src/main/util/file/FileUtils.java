package main.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class FileUtils {

	public static ArrayList<FileMeta> getFileMeta(String dirName) {
		String path = dirName;
		ArrayList<FileMeta> filesMetaList = new ArrayList<FileMeta>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
	 
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				FileMeta fm = new FileMeta();
				Calendar curFileDate = Calendar.getInstance();
				curFileDate.setTimeInMillis(listOfFiles[i].lastModified());
				
				// load fm object
				fm.setName(listOfFiles[i].getName());
				fm.setSize(listOfFiles[i].length());
				fm.setDate(curFileDate);
				filesMetaList.add(fm);
				
				// debug
				System.out.println(fm.toString());
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
