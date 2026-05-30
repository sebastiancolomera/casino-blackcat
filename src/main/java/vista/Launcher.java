package vista;

import controlador.SessionController;

public class Launcher {
    public static void main(String[] args) {
        SessionController session = new SessionController();
        VentanaLogin login = new VentanaLogin(session);
        login.mostrarVentana();
    }
}