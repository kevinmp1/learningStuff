package passap.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBInterface_test {
	DBInterface dbObject = new DBInterface();

	@Test
	public void getNamesTest() {
		String[] testString = {"Amazon","TeddyBear Junction", "Dell", "Taiga","Google","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"};
		assertArrayEquals("fails getNames", testString, dbObject.getNames());
	}
	
	@Test
	public void getInfoTest() {
		String[] testString = {"Sonic", "INFO.temp", "PASS.temp"};
		assertArrayEquals("fails getInfo", testString, dbObject.getInfo("Sonic"));
	}


}
