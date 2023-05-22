package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShowDAO;
import DAO.TheatreDAO;
import model.Show;
import model.Theatre;

/**
 * Servlet implementation class insertTheatre
 */
@WebServlet("/insertTheatre")
public class insertTheatre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertTheatre() {
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
			String address = request.getParameter("address");
			String place = request.getParameter("place");
			String state = request.getParameter("state");
			String pin = request.getParameter("pin");

			Theatre theatre = new Theatre("", name, address, place, state, pin);
			
			int result = TheatreDAO.insertTheathe(theatre);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.println(result);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
