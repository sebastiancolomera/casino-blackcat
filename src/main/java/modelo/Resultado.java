package modelo;

import java.io.Serializable;

public class Resultado implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int numero;
    private final int apuesta;
    private final boolean acierto;
    private final String tipo;

    public Resultado(int numero, int apuesta, boolean acierto, String tipo) {
        this.numero = numero;
        this.apuesta = apuesta;
        this.acierto = acierto;
        this.tipo = tipo;
    }

    public int getNumero() { return numero; }
    public int getApuesta() { return apuesta; }
    public boolean isAcierto() { return acierto; }
    public String getTipo() { return tipo; }
}