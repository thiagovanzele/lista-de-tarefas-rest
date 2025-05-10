package br.com.vanzele.lista_de_tarefas.rest.service;

import br.com.vanzele.lista_de_tarefas.model.dto.request.TarefaRequest;
import br.com.vanzele.lista_de_tarefas.model.dto.response.TarefaResponse;
import br.com.vanzele.lista_de_tarefas.service.tarefa.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaRestService {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaRestService(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adiciona uma nova tarefa")
    public TarefaResponse adicionarNovaTarefa(@RequestBody TarefaRequest request) {
        return tarefaService.adicionarNovaTarefa(request.getDescricao());
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todas as tarefas")
    public List<TarefaResponse> buscarTodasTarefas() {
        return tarefaService.buscarTodasTarefas();
    }

    @GetMapping("/{tarefaId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todas as tarefas")
    public TarefaResponse buscarTarefa(@PathVariable Long tarefaId) {
        return tarefaService.buscarTarefaPorId(tarefaId);
    }

    @DeleteMapping("/{tarefaId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deleta uma tarefa específica")
    public void deletarTarefa(@PathVariable Long tarefaId) {
        tarefaService.apagarTarefa(tarefaId);
    }

    @PutMapping("/{tarefaId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza uma tarefa específica")
    public void atualizarTarefa(@PathVariable Long tarefaId, @RequestBody TarefaRequest request) {
        tarefaService.editarTarefa(tarefaId, request.getDescricao());
    }

}
