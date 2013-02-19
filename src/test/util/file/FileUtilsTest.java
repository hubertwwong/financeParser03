package test.util.file;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.util.file.FileMeta;
import main.util.file.FileUtils;

import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testGetFullPath02() {
		String fileName = "C:/Users/Hubert/Google Drive/code/main/java/financeParser03/testData/dirTest/temp01/blah01 - sadkl";
		String path = FileUtils.getJustPath(fileName);
		assertNotNull(path);
		assertEquals("C:/Users/Hubert/Google Drive/code/main/java/financeParser03/testData/dirTest/temp01", path);
		//assertEquals("01", fm.getPath());
	}
	
	@Test
	public void testFileMeta02() {
		String fileName = "C:/Users/Hubert/Google Drive/code/main/java/financeParser03/testData/dirTest/temp01/blah01.txt";
		FileMeta fm = FileUtils.getFileMeta(fileName);
		assertNotNull(fm);
		assertEquals("blah01.txt", fm.getName());
		//assertEquals("01", fm.getPath());
	}
	
	@Test
	public void testFileMeta01() {
		FileMeta fm = FileUtils.getFileMeta("c:/blah/asdf.cnd");
		assertNull(fm);
	}
	
	@Test
	public void testFilesToDownload01() {
		String dirPrefix = "C:/Users/Hubert/Google Drive/code/main/java/financeParser03/testData/dirTest/";
		ArrayList<FileMeta> newFilesMeta = FileUtils.getDirFilesMeta(dirPrefix + "temp01/");
		ArrayList<FileMeta> oldFilesMeta = FileUtils.getDirFilesMeta(dirPrefix + "temp02/");
		
		// checking for a diff.
		ArrayList<FileMeta> results = FileUtils.filesToDownload(newFilesMeta, oldFilesMeta);
		assertEquals(1, results.size());
		
		// checking to see if there is a new file.
		// i think blah01 has older text.
		FileMeta firstResult = results.get(0);
		assertEquals("blah02.txt", firstResult.getName());
	}
	
	@Test
	public void testFileCompareFiles01() {
		String dirPrefix = "C:/Users/Hubert/Google Drive/code/main/java/financeParser03/testData/dirTest/";
		ArrayList<FileMeta> newFilesMeta = FileUtils.getDirFilesMeta(dirPrefix + "temp01/");
		ArrayList<FileMeta> oldFilesMeta = FileUtils.getDirFilesMeta(dirPrefix + "temp02/");
		
		int[] results = FileUtils.compareDirSizes(newFilesMeta, oldFilesMeta);
		assertEquals(1, results[0]);
		assertEquals(2, results[1]);
	}
	
	@Test
	public void testFileCompareDates02() {
		String dirPrefix = "C:/Users/Hubert/Google Drive/code/main/java/financeParser03/testData/dirTest/";
		ArrayList<FileMeta> newFilesMeta = FileUtils.getDirFilesMeta(dirPrefix + "temp01/");
		ArrayList<FileMeta> oldFilesMeta = FileUtils.getDirFilesMeta(dirPrefix + "temp02/");
		
		int[] results = FileUtils.compareDirDates(newFilesMeta, oldFilesMeta);
		assertEquals(-1, results[0]);
		assertEquals(2, results[1]);
	}
	
	@Test
	public void testFileCompareDates01() {
		ArrayList<FileMeta> newFilesMeta = FileUtils.getDirFilesMeta("c:/Temp");
		ArrayList<FileMeta> oldFilesMeta = FileUtils.getDirFilesMeta("c:/Temp");
		int[] results = FileUtils.compareDirDates(newFilesMeta, oldFilesMeta);
		assertEquals(0, results[0]);
	}
	
	@Test
	public void testGetFileMeta() {
		ArrayList<FileMeta> files = FileUtils.getDirFilesMeta("c:/windows");
		assertEquals(true, files.size() > 2);
	}

	@Test
	public void testGetPath01() {
		String filename = "C:/windows/foo/bar.txt";
		String results = FileUtils.getJustPath(filename);
		
		assertEquals("C:/windows/foo", results);
	}
	
	@Test
	public void testGetFile01() {
		String filename = "C:/windows/foo/bar.txt";
		String results = FileUtils.getJustFilename(filename);
		
		assertEquals("bar.txt", results);
	}

	@Test
	public void testGetFile02() {
		String filename = "/windows/foo/bar.txt";
		String results = FileUtils.getJustFilename(filename);
		
		assertEquals("bar.txt", results);
	}
	
//	@Test
//	public void testListDirectory() {
//		FileUtils.listDir("c:\\Temp\\");
//		fail("Not yet implemented");
//	}

}
