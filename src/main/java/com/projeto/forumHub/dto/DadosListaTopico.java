package com.projeto.forumHub.dto;

import com.projeto.forumHub.model.Topico;

import java.time.LocalDate;

// Classe de registro para listar tópicos
public record DadosListaTopico(String titulo, String mensagem, String nomeAutor, LocalDate dataDeCriacao) {

    public DadosListaTopico (Topico topico){
        // Chama o construtor principal com os dados do tópico
        this(   topico.getTitulo(),        // Título do tópico
                topico.getMensagem(),      // Mensagem do tópico
                topico.getNomeAutor(),     // Nome do autor do tópico
                topico.getDataDeCriacao()  // Data de criação do tópico
        );
    }
}