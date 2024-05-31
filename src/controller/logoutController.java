package controller;
import view.Login;
import view.MainMenu;
import javax.swing.JOptionPane;

public class logoutController {
    public static void logout(MainMenu mainMenu) {
        // Tampilkan pesan konfirmasi
        int confirm = JOptionPane.showConfirmDialog(mainMenu, "Apakah Anda yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Tutup jendela MainMenu
            mainMenu.dispose();
            
            // Tampilkan jendela Login
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Login().setVisible(true);
                }
            });
        }
    }
}
