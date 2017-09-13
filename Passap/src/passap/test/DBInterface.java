package passap.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;

public class DBInterface {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private PreparedStatement psmt;
	
	public DBInterface(){
		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ods.setUser("kevin");
			ods.setPassword("123");
			conn = ods.getConnection();
			stmt = conn.createStatement();
			psmt = conn.prepareStatement("insert into THEMANAGER values (?, ?, ?)");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	public String[] getNames() {
		//unscramble names
		ArrayList<String> scrambledList = new ArrayList<String>();
		try {
			rset = stmt.executeQuery("select Name from THEMANAGER");
			while (rset.next()) {
				scrambledList.add(rset.getString(1));
			}
			if (scrambledList.isEmpty()) {
				return null;
			}
			String[] list = new String[scrambledList.size()];
			for (int i = 0; i<scrambledList.size(); i++) {
				list[i] = Scrambler.decrypt(scrambledList.get(i));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String[] getInfo(String name) {
		ArrayList<String> scrambledList = new ArrayList<String>();
		try {
			String scrambledName = Scrambler.encrypt(name);
			rset = stmt.executeQuery("select THEMANAGER.* from THEMANAGER where Name = '"+scrambledName+"'");
			while (rset.next()) {
				scrambledList.add(rset.getString(1));
				scrambledList.add(rset.getString(2));
				scrambledList.add(rset.getString(3));
			}
			if (scrambledList.isEmpty()) {
				return null;
			}
			String[] list = new String[scrambledList.size()];
			for (int i = 0; i<scrambledList.size(); i++) {
				list[i] = Scrambler.decrypt(scrambledList.get(i));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int addEntry(String name, String usr, String pass) {
		//scramble info
		try {
			String scrambledName = Scrambler.encrypt(name);
			String scrambledUsr = Scrambler.encrypt(usr);
			String scrambledPass = Scrambler.encrypt(pass);
			try {
				psmt.setString(1, scrambledName);
				psmt.setString(2, scrambledUsr);
				psmt.setString(3, scrambledPass);
				psmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				return -2; //-2 = SQL error
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1; //-1 = scrambler error
		}
		return 0; //ok
	}
	
	public int updateEntry(String name, String usr, String pass) { //TODO: ensure that name passed in is not updated name but old name
		//scramble info
		try {
			String scrambledName = Scrambler.encrypt(name);
			String scrambledUsr = Scrambler.encrypt(usr);
			String scrambledPass = Scrambler.encrypt(pass);
			try {
				stmt.executeUpdate("update THEMANAGER set Username = '"+ scrambledUsr + "', Password = '"+ scrambledPass + "' where Name = '" + scrambledName +"'");
			} catch (SQLException e) {
				e.printStackTrace();
				return -2; //-2 = SQL error
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1; //-1 = scrambler error
		}
		return 0; //ok
	}
	
	public int deleteEntry(String name) {
		try {
			String scrambledName = Scrambler.encrypt(name);
			try {
				stmt.executeUpdate("delete from THEMANAGER "+ " where Name = '" + scrambledName +"'");
			} catch (SQLException e) {
				e.printStackTrace();
				return -2; //-2 = SQL error
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1; //-1 = scrambler error
		}
		return 0; //ok
	}
	
	public void close() {
		try {
			if (rset !=null)rset.close();
			if (stmt !=null)stmt.close();
			if (psmt !=null)psmt.close();
			if (conn !=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
