import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AppointmentDAO {

    public void addAppointment(int patientId, int doctorId, String date, String status) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setString(3, date);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM appointments WHERE appointment_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getAllAppointments() {
        String[] columns = {"ID", "Patient ID", "Doctor ID", "Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointments");
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_date"),
                        rs.getString("status")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
