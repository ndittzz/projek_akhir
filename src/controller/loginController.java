package controller;

import DAOdata.userDAO;
import view.Login;
import view.MainMenu;
import javax.swing.JOptionPane;

public class loginController {
    private Login view;
    private userDAO userDAO;

    public loginController(Login view) {
        this.view = view;
        this.userDAO = new userDAO();
    }

    public void login() {
        String username = view.getUsername();
        String password = new String(view.getPassword());

        if (userDAO.validate(username, password)) {
            JOptionPane.showMessageDialog(view, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();  // Tutup jendela login
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);  // Tampilkan menu utama
        } else {
            JOptionPane.showMessageDialog(view, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
