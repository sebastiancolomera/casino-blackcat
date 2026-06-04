package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private String nombreArchivo;

    public RepositorioArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void agregar(Resultado resultado) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(resultado.getNumero() + "," + resultado.getApuesta() + "," + resultado.isAcierto() +
                    "," + resultado.getTipo());
            bw.newLine();

        } catch (IOException e) {
            System.err.println("Error al guardar resultado en archivo " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerTodos() {
        List<Resultado> historial = new ArrayList<>();
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            return historial;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int numero = Integer.parseInt(partes[0]);
                int apuesta = Integer.parseInt(partes[1]);
                boolean acierto = Boolean.parseBoolean(partes[2]);
                String tipo = partes[3];
                historial.add(new Resultado(numero, apuesta, acierto, tipo));
            }
        } catch (IOException e) {
            System.err.println("Error al leer historial desde archivo " + e.getMessage());
        }
        return historial;
    }
}