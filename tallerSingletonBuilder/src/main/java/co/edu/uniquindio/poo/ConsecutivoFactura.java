package co.edu.uniquindio.poo;

public class ConsecutivoFactura {

    private static final ConsecutivoFactura INSTANCIA = new ConsecutivoFactura();

    private int ultimo = 0;

    private ConsecutivoFactura() {}

    public static ConsecutivoFactura getInstancia() {
        return INSTANCIA;
    }

    public int siguiente() {
        return ++ultimo;
    }

}
