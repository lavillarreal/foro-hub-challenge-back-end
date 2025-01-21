package com.alura.forohub.repositories;

import com.alura.forohub.entities.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Métodos adicionales de consulta si son necesarios
    List<Topico> findByStatus(String status);
    List<Topico> findByCurso_Id(Long cursoId);
    List<Topico> findByAutor_Id(Long autorId);

}
