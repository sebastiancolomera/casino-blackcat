package modelo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void calculaRachaYTipoMasJugadoIgnorandoNulos() {
        List<Resultado> historial = new ArrayList<>();
        historial.add(new Resultado(1, 100, true, "ROJO"));
        historial.add(new Resultado(2, 100, true, "ROJO"));
        historial.add(null);
        historial.add(new Resultado(3, 100, false, "NEGRO"));
        historial.add(new Resultado(4, 100, true, "ROJO"));

        RepositorioEnMemoria repo = new RepositorioEnMemoria(historial);
        Estadisticas stats = new Estadisticas(repo);

        assertEquals(4, stats.getTotalJugadas());
        assertEquals(3, stats.getVictorias());
        assertEquals(75.0, stats.getPorcentajeVictorias());
        assertEquals(2, stats.getRachaMaxima());
        assertEquals("ROJO", stats.getTipoMasJugado());
    }
}
