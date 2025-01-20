package com.projeto.forumHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Classe de registro para dados de atualização
public record DadosAtualizacao(

        @NotNull // Indica que o ID não pode ser nulo
        Long id,
        String emailAutor, // Email do autor (opcional)

        @NotBlank // Indica que a mensagem não pode ser nula ou vazia
        String mensagem

        ) {

}
