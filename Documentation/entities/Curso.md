
# Documentación de la entidad `Curso`

La clase `Curso` representa la entidad de un curso en el sistema. Está mapeada a la tabla `Curso` en la base de datos.

## Atributos

| Atributo   | Tipo    | Descripción                            | Restricciones          |
|------------|---------|----------------------------------------|------------------------|
| `id`       | `Long`  | Identificador único del curso.         | Autogenerado, único.   |
| `nombre`   | `String`| Nombre del curso.                     | No nulo.               |
| `categoria`| `String`| Categoría del curso.                  | No nulo.               |

## Anotaciones JPA

- `@Entity`: Indica que la clase es una entidad JPA.
- `@Table(name = "Curso")`: Especifica el nombre de la tabla en la base de datos.
- `@Id`: Declara el atributo `id` como la clave primaria.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Indica que el valor de `id` es generado automáticamente por la base de datos.
- `@Column`: Especifica mapeos de columna en la base de datos para los atributos.

## Ejemplo de Representación JSON

```json
{
  "id": 1,
  "nombre": "Introducción a Java",
  "categoria": "Programación"
}
```

## Operaciones CRUD Asociadas

### Crear un nuevo curso

**Endpoint:** `POST /api/cursos`  
**Cuerpo de la solicitud:**

```json
{
  "nombre": "Introducción a Python",
  "categoria": "Programación"
}
```

**Respuesta Exitosa:**

```json
{
  "id": 2,
  "nombre": "Introducción a Python",
  "categoria": "Programación"
}
```

### Obtener todos los cursos

**Endpoint:** `GET /api/cursos`  
**Respuesta Exitosa:**

```json
[
  {
    "id": 1,
    "nombre": "Introducción a Java",
    "categoria": "Programación"
  },
  {
    "id": 2,
    "nombre": "Introducción a Python",
    "categoria": "Programación"
  }
]
```

### Actualizar un curso

**Endpoint:** `PUT /api/cursos/{id}`  
**Cuerpo de la solicitud:**

```json
{
  "nombre": "Java Avanzado",
  "categoria": "Programación"
}
```

**Respuesta Exitosa:**

```json
{
  "id": 1,
  "nombre": "Java Avanzado",
  "categoria": "Programación"
}
```

### Eliminar un curso

**Endpoint:** `DELETE /api/cursos/{id}`  
**Respuesta Exitosa:**

```json
{
  "mensaje": "Curso eliminado exitosamente."
}
```

## Notas

- Asegúrese de que los valores `nombre` y `categoria` no estén vacíos al crear o actualizar un curso.
