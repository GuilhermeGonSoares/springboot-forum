package br.com.alura.forum.dto;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TopicoIdDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String nomeAutor;
    private String nomeCurso;
    private List<RespostaDto> resposta = new ArrayList<>();

    public TopicoIdDto(Topico topico){
        id = topico.getId();
        titulo = topico.getTitulo();
        mensagem = topico.getMensagem();
        dataCriacao = topico.getDataCriacao();
        status = topico.getStatus();
        nomeAutor = topico.getAutor().getNome();
        nomeCurso = topico.getCurso().getNome();
        var respostasDoTopico = topico.getRespostas();
        respostasDoTopico.forEach(r -> {
            resposta.add(new RespostaDto(r));
        });
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public List<RespostaDto> getResposta() {
        return resposta;
    }
}
