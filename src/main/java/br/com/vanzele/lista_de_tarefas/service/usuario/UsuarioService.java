package br.com.vanzele.lista_de_tarefas.service.usuario;

import br.com.vanzele.lista_de_tarefas.model.dto.request.CriarUsuarioRequest;
import br.com.vanzele.lista_de_tarefas.model.entities.Usuario;

import java.util.List;

public interface UsuarioService {

    /**
     * Cria um novo usuario
     */
    public void criarUsuario(CriarUsuarioRequest request);

    /**
     * Lista todos os usuários
     */
    public List<Usuario> buscarTodosUsuarios();

}
