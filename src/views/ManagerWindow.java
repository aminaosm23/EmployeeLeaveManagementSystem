package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManagerWindow extends JFrame {
    private JPanel panel;
    private JLabel naslov;
    private JButton odobriButton;
    private JButton odbijButton;
    private JList<String> zahtjeviList;

    public ManagerWindow() {
        setTitle("Manager Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        pack();

        loadZahtjevi();

        odobriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = zahtjeviList.getSelectedValue();
                if (selectedRequest != null) {
                    String id = selectedRequest.split(" ")[0];
                    updateStatus(id, "Odobren");
                } else {
                    JOptionPane.showMessageDialog(null, "Nema selektovanog zahtjeva", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        odbijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = zahtjeviList.getSelectedValue();
                if (selectedRequest != null) {
                    String id = selectedRequest.split(" ")[0];
                    updateStatus(id, "Odbijen");
                } else {
                    JOptionPane.showMessageDialog(null, "Nema selektovanog zahtjeva", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadZahtjevi() {
        String url = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
        String dbUser = "root";
        String dbPassword = "amina";
        String query = "SELECT id, ime, razlog, dani, status FROM leaverequests";

        DefaultListModel<String> listModel = new DefaultListModel<>();
        zahtjeviList.setModel(listModel);

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String ime = rs.getString("ime");
                String razlog = rs.getString("razlog");
                int dani = rs.getInt("dani");
                String status = rs.getString("status");

                listModel.addElement("id: " + id + " --- " + "ime: " + ime + " --- " + "razlog: " + razlog + " --- " + "dani: " + dani + " --- " + "status zahtjeva: " + status);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greska sa bazom podataka", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStatus(String id, String status) {
        String url = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
        String dbUser = "root";
        String dbPassword = "amina";
        String query = "UPDATE leaverequests SET status = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setString(2, id);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Zahtjev je " + status, "ManagerWindow", JOptionPane.INFORMATION_MESSAGE);
                loadZahtjevi();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greska sa bazom podataka", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManagerWindow");
        frame.setContentPane(new ManagerWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //getteri i setteri
    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getNaslov() {
        return naslov;
    }

    public void setNaslov(JLabel naslov) {
        this.naslov = naslov;
    }

    public JButton getOdobriButton() {
        return odobriButton;
    }

    public void setOdobriButton(JButton odobriButton) {
        this.odobriButton = odobriButton;
    }

    public JButton getOdbijButton() {
        return odbijButton;
    }

    public void setOdbijButton(JButton odbijButton) {
        this.odbijButton = odbijButton;
    }

    public JList<String> getZahtjeviList() {
        return zahtjeviList;
    }

    public void setZahtjeviList(JList<String> zahtjeviList) {
        this.zahtjeviList = zahtjeviList;
    }
//konsturktor
    public ManagerWindow(JPanel panel, JLabel naslov, JButton odobriButton, JButton odbijButton, JList<String> zahtjeviList) throws HeadlessException {
        this.panel = panel;
        this.naslov = naslov;
        this.odobriButton = odobriButton;
        this.odbijButton = odbijButton;
        this.zahtjeviList = zahtjeviList;
    }

    public ManagerWindow(GraphicsConfiguration gc, JPanel panel, JLabel naslov, JButton odobriButton, JButton odbijButton, JList<String> zahtjeviList) {
        super(gc);
        this.panel = panel;
        this.naslov = naslov;
        this.odobriButton = odobriButton;
        this.odbijButton = odbijButton;
        this.zahtjeviList = zahtjeviList;
    }

    public ManagerWindow(String title, JPanel panel, JLabel naslov, JButton odobriButton, JButton odbijButton, JList<String> zahtjeviList) throws HeadlessException {
        super(title);
        this.panel = panel;
        this.naslov = naslov;
        this.odobriButton = odobriButton;
        this.odbijButton = odbijButton;
        this.zahtjeviList = zahtjeviList;
    }

    public ManagerWindow(String title, GraphicsConfiguration gc, JPanel panel, JLabel naslov, JButton odobriButton, JButton odbijButton, JList<String> zahtjeviList) {
        super(title, gc);
        this.panel = panel;
        this.naslov = naslov;
        this.odobriButton = odobriButton;
        this.odbijButton = odbijButton;
        this.zahtjeviList = zahtjeviList;
    }
}
