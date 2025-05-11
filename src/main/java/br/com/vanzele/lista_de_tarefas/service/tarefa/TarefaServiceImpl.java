package br.com.vanzele.lista_de_tarefas.service.tarefa;

import br.com.vanzele.lista_de_tarefas.model.dto.request.TarefaRequest;
import br.com.vanzele.lista_de_tarefas.model.dto.response.TarefaResponse;
import br.com.vanzele.lista_de_tarefas.model.entities.Tarefa;
import br.com.vanzele.lista_de_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaServiceImpl(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public TarefaResponse adicionarNovaTarefa(String descricao) {
        Tarefa tarefa = new Tarefa();
        if (descricao.trim().isBlank()) {
            throw new RuntimeException("É necessário inserir a descrição da tarefa");
        }
        tarefa.setDescricao(descricao);
        tarefa.setDataCriacao(LocalDateTime.now());
        this.tarefaRepository.save(tarefa);

        return TarefaResponse.fromEntity(tarefa);
    }

    @Override
    public Page<TarefaResponse> buscarTodasTarefas(int pagina, int quantidadeItens) {
       Page<Tarefa> tarefas = tarefaRepository
               .findAll(PageRequest.of(pagina, quantidadeItens, Sort.by("dataCriacao").ascending()));
        return tarefas.map(TarefaResponse::fromEntity);
    }

    @Override
    public void apagarTarefa(Long tarefaId) {
        tarefaRepository.deleteById(tarefaId);
    }

    @Override
    public void editarTarefa(Long tarefaId, String descricao) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (descricao.trim().isBlank()) {
            throw new RuntimeException("É necessário inserir a descrição da tarefa");
        }
        tarefa.setDescricao(descricao);
        tarefaRepository.save(tarefa);

    }

    @Override
    public TarefaResponse buscarTarefaPorId(Long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return TarefaResponse.fromEntity(tarefa);
    }
}
