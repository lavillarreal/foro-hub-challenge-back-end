
# Documentación de la Entidad `Usuario`

La clase `Usuario` representa la entidad de usuario en el sistema y se mapea a la tabla `Usuario` en la base de datos.

## Atributos

| Campo              | Tipo              | Descripción                                      |
|--------------------|-------------------|--------------------------------------------------|
| `id`               | `Long`           | Identificador único del usuario.                |
| `nombre`           | `String`         | Nombre del usuario.                             |
| `correoElectronico`| `String`         | Correo electrónico único del usuario.           |
| `contrasena`       | `String`         | Contraseña del usuario (almacenada en hash).     |
| `isAdmin`          | `boolean`        | Indica si el usuario tiene privilegios de admin.|
| `perfil`           | `Perfil`         | Relación con el perfil del usuario.             |

## Detalles de la Entidad

- **Tabla:** `Usuario`
- **Anotaciones:**  
  - `@Entity`: Define que esta clase es una entidad JPA.
  - `@Table(name = "Usuario")`: Especifica el nombre de la tabla asociada.
  - `@Id`: Indica la clave primaria.
  - `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Generación automática del ID.
  - `@Column`: Configuración de columnas de la tabla.

## Relación con otras Entidades

- **Perfil:** Relación de muchos a uno (`@ManyToOne`) con la entidad `Perfil`.  
  - `@JoinColumn(name = "perfiles", referencedColumnName = "id")`

## Ejemplo de Uso

### Ejemplo JSON de un Usuario

```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "correoElectronico": "juan.perez@correo.com",
  "contrasena": "hashed_password",
  "isAdmin": true,
  "perfil": {
    "id": 2,
    "nombre": "Administrador"
  }
}
```

### Ejemplo de Creación de un Usuario

```java
Usuario usuario = new Usuario();
usuario.setNombre("Juan Pérez");
usuario.setCorreoElectronico("juan.perez@correo.com");
usuario.setContrasena(hashPassword("123456"));
usuario.setIsAdmin(true);
usuario.setPerfil(perfil);
```

## Notas

- **Seguridad:** La contraseña debe ser almacenada de forma segura (ej., utilizando hashing).  
- **Correo Electrónico:** Debe ser único y válido.

