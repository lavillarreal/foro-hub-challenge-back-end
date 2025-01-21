
# Documentación de la clase `UserBody`

La clase `UserBody` es un DTO (Data Transfer Object) utilizado para representar los datos de un usuario extraídos de una solicitud HTTP en el sistema. Se utiliza, por ejemplo, al crear un usuario administrador inicial.

## Propiedades

| Nombre             | Tipo      | Descripción                             |
|--------------------|-----------|-----------------------------------------|
| `nombre`           | `String`  | Nombre del usuario.                     |
| `correoElectronico`| `String`  | Dirección de correo electrónico única del usuario. |
| `contrasena`       | `String`  | Contraseña del usuario en formato de texto plano antes del hash. |
| `isAdmin`          | `boolean` | Indica si el usuario tiene privilegios de administrador. |

## Métodos

### Getters y Setters

| Método               | Descripción                                      |
|----------------------|--------------------------------------------------|
| `getNombre()`        | Obtiene el nombre del usuario.                   |
| `setNombre(String)`  | Establece el nombre del usuario.                 |
| `getCorreoElectronico()` | Obtiene el correo electrónico del usuario.       |
| `setCorreoElectronico(String)` | Establece el correo electrónico del usuario. |
| `getContrasena()`    | Obtiene la contraseña del usuario.               |
| `setContrasena(String)` | Establece la contraseña del usuario.             |
| `isAdmin()`          | Verifica si el usuario tiene privilegios de administrador. |
| `setAdmin(boolean)`  | Establece si el usuario es administrador.        |

## Ejemplo de Uso

### Ejemplo en JSON para crear un usuario administrador

```json
{
  "nombre": "Juan Pérez",
  "correoElectronico": "juan.perez@example.com",
  "contrasena": "mi_contrasena_segura",
  "isAdmin": true
}
```

### Uso en el controlador `InitController`

El siguiente ejemplo muestra cómo se utiliza la clase `UserBody` en el método `createAdminUser` del controlador:

```java
@PostMapping
public ResponseEntity<String> createAdminUser(@RequestBody UserBody request) {
    if (databaseContext.getUsuarioRepository().count() > 0) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Users already exist. Cannot create initial user.");
    }

    try {
        // Hash the password
        String hashedPassword = hashPassword(request.getContrasena());

        // Create a new Perfil (if needed)
        Perfil perfil = new Perfil();
        perfil.setNombre(request.isAdmin() ? "Admin" : "User");
        databaseContext.savePerfil(perfil);

        // Create the Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setContrasena(hashedPassword);
        usuario.setPerfil(perfil);

        databaseContext.saveUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Admin user created successfully.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
    }
}
```

Este método utiliza el objeto `UserBody` para recibir y procesar los datos de la solicitud HTTP al crear un usuario administrador.

---

## Notas

- Asegúrese de que los campos enviados en el cuerpo de la solicitud cumplan con las validaciones necesarias antes de procesarlos.
- La contraseña debe ser almacenada de manera segura (por ejemplo, usando un hash).

