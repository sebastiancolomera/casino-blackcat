package controlador;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private Usuario usuarioActual;
    private final List<Usuario> usuarios;

    public SessionController() {
        this.usuarios = new ArrayList<>();
        inicializarUsuarios();
    }

    private void inicializarUsuarios() {
        usuarios.add(new Usuario("admin", "DD777", "Don Donnie"));
        usuarios.add(new Usuario("jugador1", "1234", "Gato Ludopata"));
    }

    public Usuario login(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.validarCredenciales(username, password)) {
                this.usuarioActual = usuario;
                return usuario;
            }
        }
        return null;
    }

    public boolean registro(String username, String password, String nombre) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }

        Usuario nuevo = new Usuario(username, password, nombre);
        usuarios.add(nuevo);
        this.usuarioActual = nuevo;
        return true;
    }

    public void logout() {
        this.usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}