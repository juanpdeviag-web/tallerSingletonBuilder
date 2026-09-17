# Respuestas Ejercicio 3: Análisis de Patrones Creacionales

### a) ¿Cuál es la limitación de Singleton frente a DIP, y cómo se resuelve?
* **Limitación:** Viola el Principio de Inversión de Dependencias (DIP) al acoplar directamente las clases consumidoras a una implementación concreta y global (la clase estática), impidiendo la sustitución por mocks/stubs para pruebas unitarias.
* **Solución:** Se resuelve abstrayendo el Singleton detrás de una interfaz que represente el contrato o delegando la creación y gestión del ciclo de vida de la instancia única a un contenedor de Inyección de Dependencias (como Spring Framework).

---

### b) ¿En cuántos lugares del proyecto debe aparecer `getInstancia()`? ¿Cuáles?
Debe aparecer únicamente en **dos** tipos de lugares:
1. En la definición propia de la clase Singleton (donde se implementa el método estático).
2. En las clases de nivel de infraestructura/configuración que inyectan o pasan esta dependencia hacia las demás capas. *(No debe dispersarse por toda la lógica de negocio del dominio).*

---

### c) Singleton y Builder usan los dos un constructor privado. ¿Para qué sirve en cada uno?
* **Singleton:** Sirve para impedir que cualquier clase externa cree nuevas instancias con `new`, garantizando de forma estricta que exista una única instancia en toda la aplicación.
* **Builder:** Sirve para forzar que los objetos del producto (ej. `Compra`) solo se puedan instanciar pasando a través del proceso de construcción validado del `Builder`, impidiendo la creación de objetos incompletos o en estados no válidos.

---

### d) De estas clases de su proyecto, ¿cuál llevaría Builder y cuál no? Justifiquen en una línea:
* **Compra:** **SÍ lleva Builder**, porque tiene múltiples atributos, combinaciones opcionales (combo, promoción) y requiere validaciones de negocio previas a la creación.
* **Cliente:** **NO lleva Builder**, posee pocos campos obligatorios de identificación y se puede manejar mediante un constructor simple o *Value Object*.
* **Funcion:** **NO lleva Builder**, representa una entidad con parámetros definidos y conocidos al programar el evento.
* **Asiento (fila, numero):** **NO lleva Builder**, es un *Value Object* inmutable y muy simple definido únicamente por su fila y número.
* **Combo:** **NO lleva Builder**, es un producto básico o catálogo con atributos fijos creados directamente o mediante un *Factory*.

---

### e) ConfiguracionCine es Singleton y el Builder de Compra necesita el IVA que ella guarda. ¿El Builder debe llamar a `ConfiguracionCine.getInstancia()` por dentro? ¿Por qué?
**No.** El Builder no debe invocar directamente `ConfiguracionCine.getInstancia()` internamente porque acoplaría la lógica de construcción a un Singleton global. En su lugar, la tasa del IVA o el valor configurado debe pasarse como un parámetro al Builder desde afuera o inyectarse al invocar `.build()`, preservando el desacoplamiento y la facilitación de pruebas unitarias.