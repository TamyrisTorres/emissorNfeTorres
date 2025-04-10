package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@AllArgsConstructor
@Table(name = "produto")
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @Column(name = "codigo-produto", unique = true, nullable = false)
    private Long codigo;

    @Setter
    @Getter
    @Column(name = "codigo-produto", nullable = false)
    private String nome;

    @Setter
    @Getter
    private String descricao;

    @Setter
    @Getter
    private Long quantidade;

    @Setter
    @Getter
    private Double preco;

    public Produto(Long codigo, String nome, String descricao, Long quantidade, Double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto() {}
}
