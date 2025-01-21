
# Documentación de la API: InitController

## Descripción
El controlador `InitController` proporciona una API para inicializar la aplicación creando un usuario administrador. Este controlador se utiliza principalmente cuando no existen usuarios en el sistema.

## Endpoints

### Verificar si hay usuarios existentes
**GET** `/init`

#### Respuesta exitosa
Si no existen usuarios en el sistema:
- **Código de estado:** 200 OK
- **Cuerpo de respuesta:**  
```json
"No users found. Ready to create an admin user."
```

#### Respuesta en caso de usuarios existentes
- **Código de estado:** 403 FORBIDDEN
- **Cuerpo de respuesta:**  
```json
"Users already exist. Initialization not allowed."
```

---

### Crear un usuario administrador
**POST** `/init`

#### Cuerpo de la solicitud
Debe enviarse un JSON con los siguientes campos:
```json
{
  "nombre": "Nombre del usuario",
  "correoElectronico": "correo@example.com",
  "contrasena": "contraseñaSegura",
  "admin": true
}
```

#### Respuesta exitosa
Si se crea el usuario administrador con éxito:
- **Código de estado:** 201 CREATED
- **Cuerpo de respuesta:**  
```json
"Admin user created successfully."
```

#### Respuesta en caso de error
Si ya existen usuarios en el sistema:
- **Código de estado:** 403 FORBIDDEN
- **Cuerpo de respuesta:**  
```json
"Users already exist. Cannot create initial user."
```

Si ocurre un error interno al crear el usuario:
- **Código de estado:** 500 INTERNAL SERVER ERROR
- **Cuerpo de respuesta:**  
```json
"Error creating user: <mensaje del error>"
```

## Notas adicionales
- Este controlador utiliza un método privado para realizar el hashing de contraseñas utilizando el algoritmo SHA-256.
- Solo debe usarse para inicializar el sistema cuando no existen usuarios registrados.

