package com.alura.forohub.repositories;

import com.alura.forohub.entities.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Métodos adicionales de consulta si son necesarios
    List<Curso> findByCategoria(String categoria);
    Optional<Curso> findByNombre(String nombre);
}
