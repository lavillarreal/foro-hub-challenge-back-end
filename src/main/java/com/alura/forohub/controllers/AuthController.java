package com.alura.forohub.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alura.forohub.services.SecurityAgent;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    SecurityAgent securityAgent = new SecurityAgent();
    // GET: Verificar usuario y contraseña
    @GetMapping
    public ResponseEntity<String> handleGet(@RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.ok("Credentials are valid");
    }

    // POST: Generar un token JWT
    @PostMapping
    public ResponseEntity<String> handlePost(@RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String jwtToken = securityAgent.generateJwtToken("username");
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtToken);
    }

    // Otros métodos: Responder con Method Not Allowed
    @RequestMapping
    public ResponseEntity<Void> handleOthers() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    // Métodos de utilidad

    // Verifica las credenciales del usuario

}
