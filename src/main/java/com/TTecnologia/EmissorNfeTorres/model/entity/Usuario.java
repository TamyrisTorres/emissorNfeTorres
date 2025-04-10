package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @NotNull(message = "O nome não pode ser nulo.")
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }
}
