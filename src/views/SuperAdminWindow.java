package views;

import addEmployee.AddEmployeeForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SuperAdminWindow extends JFrame {
    private JPanel panel;
    private JList<String> usersList;
    private JButton obrisiButton;
    private JButton dodajButton;
    public JScrollPane ScrollPane;
    private DefaultListModel<String> listModel;

    public SuperAdminWindow() {
        setTitle("SuperAdmin Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        pack();

        listModel = new DefaultListModel<>();
        usersList.setModel(listModel);

        loadusers(); //ucitava korisnike iz baze

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEmployeeForm novaForma = new AddEmployeeForm();
                novaForma.setVisible(true);
            }
        });

        obrisiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmployee = usersList.getSelectedValue();
                if (selectedEmployee != null) {
                    String id = selectedEmployee.split(" ")[0];
                    deleteZaposlenik(id);
                } else {
                    JOptionPane.showMessageDialog(null, "Nema selektovanog uposlenika", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //metoda za ucitavanje korisnika iz baze i prikazuje ih u jlist
    private void loadusers() {
        String url = "jdbc:mysql://localhost:3306/leavemanagementsystem";
        String dbUser = "root";
        String dbPassword = "amina";
        String query = "SELECT id, username, password, role FROM users";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                System.out.println("ID: " + id + ", Username: " + username + ", Role: " + role);
                String element = id + " - " + username + " - " + role;
                listModel.addElement(element);
            }
            usersList.repaint(); //refresh da vidim nove podatke


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greska sa bazom podataka: " + e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteZaposlenik(String id) {
        String url = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
        String dbUser = "root";
        String dbPassword = "amina";
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Uposlenik je obrisan", "SuperAdminwindow", JOptionPane.INFORMATION_MESSAGE);
                loadusers();
                dispose();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greska sa bazom podataka: " + e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SuperAdminWindow");
        frame.setContentPane(new SuperAdminWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JList<String> getUsersList() {
        return usersList;
    }

    public void setUsersList(JList<String> usersList) {
        this.usersList = usersList;
    }

    public JButton getObrisiButton() {
        return obrisiButton;
    }

    public void setObrisiButton(JButton obrisiButton) {
        this.obrisiButton = obrisiButton;
    }

    public JButton getDodajButton() {
        return dodajButton;
    }

    public void setDodajButton(JButton dodajButton) {
        this.dodajButton = dodajButton;
    }

    public JScrollPane getScrollPane() {
        return ScrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        ScrollPane = scrollPane;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }

    public SuperAdminWindow(JPanel panel, JList<String> usersList, JButton obrisiButton, JButton dodajButton, JScrollPane scrollPane, DefaultListModel<String> listModel) throws HeadlessException {
        this.panel = panel;
        this.usersList = usersList;
        this.obrisiButton = obrisiButton;
        this.dodajButton = dodajButton;
        ScrollPane = scrollPane;
        this.listModel = listModel;
    }

    public SuperAdminWindow(GraphicsConfiguration gc, JPanel panel, JList<String> usersList, JButton obrisiButton, JButton dodajButton, JScrollPane scrollPane, DefaultListModel<String> listModel) {
        super(gc);
        this.panel = panel;
        this.usersList = usersList;
        this.obrisiButton = obrisiButton;
        this.dodajButton = dodajButton;
        ScrollPane = scrollPane;
        this.listModel = listModel;
    }

    public SuperAdminWindow(String title, JPanel panel, JList<String> usersList, JButton obrisiButton, JButton dodajButton, JScrollPane scrollPane, DefaultListModel<String> listModel) throws HeadlessException {
        super(title);
        this.panel = panel;
        this.usersList = usersList;
        this.obrisiButton = obrisiButton;
        this.dodajButton = dodajButton;
        ScrollPane = scrollPane;
        this.listModel = listModel;
    }

    public SuperAdminWindow(String title, GraphicsConfiguration gc, JPanel panel, JList<String> usersList, JButton obrisiButton, JButton dodajButton, JScrollPane scrollPane, DefaultListModel<String> listModel) {
        super(title, gc);
        this.panel = panel;
        this.usersList = usersList;
        this.obrisiButton = obrisiButton;
        this.dodajButton = dodajButton;
        ScrollPane = scrollPane;
        this.listModel = listModel;
    }
}
