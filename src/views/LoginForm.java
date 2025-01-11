package views;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginForm() {
        setTitle("Login Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        pack();

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            authenticateUser(username, password);
        });

    }

    private void authenticateUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
        String dbUser = "root";
        String dbPassword = "amina";

        String query = "SELECT role FROM users WHERE username = ? AND password = ?";
        //otvara vezu s bazom podataka
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username); //stavlja vrijednost korisnickog imena
            stmt.setString(2, password); //vrijednost sifre

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                openWindowBasedOnRole(role);
            } else {
                JOptionPane.showMessageDialog(this, "Netacni podaci", "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greska s bazom podataka", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    //METODA ZA OTVARANJE PROZORA NA OSNOVU ROLE/uloge
    private void openWindowBasedOnRole(String role) {
        switch (role.toLowerCase()) {
            case "superadmin":
                JOptionPane.showMessageDialog(this, "Podaci su tacni, otvara se superAdminWindow");
                new SuperAdminWindow();
                break;
            case "manager":
                JOptionPane.showMessageDialog(this, "Podaci su tacni, otvara se prozor manager");
                new ManagerWindow();
                break;
            case "employee":
                JOptionPane.showMessageDialog(this, "Podaci su tacni, otvara se prozor employee");
                new EmployeeWindow();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Podaci su netacni", "Greska", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //getteri
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    //setteri

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

//konstruktori
    public LoginForm(JTextField usernameField, JPasswordField passwordField, JButton loginButton, JPanel panel, JLabel usernameLabel, JLabel passwordLabel) throws HeadlessException {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;
        this.panel = panel;
        this.usernameLabel = usernameLabel;
        this.passwordLabel = passwordLabel;
    }

    public LoginForm(GraphicsConfiguration gc, JTextField usernameField, JPasswordField passwordField, JButton loginButton, JPanel panel, JLabel usernameLabel, JLabel passwordLabel) {
        super(gc);
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;
        this.panel = panel;
        this.usernameLabel = usernameLabel;
        this.passwordLabel = passwordLabel;
    }

    public LoginForm(String title, JTextField usernameField, JPasswordField passwordField, JButton loginButton, JPanel panel, JLabel usernameLabel, JLabel passwordLabel) throws HeadlessException {
        super(title);
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;
        this.panel = panel;
        this.usernameLabel = usernameLabel;
        this.passwordLabel = passwordLabel;
    }

    public LoginForm(String title, GraphicsConfiguration gc, JTextField usernameField, JPasswordField passwordField, JButton loginButton, JPanel panel, JLabel usernameLabel, JLabel passwordLabel) {
        super(title, gc);
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginButton = loginButton;
        this.panel = panel;
        this.usernameLabel = usernameLabel;
        this.passwordLabel = passwordLabel;
    }
}
