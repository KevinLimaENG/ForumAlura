package com.projeto.forumHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Classe que representa a entidade "Usuario" no banco de dados
@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID do usuário
    private String login; // Nome de login do usuário
    private String senha; // Senha do usuário

    // Método para retornar as autoridades do usuário
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna uma lista de autoridades, aqui um usuário comum
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // Método que retorna a senha do usuário
    @Override
    public String getPassword() {
        return senha;
    }

    // Método que retorna o nome de login do usuário
    @Override
    public String getUsername() {
        return login;
    }

    // Método que verifica se a conta não expirou
    @Override
    public boolean isAccountNonExpired() {
        return true; // Sempre retorna true, pois não temos controle sobre a expiração
    }

    // Método que verifica se a conta não está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true; // Sempre retorna true, sem lógica de bloqueio implementada
    }

    // Método que verifica se as credenciais não expiraram
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Sempre retorna true, sem controle de expiração
    }

    // Método que verifica se a conta está habilitada
    @Override
    public boolean isEnabled() {
        return true; // Sempre retorna true, a conta está habilitada
    }
}

