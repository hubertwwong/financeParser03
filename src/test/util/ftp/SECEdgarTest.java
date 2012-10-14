package test.util.ftp;

import static org.junit.Assert.*;

import main.util.ftp.SECEdgar;

import org.junit.Test;

public class SECEdgarTest {

//	@Test
//	public void testGetXBRLKey() {
//		SECEdgar se = new SECEdgar();
//		
//		// connect.
//		boolean status = se.connect();
//		assertEquals(true, status);
//		
//		// pull key.
//		status = se.getXBRLKeyFiles();
//		assertEquals(true, status);
//		
//		se.disconnect();
//	}
	
	@Test
	public void testGetXBRLKeyMeta() {
		SECEdgar se = new SECEdgar();
		
		// connect.
		boolean status = se.connect();
		assertEquals(true, status);
		
		// pull key.
		status = se.getXBRLKeyFilesMeta();
		assertEquals(true, status);
		
		se.disconnect();
	}
	
//	@Test
//	public void testConnect() {
//		SECEdgar se = new SECEdgar();
//		
//		boolean status = se.connect();
//		assertEquals(true, status);
//		
//		se.disconnect();
//	}

}
