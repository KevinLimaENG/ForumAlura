package com.projeto.forumHub.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

// Classe de registro para representar os dados de um tópico
public record DadosTopico(

        @NotBlank // Validação para garantir que o título não seja vazio
        String titulo,

        @NotBlank // Validação para garantir que a mensagem não seja vazia
        String mensagem,
        LocalDate dataDeCriacao,  // Data de criação do tópico (opcional)

        @NotBlank // Validação para garantir que o nome do autor não seja vazio
        String nomeAutor,

        @NotBlank // Validação para garantir que o email do autor não seja vazio
        @Email // Validação para garantir que o email esteja em um formato válido
        String emailAutor

        ) {

}