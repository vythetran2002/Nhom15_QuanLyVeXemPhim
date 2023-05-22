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

import DAO.LoginDAo;
import DAO.MovieDAO;
import model.Login;
import model.Movie;

/**
 * Servlet implementation class LoadMovie
 */
@WebServlet("/LoadMovie")
public class LoadMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			Gson gson = new Gson();

			List<Movie> movies = MovieDAO.getAllMovie();
			
			String jsonString = gson.toJson(movies);
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonString);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			Gson gson = new Gson();

			List<Movie> movies = MovieDAO.getAllMovie();
			
			String jsonString = gson.toJson(movies);
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonString);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
