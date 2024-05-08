package loginapp;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet 
{
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet2024", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try 
		{
			String s1  = request.getParameter("uname");
			String s2 = request.getParameter("pword");
			PreparedStatement ptsm = conn.prepareStatement("select * from uinfo where uname=? and pword=?");
			ptsm.setString(1, s1);
			ptsm.setString(2, s2);
			ResultSet rs = ptsm.executeQuery();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=gray text=green><h1>");
			if(rs.next())
			{
				pw.println("welcome "+s1);
				
			}
			else
			{
				pw.println("Invalid UserName/Password");
			}
			pw.println("</h1></body></html>");
			
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
	}
//	public void destroy() 
//	{
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}
//		// TODO Auto-generated method stub
//	}

}
