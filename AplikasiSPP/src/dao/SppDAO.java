package dao;

import config.DatabaseConnection;
import model.Spp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SppDAO {
    
    public boolean insert(Spp spp) throws SQLException {
        String sql = "INSERT INTO pembayaran_spp VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, spp.getNis());
            ps.setString(2, spp.getNama());
            ps.setString(3, spp.getKelas());
            ps.setString(4, spp.getBulan());
            ps.setString(5, spp.getStatus());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Spp> getAllOrSearch(String keyword) throws SQLException {
        List<Spp> list = new ArrayList<>();
        String sql = "SELECT * FROM pembayaran_spp WHERE nis LIKE ? OR nama LIKE ? ORDER BY nis ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Spp(
                        rs.getString("nis"),
                        rs.getString("nama"),
                        rs.getString("kelas"),
                        rs.getString("bulan"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }

    public boolean update(Spp spp) throws SQLException {
        String sql = "UPDATE pembayaran_spp SET nama=?, kelas=?, bulan=?, status=? WHERE nis=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, spp.getNama());
            ps.setString(2, spp.getKelas());
            ps.setString(3, spp.getBulan());
            ps.setString(4, spp.getStatus());
            ps.setString(5, spp.getNis());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(String nis) throws SQLException {
        String sql = "DELETE FROM pembayaran_spp WHERE nis=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nis);
            return ps.executeUpdate() > 0;
        }
    }
    
    public boolean validateLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}