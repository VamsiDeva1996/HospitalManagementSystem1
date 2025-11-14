import javax.swing.*;
import java.awt.*;

public class AppointmentPanel extends JPanel {
    private AppointmentDAO appointmentDAO;
    private JTable table;
    private JTextField patientIdField, doctorIdField, dateField, statusField;

    public AppointmentPanel(AppointmentDAO dao) {
        this.appointmentDAO = dao;
        setLayout(null);

        JLabel lbl = new JLabel("Appointment Management");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setBounds(280, 10, 400, 30);
        add(lbl);

        JLabel pidLbl = new JLabel("Patient ID:");
        pidLbl.setBounds(50, 70, 100, 25);
        add(pidLbl);
        patientIdField = new JTextField();
        patientIdField.setBounds(150, 70, 200, 25);
        add(patientIdField);

        JLabel didLbl = new JLabel("Doctor ID:");
        didLbl.setBounds(50, 100, 100, 25);
        add(didLbl);
        doctorIdField = new JTextField();
        doctorIdField.setBounds(150, 100, 200, 25);
        add(doctorIdField);

        JLabel dateLbl = new JLabel("Date (YYYY-MM-DD):");
        dateLbl.setBounds(50, 130, 150, 25);
        add(dateLbl);
        dateField = new JTextField();
        dateField.setBounds(200, 130, 150, 25);
        add(dateField);

        JLabel statusLbl = new JLabel("Status:");
        statusLbl.setBounds(50, 160, 100, 25);
        add(statusLbl);
        statusField = new JTextField();
        statusField.setBounds(150, 160, 200, 25);
        add(statusField);

        JButton addBtn = new JButton("Add Appointment");
        addBtn.setBounds(50, 210, 160, 30);
        add(addBtn);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(220, 210, 150, 30);
        add(deleteBtn);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(390, 210, 100, 30);
        add(refreshBtn);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 260, 750, 300);
        add(scroll);

        refreshTable();

        addBtn.addActionListener(e -> {
            try {
                int patientId = Integer.parseInt(patientIdField.getText());
                int doctorId = Integer.parseInt(doctorIdField.getText());
                String date = dateField.getText();
                String status = statusField.getText();
                appointmentDAO.addAppointment(patientId, doctorId, date, status);
                JOptionPane.showMessageDialog(this, "Appointment added!");
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                appointmentDAO.deleteAppointment(id);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Select an appointment to delete.");
            }
        });

        refreshBtn.addActionListener(e -> refreshTable());
    }

    private void refreshTable() {
        table.setModel(appointmentDAO.getAllAppointments());
    }
}