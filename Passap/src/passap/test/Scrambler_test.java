package passap.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Scrambler_test {
	
	String pass = "ThisIsAPassword1@;";
	
	String scrambledEggs;
	
	String overEasy;

	@Test
	public void scrambleTest() {
		try {
			scrambledEggs = Scrambler.encrypt(pass);
			assertNotEquals("scramble failed to scramble","ThisIsAPassword1@;", scrambledEggs);
		} catch (Exception e) {
			fail("scramble failed to scramble");
		}
	}

	@Test
	public void descrambleTest() {
		try {
			overEasy = Scrambler.decrypt("GOEyZtI5k4OARu4eYfIEGwfhc4XBtIrxvnAQ7J2s80Q=");
			assertEquals("descramble failed to descramble","ThisIsAPassword1@;", overEasy);
		} catch (Exception e) {
			fail("descramble failed to descramble");
		}
	}
	
}
