package modelo;

public class ApuestaNegro extends ApuestaBase {

    public ApuestaNegro(double monto) {
        super(monto, "NEGRO");
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && color.equals("negro");
    }
}