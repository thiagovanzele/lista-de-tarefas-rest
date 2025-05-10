package br.com.vanzele.lista_de_tarefas.model.dto.response;

import br.com.vanzele.lista_de_tarefas.model.entities.Tarefa;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaResponse {

    private Long tarefaId;
    private String descricao;

    public static TarefaResponse fromEntity(Tarefa tarefa) {
        return TarefaResponse.builder()
                .tarefaId(tarefa.getId())
                .descricao(tarefa.getDescricao())
                .build();
    }
}
