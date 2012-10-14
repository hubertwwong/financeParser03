package test.util.file;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.util.file.FileMeta;
import main.util.file.FileUtils;

import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testGetFileMeta() {
		ArrayList<FileMeta> files = FileUtils.getFileMeta("c:/windows");
		assertEquals(true, files.size() > 2);
	}
	
//	@Test
//	public void testListDirectory() {
//		FileUtils.listDir("c:\\Temp\\");
//		fail("Not yet implemented");
//	}

}
