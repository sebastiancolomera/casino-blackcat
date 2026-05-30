package modelo;

public class Resultado {
    private final int numero;
    private final int apuesta;
    private final boolean acierto;
    private final TipoApuesta tipo;

    public Resultado(int numero, int apuesta, boolean acierto, TipoApuesta tipo) {
        this.numero = numero;
        this.apuesta = apuesta;
        this.acierto = acierto;
        this.tipo = tipo;
    }

    public int getNumero() { return numero; }
    public int getApuesta() { return apuesta; }
    public boolean isAcierto() { return acierto; }
    public TipoApuesta getTipo() { return tipo; }
}