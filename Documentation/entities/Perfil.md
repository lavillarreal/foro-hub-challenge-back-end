
# Documentación de la clase Perfil

## Descripción

La clase `Perfil` representa la entidad de un perfil en el sistema, con un identificador único y un nombre asociado. Está mapeada a la tabla `Perfil` en la base de datos.

---

## Detalles de la entidad

- **Nombre de la tabla:** `Perfil`

### Atributos

| Atributo | Tipo   | Descripción                        | Restricciones             |
|----------|--------|------------------------------------|---------------------------|
| `id`     | `Long` | Identificador único del perfil.    | Generado automáticamente. |
| `nombre` | `String` | Nombre del perfil.               | No nulo.                  |

---

## Anotaciones de JPA

- `@Entity`: Marca esta clase como una entidad JPA.
- `@Table(name = "Perfil")`: Define el nombre de la tabla en la base de datos asociada a esta entidad.
- `@Id`: Especifica el campo `id` como clave primaria.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Indica que el valor de `id` se genera automáticamente.
- `@Column(name = "nombre", nullable = false)`: Configura la columna `nombre` como no nula.

---

## Métodos

### Getters y Setters

- `getId()`: Retorna el identificador del perfil.
- `setId(Long id)`: Asigna el identificador del perfil.
- `getNombre()`: Retorna el nombre del perfil.
- `setNombre(String nombre)`: Asigna el nombre del perfil.

---

## Ejemplo de instancia

```java
// Crear una instancia de Perfil
Perfil perfil = new Perfil();
perfil.setId(1L);
perfil.setNombre("Administrador");
```

---

## JSON de ejemplo

### Crear un nuevo perfil

```json
{
    "nombre": "Usuario"
}
```

### Respuesta del servidor al consultar un perfil

```json
{
    "id": 1,
    "nombre": "Administrador"
}
```

---

## Notas adicionales

- El campo `nombre` es obligatorio y debe ser único si se implementan restricciones adicionales en la base de datos.
- Esta entidad puede relacionarse con otras entidades como `Usuario` en una relación de uno a muchos.

---

## Relación con la base de datos

Esta entidad se mapea directamente a la tabla `Perfil` en la base de datos. Es importante asegurarse de que la base de datos esté configurada correctamente para soportar estas entidades.

