package registrationapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet 
{
	 Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet2024", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			String s1= request.getParameter("fname");
			String s2 = request.getParameter("lname");
			String s3  = request.getParameter("uname");
			String s4 = request.getParameter("pword");
			PreparedStatement ptsm = conn.prepareStatement("insert into uinfo values(?,?,?,?)");
			ptsm.setString(1,s1);
			ptsm.setString(2, s2);
			ptsm.setString(3,s3);
			ptsm.setString(4,s4);
			ptsm.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=green text=red><center>");
			pw.println("<h1> You Have Registered Successfully</h1>");
			pw.println("<a href=login.html>Login</a>");
			pw.println("</center></body></html>");
			
		}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void destroy()
	{
		try {
			conn.close();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
