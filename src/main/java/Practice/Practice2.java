package Practice;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Practice1
 */
@WebServlet("/Practice2")
public class Practice2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Practice2() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.printf("<form action=\"/Lab/Practice2\" method=\"post\">");
		out.printf("<span>Account : </span><input type=\"text\" name=\"account\" style=\"margin-bottom:30px\"><br/>");
		out.printf("<span>Password : </span><input type=\"password\" name=\"password\" style=\"margin-bottom:30px\"><br/>");
		out.printf("<input type=\"submit\" value=\"login\">");
		out.printf("</form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String acc = request.getParameter("account");
	    String password = request.getParameter("password");
		
		String url="jdbc:sqlserver://localhost:1433;DatabaseName=Practice";
		String sqluser="user";
		String sqlpassword="123456";
		String sql="select * from account";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn= DriverManager.getConnection(url,sqluser,sqlpassword);
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			Boolean verify = false; 

			while(rs.next()) {
				if (acc.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
				    out.printf("<h1>%s</h1><h1>歡迎回來</h1>",acc);
				    verify = true;
				    break;
				}
			}
			if (verify != true) {
				out.print("<h1>歡迎光臨！</h1>");
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
