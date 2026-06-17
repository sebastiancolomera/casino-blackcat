package controlador;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SessionController {
    private Usuario usuarioActual;
    private final List<Usuario> usuarios;
    private static final String ARCHIVO = "usuarios.dat";

    public SessionController() {
        this.usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    private void inicializarUsuarios() {
        usuarios.add(new Usuario("admin", "DD777", "Don Donnie"));
        usuarios.add(new Usuario("jugador1", "1234", "Gato Ludopata"));
        guardarUsuarios();
    }

    @SuppressWarnings("unchecked")
    private void cargarUsuarios() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            inicializarUsuarios();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            List<Usuario> cargados = (List<Usuario>) ois.readObject();
            if (cargados != null) {
                usuarios.addAll(cargados);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            inicializarUsuarios();

        }
    }

    public void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
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
        if (username == null || password == null) {
            return false;
        }
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }

        Usuario nuevo = new Usuario(username, password, nombre);
        usuarios.add(nuevo);
        this.usuarioActual = nuevo;
        guardarUsuarios();
        return true;
    }

    public void logout() {
        this.usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}