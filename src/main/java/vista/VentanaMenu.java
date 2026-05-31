package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Usuario;
import controlador.SessionController;
import controlador.RuletaController;
import controlador.ResultadoController;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Casino Black Cat - Menú Principal");
    private final JButton btnJugar = new JButton("Jugar Ruleta");
    private final JButton btnHistorial = new JButton("Ver Historial");
    private final JButton btnEstadisticas = new JButton("Estadísticas");
    private final JButton btnPerfil = new JButton("Perfil");
    private final JButton btnLogout = new JButton("Logout");
    private final SessionController session;
    private final RuletaController ruletaController;

    public VentanaMenu(SessionController session) {
        this.session = session;
        this.ruletaController = new RuletaController(10000,session);
        initUI();
    }

    public VentanaMenu(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;
        initUI();
    }

    private void initUI() {
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10,10));

        Usuario usuario = session.getUsuarioActual();
        String nombre = (usuario != null) ? usuario.getNombre() : "Invitado";
        JLabel mensaje = new JLabel("Bienvenido, " + nombre, SwingConstants.CENTER);

        JPanel panelBotones = new JPanel(new java.awt.GridLayout(5, 1, 10, 10));
        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnEstadisticas);
        panelBotones.add(btnPerfil);
        panelBotones.add(btnLogout);

        frame.add(mensaje, BorderLayout.NORTH);
        frame.add(panelBotones, BorderLayout.CENTER);

        btnJugar.addActionListener(e -> abrirRuleta());
        btnHistorial.addActionListener(e -> verHistorial());
        btnEstadisticas.addActionListener(e -> abrirEstadisticas());
        btnPerfil.addActionListener(e -> abrirPerfil());
        btnLogout.addActionListener(e -> logout());
    }

    private void abrirRuleta() {
        frame.dispose();
        new VentanaRuleta(session, ruletaController).mostrarVentana();
    }

    private void verHistorial() {
        frame.dispose();
        ResultadoController resultadoController = new ResultadoController(session);
        new VentanaHistorial(session, ruletaController, resultadoController).mostrarVentana();
    }

    private void abrirEstadisticas() {
        frame.dispose();
        ResultadoController resultadoController = new ResultadoController(session);
        new VentanaEstadisticas(session, ruletaController, resultadoController).mostrarVentana();
    }

    private void abrirPerfil() {
        frame.dispose();
        new VentanaPerfil(session, ruletaController).mostrarVentana();
    }

    private void logout() {
        session.logout();
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}