package modelo;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Estadisticas {

	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;
	private String tipoMasJugado;

	public Estadisticas(List<Resultado> historial) {
		calcularMetricas(historial);
	}

	private void calcularMetricas(List<Resultado> historial) {
		if (historial == null || historial.isEmpty()) {
			this.totalJugadas = 0;
			this.victorias = 0;
			this.porcentajeVictorias = 0.0;
			this.rachaMaxima = 0;
			this.tipoMasJugado = null;
			return;
		}

		this.totalJugadas = historial.size();
		this.victorias = 0;
		int rachaActual = 0;

		Map<String, Integer> conteoTipos = new HashMap<>();
		for (Resultado resultado : historial) {
			if (resultado.isAcierto()) {
				this.victorias++;
				rachaActual++;
				if (this.rachaMaxima < rachaActual) {
					this.rachaMaxima = rachaActual;
				}
			} else {
				rachaActual = 0;
			}
			String tipo = resultado.getTipo();
			conteoTipos.put(tipo, conteoTipos.getOrDefault(tipo, 0) + 1);
		}
		this.porcentajeVictorias = (this.victorias * 100.0) / this.totalJugadas;
		int maxApariciones = 0;
		for (Map.Entry<String, Integer> entry : conteoTipos.entrySet()) {
			if (entry.getValue() > maxApariciones) {
				maxApariciones = entry.getValue();
				this.tipoMasJugado = entry.getKey();
			}
		}
	}

	public int getTotalJugadas() {
		return this.totalJugadas;
	}

	public int getVictorias() {
		return this.victorias;
	}

	public double getPorcentajeVictorias() {
		return this.porcentajeVictorias;
	}

	public int getRachaMaxima() {
		return this.rachaMaxima;
	}

	public String getTipoMasJugado() {
		return this.tipoMasJugado;
	}
}