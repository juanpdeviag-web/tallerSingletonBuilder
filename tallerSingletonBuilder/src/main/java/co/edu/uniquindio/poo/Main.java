package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Combo;
import co.edu.uniquindio.poo.model.Funcion;
import co.edu.uniquindio.poo.model.Promocion;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){

        //VERIFICACIONES CLASE ConsecutivoFactura
        System.out.println("\n-- VERIFICACIONES ConsecutivoFactura --");

        // 1. Obtener la instancia desde dos variables

        System.out.println("1. Obtener la instancia desde dos variables");

        ConsecutivoFactura a = ConsecutivoFactura.getInstancia();
        ConsecutivoFactura b = ConsecutivoFactura.getInstancia();

        // Verificar que ambas variables apuntan al mismo objeto
        System.out.println("¿a == b?");
        System.out.println(a == b);

        // 2. Intentar crear directamente

        System.out.println("\n2. Intentar crear directamente:");
            // ConsecutivoFactura c = new ConsecutivoFactura();
        System.out.println("Resultado: new ConsecutivoFactura() en el main no compila.");
        System.out.println("Razón: El constructor ConsecutivoFactura() tiene acceso privado ('private').");
        System.out.println();

        // 3. Pedir tres números desde una variable

        System.out.println("\n3. Pedir tres números desde una variable");
        System.out.println("\nNúmeros desde a y b:");

        System.out.println(a.siguiente());
        System.out.println(b.siguiente());
        System.out.println(a.siguiente());

        // Más números

        System.out.println("\nMás números:");
        //
        System.out.println(b.siguiente());
        System.out.println(a.siguiente());
        System.out.println(b.siguiente());

        //VERIFICACIONES CLASE Compra
        System.out.println("\n-- VERIFICACIONES Compra --");
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

        System.out.println("\n1. Crear Compra sin Builder:");
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
                .conFecha(LocalDate.now()) //Lo cuatro son obligatorios
                .build();

        System.out.println("Sí, se pueden encadenar.");
        System.out.println(compra);
        System.out.println("La Compra fue creada correctamente.\n");

        // 4. ¿Después de creada alguien puede cambiarle el cliente?

        System.out.println("4. ¿Se puede cambiar el cliente después de crearla?");

        // Cliente otroCliente = new Cliente();
        // compraMinima.setCliente(otroCliente);

        System.out.println("Resultado: NO, es imposible cambiar el cliente.");
        System.out.println("Razones técnicas:");
        System.out.println("  1. No existen métodos modificadores (setters) en la clase Compra.");
        System.out.println("  2. El atributo 'cliente' está declarado como 'private final', lo que garantiza su inmutabilidad.");
        System.out.println();



        // VERIFICACIÓN DEL RETO (Campo 'esCortesia')

        System.out.println("--- VERIFICACIÓN DEL RETO ---");
        System.out.println("¿Cuántas llamadas existentes se modificaron: 0");
        System.out.println("Principio SOLID cumplido: Principio Abierto/Cerrado (OCP)");

        // Demostración: Creamos una nueva compra usando el nuevo método
        Compra compraReto = new Compra.Builder()
                .conCliente(cliente)
                .conFuncion(funcion)
                .conAsiento("B1")
                .conFecha(LocalDate.now())
                .conCortesia(true) // <- Nueva funcionalidad añadida sin romper lo anterior
                .build();

        System.out.println("Compra del reto (con cortesía) creada con éxito.");
        System.out.println(compraReto);
    }
}
