package controller;

import dao.SppDAO;
import model.Spp;
import java.sql.SQLException;
import java.util.List;

public class SppController {
    private final SppDAO dao = new SppDAO();

    // Fungsi untuk menghandle login
    public boolean login(String username, String password) throws SQLException {
        return dao.validateLogin(username, password);
    }

    // Fungsi untuk mengambil data (termasuk pencarian)
    public List<Spp> loadData(String keyword) throws SQLException {
        return dao.getAllOrSearch(keyword); // Sudah disesuaikan dengan SppDAO
    }

    // Fungsi untuk Simpan atau Ubah Data dengan Validasi
    public String simpan(String nis, String nama, String kelas, String bulan, String status, boolean isEdit) {
        // Validasi Wajib diisi (tidak boleh kosong)
        if (nis.trim().isEmpty() || nama.trim().isEmpty() || kelas.trim().isEmpty()) {
            return "Semua kolom input wajib diisi!";
        }
        
        Spp spp = new Spp(nis, nama, kelas, bulan, status);
        try {
            if (isEdit) {
                return dao.update(spp) ? "Data berhasil diubah!" : "Gagal mengubah data.";
            } else {
                return dao.insert(spp) ? "Data berhasil disimpan!" : "Gagal menyimpan data.";
            }
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    // Fungsi untuk Hapus Data
    public String hapus(String nis) {
        if (nis.trim().isEmpty()) return "Pilih data yang ingin dihapus!";
        try {
            return dao.delete(nis) ? "Data berhasil dihapus!" : "Gagal menghapus data.";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }
}