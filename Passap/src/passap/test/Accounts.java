package passap.test;

public class Accounts {
	private final String loginUser[] = {"Kevin"};
	private final String loginPassword[]= {"123"};
	
	public boolean authenticate(String usr, String pass) {
		String[] users = getLoginUser();
		String[] passs = getLoginPassword();
		
		//assuming user array same length as passwords (and in correct order)
		for (int i = 0; i < users.length; i++) {
			if (users[i].equals(usr) && passs[i].equals(pass))
				return true;
		}
		return false;
	}
	
	private String[] getLoginPassword() {
		return loginPassword;
	}
	
	private String[] getLoginUser() {
		return loginUser;
	}

}
