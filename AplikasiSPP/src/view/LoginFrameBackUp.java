package view;

import controller.SppController;
import javax.swing.*;
import java.awt.*;

public class LoginFrameBackUp extends JFrame {
    private JTextField txtUsername = new JTextField(15);
    private JPasswordField txtPassword = new JPasswordField(15);
    private JButton btnLogin = new JButton("Login");
    private SppController controller = new SppController();

    public LoginFrameBackUp() {
        setTitle("Login Aplikasi SPP");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel(" Username:")); add(txtUsername);
        add(new JLabel(" Password:")); add(txtPassword);
        add(new JLabel("")); add(btnLogin);

        btnLogin.addActionListener(e -> {
            try {
                String user = txtUsername.getText();
                String pass = new String(txtPassword.getPassword());
                if (controller.login(user, pass)) {
                    new MainFrame().setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Username/Password Salah!", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrameBackUp().setVisible(true));
    }
}