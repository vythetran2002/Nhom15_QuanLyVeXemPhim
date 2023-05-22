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
import DAO.UserDAO;
import model.Registration;
import model.aboutMovie;

/**
 * Servlet implementation class addRegister
 */
@WebServlet("/insertRegistration")
public class insertRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertRegistration() {
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
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");

			String age = request.getParameter("age");

			String gender = request.getParameter("gender");

		
			Registration registration = new Registration();
			registration.setName(name);
			registration.setEmail(email);
			registration.setPhone(phone);
			registration.setAge(age);
			registration.setGender(gender);
			
			int result = UserDAO.addRegister(registration);
			
			PrintWriter printWriter = response.getWriter();
			printWriter.println(result);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
