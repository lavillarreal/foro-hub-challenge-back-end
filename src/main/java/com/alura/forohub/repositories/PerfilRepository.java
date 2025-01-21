package com.alura.forohub.repositories;

import com.alura.forohub.entities.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // Métodos adicionales de consulta si son necesarios
    Optional<Perfil> findByNombre(String nombre);

}
