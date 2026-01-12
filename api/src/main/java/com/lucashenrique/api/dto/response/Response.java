package com.lucashenrique.api.dto.response;

public class Response <T>{
    private String status;
    private String mensagem;
    private T data;

    public Response(String status, String mensagem, T data) {
        this.status = status;
        this.mensagem = mensagem;
        this.data = data;
    }

    public Response(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public Response() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

