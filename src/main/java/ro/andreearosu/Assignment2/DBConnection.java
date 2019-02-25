package ro.andreearosu.Assignment2;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.derby.jdbc.EmbeddedDriver;

public class DBConnection {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	String createTable = "create table users("
			+ "id integer not null generated always as identity (start with 1, increment by 1), "
			+ "name varchar(30) not null, "
			+ "email varchar(30) not null, "
			+ "phone varchar(30) not null)";
	String insertIntoTable = "insert into users(name,email,phone) values(?,?,?)";

	public DBConnection() {
		try {
			Driver derbyEmbeddedDriver = new EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);
			connect = DriverManager.getConnection("jdbc:derby:userDB;create=true",
					"sqluser","pass123");
			connect.setAutoCommit(false);
			Statement stmt = connect.createStatement();
			/*System.out.println("Trying to create table <users>");
			//stmt.execute(createTable);
			System.out.println("Table <users> has been created");
			PreparedStatement prepStatement = connect.prepareStatement(insertIntoTable);
			prepStatement.setString(1, "Andreea Red");
			prepStatement.setString(2, "smth@domail.com");
			prepStatement.setString(3, "0765432187");
			//prepStatement.executeUpdate();
			prepStatement.close();*/
			SelectFromDB("users");
			//stmt.execute("drop table users");
			connect.commit();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConn() {
		try {
	         DriverManager.getConnection
	            ("jdbc:derby:;shutdown=true");
	      } catch (SQLException e) {
	         if (((e.getErrorCode() == 50000) &&
	            ("XJ015".equals(e.getSQLState())))) {
	               System.out.println("Derby shut down normally");
	         } else {
	            System.err.println("Derby did not shut down normally");
	            System.err.println(e.getMessage());
	         }
	      }
	}
	
	public void SelectFromDB(String dbTable) throws Exception {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from " + dbTable);
		while (resultSet.next()) {
			String user = resultSet.getString("name");
			String email = resultSet.getString("email");
			String phone = resultSet.getString("phone");
			//System.out.println("Username: " + user + ", Email address: " + email+ ", Phone no: " + phone);
		}
	resultSet.close();
	}
	
	public ArrayList<String[]> selectToHtml(String dbTable) throws Exception {
		ArrayList<String[]> table = new ArrayList<String[]>();
		statement = connect.createStatement();
		//String responsec = "";
		resultSet = statement.executeQuery("select * from " + dbTable);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnCount = rsmd.getColumnCount();
		System.out.println("Select * from users;");
		while (resultSet.next()) {
			System.out.println("");
			for (int x = 1; x<=columnCount; x++) { 
				System.out.format("%20s", resultSet.getString(x) + "  |  " );
				//responsec += resultSet.getString(x) + "  |  " ; 
			}
			System.out.println("");
			String id = "" + resultSet.getInt("id");
			String name = resultSet.getString("name").replace("+", " ");
			String email = resultSet.getString("email");
			String phone = resultSet.getString("phone");
			//System.out.println("Username: " + name + ", Email address: " + email+ ", Phone no: " + phone);
			String[] row = {id, name, email, phone};
			table.add(row);
		}
	resultSet.close();
	return table; 
	}
	
	public void InsertIntoDB(Map<String, String> keyVal, String dbTable) throws SQLException {
		
		String ps = "insert into " + dbTable +"(";
		String psv = " values(";
		int i=1;
		for(Map.Entry<String, String> entry :keyVal.entrySet()) {
			if (i < keyVal.size()) {
				ps += entry.getKey() + ", ";
				psv += "?,";
				//System.out.println(i);
				} else {
					ps += entry.getKey() + ")";
					psv += "?)";
					}
			i++;
			//System.out.println("Entry.getKey() -> " + entry.getKey());
		}
		ps += psv;
		
		preparedStatement = connect.prepareStatement(ps);
		int j=1;
		for(Map.Entry<String, String> entry :keyVal.entrySet()) {
			preparedStatement.setString(j, entry.getValue());
			//System.out.println("Entry.getValue() -> " + entry.getValue());
			j++;
		}
		try {
			preparedStatement.executeUpdate();
			connect.commit();
			preparedStatement.close();	
		} catch (SQLException e) {
			String theError = e.getSQLState();
		    System.out.println( "Can't insert rows in table: " + theError );
		    //System.exit(1);
		}
	}
	
	public void DeleteFromDB(String dbTable, String referinta) throws SQLException{
		preparedStatement = connect.prepareStatement("delete from " + dbTable +" where name = ?;");
		preparedStatement.setString(1,referinta);
		preparedStatement.executeUpdate();
	}
}
