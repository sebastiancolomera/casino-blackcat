package modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {

    private List<Resultado> historial;

    public RepositorioEnMemoria() {
        this.historial = new ArrayList<>();
    }

    public RepositorioEnMemoria(List<Resultado> inicial) {
        this.historial = new ArrayList<>(inicial);
    }

    @Override
    public void agregar(Resultado resultado) {
        historial.add(resultado);
    }

    @Override
    public List<Resultado> obtenerTodos() {
        return historial;
    }
}