package br.com.vanzele.lista_de_tarefas.repository;

import br.com.vanzele.lista_de_tarefas.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
}
