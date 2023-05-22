package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DB.DBconnection;
import model.Profile;
import model.Registration;
import model.aboutMovie;

public class UserDAO {
	public static List<Profile> LoadMyProfileByID(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_bookings.ticket_id, tbl_theatre.name, tbl_screens.screen_name, tbl_bookings.no_seats, tbl_bookings.amount, tbl_movie.movie_name, tbl_show_time.name as showname,tbl_bookings.status\n"
				+ "FROM tbl_bookings, tbl_theatre, tbl_screens, tbl_shows, tbl_movie, tbl_show_time\n"
				+ "WHERE tbl_bookings.t_id = tbl_theatre.id and tbl_bookings.screen_id = tbl_screens.screen_id and tbl_bookings.show_id = tbl_shows.s_id and tbl_shows.st_id = tbl_show_time.st_id and tbl_shows.movie_id = tbl_movie.movie_id and tbl_bookings.user_id = '"+id+"'";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Profile> profiles = new ArrayList<Profile>();
		
		while (rs.next()) {
			profiles.add(new Profile(rs.getString("ticket_id"), rs.getString("movie_name"), rs.getString("name"), rs.getString("screen_name"),rs.getString("showname"),rs.getString("no_seats"),rs.getString("amount"), rs.getString("status")));
		}
		return profiles;
	}
	
	public static int addRegister(Registration registration) throws SQLException, ClassNotFoundException
	{
		String queryString = "INSERT INTO tbl_registration(name, email, phone, age, gender) VALUES(?,?,?,?,?)";
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, registration.getName());
		pstmt.setString(2, registration.getEmail());
		pstmt.setString(3, registration.getPhone());
		pstmt.setString(4, registration.getAge());
		pstmt.setString(5, registration.getGender());
		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
//	public static String getUserLast() throws SQLException, ClassNotFoundException
//	{
//		String queryString = "SELECT * FROM tbl_registration ORDER BY user_id DESC LIMIT 1";
//		Connection conn = DBconnection.getOracleConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs  = stmt.executeQuery(queryString);
//		List<Registration> list=new ArrayList<>();
//		while (rs.next())
//			list.add(new Registration(rs.getString("user_id"),rs.getString("name"),
//					rs.getString("email"),rs.getString("phone"),rs.getString("age"),rs.getString("gender")));
//		return  list.get(0).getId();
//
//	}

}

