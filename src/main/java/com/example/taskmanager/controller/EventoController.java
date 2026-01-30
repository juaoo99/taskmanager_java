package com.example.taskmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.model.Evento;
import com.example.taskmanager.service.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    // CRIAR (POST)
    @PostMapping
    public ResponseEntity<Map<String, Object>> criar(@Valid @RequestBody Evento evento) {
        Evento salvo = service.criar(evento);
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("dados", salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // LISTAR TODOS (GET)
    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        List<Evento> eventos = service.listarTodos();
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("dados", eventos);
        return ResponseEntity.ok(resposta);
    }

    // BUSCAR POR ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(evento -> {
                    Map<String, Object> resposta = new HashMap<>();
                    resposta.put("dados", evento);
                    return ResponseEntity.ok(resposta);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(criarRespostaErro("Evento não encontrado!")));
    }

    // ATUALIZAR (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarParcial(@PathVariable Long id, @RequestBody Evento dadosEnviados) {
        return service.atualizar(id, dadosEnviados)
                .map(eventoAtualizado -> {
                    Map<String, Object> resposta = new HashMap<>();
                    resposta.put("dados", eventoAtualizado);
                    return ResponseEntity.ok(resposta);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(criarRespostaErro("Evento não encontrado!")));
    }

    // DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        if (service.deletar(id)) {
            Map<String, Object> resposta = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(criarRespostaErro("Evento não encontrado!"));
    }

    private Map<String, Object> criarRespostaErro(String mensagem) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", mensagem);
        resposta.put("dados", null);
        return resposta;
    }
}