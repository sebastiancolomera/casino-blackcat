package modelo;

public class ApuestaImpar extends ApuestaBase {

    public ApuestaImpar(double monto) {
        super(monto, "IMPAR");
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero % 2 != 0;
    }
}