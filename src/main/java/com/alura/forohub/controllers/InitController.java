package com.alura.forohub.controllers;

import com.alura.forohub.entities.Usuario;
import com.alura.forohub.dto.UserBody;
import com.alura.forohub.entities.Perfil;
import com.alura.forohub.services.DatabaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private DatabaseContext databaseContext;

    @GetMapping
    public ResponseEntity<String> checkUsers() {
        long userCount = databaseContext.getUsuarioRepository().count();
        if (userCount == 0) {
            return ResponseEntity.ok("No users found. Ready to create an admin user.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Users already exist. Initialization not allowed.");
        }
    }

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

    // Utility method to hash passwords
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}

