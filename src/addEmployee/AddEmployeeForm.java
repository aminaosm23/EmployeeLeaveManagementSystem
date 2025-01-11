package addEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddEmployeeForm extends JFrame{
    private JPanel panel;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel roleLabel;
    private JTextField roleField;
    private JButton dodajButton;

    public AddEmployeeForm() {
        setTitle("Add Employee Window");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        pack();

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String role = roleField.getText();

                if (id.isEmpty() || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    dodajZaposlenikaUBazu(id, username, password, role);
                }
            }
        });
    }

        //otvara vezu s bazom podataka 
    private void dodajZaposlenikaUBazu(String id, String username, String password, String role) {
        String url = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
        String dbUser = "root";
        String dbPassword = "amina";
        String query = "INSERT INTO users (id, username, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, idField.getText());
            stmt.setString(2, usernameField.getText());
            stmt.setString(3, passwordField.getText());
            stmt.setString(4, roleField.getText());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Dodali ste novog uposlenika", "AddEmployeeForm", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "id mora biti jedinstven. Pokusajte ponovo");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddEmployeeForm");
        frame.setContentPane(new AddEmployeeForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


