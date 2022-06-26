package br.com.alura.forum.controller;

import br.com.alura.forum.dto.TopicoAtt;
import br.com.alura.forum.dto.TopicoDto;
import br.com.alura.forum.dto.TopicoForm;
import br.com.alura.forum.dto.TopicoIdDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDto> getTopicos(@RequestParam(required = false) String nomeCurso,
                                     @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){

        Page<Topico> topicos;
        if (nomeCurso == null){
            topicos = topicosRepository.findAll(paginacao);
        }
        else{
            topicos = topicosRepository.findByCursoNome(nomeCurso, paginacao);
        }
        return TopicoDto.converteParaDto(topicos);
    }

    @PostMapping
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
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
    public ResponseEntity<TopicoIdDto> getTopicoById(@PathVariable("id") Long id){
        var existeTopico = topicosRepository.findById(id);
        System.out.println(existeTopico);
        if(!existeTopico.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var topico = existeTopico.get();

        return ResponseEntity.ok(new TopicoIdDto(topico));
    }
    @PutMapping("/{id}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoAtt topicoAtt){
        var exiteTopico = topicosRepository.findById(id);
        if(!exiteTopico.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var topico = exiteTopico.get();
        Topico topicoAtualizado = topicoAtt.atualizar(id, topico);

        topicosRepository.save(topicoAtualizado);

        return ResponseEntity.ok(new TopicoDto(topicoAtualizado));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> deletarTopico(@PathVariable Long id){
        var existeTopico = topicosRepository.findById(id);
        if(!existeTopico.isPresent()){
            return ResponseEntity.notFound().build();
        }
        var topico = existeTopico.get();
        topicosRepository.delete(topico);

        return ResponseEntity.ok().build();
    }
}
