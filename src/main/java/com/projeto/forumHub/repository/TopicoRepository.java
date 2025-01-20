package com.projeto.forumHub.repository;

import com.projeto.forumHub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Interface que representa o repositório de tópicos
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Método para encontrar um tópico pelo título e nome do autor
    Optional<Topico> findByTituloAndNomeAutor(String titulo, String nomeAutor);

}