package main.util.file;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * a simple class that contains the files meta info.
 * stuff like file size, file name, date,
 * 
 * might wanna convert this to a File Object..
 * 
 * @author Hubert
 *
 */
public class FileMeta {
	
	private String name;
	private long size;
	private Calendar date;
	private String path;
	
	/**
	 * compare 2 file meta items and to see if there is an exact match
	 * weird that there isn't a file meta.
	 * 
	 * @param fm
	 * @return
	 */
	public boolean equals(FileMeta fm) {
		boolean status = true;

		if (!this.name.equals(fm.getName())) {
			System.out.println("name not equals");
			status = false;
		}
		if (this.size != fm.getSize()) {
			System.out.println("size not equals");
			status = false;
		}
		if (!this.date.equals(fm.getDate())) {
			System.out.println("date not equals");
			status = false;
		}
		
		return status;
	}

	/**
	 * equalsBySize
	 * 
	 * @param fm
	 * @return
	 */
	public boolean equalsBySize(FileMeta fm) {
		boolean status = true;

		if (this.size != fm.getSize()) {
			status = false;
		}
		return status;
	}
	
	/**
	 * equalsByName
	 * just check name only.
	 * not the other fields.
	 * 
	 * @param fm
	 * @return
	 */
	public boolean equalsByName(FileMeta fm) {
		boolean status = true;

		if (!this.name.equals(fm.getName())) {
			status = false;
		}
		return status;
	}
	
	/**
	 * checks if a file meta object is in a list of FM objects.
	 * simple conveinence method.
	 * using a matcher that compare all fields that I wrote.
	 */
	public boolean exists(ArrayList<FileMeta> fmList) {
		boolean status = false;
		
		// check the current file meta
		for(FileMeta currentFileMeta : fmList) {
			if (this.equals(currentFileMeta)) {
				status = true;
			}
		}
		
		return status;
	}
	
	/**
	 * checks if the file meta matches the file name of the list provided.
	 * 
	 * @param fmList
	 * @return
	 */
	public boolean existsByName(ArrayList<FileMeta> fmList) {
		boolean status = false;
		
		// check the current file meta
		for(FileMeta currentFileMeta : fmList) {
			if (this.equalsByName(currentFileMeta)) {
				status = true;
			}
		}
		
		return status;
	}
	
	/**
	 * compares a file meta file against a arraylist.
	 * returns either null or the file meta object in the list.
	 * 
	 * @param fm
	 * @param fmList
	 * @return
	 */
	public static FileMeta compareByName(FileMeta fm, ArrayList<FileMeta> fmList) {
		FileMeta finalFileMeta = null;
		
		// compare file file name.
		for(FileMeta currentMeta: fmList) {
			if(currentMeta.getName().equals(fm.getName())) {
				finalFileMeta = currentMeta;
			}
		}
		
		return finalFileMeta;
	}
	
	/**
	 * compare 2 files to see if the dates
	 * and file dates matches
	 * 
	 * neg fNew has older date
	 * 0   files have same dates
	 * pos fNew has newer date
	 * 
	 * the value 0 if the time represented by the argument is equal to 
	 * the time represented by this Calendar; a value less than 0 if the 
	 * time of this Calendar is before the time represented by the argument; 
	 * and a value greater than 0 if the time of this Calendar is after the 
	 * time represented by the argument.
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static int compareByDates(FileMeta fmNew, FileMeta fmOld) {
		Calendar dateNew = fmNew.getDate();
		Calendar dateOld = fmOld.getDate();
		return dateNew.compareTo(dateOld);
	}
	
	/**
	 * compare file sizes of new files vs. the old one.
	 * 
	 * returns fnew vs fold.
	 * >0 when fnew is larger
	 * <0 when fold is smaller
	 * @param fmNew
	 * @param fmOld
	 * @return
	 */
	public static long compareBySize(FileMeta fmNew, FileMeta fmOld) {
		long fileSizeNew = fmNew.getSize();
		long fileSizeOld = fmOld.getSize();
		System.out.println(fileSizeNew + " " + fileSizeOld);
		return fileSizeNew - fileSizeOld;
	}
	
	// getter / setters
	// =======================================================================
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("MM/dd/yyyy hh:mm:ss a");
		
		return String.format("%32s", name) 
				+ " | " + String.format("%16s", size) 
				+ " | " + df.format(this.getDate().getTime())
				;
	}
	
	/**
	 * a simple helper to print out files meta.
	 * 
	 * @param filesNames
	 */
	public static void printArray(ArrayList<FileMeta> filesNames) {
		System.out.println("printing " + filesNames.size() + " items");
		for(FileMeta currentFileName : filesNames) {
			System.out.println(currentFileName.toString());
		}
	}	
}