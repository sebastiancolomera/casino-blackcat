package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Usuario;
import controlador.SessionController;
import controlador.RuletaController;

public class VentanaPerfil {
    private final JFrame frame = new JFrame("Perfil - Casino Black Cat");
    private final SessionController session;
    private final RuletaController ruletaController;
    private final JLabel lblNombre = new JLabel();
    private final JTextField txtNombre = new JTextField();
    private final JButton btnActualizar = new JButton("Actualizar Nombre");
    private final JLabel lblSaldo = new JLabel();
    private final JTextField txtDeposito = new JTextField();
    private final JButton btnDepositar = new JButton("Depositar");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaPerfil(SessionController session,RuletaController ruletaController) {
        this.session = session;
        this.ruletaController = ruletaController;
        initUI();
    }

    private void initUI() {
        frame.setSize(350, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(7, 2, 5, 5));

        frame.add(new JLabel("Usuario:"));
        frame.add(lblNombre);
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);
        frame.add(btnActualizar);
        frame.add(new JLabel(""));
        frame.add(new JLabel("Saldo:"));
        frame.add(lblSaldo);
        frame.add(new JLabel("Depositar:"));
        frame.add(txtDeposito);
        frame.add(btnDepositar);
        frame.add(new JLabel(""));
        frame.add(btnVolver);

        actualizarDatos();

        btnActualizar.addActionListener(e -> actualizarNombre());
        btnDepositar.addActionListener(e -> depositar());
        btnVolver.addActionListener(e -> volver());
    }

    private void actualizarDatos() {
        Usuario usuario = session.getUsuarioActual();
        lblNombre.setText(usuario.getUsername());
        txtNombre.setText(usuario.getNombre());
        lblSaldo.setText(String.valueOf(ruletaController.getBalance()));
    }

    private void actualizarNombre() {
        String nuevoNombre = txtNombre.getText().trim();
        session.getUsuarioActual().setNombre(nuevoNombre);
        JOptionPane.showMessageDialog(frame, "Nombre actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        actualizarDatos();
    }

    private void depositar() {
        try {
            int monto = Integer.parseInt(txtDeposito.getText());
            if (monto > 0) {
                ruletaController.depositar(monto);
                txtDeposito.setText("");
                actualizarDatos();
                JOptionPane.showMessageDialog(frame, "Depósito exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
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