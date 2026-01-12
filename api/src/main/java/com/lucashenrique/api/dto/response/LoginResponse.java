package com.lucashenrique.api.dto.response;

public class LoginResponse {
    private Long idFuncionario;
    private String login;
    private String cargo;

    public LoginResponse() {
    }

    public LoginResponse(Long idFuncionario, String login, String cargo) {
        this.idFuncionario = idFuncionario;
        this.login = login;
        this.cargo = cargo;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
