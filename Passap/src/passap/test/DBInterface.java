package passap.test;

public class DBInterface {
	
	public String[] getNames() {
		//unscramble names
		String[] list = {"Amazon","TeddyBear Junction", "Dell", "Taiga","Google","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"
				,"Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1","Name1"};
		return list;
		//return null; //return list of names
	}

	public String[] getInfo(String name) {
		//unscramble user/pass
		String[] info = {name, "INFO.temp", "PASS.temp"};
		return info; //return name, user, pass
	}
	
	public int addEntry(String name, String usr, String pass) {
		//scramble info
		return 0; //return codes (0:ok,-1:name error....)
	}
	
	public int updateEntry(String name, String usr, String pass) {
		//scramble info
		return 0; //return codes (0:ok, -1:name error....)
	}
	
	public int deleteEntry(String name) {
		//scramble name to delete
		return 0; //return codes (0:ok, -1:name error....)
	}
}
