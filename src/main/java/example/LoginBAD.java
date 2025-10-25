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

@WebServlet("/LoginBAD")
public class LoginBAD extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Replace these with your cloud database credentials
//    private static final String DB_URL = "jdbc:mysql://<DB_HOST>:<DB_PORT>/<DB_NAME>";
//    private static final String DB_USER = "<DB_USER>";
//    private static final String DB_PASSWORD = "<DB_PASSWORD>";
    private static final String DB_URL = "jdbc:mysql://<DB_HOST>:<DB_PORT>/<DB_NAME>";
    private static final String DB_USER = "<DB_USER>";
    private static final String DB_PASSWORD = "<DB_PASSWORD>";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = con.prepareStatement(
                         "SELECT * FROM register22 WHERE username=? AND passwords=?")) {

                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        response.getWriter().println("<h1>Login Successful! Welcome " + username + "</h1>");
                    } else {
                        response.getWriter().println("<h1>Invalid Username or Password. Try Again.</h1>");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
