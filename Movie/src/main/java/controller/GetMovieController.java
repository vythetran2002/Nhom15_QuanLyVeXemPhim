package controller;

import java.io.BufferedReader;
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

import org.json.JSONObject;

import com.google.gson.Gson;

import DAO.LoginDAo;
import model.Login;

/**
 * Servlet implementation class GetMovieController
 */
@WebServlet("/Login")
public class GetMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMovieController() {
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
			
			StringBuilder sb = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            sb.append(line).append('\n');
		        }
		    } finally {
		        reader.close();
		    }
		    JSONObject jsonObject = new JSONObject(sb.toString());
		    String username = jsonObject.getString("username");
		    String password = jsonObject.getString("password");

			Login login = LoginDAo.getUser(username, password);
			
			String jsonString = gson.toJson(login);
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

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			List<Login> login = new ArrayList<Login>();
			login.add(LoginDAo.getUser(username, password));
			
			String jsonString = gson.toJson(login);
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonString);
			printWriter.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
