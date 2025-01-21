package com.alura.forohub.controllers;

import com.alura.forohub.entities.Curso;
import com.alura.forohub.services.DatabaseContext;
import com.alura.forohub.services.SecurityAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private DatabaseContext databaseContext;

    @Autowired
    private SecurityAgent securityAgent;

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<?> getAllCursos(@RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado.");
        }
        return ResponseEntity.ok(databaseContext.getCursoRepository().findAll());
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado.");
        }
        Optional<Curso> curso = databaseContext.getCursoRepository().findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado.");
        }
    }

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<?> createCurso(@RequestBody Curso curso, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado.");
        }
        databaseContext.saveCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body("Curso creado exitosamente.");
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @RequestBody Curso curso, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado.");
        }
        Optional<Curso> existingCurso = databaseContext.getCursoRepository().findById(id);
        if (existingCurso.isPresent()) {
            curso.setId(id); // Asegurarse de mantener el mismo ID
            databaseContext.saveCurso(curso);
            return ResponseEntity.ok("Curso actualizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado.");
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id, @RequestHeader("Authorization") String authorization) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autorizado.");
        }
        Optional<Curso> curso = databaseContext.getCursoRepository().findById(id);
        if (curso.isPresent()) {
            databaseContext.getCursoRepository().deleteById(id);
            return ResponseEntity.ok("Curso eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado.");
        }
    }
}

