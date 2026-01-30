package com.example.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}