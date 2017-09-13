package passap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBInterface_test {
	private DBInterface dbObject;
	
	@Before
	public void setup() {
		dbObject = new DBInterface();
	}
	
	
	@Test
	public void getNamesTest() {
		assertNotNull("Failed to getNames",dbObject.getNames());
	}
	
	
	@Test
	public void getInfoTest() {
		assertNotNull("getInfo failed", dbObject.getInfo("Test"));
		assertNotNull("getInfo failed1", dbObject.getInfo("Kroger"));
		assertNotNull("getInfo failed2", dbObject.getInfo("Swiffer"));
	}

	@Test
	public void addEntryTest() {
		assertEquals("Failed add entry",0,dbObject.addEntry("Google", "Testuser", "TestPass"));
	}
	
	@Test
	public void updateEntryTest() {
		assertEquals("Failed to update", 0, dbObject.updateEntry("Google", "New", "New"));
	}
	
	@Test
	public void deleteEntryTest() {
		assertEquals("Failed delete entry",0,dbObject.deleteEntry("Google"));
	}
	
	@After
	public void close() {
		dbObject.close();
	}
}
