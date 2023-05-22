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
import DAO.MovieDAO;
import model.Movie;
import model.aboutMovie;

/**
 * Servlet implementation class insertMovieAdd
 */
@WebServlet("/insertMovieAdd")
public class insertMovieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertMovieAdd() {
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

			String name = request.getParameter("name");
			String cast = request.getParameter("cast");
			String release = request.getParameter("release");
			String description = request.getParameter("description");
			String images = request.getParameter("images");
			String link = request.getParameter("link");
			String idtheatre = request.getParameter("idtheatre");
			
			Movie movie = new Movie();
			movie.setMovie_name(name);
			movie.setCast(cast);
			movie.setRelease_date(release);
			movie.setDesc(description);
			movie.setImage(images);
			movie.setVideo_url(link);
			movie.setThreatre_id(idtheatre);
			
			int result = MovieDAO.insertMovie(movie);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.println(result);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
