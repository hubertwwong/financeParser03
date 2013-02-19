package test.runners.parsers;

import static org.junit.Assert.*;

import main.runners.parsers.XBRLRunner;

import org.junit.Test;

public class XBRLRunnerTest {

	@Test
	public void test01() {
		XBRLRunner xbrl = new XBRLRunner();
		xbrl.run();
		assertEquals(true, false);
	}

}
