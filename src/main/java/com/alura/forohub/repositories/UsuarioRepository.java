package com.alura.forohub.repositories;

import com.alura.forohub.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar un usuario por correo electrónico (único)
    Usuario findByCorreoElectronico(String correoElectronico);

    // Buscar usuarios por nombre (puede haber más de uno con el mismo nombre)
    List<Usuario> findByNombre(String nombre);

    // Buscar usuarios cuyo nombre contenga un fragmento (búsqueda parcial)
    List<Usuario> findByNombreContaining(String fragmento);

    // Buscar usuarios asociados a un perfil específico
    List<Usuario> findByPerfil_Id(Long perfilId);

    // Buscar usuarios por nombre y perfil
    List<Usuario> findByNombreAndPerfil_Id(String nombre, Long perfilId);

    // Buscar usuarios con correos electrónicos que terminen con un dominio específico
    List<Usuario> findByCorreoElectronicoEndingWith(String dominio);

    // Buscar todos los usuarios ordenados por nombre (ascendente)
    List<Usuario> findAllByOrderByNombreAsc();

    // Buscar todos los usuarios ordenados por nombre (descendente)
    List<Usuario> findAllByOrderByNombreDesc();
}
