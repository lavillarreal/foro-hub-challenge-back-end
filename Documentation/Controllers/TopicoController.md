
# Documentación de la API - TopicoController

Este controlador gestiona los tópicos del sistema, permitiendo a los usuarios y administradores crear, consultar, actualizar y eliminar tópicos.

## Endpoints

### 1. Crear un nuevo tópico
**POST** `/api/topicos`

#### Descripción:
Crea un nuevo tópico. Solo usuarios autorizados pueden realizar esta acción.

#### Headers:
```json
{
  "Authorization": "Bearer <token>"
}
```

#### Body (JSON):
```json
{
  "titulo": "Título del tópico",
  "mensaje": "Mensaje del tópico",
  "status": "ABIERTO"
}
```

#### Respuesta exitosa:
**Código:** 200 OK  
**Cuerpo:**
```json
{
  "id": 1,
  "titulo": "Título del tópico",
  "mensaje": "Mensaje del tópico",
  "status": "ABIERTO",
  "autor": {
    "id": 10,
    "nombre": "Usuario Autor"
  }
}
```

#### Respuesta de error:
- **403 Forbidden:** Usuario no autorizado.
- **400 Bad Request:** Error en el cuerpo de la solicitud.

---

### 2. Obtener todos los tópicos
**GET** `/api/topicos`

#### Descripción:
Obtiene una lista de todos los tópicos disponibles.

#### Respuesta exitosa:
**Código:** 200 OK  
**Cuerpo:**
```json
[
  {
    "id": 1,
    "titulo": "Primer tópico",
    "mensaje": "Mensaje del primer tópico",
    "status": "ABIERTO"
  },
  {
    "id": 2,
    "titulo": "Segundo tópico",
    "mensaje": "Mensaje del segundo tópico",
    "status": "CERRADO"
  }
]
```

---

### 3. Obtener un tópico por ID
**GET** `/api/topicos/{id}`

#### Descripción:
Obtiene los detalles de un tópico específico por su ID. Solo accesible para el autor del tópico o administradores.

#### Headers:
```json
{
  "Authorization": "Bearer <token>"
}
```

#### Respuesta exitosa:
**Código:** 200 OK  
**Cuerpo:**
```json
{
  "id": 1,
  "titulo": "Primer tópico",
  "mensaje": "Mensaje del primer tópico",
  "status": "ABIERTO",
  "autor": {
    "id": 10,
    "nombre": "Usuario Autor"
  }
}
```

#### Respuesta de error:
- **403 Forbidden:** Usuario no autorizado.
- **404 Not Found:** Tópico no encontrado.

---

### 4. Actualizar un tópico
**PUT** `/api/topicos/{id}`

#### Descripción:
Actualiza un tópico existente. Solo el autor o un administrador pueden realizar esta acción.

#### Headers:
```json
{
  "Authorization": "Bearer <token>"
}
```

#### Body (JSON):
```json
{
  "titulo": "Nuevo título",
  "mensaje": "Nuevo mensaje",
  "status": "CERRADO"
}
```

#### Respuesta exitosa:
**Código:** 200 OK  
**Cuerpo:**
```json
{
  "id": 1,
  "titulo": "Nuevo título",
  "mensaje": "Nuevo mensaje",
  "status": "CERRADO"
}
```

#### Respuesta de error:
- **403 Forbidden:** Usuario no autorizado o no es el autor.
- **404 Not Found:** Tópico no encontrado.

---

### 5. Eliminar un tópico
**DELETE** `/api/topicos/{id}`

#### Descripción:
Elimina un tópico existente. Solo el autor o un administrador pueden realizar esta acción.

#### Headers:
```json
{
  "Authorization": "Bearer <token>"
}
```

#### Respuesta exitosa:
**Código:** 200 OK  
**Cuerpo:**
```json
"Tópico eliminado con éxito"
```

#### Respuesta de error:
- **403 Forbidden:** Usuario no autorizado o no es el autor.
- **404 Not Found:** Tópico no encontrado.

---

### Notas:
- El token de autorización debe ser válido y contener los permisos adecuados para cada operación.
- Los códigos de error están diseñados para reflejar las condiciones de autorización o existencia del recurso.

