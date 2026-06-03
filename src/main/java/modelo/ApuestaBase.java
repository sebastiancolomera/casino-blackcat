package modelo;

public abstract class ApuestaBase {

    private double monto;
    private String etiqueta;

    public ApuestaBase(double monto, String etiqueta) {
        this.monto = monto;
        this.etiqueta = etiqueta;
    }

    public double getMonto() {
        return monto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public abstract boolean acierta(int numero, String color);
}