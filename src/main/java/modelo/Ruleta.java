package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {
    private final List<Resultado> historial;
    private int balance = 0;
    private final Random rng = new Random();
    private final int[] numerosRojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public Ruleta() {
        this.historial = new ArrayList<>();
    }

    public Ruleta(int saldoInicial) {
        this.historial = new ArrayList<>();
        this.balance = saldoInicial;
    }

    public int getBalance() {
        return balance;
    }

    public void depositar(int monto) {
        if (monto > 0) {
            balance += monto;
        }
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (n == rojo) {
                return true;
            }
        }
        return false;
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) {
            return false;
        }
        if (tipo == TipoApuesta.ROJO) {
            return esRojo(numero);
        }
        if (tipo == TipoApuesta.NEGRO) {
            return !esRojo(numero);
        }
        if (tipo == TipoApuesta.PAR) {
            return numero % 2 == 0;
        }
        if (tipo == TipoApuesta.IMPAR) {
            return numero % 2 != 0;
        }
        return false;
    }

    public void registrarResultado(int numero, int apuesta, boolean acierto, TipoApuesta tipo) {
        historial.add(new Resultado(numero, apuesta, acierto, tipo));

        if (acierto) {
            balance += apuesta;
        } else {
            balance -= apuesta;
        }
    }

    public String getEstadisticas() {
        int apostado = 0;
        int aciertos = 0;

        for (Resultado r : historial) {
            apostado += r.getApuesta();
            if (r.isAcierto()) {
                aciertos++;
            }
        }

        int cantidadJugadas = historial.size();
        double porcentaje = (cantidadJugadas == 0) ? 0 : (aciertos * 100.0) / cantidadJugadas;

        return "Rondas: " + cantidadJugadas + "\nTotal Apostado: " + apostado + "\nAciertos: " + aciertos
                + "\nBalance" + balance +"\nPorcentaje: " + String.format("%.1f", porcentaje) + "%";
    }
}