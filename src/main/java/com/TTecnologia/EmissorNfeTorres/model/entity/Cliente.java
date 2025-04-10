package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Getter
    @Column(name = "nome-cliente", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Column(name = "cnpj", unique = true)
    private CNPJ CNPJ;

    @Setter
    @Getter
    @Column(name = "cpf", unique = true)
    private CPF CPF;

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Setter
    @Getter
    @Column(name = "telefone")
    private String telefone;

    public Cliente(String nome,
                   org.hibernate.validator.constraints.br.CNPJ CNPJ,
                   org.hibernate.validator.constraints.br.CPF CPF,
                   Endereco endereco, String telefone) {
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.CPF = CPF;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente() {}
}
