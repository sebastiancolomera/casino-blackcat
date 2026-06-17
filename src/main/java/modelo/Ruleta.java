package modelo;

import java.util.List;
import java.util.Random;

public class Ruleta {
    private final IRepositorioResultados repositorio;
    private int balance = 0;
    private final Random rng = new Random();
    private final int[] numerosRojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};

    public Ruleta(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public Ruleta(int saldoInicial, IRepositorioResultados repositorio) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial inválido");
        }
        this.repositorio = repositorio;
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

    private String colorDe(int numero) {
        if (numero == 0) return "verde";
        return esRojo(numero) ? "rojo" : "negro";
    }

    public Resultado jugar(ApuestaBase apuesta) {
        if (apuesta == null) {
            throw new IllegalArgumentException("Apuesta requerida");
        }
        int numero = girarRuleta();
        String color = colorDe(numero);
        boolean acierto = apuesta.acierta(numero, color);
        if (acierto) {
            balance += (int) apuesta.getMonto();
        } else {
            balance -= (int) apuesta.getMonto();
        }

        Resultado resultado = new Resultado(numero, (int) apuesta.getMonto(), acierto, apuesta.getEtiqueta());
        repositorio.agregar(resultado);
        return resultado;
    }

    public String getEstadisticas() {
        int apostado = 0;
        int aciertos = 0;

        for (Resultado r : repositorio.obtenerTodos()) {
            apostado += r.getApuesta();
            if (r.isAcierto()) {
                aciertos++;
            }
        }

        int cantidadJugadas = repositorio.obtenerTodos().size();
        double porcentaje = (cantidadJugadas == 0) ? 0 : (aciertos * 100.0) / cantidadJugadas;

        return "Rondas: " + cantidadJugadas + "\nTotal Apostado: " + apostado + "\nAciertos: " + aciertos
                + "\nBalance" + balance +"\nPorcentaje: " + String.format("%.1f", porcentaje) + "%";
    }
}