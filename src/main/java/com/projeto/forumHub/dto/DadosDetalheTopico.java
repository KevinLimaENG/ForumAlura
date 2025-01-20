package com.projeto.forumHub.dto;

import com.projeto.forumHub.model.Topico;

// Classe de registro para detalhes de um tópico
public record DadosDetalheTopico(Topico topico, String titulo, String mensagem, String nomeAutor, String dataDeCriacao) {

    // Construtor adicional que permite passar informações extras
    public DadosDetalheTopico(Topico topico, String extraInfo) {
        // Chama o construtor principal com os detalhes do tópico
        this(topico, topico.getTitulo(), topico.getMensagem(), topico.getNomeAutor(), topico.getDataDeCriacao().toString());
    }
}
