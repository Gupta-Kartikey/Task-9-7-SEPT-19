package library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	private PrintWriter pw;
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Driver Loaded");
		// step-2 (Connection Establishment)
		try {
			
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected Successfully");
	}
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pw = response.getWriter();
		String search= request.getParameter("search");
		try {
			PreparedStatement ps= con.prepareStatement("select bookname from book where genre="+search+"");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				pw.print(rs.getString(1));
				pw.print("<a href=\" Download\">Download it</a>");
			}
			

		} catch (SQLException e) {
		  e.printStackTrace();
		}
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
