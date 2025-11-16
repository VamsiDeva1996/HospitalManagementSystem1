import javax.swing.*;
import java.awt.*;

public class HospitalApp extends JFrame {
    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public HospitalApp() {
        setTitle("🏥 Hospital Management System");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Patients", new PatientPanel(patientDAO));
        tabs.add("Doctors", new DoctorPanel(doctorDAO));
        tabs.add("Appointments", new AppointmentPanel(appointmentDAO));
        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalApp().setVisible(true));
    }
}