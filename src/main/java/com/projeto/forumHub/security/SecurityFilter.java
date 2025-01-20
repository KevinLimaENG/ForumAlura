package com.projeto.forumHub.security;

import com.projeto.forumHub.repository.UsuarioRepository;
import com.projeto.forumHub.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Indica que esta classe é um componente gerenciado pelo Spring
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService; // Injeção do serviço de token

    @Autowired
    private UsuarioRepository repository; // Injeção do repositório de usuários

    // Método que realiza a filtragem das requisições
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        var tokenGerado = recuperToken(request); // Recupera o token da requisição

        if (tokenGerado != null) { // Verifica se o token existe
            var subject = tokenService.getSubject(tokenGerado); // Obtém o usuário do token
            var usuario = repository.login(subject); // Busca o usuário no repositório
            // Cria um objeto de autenticação, considerando o usuário como logado
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); // Define a autenticação no contexto de segurança
        }

        // Continua com a cadeia de filtros
        filterChain.doFilter(request, response);
    }

    // Método para recuperar o token do cabeçalho da requisição
    private String recuperToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}

