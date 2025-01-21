
# Documentación de TestController

Este controlador se utiliza para probar diferentes métodos HTTP y sus respectivas respuestas.

## Endpoints

### GET `/api/test`
**Descripción:** Devuelve una respuesta HTML con un mensaje de estado.  
**Respuesta:**  
- Código de estado: `200 OK`
- Cuerpo:
  ```html
  <html><body><h1>status: ok -> PUSASA</h1></body></html>
  ```

### POST `/api/test`
**Descripción:** Indica que se ha creado un recurso.  
**Respuesta:**  
- Código de estado: `201 Created`  
- Cuerpo: vacío

### PUT `/api/test`
**Descripción:** Indica que la solicitud se procesó correctamente.  
**Respuesta:**  
- Código de estado: `200 OK`  
- Cuerpo: vacío

### DELETE `/api/test`
**Descripción:** Indica que el recurso fue eliminado correctamente.  
**Respuesta:**  
- Código de estado: `204 No Content`  
- Cuerpo: vacío

### PATCH `/api/test`
**Descripción:** Indica que se realizó una actualización parcial correctamente.  
**Respuesta:**  
- Código de estado: `200 OK`  
- Cuerpo: vacío

### HEAD `/api/test`
**Descripción:** Devuelve solo los encabezados HTTP sin cuerpo de respuesta.  
**Respuesta:**  
- Código de estado: `200 OK`  
- Cuerpo: vacío

### OPTIONS `/api/test`
**Descripción:** Devuelve las opciones HTTP permitidas para este endpoint.  
**Respuesta:**  
- Código de estado: `200 OK`  
- Cuerpo: vacío

### Otros métodos no soportados
**Descripción:** Devuelve un error indicando que el método no está permitido.  
**Respuesta:**  
- Código de estado: `405 Method Not Allowed`  
- Cuerpo: vacío

## Ejemplo de uso

### Solicitud GET
**Curl:**  
```bash
curl -X GET http://localhost:8080/api/test
```  
**Respuesta:**  
Código de estado: `200 OK`  
Cuerpo:
```html
<html><body><h1>status: ok -> PUSASA</h1></body></html>
```

### Solicitud POST
**Curl:**  
```bash
curl -X POST http://localhost:8080/api/test
```  
**Respuesta:**  
Código de estado: `201 Created`  
Cuerpo: vacío
