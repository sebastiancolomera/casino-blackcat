package controlador;

import modelo.Resultado;
import java.util.ArrayList;
import java.util.List;

public class ResultadoController {
    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> getHistorialUsuario() {
        if (session.getUsuarioActual() != null) {
            return session.getUsuarioActual().getHistorial();
        }
        return new ArrayList<>();
    }
    public modelo.Estadisticas getEstadisticasUsuario() {
        return new modelo.Estadisticas(new modelo.RepositorioEnMemoria(getHistorialUsuario()));
    }
}