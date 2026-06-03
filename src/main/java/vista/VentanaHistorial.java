package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modelo.Resultado;
import controlador.SessionController;
import controlador.RuletaController;
import controlador.ResultadoController;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial de Jugadas");
    private final JTextArea txtAreaHistorial = new JTextArea();
    private final JButton btnVolver = new JButton("Volver al Menú");

    private final SessionController session;
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;

    public VentanaHistorial(SessionController session, RuletaController ruletaController, ResultadoController resultadoController) {
        this.session = session;
        this.ruletaController = ruletaController;
        this.resultadoController = resultadoController;
        initUI();
    }

    private void initUI() {
        frame.setSize(700, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // Configurar el área de texto
        txtAreaHistorial.setEditable(false);
        txtAreaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        cargarHistorial();

        // Agregar scroll en caso de que el historial sea muy largo
        JScrollPane scrollPane = new JScrollPane(txtAreaHistorial);

        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);

        String nombreUsuario = session.getUsuarioActual() != null ? session.getUsuarioActual().getNombre() : "Usuario";
        frame.add(new JLabel("Historial de partidas: " + nombreUsuario, SwingConstants.CENTER), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelSur, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> volver());
    }

    private void cargarHistorial() {
        List<Resultado> historial = resultadoController.getHistorialUsuario();

        if (historial.isEmpty()) {
            txtAreaHistorial.setText("No hay jugadas registradas en esta sesión.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < historial.size(); i++) {
                Resultado r = historial.get(i);
                String estado = r.isAcierto() ? "GANÓ" : "PERDIÓ";
                // Formateamos la salida de cada jugada
                sb.append(String.format("Jugada #%d | Tipo: %-6s | N° Obtenido: %02d | Apuesta: $%d | Resultado: %s\n",
                        (i + 1), r.getTipo(), r.getNumero(), r.getApuesta(), estado));
            }
            txtAreaHistorial.setText(sb.toString());
        }
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void volver() {
        frame.dispose();
        new VentanaMenu(session, ruletaController).mostrarVentana();
    }
}
