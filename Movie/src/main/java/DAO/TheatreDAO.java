package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.DBconnection;
import model.Theatre;

public class TheatreDAO {
	public static List<Theatre> loadTheatres(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM `tbl_theatre` WHERE tbl_theatre.id = '"+id+"'";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Theatre> shows = new ArrayList<Theatre>();
		
		while (rs.next()) {
			shows.add(new Theatre(rs.getString("id"), rs.getString("name"), rs.getString("address"), rs.getString("place"), rs.getString("state"),rs.getString("pin")));
			}
		return shows;
	}

	public static int insertTheathe(Theatre theatre) throws SQLException, ClassNotFoundException
		{
			
			String queryString = "INSERT INTO tbl_theatre(name, address, place, state, pin) VALUES(?,?,?,?,?)";
			Connection conn = DBconnection.getOracleConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryString,
	                Statement.RETURN_GENERATED_KEYS);
		
			pstmt.setString(1, theatre.getName());
			pstmt.setString(2, theatre.getAddress());
			pstmt.setString(3, theatre.getPlace());
			pstmt.setString(4, theatre.getState());
			pstmt.setString(5, theatre.getPin());
		
			
			int rowAffected = pstmt.executeUpdate();
	
			if (rowAffected == 1) {
				return rowAffected;
			}
			
			conn.close();
			pstmt.close();
			return -1;
		}
	
	
}
