package com.projeto.forumHub.controller;

import com.projeto.forumHub.dto.DadosAtualizacao;
import com.projeto.forumHub.dto.DadosDetalheTopico;
import com.projeto.forumHub.dto.DadosListaTopico;
import com.projeto.forumHub.dto.DadosTopico;
import com.projeto.forumHub.model.Topico;
import com.projeto.forumHub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping ("/topicos")

public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping

    public ResponseEntity registrartopico(@RequestBody @Valid DadosTopico dados, UriComponentsBuilder uriBuilder ) {

        // Verifica se já existe um tópico com o mesmo título e autor
        Optional<Topico> topicoExistente = repository.findByTituloAndNomeAutor(dados.titulo(), dados.nomeAutor());
        if (topicoExistente.isPresent()) {
            // Se existir, retorna um erro 400 Bad Request com detalhes
            return new ResponseEntity<> (new DadosDetalheTopico (topicoExistente.get(), "Tópico já existente."), HttpStatus.BAD_REQUEST);
        }

        // Cria um novo objeto Topico com os dados fornecidos
        var topico = new Topico(dados);

        // Salva o novo tópico no repositório
        repository.save(topico);

        // Cria a URI do novo tópico usando o ID gerado
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        // Retorna a URI do novo tópico com um status 201 Created
        return ResponseEntity.created(uri).body(new DadosDetalheTopico(topico, "Tópico criado com sucesso!"));
    }

    @GetMapping
    public Page <DadosListaTopico> listarTopicos(
            @PageableDefault(size = 10, sort = {"dataDeCriacao"}) Pageable paginacao) {
        // Retorna uma página de tópicos com a configuração de paginação aplicada
        return repository.findAll(paginacao).map(DadosListaTopico::new);
    }


    @GetMapping("/{id}")
    public ResponseEntity <DadosDetalheTopico> detalharTopico (@PathVariable Long id) {
        // Busca o tópico pelo ID fornecido
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            // Se o tópico existir, retorna os detalhes do tópico
            var topico = topicoOptional.get();
            return ResponseEntity.ok(new DadosDetalheTopico (topico, "Detalhes do tópico"));
        } else {
            // Se não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacao dados) {
        // Busca o tópico pelo ID fornecido
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            // Se o tópico existir, atualiza suas informações
            Topico topico = topicoOptional.get();
            topico.atualizarInformacoes(dados);
            repository.save(topico); // Salva as atualizações no repositório
            return new ResponseEntity<>("Tópico atualizado com sucesso!", HttpStatus.OK);
        } else {
            // Se não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        // Busca o tópico pelo ID fornecido
        Optional<Topico> topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            // Se o tópico existir, deleta-o do repositório
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            // Se não existir, retorna 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

}
