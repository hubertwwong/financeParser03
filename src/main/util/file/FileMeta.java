package main.util.file;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * a simple class that contains the files meta info.
 * stuff like file size, file name, date,
 * 
 * @author Hubert
 *
 */
public class FileMeta {
	
	private String name;
	private long size;
	private Calendar date;
	
	/**
	 * compares a file meta file against a arraylist.
	 * returns either null or the file meta object in the list.
	 * 
	 * @param fm
	 * @param fmList
	 * @return
	 */
	public static FileMeta compareFMByName(FileMeta fm, ArrayList<FileMeta> fmList) {
		FileMeta finalFileMeta = null;
		
		// compare file file name.
		for(FileMeta currentMeta: fmList) {
			if(currentMeta.getName().equals(fm)) {
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
	public int compareDates(FileMeta fmNew, FileMeta fmOld) {
		Calendar dateNew = fmNew.getDate();
		Calendar dateOld = fmOld.getDate();
		return dateNew.compareTo(dateOld);
	}
	
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
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("MM/dd/yyyy hh:mm:ss a");
		
		return String.format("%32s", name) 
				+ " | " + String.format("%16s", size) 
				+ " | " + df.format(this.getDate().getTime())
				;
	}
}
