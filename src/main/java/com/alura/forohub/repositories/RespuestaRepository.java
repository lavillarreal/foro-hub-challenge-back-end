package com.alura.forohub.repositories;

import com.alura.forohub.entities.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    // Buscar todas las respuestas asociadas a un tópico
    List<Respuesta> findByTopico_Id(Long topicoId);

    // Buscar todas las respuestas de un autor específico
    List<Respuesta> findByAutor_Id(Long autorId);

    // Buscar todas las respuestas marcadas como solución
    List<Respuesta> findBySolucionTrue();

    // Buscar todas las respuestas asociadas a un tópico y marcadas como solución
    List<Respuesta> findByTopico_IdAndSolucionTrue(Long topicoId);
}
