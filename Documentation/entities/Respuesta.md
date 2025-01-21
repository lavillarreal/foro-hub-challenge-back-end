
# Documentación de la entidad `Respuesta`

La clase **`Respuesta`** representa una entidad JPA que modela las respuestas asociadas a tópicos en el sistema.

## Detalles de la tabla

- **Nombre de la tabla**: `Respuesta`

## Atributos

| Nombre         | Tipo                | Descripción                                                          | Restricciones           |
|----------------|---------------------|----------------------------------------------------------------------|-------------------------|
| `id`           | `Long`             | Identificador único de la respuesta.                                | Auto-generado, Primary Key |
| `mensaje`      | `String`           | Contenido de la respuesta.                                           | No nulo, tipo `TEXT`        |
| `topico`       | `Topico`           | Tópico asociado a la respuesta.                                      | Relación `@ManyToOne`       |
| `fechaCreacion`| `LocalDateTime`    | Fecha y hora de creación de la respuesta.                            | No nulo, valor por defecto: fecha y hora actual |
| `autor`        | `Usuario`          | Autor de la respuesta.                                               | Relación `@ManyToOne`       |
| `solucion`     | `Boolean`          | Indica si esta respuesta es la solución al tópico.                   | No nulo, por defecto: `false`|

## Relación con otras entidades

- **`Topico`**: Relación `@ManyToOne` con la entidad `Topico`. La respuesta pertenece a un único tópico.
- **`Usuario`**: Relación `@ManyToOne` con la entidad `Usuario`. Indica quién es el autor de la respuesta.

## Código de la clase

```java
@Entity
@Table(name = "Respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico", referencedColumnName = "id")
    private Topico topico;

    @Column(name = "fechacreacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;

    @Column(name = "solucion", nullable = false)
    private Boolean solucion = false;

    // Getters y Setters
}
```

## Ejemplo de instancia en JSON

```json
{
    "mensaje": "Esta es una respuesta al tópico.",
    "topico": {
        "id": 1
    },
    "autor": {
        "id": 2
    },
    "solucion": false
}
```

## Notas

- La fecha de creación (`fechaCreacion`) se establece automáticamente en el momento de crear la entidad.
- La columna `solucion` permite identificar si esta respuesta resuelve el problema planteado en el tópico.
