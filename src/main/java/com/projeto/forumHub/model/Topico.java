package com.projeto.forumHub.model;

import com.projeto.forumHub.dto.DadosAtualizacao;
import com.projeto.forumHub.dto.DadosTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Classe que representa a entidade "Tópico" no banco de dados
@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo; // Título do tópico

    private String mensagem; // Mensagem do tópico

    @Column(name = "dataDeCriacao") // Nome da coluna no banco de dados
    private LocalDate dataDeCriacao = LocalDate.now(); // Data de criação, padrão para a data atual

    private String nomeAutor; // Nome do autor do tópico

    // Construtor que inicializa o tópico com dados fornecidos
    public Topico(DadosTopico dados) {
        this.titulo = dados.titulo(); // Atribui o título
        this.mensagem = dados.mensagem(); // Atribui a mensagem
        this.dataDeCriacao = (dados.dataDeCriacao() != null) ? dados.dataDeCriacao() : LocalDate.now(); // Define a data de criação
        this.nomeAutor = dados.nomeAutor(); // Atribui o nome do autor
    }

    // Método para atualizar informações do tópico
    public void atualizarInformacoes(DadosAtualizacao dados) {
        if (dados.mensagem() != null) { // Verifica se a nova mensagem não é nula
            this.mensagem = dados.mensagem(); // Atualiza a mensagem
        }
    }

}