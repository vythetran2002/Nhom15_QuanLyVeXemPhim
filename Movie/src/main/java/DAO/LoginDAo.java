package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import DB.DBconnection;
import model.Login;

public class LoginDAo {
	public static Login getUser(String username, String password) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM tbl_login where FIND_IN_SET('"+username+"', tbl_login.username) and FIND_IN_SET('"+password+"', tbl_login.password) and tbl_login.user_type = 2";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		
		while (rs.next()) {
			return new Login(rs.getString("id"), rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"));
		}
		return null;
	}
	
	public static int InsertLogin(Login login) throws ClassNotFoundException, SQLException
	{
		String queryString = "INSERT INTO tbl_login(user_id, username, password, user_type) VALUES(?,?,?,?)";
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, login.getUserId());
		pstmt.setString(2, login.getUsername());
		pstmt.setString(3, login.getPassword());
		pstmt.setString(4, login.getUserType());
		
		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
	public static Login getUserAdmin(String username, String password) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM tbl_login where FIND_IN_SET('"+username+"', tbl_login.username) and FIND_IN_SET('"+password+"', tbl_login.password) and tbl_login.user_type != 2";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		
		while (rs.next()) {
			return new Login(rs.getString("id"), rs.getString("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"));
		}
		return null;
	}
	
}
