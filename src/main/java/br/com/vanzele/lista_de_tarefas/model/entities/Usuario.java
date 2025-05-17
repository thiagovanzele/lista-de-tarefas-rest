package br.com.vanzele.lista_de_tarefas.model.entities;

import br.com.vanzele.lista_de_tarefas.model.dto.request.LoginRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usuario_id")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_usuario_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public boolean isLoginCorrent(LoginRequest request, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(request.password(), this.senha);
    }
}
