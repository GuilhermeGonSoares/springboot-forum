package br.com.alura.forum.dto;

import br.com.alura.forum.model.Topico;

import javax.validation.constraints.NotBlank;

public class TopicoAtt {
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, Topico topico){
        topico.setTitulo(titulo);
        topico.setMensagem(mensagem);
        return topico;
    }
}
