package test.parsers;

import static org.junit.Assert.*;

import main.parsers.EdgarParser;

import org.junit.Test;

public class EdgarParserTest {

	@Test
	public void connectToEdgar() {
		EdgarParser e = new EdgarParser();
		e.connectToEdgar();
		assertEquals(true, false);
	}
	
	@Test
	public void connectToGoogle() {
		EdgarParser e = new EdgarParser();
		e.connectToGoogle();
		assertEquals(true, false);
	}

}
