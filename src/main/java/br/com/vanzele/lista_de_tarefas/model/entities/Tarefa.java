package br.com.vanzele.lista_de_tarefas.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tarefa")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
}
