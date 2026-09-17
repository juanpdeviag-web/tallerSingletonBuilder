package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Combo;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Promocion;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        //VERIFICACIONES CLASE ConsecutivoFactura

        // 1. Obtener la instancia desde dos variables

        ConsecutivoFactura a = ConsecutivoFactura.getInstancia();
        ConsecutivoFactura b = ConsecutivoFactura.getInstancia();

        // Verificar que ambas variables apuntan al mismo objeto
        System.out.println("¿a == b?");
        System.out.println(a == b);

        // 2. Intentar crear directamente

        // ConsecutivoFactura c = new ConsecutivoFactura();
        // Esto no compila;

        // 3. Pedir tres números desde una variable

        System.out.println("\nNúmeros desde a:");

        System.out.println(a.siguiente());
        System.out.println(a.siguiente());
        System.out.println(a.siguiente());

        // 3. Pedir tres números desde otra variable

        System.out.println("\nNúmeros desde b:");
        //
        System.out.println(b.siguiente());
        System.out.println(b.siguiente());
        System.out.println(b.siguiente());

        //VERIFICACIONES CLASE Compra

        //Objetos necesarios para las pruebas de Compra

        Cliente cliente = new Cliente();
        Funcion funcion = new Funcion();
        Combo combo = new Combo();
        Promocion promocion = new Promocion();

        // COMPRA MÍNIMA

        Compra compraMinima = new Compra.Builder()
                .conCliente(cliente)
                .conFuncion(funcion)
                .conAsiento("A1")
                .conFecha(LocalDate.now())
                .build();

        // COMPRA COMPLETA

        Compra compraCompleta = new Compra.Builder()
                .conCliente(cliente)
                .conFuncion(funcion)
                .conAsiento("A2")
                .conAsiento("A3")
                .conFecha(LocalDate.now())
                .conCombo(combo)
                .conPromocion(promocion)
                .conPuntos(500)
                .conCortesia(false)
                .build();

        //1. ¿Se puede crear una Compra sin pasar por el Builder?

        /*Compra compra = new Compra(cliente, funcion, asientos, fecha, combo, promocion, puntosRedimidos, esCortesia);
        *
        * No se puede realizar esta funcion debido a que si o si se debe pasar primero por el builder
        * dado que es un constructor private*/

        System.out.println("1. Crear Compra sin Builder:");
        System.out.println("NO se puede, porque el constructor de Compra es private.");
        System.out.println();

        // 2. build() sin cliente: ¿qué pasa?

        System.out.println("2. build() sin Cliente:");

        try {
            Compra compraSinCliente = new Compra.Builder()
                    .conFuncion(funcion)
                    .conAsiento("A1")
                    .conFecha(LocalDate.now())
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Se produjo una excepción:");
            System.out.println(e.getMessage());
        }

        System.out.println();

        // 3. ¿Se pueden encadenar tres llamadas en una expresión?

        System.out.println("3. Encadenar tres llamadas:");

        Compra compra = new Compra.Builder()
                .conCliente(cliente)
                .conFuncion(funcion)
                .conAsiento("A1")
                .build();

        System.out.println("Sí, se pueden encadenar.");
        System.out.println("La Compra fue creada correctamente.");
        System.out.println(compra);

        // 4. ¿Después de creada alguien puede cambiarle el cliente?

        System.out.println("4. ¿Se puede cambiar el cliente después de crearla?");

        /* compra.setCliente(otroCliente);
        No se puede realizar dado que:
        1. No existe setCliente()
        2. private final Cliente cliente;
         */

        Cliente otroCliente = new Cliente();

        // Intentar cambiar el cliente
        //compraMinima.setCliente(otroCliente);

        System.out.println();
        System.out.println("El cliente no se puede cambiar porque el atributo es final.");
    }
}
