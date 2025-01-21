package com.alura.forohub.controllers;

import com.alura.forohub.entities.Topico;
import com.alura.forohub.entities.Usuario;
import com.alura.forohub.repositories.TopicoRepository;
import com.alura.forohub.services.SecurityAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private SecurityAgent securityAgent;

    // Crear un nuevo tópico (usuarios y administradores)
    @PostMapping
    public ResponseEntity<?> crearTopico(@RequestHeader("Authorization") String authorization,
                                         @RequestBody Topico topico) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(403).body("Usuario no autorizado");
        }

        String username = securityAgent.getUsernameFromToken(authorization.substring(7));
        Usuario autor = securityAgent.getDatabaseContext()
                                     .getUsuarioRepository()
                                     .findByCorreoElectronico(username);

        if (autor == null) {
            return ResponseEntity.status(403).body("Usuario no válido");
        }

        topico.setAutor(autor);
        topicoRepository.save(topico);
        return ResponseEntity.ok(topico);
    }

    // Obtener todos los tópicos
    @GetMapping
    public ResponseEntity<List<Topico>> obtenerTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    // Obtener un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@RequestHeader("Authorization") String authorization,
                                                @PathVariable Long id) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(403).body("Usuario no autorizado");
        }

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }

        Topico topico = topicoOptional.get();
        if (!esAutorOAdministrador(authorization, topico)) {
            return ResponseEntity.status(403).body("Acceso denegado");
        }

        return ResponseEntity.ok(topico);
    }

    // Actualizar un tópico (solo autor o administrador)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@RequestHeader("Authorization") String authorization,
                                              @PathVariable Long id,
                                              @RequestBody Topico topicoActualizado) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(403).body("Usuario no autorizado");
        }

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }

        Topico topico = topicoOptional.get();
        if (!esAutorOAdministrador(authorization, topico)) {
            return ResponseEntity.status(403).body("Acceso denegado");
        }

        topico.setTitulo(topicoActualizado.getTitulo());
        topico.setMensaje(topicoActualizado.getMensaje());
        topico.setStatus(topicoActualizado.getStatus());
        topicoRepository.save(topico);

        return ResponseEntity.ok(topico);
    }

    // Eliminar un tópico (solo autor o administrador)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@RequestHeader("Authorization") String authorization,
                                            @PathVariable Long id) {
        if (!securityAgent.isAuthorized(authorization)) {
            return ResponseEntity.status(403).body("Usuario no autorizado");
        }

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }

        Topico topico = topicoOptional.get();
        if (!esAutorOAdministrador(authorization, topico)) {
            return ResponseEntity.status(403).body("Acceso denegado");
        }

        topicoRepository.delete(topico);
        return ResponseEntity.ok("Tópico eliminado con éxito");
    }

    // Método auxiliar para verificar si es autor o administrador
    private boolean esAutorOAdministrador(String authorization, Topico topico) {
        if (securityAgent.isAdminUser(authorization)) {
            return true;
        }

        String username = securityAgent.getUsernameFromToken(authorization.substring(7));
        return username != null && topico.getAutor().getCorreoElectronico().equals(username);
    }
}
