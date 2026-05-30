package vista;

import javax.swing.*;
import modelo.Usuario;
import controlador.SessionController;

public class VentanaLogin {
    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar:");
    private final JButton btnRegistrar = new JButton("Registrarse");
    private final SessionController session;

    public VentanaLogin(SessionController session) {
        this.session = session;
        configurarVentana();
    }

    private void configurarVentana() {
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        agregarComponentes();

        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    private void agregarComponentes() {
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(new JLabel(""));
        frame.add(btnIngresar);
        frame.add(new JLabel(""));
        frame.add(btnRegistrar);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = String.valueOf(txtClave.getPassword());

        Usuario usuario = session.login(u, p);

        if (usuario != null) {
            JOptionPane.showMessageDialog(frame, "¡Bienvenido, " + usuario.getNombre() + "!!");
            frame.dispose();
            new VentanaSaludo(session).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro(session).mostrarVentana();
    }
}