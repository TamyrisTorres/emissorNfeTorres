package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rua;
    private Long numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
