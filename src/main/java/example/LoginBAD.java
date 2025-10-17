package example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginBAD
 */
@WebServlet("/LoginBAD")
public class LoginBAD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBAD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username");
        String passwords = request.getParameter("password");
        

        try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");

             // Database connection details
             String url = "jdbc:mysql://localhost:3306/spirit";
             String user = "root";
             String password1 = "spirit@20";

             Connection con = DriverManager.getConnection(url, user, password1);

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM  register22 WHERE username=? AND passwords=?");
            ps.setString(1, username);
            ps.setString(2, passwords);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                response.getWriter().println("<h1>Login Successful! Welcome " + username + "</h1>");
            } else {
                response.getWriter().println("<h1> Invalid Username or Password. Try Again. </h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
