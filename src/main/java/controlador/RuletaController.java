package controlador;

import modelo.Ruleta;
import modelo.TipoApuesta;
import modelo.Resultado;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
    }

    public RuletaController(int saldoInicial, SessionController session) {
        this.ruleta = new Ruleta(saldoInicial);
        this.session = session;
    }

    public Resultado jugar(int monto, TipoApuesta tipo) {
        int numero = ruleta.girarRuleta();
        boolean acierto = ruleta.evaluarResultado(numero, tipo);
        ruleta.registrarResultado(numero, monto, acierto, tipo);

        Resultado resultado = new Resultado(numero, monto, acierto, tipo);

        if (session.getUsuarioActual() != null) {
            session.getUsuarioActual().agregarResultado(resultado);
        }
        return resultado;
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