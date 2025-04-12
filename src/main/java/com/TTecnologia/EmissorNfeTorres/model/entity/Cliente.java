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
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "nome-cliente", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Column(name = "cnpj-cpf", unique = true)
    private String cnpjCpf;


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
                   String cnpjCpf,
                   Endereco endereco, String telefone) {
        this.nome = nome;
        this.cnpjCpf = cnpjCpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente() {}
}
