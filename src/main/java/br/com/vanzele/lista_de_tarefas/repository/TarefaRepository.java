package br.com.vanzele.lista_de_tarefas.repository;

import br.com.vanzele.lista_de_tarefas.model.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
