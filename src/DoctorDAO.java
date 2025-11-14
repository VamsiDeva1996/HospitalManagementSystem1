import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DoctorDAO {

    public void addDoctor(String name, String specialization, String contact) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO doctors (name, specialization, contact) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, specialization);
            ps.setString(3, contact);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM doctors WHERE doctor_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getAllDoctors() {
        String[] columns = {"ID", "Name", "Specialization", "Contact"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctors");
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("contact")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}