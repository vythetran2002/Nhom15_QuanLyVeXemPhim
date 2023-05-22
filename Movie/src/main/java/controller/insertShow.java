package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookingDAO;
import DAO.ShowDAO;
import model.Show;
import model.aboutMovie;

/**
 * Servlet implementation class insertShow
 */
@WebServlet("/insertShow")
public class insertShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertShow() {
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

			String st_id = request.getParameter("st_id");
			String theatre_id = request.getParameter("theatre_id");
			String movie_id = request.getParameter("movie_id");
			String date = request.getParameter("date");
			
			Show show = new Show();
			show.setShowTime(st_id);
			show.setTheatre(theatre_id);
			show.setMovie(movie_id);
			show.setDate(date);
			
			int result = ShowDAO.insertShow(show);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.println(result);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
