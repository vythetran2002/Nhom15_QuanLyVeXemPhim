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
import model.Show;
import model.Showtime;
import model.aboutMovie;

public class ShowDAO {

	public static int updatrShow(String idShow, String status) throws ClassNotFoundException, SQLException
		{
			String queryString = "UPDATE `tbl_shows` SET `r_status` = ? WHERE `tbl_shows`.`s_id` = ?";
			Connection conn = DBconnection.getOracleConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryString,
	                Statement.RETURN_GENERATED_KEYS);
		
			pstmt.setString(1, status);
			pstmt.setString(2, idShow);
		
			int rowAffected = pstmt.executeUpdate();
	
			if (rowAffected == 1) {
				return rowAffected;
			}
			
			conn.close();
			pstmt.close();
			return -1;
		}
	
	public static int stopShow(String idShow, String status) throws ClassNotFoundException, SQLException
	{
		String queryString = "UPDATE `tbl_shows` SET `status` = ? WHERE `tbl_shows`.`s_id` = ?";
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, status);
		pstmt.setString(2, idShow);
	
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
	public static int insertShow(Show show) throws SQLException, ClassNotFoundException
	{
		
		String queryString = "INSERT INTO `tbl_shows`(`st_id`, `theatre_id`, `movie_id`, `start_date`, `status`, `r_status`) VALUES (?,?,?,?,1,0)";
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, show.getShowTime());
		pstmt.setString(2, show.getTheatre());
		pstmt.setString(3, show.getMovie());
		pstmt.setString(4, show.getDate());
	
		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
	
	
	public static List<Show> loadShow(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_shows.s_id, tbl_screens.screen_name, tbl_show_time.name, tbl_show_time.start_time, tbl_movie.movie_name,tbl_shows.status, tbl_shows.r_status FROM tbl_shows, tbl_show_time, tbl_screens, tbl_movie\n"
				+ "WHERE tbl_shows.st_id = tbl_show_time.st_id and tbl_show_time.screen_id = tbl_screens.screen_id and tbl_shows.movie_id = tbl_movie.movie_id AND tbl_shows.theatre_id  AND tbl_screens.t_id and tbl_shows.theatre_id = '"+id+"' and tbl_shows.r_status = 1 and tbl_shows.status = 1";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Show> shows = new ArrayList<Show>();
		
		while (rs.next()) {
			shows.add(new Show(rs.getString("s_id"), rs.getString("screen_name"), rs.getString("name"), rs.getString("start_time"), rs.getString("movie_name"),rs.getString("status"),rs.getString("r_status")));
			}
		return shows;
	}
	
	public static List<Show> loadAllShow(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_shows.s_id, tbl_screens.screen_name, tbl_show_time.name, tbl_show_time.start_time, tbl_movie.movie_name,tbl_shows.status, tbl_shows.r_status FROM tbl_shows, tbl_show_time, tbl_screens, tbl_movie\n"
				+ "WHERE tbl_shows.st_id = tbl_show_time.st_id and tbl_show_time.screen_id = tbl_screens.screen_id and tbl_shows.movie_id = tbl_movie.movie_id AND tbl_shows.theatre_id  AND tbl_screens.t_id and tbl_shows.theatre_id = '"+id+"' and tbl_shows.status = 1";
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Show> shows = new ArrayList<Show>();
		
		while (rs.next()) {
			shows.add(new Show(rs.getString("s_id"), rs.getString("screen_name"), rs.getString("name"), rs.getString("start_time"), rs.getString("movie_name"),rs.getString("status"),rs.getString("r_status")));
			}
		return shows;
	}
	
	
	public static List<Showtime> loadShowTime(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM `tbl_show_time` WHERE tbl_show_time.screen_id = '"+id+"'";
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Showtime> shows = new ArrayList<Showtime>();
		
		while (rs.next()) {
			shows.add(new Showtime(rs.getString("st_id"), rs.getString("screen_id"), rs.getString("name"), rs.getString("start_time")));
			}
		return shows;
	}
	

}
