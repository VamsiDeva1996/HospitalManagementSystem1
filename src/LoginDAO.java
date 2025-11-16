import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean validateLogin(String username, String password) {
        boolean valid = false;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM admin_users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                valid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }
}
