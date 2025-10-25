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

@WebServlet("/RegisterBAD")
public class RegisterBAD extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    // Replace these with your cloud database credentials
//    private static final String DB_URL = "jdbc:mysql://<DB_HOST>:<DB_PORT>/<DB_NAME>";
//    private static final String DB_USER = "<DB_USER>";
//    private static final String DB_PASSWORD = "<DB_PASSWORD>";
    private static final String DB_URL = "jdbc:mysql://<DB_HOST>:<DB_PORT>/<DB_NAME>";
    private static final String DB_USER = "<DB_USER>";
    private static final String DB_PASSWORD = "<DB_PASSWORD>";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phnumber = request.getParameter("phone");

        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "INSERT INTO register22 (username, email, passwords, phnumber) VALUES (?, ?, ?, ?)";

            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setString(4, phnumber);

                int rows = ps.executeUpdate();
                response.getWriter().println("<h1>" + rows + " record(s) successfully inserted</h1>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<h3>Please submit the form using POST method.</h3>");
    }
}
