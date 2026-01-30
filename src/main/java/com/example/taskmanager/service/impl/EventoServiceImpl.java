package com.example.taskmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Evento;
import com.example.taskmanager.repository.EventoRepository;
import com.example.taskmanager.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository repository;

    @Override
    public Evento criar(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public List<Evento> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Evento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Evento> atualizar(Long id, Evento eventoAtualizado) {
        return repository.findById(id)
                .map(eventoExistente -> {
                    if (eventoAtualizado.getNome() != null && !eventoAtualizado.getNome().isBlank()) {
                        eventoExistente.setNome(eventoAtualizado.getNome());
                    }
                    if (eventoAtualizado.getLocal() != null) {
                        eventoExistente.setLocal(eventoAtualizado.getLocal());
                    }
                    if (eventoAtualizado.getCapacidade() != null) {
                        eventoExistente.setCapacidade(eventoAtualizado.getCapacidade());
                    }

                    return repository.save(eventoExistente);
                });
    }

    @Override
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
