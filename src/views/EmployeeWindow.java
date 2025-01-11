package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeWindow extends JFrame {

    private JTextField idField;
    private JTextField imeField;
    private JTextField razlogField;
    private JTextField daniField;
    private JButton posaljiButton;
    private JLabel idLabel;
    private JLabel imeLabel;
    private JLabel razlogLabel;
    private JLabel daniLabel;
    private JPanel panel;

    public EmployeeWindow() {
        setTitle("Employee Window");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        pack();

        posaljiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String ime = imeField.getText();
                String razlog = razlogField.getText();
                String dani = daniField.getText();

                if (id.isEmpty() || ime.isEmpty() || razlog.isEmpty() || dani.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Sva polja moraju biti popunjena!", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int brojDana = Integer.parseInt(dani);
                        dodajZahtjevUBazu(id, ime, razlog, brojDana);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Broj dana mora biti validan broj!", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    private void dodajZahtjevUBazu(String id, String ime, String razlog, int dani) {
        String url = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
        String dbUser = "root";
        String dbPassword = "amina";

        String query = "INSERT INTO leaverequests (id, ime, razlog, dani) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.setString(2, ime);
            stmt.setString(3, razlog);
            stmt.setInt(4, dani);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Zahtjev je uspjesno poslan!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greska s bazom podataka: " + e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        imeField.setText("");
        razlogField.setText("");
        daniField.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmployeeWindow");
        frame.setContentPane(new EmployeeWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //getteri

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getImeField() {
        return imeField;
    }

    public JTextField getRazlogField() {
        return razlogField;
    }

    public JTextField getDaniField() {
        return daniField;
    }

    public JButton getPosaljiButton() {
        return posaljiButton;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public JLabel getImeLabel() {
        return imeLabel;
    }

    public JLabel getRazlogLabel() {
        return razlogLabel;
    }

    public JLabel getDaniLabel() {
        return daniLabel;
    }

    public JPanel getPanel() {
        return panel;
    }

    //setteri

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    public void setImeField(JTextField imeField) {
        this.imeField = imeField;
    }

    public void setRazlogField(JTextField razlogField) {
        this.razlogField = razlogField;
    }

    public void setDaniField(JTextField daniField) {
        this.daniField = daniField;
    }

    public void setPosaljiButton(JButton posaljiButton) {
        this.posaljiButton = posaljiButton;
    }

    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }

    public void setImeLabel(JLabel imeLabel) {
        this.imeLabel = imeLabel;
    }

    public void setRazlogLabel(JLabel razlogLabel) {
        this.razlogLabel = razlogLabel;
    }

    public void setDaniLabel(JLabel daniLabel) {
        this.daniLabel = daniLabel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    //konstruktori

    public EmployeeWindow(JTextField idField, JTextField imeField, JTextField razlogField, JTextField daniField, JButton posaljiButton, JLabel idLabel, JLabel imeLabel, JLabel razlogLabel, JLabel daniLabel, JPanel panel) throws HeadlessException {
        this.idField = idField;
        this.imeField = imeField;
        this.razlogField = razlogField;
        this.daniField = daniField;
        this.posaljiButton = posaljiButton;
        this.idLabel = idLabel;
        this.imeLabel = imeLabel;
        this.razlogLabel = razlogLabel;
        this.daniLabel = daniLabel;
        this.panel = panel;
    }

    public EmployeeWindow(GraphicsConfiguration gc, JTextField idField, JTextField imeField, JTextField razlogField, JTextField daniField, JButton posaljiButton, JLabel idLabel, JLabel imeLabel, JLabel razlogLabel, JLabel daniLabel, JPanel panel) {
        super(gc);
        this.idField = idField;
        this.imeField = imeField;
        this.razlogField = razlogField;
        this.daniField = daniField;
        this.posaljiButton = posaljiButton;
        this.idLabel = idLabel;
        this.imeLabel = imeLabel;
        this.razlogLabel = razlogLabel;
        this.daniLabel = daniLabel;
        this.panel = panel;
    }

    public EmployeeWindow(String title, JTextField idField, JTextField imeField, JTextField razlogField, JTextField daniField, JButton posaljiButton, JLabel idLabel, JLabel imeLabel, JLabel razlogLabel, JLabel daniLabel, JPanel panel) throws HeadlessException {
        super(title);
        this.idField = idField;
        this.imeField = imeField;
        this.razlogField = razlogField;
        this.daniField = daniField;
        this.posaljiButton = posaljiButton;
        this.idLabel = idLabel;
        this.imeLabel = imeLabel;
        this.razlogLabel = razlogLabel;
        this.daniLabel = daniLabel;
        this.panel = panel;
    }

    public EmployeeWindow(String title, GraphicsConfiguration gc, JTextField idField, JTextField imeField, JTextField razlogField, JTextField daniField, JButton posaljiButton, JLabel idLabel, JLabel imeLabel, JLabel razlogLabel, JLabel daniLabel, JPanel panel) {
        super(title, gc);
        this.idField = idField;
        this.imeField = imeField;
        this.razlogField = razlogField;
        this.daniField = daniField;
        this.posaljiButton = posaljiButton;
        this.idLabel = idLabel;
        this.imeLabel = imeLabel;
        this.razlogLabel = razlogLabel;
        this.daniLabel = daniLabel;
        this.panel = panel;
    }
}
