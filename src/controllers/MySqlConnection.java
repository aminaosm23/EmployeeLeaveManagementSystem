package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnection {

    //lokacija servera, ime baze, user i password za pristup bazi
    private static final String URL = "jdbc:mysql://localhost:3306/LeaveManagementSystem";
    private static final String USER = "amina";
    private static final String PASSWORD = "amina";

    //metoda za povezivanje na bazu podataka
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                System.out.println("Korisnik postoji.");
            } else {
                System.out.println("Korisnik s username '" + username + "' ne postoji.");
            }
        } catch (SQLException e) {
            System.err.println("Greska: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MySqlConnection dbConnection = new MySqlConnection();

    }
}
