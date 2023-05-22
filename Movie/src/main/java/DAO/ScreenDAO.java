package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DB.DBconnection;
import model.Screen;
import model.TodayBooking;

public class ScreenDAO {
	public static List<Screen> loadScreenByIDTheatre(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT * FROM `tbl_screens` WHERE t_id = '"+id+"'";
		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Screen> screens = new ArrayList<Screen>();
		
		while (rs.next()) {
			screens.add(new Screen(rs.getString("screen_id"), rs.getString("t_id"), rs.getString("screen_name"), rs.getString("seats"), rs.getString("charge")));
			}
		return screens;
	}
	
	public static int insertScreen(Screen screen) throws ClassNotFoundException, SQLException
	{
		String queryString = "INSERT INTO tbl_screens(t_id, screen_name, seats, charge) VALUES(?,?,?,?)";
		
		Connection conn = DBconnection.getOracleConnection();
		PreparedStatement pstmt = conn.prepareStatement(queryString,
                Statement.RETURN_GENERATED_KEYS);
	
		pstmt.setString(1, screen.getTheatreID());
		pstmt.setString(2, screen.getScreen());
		pstmt.setString(3, screen.getSeats());
		pstmt.setString(4, screen.getCharge());

		
		int rowAffected = pstmt.executeUpdate();

		if (rowAffected == 1) {
			return rowAffected;
		}
		
		conn.close();
		pstmt.close();
		return -1;
	}
	
	public static List<Screen> loadScreenByIDScreen(String id) throws ClassNotFoundException, SQLException
	{
		String queryString = "SELECT tbl_screens.screen_id, tbl_screens.screen_name, tbl_screens.seats, tbl_screens.charge, tbl_show_time.start_time " +
                "FROM tbl_screens " +
                "LEFT JOIN tbl_show_time ON tbl_screens.screen_id = tbl_show_time.screen_id " +
                "WHERE tbl_screens.t_id = '" + id + "' " +
                "ORDER BY tbl_screens.screen_name ASC";

		
		Connection conn = DBconnection.getOracleConnection();
		Statement stmt = conn.createStatement();
	
		ResultSet rs  = stmt.executeQuery(queryString);
		List<Screen> screens = new ArrayList<Screen>();
		
		while (rs.next()) {
			String screenId = rs.getString("screen_id");
            String screenName = rs.getString("screen_name");
            String seats = rs.getString("seats");
            String charge = rs.getString("charge");
            Date startTime = rs.getTime("start_time");

            String formattedTime = startTime != null ? new SimpleDateFormat("h:mm a").format(startTime) : "";

            Screen screen = new Screen(screenId,"",screenName, seats, charge, formattedTime);
            screens.add(screen);
        }
		return screens;
	}
	
}
