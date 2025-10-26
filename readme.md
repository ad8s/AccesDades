# AccesDades — Exploración de Acceso a Ficheros en Java

## Introducción

**AccesDades** es un repositorio educativo que reúne ejemplos y ejercicios en Java para ilustrar y experimentar con distintas técnicas de acceso y manipulación de ficheros. Su propósito es mostrar, mediante código claro y práctico, cómo funcionan los diferentes mecanismos de entrada/salida (I/O) en Java: desde flujos de bytes hasta estructuras binarias complejas y serialización de objetos.

No es una librería reutilizable ni una aplicación terminada. Es, fundamentalmente, **un conjunto de ejemplos didácticos** que exploran patrones comunes y decisiones de diseño cuando se trabaja con datos persistentes en disco.

## Propósito del repositorio

Este repositorio responde a varias preguntas prácticas sobre I/O en Java:

- ¿Cuál es la diferencia entre `FileInputStream` / `FileOutputStream` y streams tipados como `DataInputStream` / `DataOutputStream`?
- ¿Cómo se diseña un formato de fichero binario sencillo y se implementa su lectura y escritura?
- ¿Cómo se usan estructuras de registro con tamaño fijo para permitir acceso aleatorio eficiente con `RandomAccessFile`?
- ¿Cuándo y cómo se utiliza la serialización de objetos en Java?
- ¿Cuáles son las limitaciones y riesgos de cada enfoque?

Al código del repositorio subyace la idea de que **la forma en que se diseña el formato de datos condiciona el código que debe leerlo y modificarlo**. Los ejemplos muestran esta relación de manera explícita.

## Estructura del repositorio

```
AccesDades/
├── Clase/
│   ├── Ejemplos/          # Código demostrativo simple: FileI/O, RandomAccessFile, etc.
│   │   ├── crearPaisos.java
│   │   ├── Pais.java
│   │   ├── easd.java
│   │   ├── ex322.java
│   │   └── ls.java
│   ├── Ex/                # Ejercicios más completos: binarios, serialización, búsquedas
│   │   ├── ex1.java
│   │   ├── ex21.java
│   │   ├── ex22.java
│   │   ├── ex23.java
│   │   ├── ex31.java
│   │   ├── ex32.java
│   │   ├── ex41.java
│   │   └── ex51.java
├── practicar/             # Prácticas adicionales: copiar ficheros, estadísticas, etc.
│   ├── ex1.java
│   ├── ex2.java
│   ├── ex3.java
│   └── ...
├── Files/                 # Ficheros de muestra: binarios, texto, datos generados
│   ├── paisos.dat
│   ├── text.bin
│   ├── numeros.txt
│   └── ...
└── readme.md
```

## Contenidos principales por carpeta

### `Clase/Ejemplos/`

Ejemplos demostrativos muy dirigidos que ilustran un concepto puntual:

- **`easd.java`**: Escritura y lectura básica de texto con `FileOutputStream` y `FileInputStream`. Muestra el concepto más simple de I/O: escribir bytes y leerlos secuencialmente.
- **`Pais.java`**: Clase modelo que representa un país (nombre, código ISO, capital, población). Se usa como estructura de datos en otros ejercicios.
- **`crearPaisos.java`**: Genera un fichero binario `paisos.dat` con registros de tamaño fijo. Enseña cómo estructurar datos con `RandomAccessFile` y cómo calcular tamaños y offsets.
- **`ls.java`**: Implementación simplificada del comando `ls` de Unix. Muestra cómo inspeccionar permisos de archivos usando la API `java.io.File`.
- **`ex322.java`**: Lee un fichero binario generado, buscando un código específico. Ilustra manejo de EOF y búsqueda dentro de un flujo binario.

### `Clase/Ex/`

Ejercicios más estructurados que combinan múltiples conceptos:

