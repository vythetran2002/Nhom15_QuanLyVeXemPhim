package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import DB.DBconnection;

public class TimeDAO {
	public static int insertTime(String idScreen, String name, String time) throws SQLException, ClassNotFoundException
	{
		
		String queryString = "INSERT INTO `tbl_show_time`( `screen_id`, `name`, `start_time`) VALUES (?,?,?)";
		
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, idScreen);
		pstmt.setString(2, name);
		pstmt.setString(3, time);
		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
}
