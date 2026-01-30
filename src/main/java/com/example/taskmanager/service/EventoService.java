package com.example.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.example.taskmanager.model.Evento;

public interface EventoService {

    // CREATE
    Evento criar(Evento evento);

    // READ
    List<Evento> listarTodos();
    Optional<Evento> buscarPorId(Long id);

    // UPDATE
    Optional<Evento> atualizar(Long id, Evento eventoAtualizado);

    // DELETE
    boolean deletar(Long id);
}
