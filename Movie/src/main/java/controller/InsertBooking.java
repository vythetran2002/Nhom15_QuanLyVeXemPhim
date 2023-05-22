package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.BookingDAO;
import DAO.LoginDAo;
import model.Login;
import model.aboutMovie;

/**
 * Servlet implementation class InsertBooking
 */
@WebServlet("/insertBooking")
public class InsertBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {

			String t_ids = request.getParameter("t_ids");
			String user_ids = request.getParameter("user_ids");
			String show_ids = request.getParameter("show_ids");

			String screen_ids = request.getParameter("screen_ids");

			String seatss = request.getParameter("seatss");

			String amounts = request.getParameter("amounts");

			String date_ticks = request.getParameter("date_ticks");
			String dates = request.getParameter("dates");
			
			aboutMovie aboutMovie = new aboutMovie();
			aboutMovie.setId(t_ids);
			aboutMovie.setS_id(show_ids);
			aboutMovie.setIdScreen(screen_ids);
			aboutMovie.setSeats(seatss);
			aboutMovie.setCharge(amounts);
			aboutMovie.setDate(date_ticks);
			aboutMovie.setTime_booking(dates);
			
			int result = BookingDAO.insertBooking(aboutMovie, user_ids);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.println(result);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
