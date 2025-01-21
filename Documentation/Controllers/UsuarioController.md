
# Documentación del Controlador UsuarioController

Este controlador maneja las operaciones CRUD relacionadas con los usuarios. Todas las rutas requieren autorización y son accesibles únicamente para usuarios administradores.

## Rutas y Ejemplos de Uso

### Obtener todos los usuarios

**Descripción:** Retorna una lista de todos los usuarios registrados.  
**Método:** `GET`  
**Endpoint:** `/api/usuarios`  

**Encabezados Requeridos:**  
```json
{
  "Authorization": "Bearer <token>"
}
```

**Respuesta Exitosa:**  
- **Código HTTP:** `200 OK`  
- **Cuerpo:**  
```json
[
  {
    "id": 1,
    "nombre": "Juan Perez",
    "correoElectronico": "juan.perez@example.com",
    "perfil": "Admin"
  }
]
```

**Errores:**  
- `401 Unauthorized` - Acceso no autorizado.

---

### Obtener un usuario por ID

**Descripción:** Retorna la información de un usuario específico.  
**Método:** `GET`  
**Endpoint:** `/api/usuarios/{id}`  

**Encabezados Requeridos:**  
```json
{
  "Authorization": "Bearer <token>"
}
```

**Respuesta Exitosa:**  
- **Código HTTP:** `200 OK`  
- **Cuerpo:**  
```json
{
  "id": 1,
  "nombre": "Juan Perez",
  "correoElectronico": "juan.perez@example.com",
  "perfil": "Admin"
}
```

**Errores:**  
- `401 Unauthorized` - Acceso no autorizado.  
- `404 Not Found` - Usuario no encontrado.

---

### Crear un nuevo usuario

**Descripción:** Crea un nuevo usuario en el sistema.  
**Método:** `POST`  
**Endpoint:** `/api/usuarios`  

**Encabezados Requeridos:**  
```json
{
  "Authorization": "Bearer <token>"
}
```

**Cuerpo de la Solicitud:**  
```json
{
  "nombre": "Maria Lopez",
  "correoElectronico": "maria.lopez@example.com",
  "contrasena": "password123",
  "perfil": "User"
}
```

**Respuesta Exitosa:**  
- **Código HTTP:** `201 Created`  
- **Cuerpo:** `"Usuario creado exitosamente."`

**Errores:**  
- `401 Unauthorized` - Acceso no autorizado.

---

### Actualizar un usuario existente

**Descripción:** Actualiza los datos de un usuario específico.  
**Método:** `PUT`  
**Endpoint:** `/api/usuarios/{id}`  

**Encabezados Requeridos:**  
```json
{
  "Authorization": "Bearer <token>"
}
```

**Cuerpo de la Solicitud:**  
```json
{
  "nombre": "Maria Lopez Actualizada",
  "correoElectronico": "maria.lopez@example.com",
  "contrasena": "nuevaPassword456",
  "perfil": "User"
}
```

**Respuesta Exitosa:**  
- **Código HTTP:** `200 OK`  
- **Cuerpo:** `"Usuario actualizado exitosamente."`

**Errores:**  
- `401 Unauthorized` - Acceso no autorizado.  
- `404 Not Found` - Usuario no encontrado.

---

### Eliminar un usuario

**Descripción:** Elimina un usuario específico del sistema.  
**Método:** `DELETE`  
**Endpoint:** `/api/usuarios/{id}`  

**Encabezados Requeridos:**  
```json
{
  "Authorization": "Bearer <token>"
}
```

**Respuesta Exitosa:**  
- **Código HTTP:** `200 OK`  
- **Cuerpo:** `"Usuario eliminado exitosamente."`

**Errores:**  
- `401 Unauthorized` - Acceso no autorizado.  
- `404 Not Found` - Usuario no encontrado.

## Notas

- Todos los endpoints requieren un token de autorización válido en el encabezado `Authorization`.
- El token debe ser emitido a un usuario administrador.

