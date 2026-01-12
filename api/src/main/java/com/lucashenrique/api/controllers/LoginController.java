package com.lucashenrique.api.controllers;

import com.lucashenrique.api.dto.loginDTO;
import com.lucashenrique.api.dto.response.LoginResponse;
import com.lucashenrique.api.dto.response.Response;
import com.lucashenrique.api.services.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@RequestBody loginDTO loginDTO, HttpServletResponse response) {

        LoginResponse usuario = loginService.login(loginDTO);

        if (usuario == null) {
            Response<LoginResponse> loginResponse = new Response<>(
                    "Erro",
                    "usuário ou senha incorretos",
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }

        String token = UUID.randomUUID().toString();

        Cookie cookie = new Cookie("AUTH_TOKEN" , token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        Response<LoginResponse> loginResponse = new Response<>(
                "Sucesso",
                "Login realizado com sucesso!",
                usuario
        );

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @PostMapping("/logout")
    ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("AUTH_TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Sessão encerrada");
    }

}
