package main.util.file;

import java.text.SimpleDateFormat;
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
