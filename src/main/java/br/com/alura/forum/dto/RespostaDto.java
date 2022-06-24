package br.com.alura.forum.dto;

import br.com.alura.forum.model.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {
    private Long id;
    private String mensagem;
    private LocalDateTime data;
    private String nomeAutor;

    public RespostaDto(Resposta resposta){
        id = resposta.getId();
        mensagem = resposta.getMensagem();
        data = resposta.getDataCriacao();
        nomeAutor = resposta.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
