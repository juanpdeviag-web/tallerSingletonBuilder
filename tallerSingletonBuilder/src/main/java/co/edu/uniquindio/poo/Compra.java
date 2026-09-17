package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.poo.model.*;

public class Compra {
    // 1. Campos del producto final (TODOS final = Inmutabilidad)
    // Obligatorios
    private final Cliente cliente;
    private final Funcion funcion;
    private final List<String> asientos;
    private final LocalDate fecha;

    // Opcionales
    private final Combo combo;
    private final Promocion promocion;
    private final int puntosRedimidos;
    private final boolean esCortesia;

    // Constructor privado: Solo el Builder puede entrar aquí
    private Compra(Builder b) {
        this.cliente = b.cliente;
        this.funcion = b.funcion;
        this.asientos = List.copyOf(b.asientos); // Copia inmutable segura
        this.fecha = b.fecha;
        this.combo = b.combo;
        this.promocion = b.promocion;
        this.puntosRedimidos = b.puntosRedimidos;
        this.esCortesia = b.esCortesia;
    }

    // 2. Clase interna Builder
    public static class Builder {
        // Mismos campos del producto, pero SIN final
        private Cliente cliente;
        private Funcion funcion;
        private List<String> asientos = new ArrayList<>();
        private LocalDate fecha; // Obligatorio

        // Opcionales con VALORES POR DEFECTO inicializados
        private Combo combo = null;
        private Promocion promocion = null;
        private int puntosRedimidos = 0;   // Defecto seguro
        private boolean esCortesia = false; // Defecto seguro

        // Métodos fluidos (retornan 'this' para encadenar)
        public Builder conCliente(Cliente c) { this.cliente = c; return this; }
        public Builder conFuncion(Funcion f) { this.funcion = f; return this; }
        public Builder conAsiento(String a) { this.asientos.add(a); return this; }
        public Builder conFecha(LocalDate f) { this.fecha = f; return this; }

        // Métodos para los opcionales
        public Builder conCombo(Combo c) { this.combo = c; return this; }
        public Builder conPromocion(Promocion p) { this.promocion = p; return this; }
        public Builder conPuntos(int p) { this.puntosRedimidos = p; return this; }
        public Builder conCortesia(boolean c) { this.esCortesia = c; return this; }

        // 3. El método clave: Valida y construye
        public Compra build() {
            // REGLAS DE NEGOCIO (Validación de Obligatorios)
            if (this.cliente == null)
                throw new IllegalStateException("Error: El cliente es obligatorio.");
            if (this.funcion == null)
                throw new IllegalStateException("Error: La función es obligatoria.");
            if (this.asientos.isEmpty())
                throw new IllegalStateException("Error: Debe seleccionar al menos un asiento.");
            if (this.fecha == null)
                throw new IllegalStateException("Error: La fecha de la compra es obligatoria.");

            // Si pasa todas las validaciones, se crea la Compra
            return new Compra(this);
        }
    }

    @Override
    public String toString() {
        return "Compra:" +
                "\ncliente=" + cliente +
                "\nfuncion=" + funcion +
                "\nasientos=" + asientos +
                "\nfecha=" + fecha +
                "\ncombo=" + combo +
                "\npromocion=" + promocion +
                "\npuntosRedimidos=" + puntosRedimidos +
                "\nesCortesia=" + esCortesia;
    }
}

