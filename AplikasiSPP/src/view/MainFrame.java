package view;

import controller.SppController;
import model.Spp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextField txtNis = new JTextField(15);
    private JTextField txtNama = new JTextField(15);
    private JTextField txtKelas = new JTextField(15);
    private JComboBox<String> cbBulan = new JComboBox<>(new String[]{"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"});
    private JComboBox<String> cbStatus = new JComboBox<>(new String[]{"LUNAS", "BELUM LUNAS"});
    
    private JTextField txtCari = new JTextField(15);
    private JButton btnCari = new JButton("Cari");
    private JTable tabelSpp = new JTable();
    private DefaultTableModel tableModel;

    private JButton btnTambah = new JButton("Tambah/Simpan");
    private JButton btnUbah = new JButton("Ubah");
    private JButton btnHapus = new JButton("Hapus");
    private JButton btnCetak = new JButton("Cetak Laporan");

    private SppController controller = new SppController();
    private boolean isEditMode = false;

    public MainFrame() {
        setTitle("Aplikasi Manajemen Pembayaran SPP");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // --- PANEL KIRI: FORM INPUT ---
        JPanel panelFormUtama = new JPanel(new BorderLayout(5, 5));
        panelFormUtama.setBorder(BorderFactory.createTitledBorder("Form Data SPP"));
        panelFormUtama.setPreferredSize(new Dimension(320, 500));

        // Grid untuk Label dan Textfield agar tingginya proporsional
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 15));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelInput.add(new JLabel("NIS:")); panelInput.add(txtNis);
        panelInput.add(new JLabel("Nama:")); panelInput.add(txtNama);
        panelInput.add(new JLabel("Kelas:")); panelInput.add(txtKelas);
        panelInput.add(new JLabel("Bulan:")); panelInput.add(cbBulan);
        panelInput.add(new JLabel("Status:")); panelInput.add(cbStatus);

        // Panel khusus Tombol Action di bawah form
        JPanel panelBtnAction = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        panelBtnAction.add(btnTambah);
        panelBtnAction.add(btnUbah);
        panelBtnAction.add(btnHapus);

        panelFormUtama.add(panelInput, BorderLayout.NORTH);
        panelFormUtama.add(panelBtnAction, BorderLayout.CENTER);

        // --- PANEL KANAN: TABEL & PENCARIAN ---
        JPanel panelKanan = new JPanel(new BorderLayout(10, 10));
        panelKanan.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        // Baris Atas: Pencarian dan Cetak
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelCari.add(new JLabel("Cari (NIS/Nama):"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnCetak);
        
        // Bagian Tengah: Tabel Data
        tableModel = new DefaultTableModel(new String[]{"NIS", "Nama", "Kelas", "Bulan", "Status"}, 0);
        tabelSpp.setModel(tableModel);
        tabelSpp.setRowHeight(22); // Membuat baris tabel lebih renggang dan rapi
        JScrollPane scrollPane = new JScrollPane(tabelSpp);

        panelKanan.add(panelCari, BorderLayout.NORTH);
        panelKanan.add(scrollPane, BorderLayout.CENTER);

        // Gabungkan ke Frame Utama
        add(panelFormUtama, BorderLayout.WEST);
        add(panelKanan, BorderLayout.CENTER);

        // --- Logika & Event Handling ---
        refreshTable("");

        // Aksi Tambah / Simpan
        btnTambah.addActionListener(e -> {
            String msg = controller.simpan(txtNis.getText(), txtNama.getText(), txtKelas.getText(), 
                    cbBulan.getSelectedItem().toString(), cbStatus.getSelectedItem().toString(), isEditMode);
            JOptionPane.showMessageDialog(this, msg);
            resetForm();
            refreshTable("");
        });

        // Aksi Pilih baris tabel untuk diubah
        btnUbah.addActionListener(e -> {
            int row = tabelSpp.getSelectedRow();
            if (row != -1) {
                txtNis.setText(tableModel.getValueAt(row, 0).toString());
                txtNis.setEditable(false); 
                txtNama.setText(tableModel.getValueAt(row, 1).toString());
                txtKelas.setText(tableModel.getValueAt(row, 2).toString());
                cbBulan.setSelectedItem(tableModel.getValueAt(row, 3).toString());
                cbStatus.setSelectedItem(tableModel.getValueAt(row, 4).toString());
                isEditMode = true;
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris di tabel terlebih dahulu!");
            }
        });

        // Aksi Hapus
        btnHapus.addActionListener(e -> {
            int row = tabelSpp.getSelectedRow();
            if (row != -1) {
                String nis = tableModel.getValueAt(row, 0).toString();
                int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin hapus data NIS " + nis + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    String msg = controller.hapus(nis);
                    JOptionPane.showMessageDialog(this, msg);
                    resetForm();
                    refreshTable("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus!");
            }
        });

        // Aksi Pencarian
        btnCari.addActionListener(e -> refreshTable(txtCari.getText()));

        // Aksi Cetak Laporan Sederhana
        btnCetak.addActionListener(e -> {
            StringBuilder report = new StringBuilder("=== LAPORAN DATA PEMBAYARAN SPP ===\n\n");
            report.append(String.format("%-10s %-20s %-10s %-12s %-10s\n", "NIS", "Nama", "Kelas", "Bulan", "Status"));
            report.append("----------------------------------------------------------------------\n");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                report.append(String.format("%-10s %-20s %-10s %-12s %-10s\n",
                        tableModel.getValueAt(i, 0), tableModel.getValueAt(i, 1),
                        tableModel.getValueAt(i, 2), tableModel.getValueAt(i, 3),
                        tableModel.getValueAt(i, 4)));
            }
            JTextArea textArea = new JTextArea(report.toString());
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Agar teks laporan rapi sejajar
            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setPreferredSize(new Dimension(550, 300));
            JOptionPane.showMessageDialog(this, scroll, "Cetak Laporan Seluruh Data", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void refreshTable(String keyword) {
        try {
            tableModel.setRowCount(0);
            List<Spp> list = controller.loadData(keyword);
            for (Spp s : list) {
                tableModel.addRow(new Object[]{s.getNis(), s.getNama(), s.getKelas(), s.getBulan(), s.getStatus()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetForm() {
        txtNis.setText("");
        txtNis.setEditable(true);
        txtNama.setText("");
        txtKelas.setText("");
        cbBulan.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        isEditMode = false;
    }
}