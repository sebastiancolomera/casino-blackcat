package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private String nombre;
    private int balance = 10000;

    private final List<Resultado> historial = new ArrayList<>();

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public void agregarResultado(Resultado resultado) {
        if (resultado != null) {
            historial.add(resultado);
        }
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}