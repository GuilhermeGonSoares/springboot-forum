package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.dto.TopicoIdDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicosRepository topicosRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> getTopicos(String nomeCurso){
        if (nomeCurso == null)
            return TopicoDto.converteParaDto(topicosRepository.findAll());
        else
            return TopicoDto.converteParaDto(topicosRepository.findByCursoNome(nomeCurso));
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Curso curso = cursoRepository.findByNome(form.getNomeCurso());
        if(curso == null)
            throw new IllegalArgumentException("Curso não encontrado");

        Topico topico = form.toTopico(curso);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public TopicoIdDto getTopicoById(@PathVariable("id") Long id){
        var existeTopico = topicosRepository.findById(id);
        if(existeTopico == null){
            throw new RuntimeException("Tópico não encontrado!");
        }
        var topico = existeTopico.get();

        return new TopicoIdDto(topico);
    }

    @Top
}
