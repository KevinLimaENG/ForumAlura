package com.projeto.forumHub.repository;

import com.projeto.forumHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// Interface que representa o repositório de usuários
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para encontrar um usuário pelo login
    UserDetails login(String login);
}