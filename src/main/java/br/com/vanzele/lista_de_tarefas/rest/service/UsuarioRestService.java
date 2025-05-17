package br.com.vanzele.lista_de_tarefas.rest.service;

import br.com.vanzele.lista_de_tarefas.model.dto.request.CriarUsuarioRequest;
import br.com.vanzele.lista_de_tarefas.model.entities.Usuario;
import br.com.vanzele.lista_de_tarefas.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestService {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRestService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário")
    public void criarUsuario(@RequestBody CriarUsuarioRequest request) {
        usuarioService.criarUsuario(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os usuários cadastrados")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioService.buscarTodosUsuarios();
    }
}
