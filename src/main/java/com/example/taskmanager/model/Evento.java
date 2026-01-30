package com.example.taskmanager.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do evento não pode ser vazio")
    private String nome;

    @NotNull(message = "A data do evento é obrigatória")
    @Future(message = "A data deve estar no futuro")
    private LocalDateTime data;

    private String local;
    private Integer capacidade;

    @JsonIgnore  // ← Não permite cliente enviar dataCriacao
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @JsonIgnore  // ← Não permite cliente enviar dataUltimaAlteracao
    @Column(nullable = false)
    private LocalDateTime dataUltimaAlteracao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDateTime getDataUltimaAlteracao() { return dataUltimaAlteracao; }
}