package DB;

import java.sql.*;
import java.util.ArrayList;


public class DBconnection {
	
	public static Connection getOracleConnection() throws SQLException,
    ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection conn = null;

		String url = "jdbc:mysql://localhost:3306/movietheatredb";
		
		String userName = "root";
		String password = "";
		conn = DriverManager.getConnection(url, userName, password);

		return conn;
	
	}
	

}
