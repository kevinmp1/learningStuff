package passap.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Accounts_test {
	Accounts acc = new Accounts();
	
	@Test
	public void testAuthenticate() {
		assertTrue("Authentication test failure", acc.authenticate("Kevin", "123"));
	}
	
	@Test
	public void testAuthenticateFailure() {
		assertFalse("Authentication test failing to fail", acc.authenticate("garbage", "thisissomerandomstuf3241"));
	}

}
