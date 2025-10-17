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

    public RegisterBAD() {
        super();
    }

    // Optional: doGet can just show a message or redirect
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<h3>Please submit the form using POST method.</h3>");
    }

    // All insertion logic is now in doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String passwords = request.getParameter("password");
        String phnumber = request.getParameter("phone"); // Store phone as String

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details
            String url = "jdbc:mysql://localhost:3306/spirit";
            String user = "root";
            String password = "spirit@20";

            Connection con = DriverManager.getConnection(url, user, password);

            // SQL Insert
            String sql = "INSERT INTO register22 (username, email, passwords, phnumber) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, passwords);
            ps.setString(4, phnumber);

            int rows = ps.executeUpdate();

            // Response to user
            response.setContentType("text/html");
            response.getWriter().println("<h1>" + rows + " record(s) successfully inserted</h1>");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
