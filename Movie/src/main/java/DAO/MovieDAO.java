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
import model.Movie;
import model.MovieNews;
import model.RunningMovie;
import model.aboutMovie;

public class MovieDAO {
	public static List<Movie> getAllMovie() throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM tbl_movie WHERE `tbl_movie`.`status` = '0'";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Movie> movies = new ArrayList<Movie>();
		
		while (rs.next()) {
			movies.add(new Movie(rs.getString("movie_id"), rs.getString("t_id"), rs.getString("movie_name"), rs.getString("cast"), rs.getString("desc"),rs.getString("release_date"),rs.getString("image"),rs.getString("video_url"),rs.getString("status")));
		}
		return movies;
	}
	
	public static List<aboutMovie> getMovieByID(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_theatre.name, tbl_theatre.id as idtheatre, tbl_theatre.place as place ,tbl_show_time.start_time, tbl_screens.screen_id as idScreen, tbl_screens.screen_name, tbl_show_time.st_id as idshowtime, tbl_show_time.name as nametime, tbl_screens.charge, tbl_screens.seats,tbl_shows.s_id\n"
				+ "FROM tbl_show_time, tbl_shows, tbl_theatre, tbl_screens\n"
				+ "WHERE tbl_show_time.st_id = tbl_shows.st_id and tbl_shows.theatre_id = tbl_theatre.id and tbl_screens.screen_id = tbl_show_time.screen_id AND tbl_shows.movie_id = '"+id+"'";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<aboutMovie> movies = new ArrayList<aboutMovie>();
		
		while (rs.next()) {
			movies.add(new aboutMovie(rs.getString("idtheatre"), rs.getString("name"), rs.getString("place"), rs.getString("start_time"), rs.getString("idScreen"),rs.getString("screen_name"),rs.getString("idShowtime"),rs.getString("nametime"),rs.getString("charge"), rs.getString("seats"), rs.getString("s_id")));
		}
		return movies;
	}
	
	public static List<MovieNews> getAllMovieNews() throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM tbl_news";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<MovieNews> movies = new ArrayList<MovieNews>();
		
		while (rs.next()) {
			movies.add(new MovieNews(rs.getString("news_id"), rs.getString("name"), rs.getString("cast"), rs.getString("news_date"), rs.getString("description"),rs.getString("attachment")));
		}
		return movies;
	}
	
	public static List<RunningMovie> loadRunningMovie(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_show_time.name,tbl_show_time.start_time ,tbl_screens.screen_name, tbl_movie.movie_name\n"
				+ "FROM tbl_shows, tbl_theatre, tbl_show_time, tbl_screens, tbl_movie\n"
				+ "WHERE tbl_shows.movie_id = tbl_movie.movie_id  AND tbl_show_time.st_id = tbl_shows.st_id and tbl_screens.screen_id = tbl_show_time.screen_id and tbl_shows.r_status = 1 AND tbl_theatre.id = tbl_shows.theatre_id and tbl_theatre.id = "+id+" AND `tbl_movie`.`status` = '0'";
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<RunningMovie> movies = new ArrayList<RunningMovie>();
		
		while (rs.next()) {
			movies.add(new RunningMovie(rs.getString("name"), rs.getString("start_time"), rs.getString("screen_name"), rs.getString("movie_name")));
		}
		return movies;
	}
	
	

	public static int deleteMovie(String idShow) throws ClassNotFoundException, SQLException
		{
			String queryString = "UPDATE `tbl_movie` SET `status` = '1' WHERE `tbl_movie`.`movie_id` = ?";
			Connection conn = DBconnection.getOracleConnection();
			PreparedStatement pstmt = conn.prepareStatement(queryString,
	                Statement.RETURN_GENERATED_KEYS);
		
			pstmt.setString(1, idShow);
		
			int rowAffected = pstmt.executeUpdate();
	
			if (rowAffected == 1) {
				return rowAffected;
			}
			
			conn.close();
			pstmt.close();
			return -1;
		}
	
	
	public static int insertMovie(Movie movie) throws SQLException, ClassNotFoundException
	{
		
		String queryString = "INSERT INTO tbl_movie( t_id, movie_name, cast, `desc`, release_date, image, video_url, status) VALUES (?, ?, ?,?,?,?,?,0)";
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, movie.getThreatre_id());
		pstmt.setString(2, movie.getMovie_name());
		pstmt.setString(3, movie.getCast());
		pstmt.setString(4, movie.getDesc());
		pstmt.setString(5, movie.getRelease_date());
		pstmt.setString(6, movie.getImage());
		pstmt.setString(7, movie.getVideo_url());
		
		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
	public static int insertMovieNews(Movie movie) throws SQLException, ClassNotFoundException
	{
		
		String queryString = "INSERT INTO tbl_news(name, cast, news_date, description, attachment) VALUES(?,?,?,?,?)";
		
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, movie.getMovie_name());
		pstmt.setString(2, movie.getCast());
		pstmt.setString(3, movie.getRelease_date());
		pstmt.setString(4, movie.getDesc());
		pstmt.setString(5, movie.getImage());
		
		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
}
