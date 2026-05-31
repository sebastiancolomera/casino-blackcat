package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Estadisticas;
import controlador.SessionController;
import controlador.RuletaController;
import controlador.ResultadoController;

public class VentanaEstadisticas {
    private final JFrame frame = new JFrame("Estadísticas del Jugador");
    private final SessionController session;
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;
    private final JButton botonVolver = new JButton("Volver al Menú");

    public VentanaEstadisticas(SessionController session, RuletaController ruletaController, ResultadoController resultadoController) {
        this.session = session;
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;
        initUI();
    }

    private void initUI() {
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10,10));

        String nombreUsuario = session.getUsuarioActual() != null ? session.getUsuarioActual().getNombre() : "Usuario";
        JLabel lblTitulo = new JLabel("Estadísticas de " + nombreUsuario, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelDatos = new JPanel(new GridLayout(5, 2, 5, 5));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        Estadisticas stats = resultadoController.getEstadisticasUsuario();

        panelDatos.add(new JLabel("Total Jugadas:"));
        panelDatos.add(new JLabel(String.valueOf(stats.getTotalJugadas())));
        panelDatos.add(new JLabel("Victorias Totales:"));
        panelDatos.add(new JLabel(String.valueOf(stats.getVictorias())));
        panelDatos.add(new JLabel("Porcentaje de Victorias:"));
        panelDatos.add(new JLabel(String.format("%.2f%%", stats.getPorcentajeVictorias())));
        panelDatos.add(new JLabel("Racha Máxima:"));
        panelDatos.add(new JLabel(String.valueOf(stats.getRachaMaxima())));
        panelDatos.add(new JLabel("Apuesta Favorita:"));
        String tipoFav = stats.getTipoMasJugado() != null ? stats.getTipoMasJugado().name() : "N/A";
        panelDatos.add(new JLabel(tipoFav));

        frame.add(panelDatos, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        panelSur.add(botonVolver);
        frame.add(panelSur, BorderLayout.SOUTH);
        botonVolver.addActionListener(e -> volver());
    }

    private void volver() {
        frame.dispose();
        new VentanaMenu(session, ruletaController).mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}