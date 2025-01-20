package com.projeto.forumHub.controller;

import com.projeto.forumHub.dto.DadosAutenticacao;
import com.projeto.forumHub.dto.DadosToken;
import com.projeto.forumHub.model.Usuario;
import com.projeto.forumHub.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login") // Mapeia as requisições para o endpoint "/login"

public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    // Método para efetuar login, aceitando um POST com os dados de autenticação
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        // Cria um token de autenticação usando as credenciais fornecidas (login e senha)
        var autenticacaoToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // Tenta autenticar as credenciais usando o AuthenticationManager
        var autenticacao = manager.authenticate(autenticacaoToken);

        // Gera um token JWT usando as informações do usuário autenticado
        var tokenGerado = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        // Retorna uma resposta HTTP 200 OK com o token gerado no corpo da resposta
        return ResponseEntity.ok(new DadosToken(tokenGerado));
    }
}
