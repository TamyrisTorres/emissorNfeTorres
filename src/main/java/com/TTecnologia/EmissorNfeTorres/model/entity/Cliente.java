package com.TTecnologia.EmissorNfeTorres.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
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
    @NotNull(message = "O nome não pode ser nulo.")
    private String nome;

    @Setter
    @Getter
    @UniqueElements
    private CNPJ CNPJ;

    @Setter
    @Getter
    @UniqueElements
    private CPF CPF;

    @Setter
    @Getter
    @NotNull(message = "O endereço não pode ser nulo.")
    private String endereco;

    @Setter
    @Getter
    private String telefone;

    public Cliente(String nome,
                   org.hibernate.validator.constraints.br.CNPJ CNPJ,
                   org.hibernate.validator.constraints.br.CPF CPF,
                   String endereco, String telefone) {
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.CPF = CPF;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente() {}
}