- **`ex1.java` a `ex3.java`**: Operaciones básicas de I/O (lectura/escritura de texto).
- **`ex21.java`, `ex22.java`**: Lectura de ficheros de texto y análisis (contar caracteres, copiar línea a línea).
- **`ex23.java`, `ex31.java`**: Generación de ficheros binarios con `DataOutputStream`. Crean ficheros con pares (int + 3 caracteres) repetidos, enseñando estructuras repetidas.
- **`ex32.java`, `ex322.java`**: Búsqueda dentro de ficheros binarios con `DataInputStream`. Manejan EOFException y demuestran lectura de bloques.
- **`ex41.java`**: Serialización de un objeto `User` (nombre + contraseña) usando `ObjectOutputStream` / `ObjectInputStream`. Enseña cómo persistir objetos Java completos (y advierte sobre las limitaciones).
- **`ex51.java`**: Lee y modifica registros dentro de `paisos.dat` usando `RandomAccessFile`. Este es el ejemplo más complejo: calcula offsets, navega el fichero y modifica campos individuales sin reescribir todo el fichero.

### `practicar/`

Colección de prácticas para reforzar conceptos:

- **`ex1.java`**: Copia un fichero línea a línea.
- **`ex2.java`**: Calcula estadísticas (número de líneas, palabras, caracteres) de un fichero de texto.
- **`ex3.java` y sucesivos**: Otros ejercicios de práctica que combinan conceptos aprendidos.

### `Files/`

Ficheros de ejemplo y muestras:

- **`paisos.dat`**: Fichero binario con registros fijos (resultado de ejecutar `crearPaisos`).
- **`text.bin`**: Fichero binario con pares (código, 3 caracteres).
- **`numeros.txt`, `text.txt`, `usertext.txt`**: Ficheros de texto de ejemplo.
- Otros ficheros generados por los ejercicios.

## Formatos de datos y decisiones de diseño

### 1. Fichero de registros fijos: `paisos.dat`

**Motivación**: Enseñar acceso aleatorio (random access) y cómo se implementa cuando los registros tienen tamaño fijo.

**Estructura lógica de un registro**:

```
[Offset 0-3]     : id (int, 4 bytes)
[Offset 4-83]    : nom (40 caracteres Java, 2 bytes/char = 80 bytes)
[Offset 84-89]   : codiISO (3 caracteres, 6 bytes)
[Offset 90-169]  : capital (40 caracteres, 80 bytes)
[Offset 170-173] : poblacio (int, 4 bytes)
─────────────────────────────────────
Total por registro: 174 bytes
```

**Enseñanzas**:
- Para acceder al registro N, se calcula `offset = (N - 1) * 174` y se usa `RandomAccessFile.seek(offset)`.
- Es crucial que los tamaños de campo coincidan exactamente (ej.: 40 caracteres, no 35 o 45).
- Si un `StringBuilder` es más corto que 40 caracteres, `setLength(40)` lo rellena de nulos (`\0`).
- Esta estructura permite lectura y modificación eficiente de cualquier registro sin releer todo el fichero.

**Limitación**: Si el formato cambia (p. ej., agregar un campo), los ficheros antiguos quedan incompatibles. No hay versionado.

### 2. Ficheros binarios con bloques repetidos: `text.bin`, `codis_secrets.bin`

**Motivación**: Ilustrar `DataOutputStream` / `DataInputStream` y la lectura secuencial de estructuras repetidas.

**Estructura lógica**:

```
[Bloque 1]  : int (código) + 3 chars (letras)
[Bloque 2]  : int (código) + 3 chars (letras)
...
[Bloque N]  : int (código) + 3 chars (letras)
```

**Enseñanzas**:
- `writeInt()` y `readInt()` encapsulan la conversión de bytes y el orden de bytes (big-endian por defecto).
- `writeChar()` / `readChar()` escriben/leen caracteres Java (2 bytes cada uno).
- No hay marcador de fin explícito; se detecta EOF con `EOFException` al intentar leer más de lo que existe.
- Es sencillo buscar un código específico iterando bloque a bloque.

### 3. Serialización de objetos: `*.usr`

