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
import model.TodayBooking;
import model.aboutMovie;

public class BookingDAO {
	public static int insertBooking(aboutMovie aboutMovie, String idUser) throws SQLException, ClassNotFoundException
	{
		
		String queryString = "INSERT INTO tbl_bookings(ticket_id, t_id, user_id, show_id, screen_id, no_seats, amount, ticket_date, date, status) VALUES(?,?,?,?,?,?,?,?,?,1)";
		
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, "BKID"+new Date().getTime()+"");
		pstmt.setString(2, aboutMovie.getId());
		pstmt.setString(3, idUser);
		pstmt.setString(4, aboutMovie.getS_id());
		pstmt.setString(5, aboutMovie.getIdScreen());
		pstmt.setString(6, aboutMovie.getSeats());
		pstmt.setString(7, aboutMovie.getCharge());
		pstmt.setString(8, aboutMovie.getDate());
		pstmt.setString(9, aboutMovie.getTime_booking());

		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
	public static List<TodayBooking> loadToDayBooking(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_bookings.ticket_id, tbl_registration.name, tbl_registration.phone, tbl_bookings.no_seats\n"
				+ "FROM tbl_shows, tbl_bookings, tbl_movie, tbl_registration\n"
				+ "WHERE tbl_shows.st_id = '"+id+"' AND tbl_shows.r_status = 1 AND tbl_shows.s_id = tbl_bookings.show_id AND  tbl_movie.movie_id = tbl_shows.movie_id AND tbl_registration.user_id = tbl_bookings.user_id AND tbl_bookings.date = CURDATE()";
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<TodayBooking> movies = new ArrayList<TodayBooking>();
		
		while (rs.next()) {
			movies.add(new TodayBooking(rs.getString("ticket_id"), rs.getString("name"), rs.getString("phone"), rs.getString("no_seats")));
			}
		return movies;
	}
}
