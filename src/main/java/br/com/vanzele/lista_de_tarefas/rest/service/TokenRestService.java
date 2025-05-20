package br.com.vanzele.lista_de_tarefas.rest.service;

import br.com.vanzele.lista_de_tarefas.model.dto.request.LoginRequest;
import br.com.vanzele.lista_de_tarefas.model.dto.response.LoginResponse;
import br.com.vanzele.lista_de_tarefas.service.token.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRestService {

    private final TokenService tokenService;

    @Autowired
    public TokenRestService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza login na plataforma")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse realizarLogin(@RequestBody LoginRequest request) {
        return tokenService.login(request);
    }
}
