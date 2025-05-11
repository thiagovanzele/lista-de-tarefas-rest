package br.com.vanzele.lista_de_tarefas.service.tarefa;

import br.com.vanzele.lista_de_tarefas.model.dto.request.TarefaRequest;
import br.com.vanzele.lista_de_tarefas.model.dto.response.TarefaResponse;
import br.com.vanzele.lista_de_tarefas.model.entities.Tarefa;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TarefaService {

    /**
     * Adiciona uma nova tarefa
     * @param descricao   descricao da tarefa a ser adicionada
     * */
    TarefaResponse adicionarNovaTarefa(String descricao);

    /**
     * Busca todas as tarefas
     * */
    Page<TarefaResponse> buscarTodasTarefas(int pagina, int quantidadeItens);

    /**
     * apaga uma tarefa específica pelo id
     * @param tarefaId    id da tarefa para ser apagada;
     */
    void apagarTarefa(Long tarefaId);

    /**
     * Edita a descrição de uma tarefa
     * @param tarefaId   id da tarefa a ser editada
     * @param descricao  nova descriçao da tarefa
     */
    void editarTarefa(Long tarefaId, String descricao);

    /**
     * busca uma tarefa pelo id
     * @param tarefaId id da tarefa
     * @return  tarefa buscada
     */
    TarefaResponse buscarTarefaPorId(Long tarefaId);
}
