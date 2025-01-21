package com.alura.forohub.controllers;

import com.alura.forohub.entities.Usuario;
import com.alura.forohub.services.DatabaseContext;
import com.alura.forohub.services.SecurityAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private DatabaseContext databaseContext;

    @Autowired
    private SecurityAgent securityAgent;

    // Obtener todos los usuarios (Solo administradores)
    @GetMapping
    public ResponseEntity<?> getAllUsuarios(@RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization) || !isAdmin(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado.");
        }
        return ResponseEntity.ok(databaseContext.getUsuarioRepository().findAll());
    }

    // Obtener un usuario por ID (Solo administradores)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization) || !isAdmin(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado.");
        }
        Optional<Usuario> usuario = databaseContext.getUsuarioRepository().findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    // Crear un nuevo usuario (Solo administradores)
    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization) || !isAdmin(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado.");
        }
        usuario.setContrasena(securityAgent.hashPassword(usuario.getContrasena())); // Hash de la contraseña
        databaseContext.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente.");
    }

    // Actualizar un usuario existente (Solo administradores)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization) || !isAdmin(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado.");
        }
        Optional<Usuario> existingUsuario = databaseContext.getUsuarioRepository().findById(id);
        if (existingUsuario.isPresent()) {
            usuario.setId(id); // Mantener el ID del usuario
            usuario.setContrasena(securityAgent.hashPassword(usuario.getContrasena())); // Hash de la contraseña actualizada
            databaseContext.saveUsuario(usuario);
            return ResponseEntity.ok("Usuario actualizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    // Eliminar un usuario (Solo administradores)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization) || !isAdmin(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado.");
        }
        Optional<Usuario> usuario = databaseContext.getUsuarioRepository().findById(id);
        if (usuario.isPresent()) {
            databaseContext.getUsuarioRepository().deleteById(id);
            return ResponseEntity.ok("Usuario eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    // Verificar si el usuario es administrador
    private boolean isAdmin(String authorization) {
        if (securityAgent.isAdminUser(authorization)) {
            return true;
        }
        return false;
    }
}