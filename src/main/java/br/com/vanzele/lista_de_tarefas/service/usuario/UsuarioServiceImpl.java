package br.com.vanzele.lista_de_tarefas.service.usuario;

import br.com.vanzele.lista_de_tarefas.model.dto.request.CriarUsuarioRequest;
import br.com.vanzele.lista_de_tarefas.model.entities.Role;
import br.com.vanzele.lista_de_tarefas.model.entities.Usuario;
import br.com.vanzele.lista_de_tarefas.model.enums.RoleType;
import br.com.vanzele.lista_de_tarefas.repository.RoleRepository;
import br.com.vanzele.lista_de_tarefas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void criarUsuario(CriarUsuarioRequest request) {
         if (this.usuarioRepository.existsByUsername(request.username())) {
             throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
         }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setRoles(Set.of(new Role(RoleType.BASIC)));
        usuario.setSenha(bCryptPasswordEncoder.encode(request.senha()));

        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }
}
