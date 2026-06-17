package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Resultado;
import modelo.ApuestaBase;
import modelo.ApuestaRojo;
import modelo.ApuestaNegro;
import modelo.ApuestaPar;
import modelo.ApuestaImpar;
import controlador.RuletaController;
import controlador.SessionController;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final SessionController session;
    private final RuletaController ruletaController;
    private final JLabel lblNumero = new JLabel("Número: -");
    private final JLabel lblResultado = new JLabel("Resultado: -");
    private final JLabel lblBalance = new JLabel("Balance: 0");
    private final JTextField txtMonto = new JTextField();
    private final JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"ROJO", "NEGRO", "PAR", "IMPAR"});
    private final JButton btnJugar = new JButton("Girar Ruleta");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaRuleta(SessionController session, RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(7, 2, 5, 5));

        frame.add(new JLabel("Tipo de apuesta:"));
        frame.add(cmbTipo);
        frame.add(new JLabel("Monto:"));
        frame.add(txtMonto);
        frame.add(btnJugar);
        frame.add(btnVolver);
        frame.add(new JLabel(""));
        frame.add(new JLabel(""));
        frame.add(new JLabel("Número obtenido:"));
        frame.add(lblNumero);
        frame.add(new JLabel("Resultado:"));
        frame.add(lblResultado);
        frame.add(new JLabel(""));
        frame.add(lblBalance);

        btnJugar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> volver());
        lblBalance.setText("Balance: " + ruletaController.getBalance());
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(frame, "La apuesta debe ser mayor a 0.",
                        "Apuesta Inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (monto > ruletaController.getBalance()) {
                JOptionPane.showMessageDialog(frame, "Saldo insuficiente. Tu balance es: " +
                                ruletaController.getBalance(),
                        "Apuesta Inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String seleccion = (String) cmbTipo.getSelectedItem();
            ApuestaBase apuesta;
            switch (seleccion) {
                case "ROJO": apuesta = new ApuestaRojo(monto); break;
                case "NEGRO": apuesta = new ApuestaNegro(monto); break;
                case "PAR": apuesta = new ApuestaPar(monto); break;
                default: apuesta = new ApuestaImpar(monto); break;
            }
            Resultado resultado = ruletaController.jugar(apuesta);

            lblNumero.setText("Número obtenido: " + resultado.getNumero());
            lblResultado.setText("Resultado: " + (resultado.isAcierto() ? "¡¡GANASTE!!" : "PERDISTE..."));
            lblBalance.setText("Balance: " + ruletaController.getBalance());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingresa un monto válido.",
                    "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volver() {
        frame.dispose();
        new VentanaMenu(session, ruletaController).mostrarVentana();
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}