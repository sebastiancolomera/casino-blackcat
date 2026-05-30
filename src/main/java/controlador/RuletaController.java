package controlador;

import modelo.Ruleta;
import modelo.TipoApuesta;
import modelo.Resultado;

public class RuletaController {
    private final Ruleta ruleta;

    public RuletaController() {
        this.ruleta = new Ruleta();
    }

    public RuletaController(int saldoInicial) {
        this.ruleta = new Ruleta(saldoInicial);
    }

    public Resultado jugar(int monto, TipoApuesta tipo) {
        int numero = ruleta.girarRuleta();
        boolean acierto = ruleta.evaluarResultado(numero, tipo);
        ruleta.registrarResultado(numero, monto, acierto, tipo);
        return new Resultado(numero, monto, acierto, tipo);
    }

    public Ruleta getRuleta() {
        return ruleta;
    }

    public void depositar(int monto) {
        ruleta.depositar(monto);
    }

    public int getBalance() {
        return ruleta.getBalance();
    }

    public String getEstadisticas() {
        return ruleta.getEstadisticas();
    }
}