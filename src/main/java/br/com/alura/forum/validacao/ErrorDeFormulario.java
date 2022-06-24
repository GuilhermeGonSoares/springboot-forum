package br.com.alura.forum.validacao;

public class ErrorDeFormulario {
    private String campo;
    private String mensagem;

    public ErrorDeFormulario(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
