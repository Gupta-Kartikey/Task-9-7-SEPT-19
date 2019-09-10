package library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Book_Entry")
public class Book_Entry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	public void init(ServletConfig config) throws ServletException {	
		// step-1 (Driver-Loading)
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
					try {
						PreparedStatement ps= con.prepareStatement("CREATE TABLE IF NOT EXISTS book(bookName VARCHAR(20) PRIMARY KEY, authorName VARCHAR(20),price VARCHAR(20), genre VARCHAR(20),format VARCHAR(10))");
						ps.execute();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Book Saved");
		String bookName= request.getParameter("bookName");
		String authorName= request.getParameter("authorName");
		String price= request.getParameter("price");
		String genre= request.getParameter("genre");
		String format=request.getParameter("format");
		try {
			PreparedStatement ps= con.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?)");
			ps.setString(1, bookName);
			ps.setString(2, authorName);
			ps.setString(3, price);
			ps.setString(4, genre);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().append("<br><br><a href=\"SearchBook.jsp\">SearchBook</a>");
		response.getWriter().append("<br><br><a href=\"Book_Entry\">Insert book again</a>");
		
	}
public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
