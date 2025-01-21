
# Documentación del API: CursoController

Este controlador permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre la entidad **Curso**. 
Requiere autenticación mediante un encabezado `Authorization`.

## Endpoints

### Obtener todos los cursos

**GET** `/api/cursos`

#### Encabezados requeridos:

- `Authorization`: Token JWT o credenciales básicas.

#### Respuestas:
- **200 OK**: Devuelve una lista de todos los cursos.
- **401 Unauthorized**: Usuario no autorizado.

#### Ejemplo de respuesta exitosa:
```json
[
    {
        "id": 1,
        "nombre": "Curso de Java",
        "descripcion": "Aprende Java desde cero."
    },
    {
        "id": 2,
        "nombre": "Curso de Spring Boot",
        "descripcion": "Construye aplicaciones con Spring Boot."
    }
]
```

---

### Obtener un curso por ID

**GET** `/api/cursos/{id}`

#### Parámetros de ruta:
- `id` (Long): ID del curso a buscar.

#### Encabezados requeridos:
- `Authorization`: Token JWT o credenciales básicas.

#### Respuestas:
- **200 OK**: Devuelve los detalles del curso.
- **404 Not Found**: El curso no existe.
- **401 Unauthorized**: Usuario no autorizado.

#### Ejemplo de respuesta exitosa:
```json
{
    "id": 1,
    "nombre": "Curso de Java",
    "descripcion": "Aprende Java desde cero."
}
```

---

### Crear un nuevo curso

**POST** `/api/cursos`

#### Encabezados requeridos:
- `Authorization`: Token JWT o credenciales básicas.

#### Cuerpo de la solicitud:
```json
{
    "nombre": "Curso de Python",
    "descripcion": "Aprende Python paso a paso."
}
```

#### Respuestas:
- **201 Created**: Curso creado exitosamente.
- **401 Unauthorized**: Usuario no autorizado.

#### Ejemplo de respuesta exitosa:
```json
"Curso creado exitosamente."
```

---

### Actualizar un curso existente

**PUT** `/api/cursos/{id}`

#### Parámetros de ruta:
- `id` (Long): ID del curso a actualizar.

#### Encabezados requeridos:
- `Authorization`: Token JWT o credenciales básicas.

#### Cuerpo de la solicitud:
```json
{
    "nombre": "Curso de Java Avanzado",
    "descripcion": "Profundiza tus conocimientos en Java."
}
```

#### Respuestas:
- **200 OK**: Curso actualizado exitosamente.
- **404 Not Found**: El curso no existe.
- **401 Unauthorized**: Usuario no autorizado.

#### Ejemplo de respuesta exitosa:
```json
"Curso actualizado exitosamente."
```

---

### Eliminar un curso

**DELETE** `/api/cursos/{id}`

#### Parámetros de ruta:
- `id` (Long): ID del curso a eliminar.

#### Encabezados requeridos:
- `Authorization`: Token JWT o credenciales básicas.

#### Respuestas:
- **200 OK**: Curso eliminado exitosamente.
- **404 Not Found**: El curso no existe.
- **401 Unauthorized**: Usuario no autorizado.

#### Ejemplo de respuesta exitosa:
```json
"Curso eliminado exitosamente."
```

---

## Notas
- Todos los endpoints requieren un encabezado `Authorization` válido.
- Las operaciones están protegidas para garantizar que solo usuarios autorizados puedan realizar cambios.
