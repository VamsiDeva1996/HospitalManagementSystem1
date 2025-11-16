import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginDAO loginDAO = new LoginDAO();

    public LoginFrame() {
        setTitle("🏥 Hospital Management Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Admin Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(100, 20, 200, 40);
        add(title);

        JLabel userLbl = new JLabel("Username:");
        userLbl.setBounds(50, 90, 100, 25);
        add(userLbl);
        usernameField = new JTextField();
        usernameField.setBounds(150, 90, 180, 25);
        add(usernameField);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(50, 130, 100, 25);
        add(passLbl);
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 180, 25);
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 180, 100, 30);
        add(loginBtn);

        JLabel hint = new JLabel("(Hint: admin / admin123)");
        hint.setFont(new Font("Arial", Font.ITALIC, 10));
        hint.setForeground(Color.GRAY);
        hint.setBounds(130, 220, 150, 20);
        add(hint);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (loginDAO.validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); // close login window
                new HospitalApp().setVisible(true); // open main app
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    public class HospitalApp {
        public void setVisible(boolean b) {
        }
    }
}