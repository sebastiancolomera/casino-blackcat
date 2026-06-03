package vista;

import javax.swing.*;
import java.awt.*;

import controlador.SessionController;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnRegistrar = new JButton("Registrar");
    private final SessionController session;

    public VentanaRegistro(SessionController session) {
        this.session = session;
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));
        agregarComponentes();
        btnRegistrar.addActionListener(e -> registrar());
    }

    private void agregarComponentes() {
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);
        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(new JLabel(""));
        frame.add(btnRegistrar);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrar() {
        String n = txtNombre.getText().trim();
        String u = txtUsuario.getText().trim();
        String c = new String(txtClave.getPassword()).trim();

        if (n.isEmpty() || u.isEmpty() || c.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = session.registro(u ,c ,n);

        if (exito) {
            JOptionPane.showMessageDialog(frame, "Registro exitoso! Ahora puedes iniciar sesión.");
            frame.dispose();
            new VentanaLogin(session).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "El nombre de usuario '" + u +
                    "' ya está en uso. Elige otro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}