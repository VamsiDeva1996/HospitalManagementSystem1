import javax.swing.*;
import java.awt.*;

public class DoctorPanel extends JPanel {
    private DoctorDAO doctorDAO;
    private JTable table;
    private JTextField nameField, specField, contactField;

    public DoctorPanel(DoctorDAO dao) {
        this.doctorDAO = dao;
        setLayout(null);

        JLabel lbl = new JLabel("Doctor Management");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setBounds(300, 10, 300, 30);
        add(lbl);

        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setBounds(50, 70, 100, 25);
        add(nameLbl);
        nameField = new JTextField();
        nameField.setBounds(150, 70, 200, 25);
        add(nameField);

        JLabel specLbl = new JLabel("Specialization:");
        specLbl.setBounds(50, 100, 100, 25);
        add(specLbl);
        specField = new JTextField();
        specField.setBounds(150, 100, 200, 25);
        add(specField);

        JLabel contactLbl = new JLabel("Contact:");
        contactLbl.setBounds(50, 130, 100, 25);
        add(contactLbl);
        contactField = new JTextField();
        contactField.setBounds(150, 130, 200, 25);
        add(contactField);

        JButton addBtn = new JButton("Add Doctor");
        addBtn.setBounds(50, 180, 140, 30);
        add(addBtn);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(210, 180, 150, 30);
        add(deleteBtn);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(380, 180, 100, 30);
        add(refreshBtn);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 230, 750, 300);
        add(scroll);

        refreshTable();

        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String spec = specField.getText();
                String contact = contactField.getText();
                doctorDAO.addDoctor(name, spec, contact);
                JOptionPane.showMessageDialog(this, "Doctor added!");
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                doctorDAO.deleteDoctor(id);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Select a doctor to delete.");
            }
        });

        refreshBtn.addActionListener(e -> refreshTable());
    }

    private void refreshTable() {
        table.setModel(doctorDAO.getAllDoctors());
    }
}
