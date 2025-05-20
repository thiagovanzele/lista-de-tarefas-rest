package br.com.vanzele.lista_de_tarefas.service.token;

import br.com.vanzele.lista_de_tarefas.model.dto.request.LoginRequest;
import br.com.vanzele.lista_de_tarefas.model.dto.response.LoginResponse;

public interface TokenService {

    /**
     * Realiza login na aplicação
     * @param request   username e senha para realizar o login
     */
    public LoginResponse login(LoginRequest request);
}
