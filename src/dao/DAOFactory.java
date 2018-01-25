package dao;

import java.sql.*;

public class DAOFactory {
	
	private String Driver = "com.mysql.jdbc.Driver";
	private String URL ="jdbc:mysql://localhost:3306/newdb?useSSL=false";
	Connection con;
	
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public Connection getDBConnection()	throws SQLException {
		
		try {
			Class.forName(Driver).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(URL, "root", "0000");
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
			
		} catch (SQLException e) {
			throw e;
		}			
		return con;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		con.close();
		super.finalize();
	}

}
