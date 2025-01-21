
# Documentación de la Entidad `Topico`

La clase `Topico` representa los temas de discusión dentro del sistema, vinculados a un autor, un curso y posibles respuestas.

## Estructura de la Tabla

- **Nombre de la tabla**: `Topico`
- **Columnas**:
  - `id` (Long): Identificador único del tópico. Generado automáticamente.
  - `titulo` (String): Título del tópico. Obligatorio.
  - `mensaje` (String): Mensaje principal del tópico. Obligatorio, permite texto largo.
  - `fechacreacion` (LocalDateTime): Fecha de creación del tópico. Obligatorio, se inicializa con la fecha actual.
  - `status` (String): Estado del tópico. Obligatorio.
  - `autor` (Usuario): Autor del tópico. Relación `@ManyToOne` con la entidad `Usuario`.
  - `curso` (Curso): Curso al que pertenece el tópico. Relación `@ManyToOne` con la entidad `Curso`.
  - `respuestas` (List<Respuesta>): Lista de respuestas asociadas al tópico. Relación `@OneToMany`.

## Relaciones

- **Autor**: Un tópico está asociado a un único autor (usuario).
- **Curso**: Un tópico pertenece a un único curso.
- **Respuestas**: Un tópico puede tener múltiples respuestas.

## Ejemplo de JSON para un Tópico

### Crear un Tópico
```json
{
  "titulo": "Dudas sobre Spring Boot",
  "mensaje": "¿Cómo puedo implementar seguridad en una API REST?",
  "status": "Abierto",
  "autor": {
    "id": 1
  },
  "curso": {
    "id": 101
  }
}
```

### Respuesta JSON al Obtener un Tópico
```json
{
  "id": 10,
  "titulo": "Dudas sobre Spring Boot",
  "mensaje": "¿Cómo puedo implementar seguridad en una API REST?",
  "fechacreacion": "2025-01-20T14:55:00",
  "status": "Abierto",
  "autor": {
    "id": 1,
    "nombre": "Juan Pérez"
  },
  "curso": {
    "id": 101,
    "nombre": "Desarrollo Web con Spring Boot"
  },
  "respuestas": [
    {
      "id": 1,
      "mensaje": "Puedes usar Spring Security para manejar la autenticación y autorización.",
      "fechacreacion": "2025-01-21T10:00:00",
      "autor": {
        "id": 2,
        "nombre": "María López"
      },
      "solucion": false
    }
  ]
}
```

## Código de la Clase

```java
@Entity
@Table(name = "Topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fechacreacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso", referencedColumnName = "id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;
}
```

## Notas

- Asegúrate de inicializar correctamente las relaciones al crear instancias de la clase `Topico`.
- Valida los campos obligatorios (`titulo`, `mensaje`, `status`) antes de guardar un tópico en la base de datos.