**Motivación**: Mostrar cómo persistir objetos Java completos sin escribir cada campo manualmente.

**Estructura lógica**:

```
[Encabezado Java]  : Información de serialización
[Datos del objeto] : Nombre, contraseña, etc.
```

**Enseñanzas**:
- `Serializable` es una interfaz marcadora; la JVM maneja automáticamente la serialización.
- Es muy conveniente para prototipos.
- **Limitación crítica**: Si cambias la clase (añades/quitas campos), los objetos guardados no se desserializan correctamente (excepto con `serialVersionUID`).
- **Riesgo de seguridad**: Los datos en ficheros serializados no están cifrados ni autenticados. Las contraseñas almacenadas de este modo son un riesgo.

## Conceptos y técnicas ilustradas

### Flujos de bytes vs. flujos tipados

- **FileInputStream / FileOutputStream**: Leen y escriben un byte a la vez. El programa decide cómo interpretarlos.
- **DataInputStream / DataOutputStream**: Encapsulan la conversión de tipos (int → 4 bytes, char → 2 bytes, etc.). Mantienen coherencia entre escritor y lector.

### Acceso aleatorio

- **FileInputStream / FileOutputStream**: Secuencial. Solo puedes leer/escribir desde el punto donde estés.
- **RandomAccessFile**: Permite saltar a cualquier offset con `seek()`. Ideal para ficheros con registros de tamaño fijo.

### Manejo de errores en I/O

- **EOFException**: Se lanza al intentar leer más allá del fin del fichero en un `DataInputStream`.
- **IOException**: Captura errores de fichero, permisos, dispositivo, etc.
- Es crítico cerrar recursos (streams, ficheros) con `try-with-resources` o `finally`.

### Serialización

- Persistir objetos Java sin escribir cada campo manualmente es conveniente, pero tiene limitaciones de compatibilidad y seguridad.

## Aprendizajes y advertencias clave

1. **El formato condiciona el código**: Un fichero de registros fijos permite acceso aleatorio rápido; un formato variable requiere índices o metadatos adicionales.

2. **Sincronización lector-escritor**: Si el escritor escribe `writeInt()` + `writeChar()` + `writeChar()`, el lector debe hacer exactamente `readInt()` + `readChar()` + `readChar()` en ese orden. Un error aquí produce datos corruptos o excepciones.

3. **Control de tamaños**: En ficheros binarios con estructura, los tamaños deben ser exactos y consistentes. `RandomAccessFile` no es indulgente.

4. **Cerrar recursos es obligatorio**: Olvidar cerrar un fichero puede causar corrupción de datos o problemas de acceso en sistemas Windows.

5. **Serialización ≠ seguridad**: Serializar contraseñas o datos sensibles es peligroso sin cifrado adicional.

6. **Versionado de formato**: Este código no maneja cambios de formato. En aplicaciones reales, se añade versionado, schemas o alternativas como JSON/XML/protobuf.

## Público objetivo

- Estudiantes de programación que quieren entender cómo funcionan las operaciones de I/O reales en Java.
- Cualquiera que necesite trabajar con ficheros binarios, formatos estructurados o persistencia de datos.
- Educadores que busquen ejemplos claros y prácticos para enseñar estos conceptos.

## Conclusión

AccesDades no es una solución lista para producción. Es un conjunto de ejemplos educativos que explorar y experimentar. Cada fichero intenta responder una pregunta concreta sobre I/O, y juntos forman una colección que cubre desde lo más básico (leer/escribir bytes) hasta patrones más complejos (acceso aleatorio, serialización).

El código está pensado para ser leído, modificado y ejecutado como parte del aprendizaje. Cambiar tamaños de campo, formatos o añadir nuevos campos son experimentos valiosos para entender las limitaciones y decisiones que toman los programadores cuando trabajan con datos persistentes.

---

*AccesDades es un proyecto educativo. Los ejemplos priorizan claridad sobre eficiencia o mejor práctica industrial.*
