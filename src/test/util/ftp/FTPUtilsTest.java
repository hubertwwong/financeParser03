package test.util.ftp;

import static org.junit.Assert.*;

import main.parsers.config.MainSettings;
import main.util.file.FileMeta;
import main.util.ftp.FTPUtils;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPUtilsTest {

	@Test
	public void testSyncFile01() {
		 FTPClient client = FTPUtils.connectAnon(MainSettings.URL, 200, 2000);
		 String ftpPath = "/edgar";
		 String ftpFilename = "README.txt";
		 String localPath = "c:/temp/zzzz/aaaa";
		 String localFilename = "README.txt";
		 boolean status = FTPUtils.syncFile(client, ftpPath, ftpFilename, localPath, localFilename);
		 
		 assertEquals(true, status);
	}
	
	@Test
	public void testGetFileInfo01() {
		 FTPClient client = FTPUtils.connectAnon(MainSettings.URL, 200, 2000);
		 String path = "/edgar";
		 String filename = "README.txt";
		 FileMeta fm = FTPUtils.getFileMeta(client, path, filename);
		 assertNotNull(fm);
	}

}
