package main.runners.parsers;

import java.util.HashMap;

import main.parsers.KeyFilesParser;
import main.parsers.config.MainSettings;

public class XBRLRunner {

	public boolean run() {
		// need to add the function to dowload key files...
		// skip it for now.
		HashMap<String, String> fileNames = KeyFilesParser.getAllKeyFilesDownloadPaths(MainSettings.LOCAL_KEY_PATH);
		fileNames = KeyFilesParser.filterDocTypes10K10Q(fileNames);
		
		// prep downloads...
		// shed names..
		
		// debug..
		System.out.println("numberOfFilesToDownload" + fileNames.size());
		
		return false;
	}
	
	public static void main(String[] args) {
		// need to add the function to dowload key files...
		// skip it for now.
		HashMap<String, String> fileNames = KeyFilesParser.getAllKeyFilesDownloadPaths(MainSettings.LOCAL_KEY_PATH);
		fileNames = KeyFilesParser.filterDocTypes10K10Q(fileNames);
		
		// prep downloads...
		// shed names..
		
		// debug..
		System.out.println("numberOfFilesToDownload" + fileNames.size());
	}
	
}