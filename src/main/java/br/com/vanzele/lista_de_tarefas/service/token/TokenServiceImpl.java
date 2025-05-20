package br.com.vanzele.lista_de_tarefas.service.token;

import br.com.vanzele.lista_de_tarefas.model.dto.request.LoginRequest;
import br.com.vanzele.lista_de_tarefas.model.dto.response.LoginResponse;
import br.com.vanzele.lista_de_tarefas.model.entities.Role;
import br.com.vanzele.lista_de_tarefas.model.entities.Usuario;
import br.com.vanzele.lista_de_tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public TokenServiceImpl(JwtEncoder jwtEncoder,
                            UsuarioRepository usuarioRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Optional<Usuario> usuario = usuarioRepository.findByUsername(request.username());

        if (usuario.isEmpty() || !usuario.get().isLoginCorrent(request, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        Instant now = Instant.now();
        long expiresIn = 300L;

        var scopes = usuario.get().getRoles().stream()
                .map(Role::getNome)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(usuario.get().getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }



}
