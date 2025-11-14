import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {
    private PatientDAO patientDAO;
    private JTable table;
    private JTextField nameField, ageField, contactField, addressField;
    private JComboBox<String> genderBox;

    public PatientPanel(PatientDAO dao) {
        this.patientDAO = dao;
        setLayout(null);

        JLabel lbl = new JLabel("Patient Management");
        lbl.setFont(new Font("Arial", Font.BOLD, 20));
        lbl.setBounds(300, 10, 300, 30);
        add(lbl);

        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setBounds(50, 70, 100, 25);
        add(nameLbl);
        nameField = new JTextField();
        nameField.setBounds(150, 70, 200, 25);
        add(nameField);

        JLabel ageLbl = new JLabel("Age:");
        ageLbl.setBounds(50, 100, 100, 25);
        add(ageLbl);
        ageField = new JTextField();
        ageField.setBounds(150, 100, 200, 25);
        add(ageField);

        JLabel genderLbl = new JLabel("Gender:");
        genderLbl.setBounds(50, 130, 100, 25);
        add(genderLbl);
        genderBox = new JComboBox<>(new String[]{"M", "F", "Other"});
        genderBox.setBounds(150, 130, 200, 25);
        add(genderBox);

        JLabel contactLbl = new JLabel("Contact:");
        contactLbl.setBounds(50, 160, 100, 25);
        add(contactLbl);
        contactField = new JTextField();
        contactField.setBounds(150, 160, 200, 25);
        add(contactField);

        JLabel addressLbl = new JLabel("Address:");
        addressLbl.setBounds(50, 190, 100, 25);
        add(addressLbl);
        addressField = new JTextField();
        addressField.setBounds(150, 190, 200, 25);
        add(addressField);

        JButton addBtn = new JButton("Add Patient");
        addBtn.setBounds(50, 230, 140, 30);
        add(addBtn);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(210, 230, 140, 30);
        add(deleteBtn);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(370, 230, 100, 30);
        add(refreshBtn);

        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 280, 750, 300);
        add(scroll);

        refreshTable();

        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderBox.getSelectedItem().toString();
                String contact = contactField.getText();
                String address = addressField.getText();
                patientDAO.addPatient(name, age, gender);
                JOptionPane.showMessageDialog(this, "Patient added!");
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) table.getValueAt(row, 0);
                patientDAO.deletePatient(id);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Select a patient to delete.");
            }
        });

        refreshBtn.addActionListener(e -> refreshTable());
    }

    private void refreshTable() {
        table.setModel(patientDAO.getAllPatient());
    }
}
